package week4.practice_problems;
public class NightlyFleetReconciliation{
public static void main(String[] args){
BusTicketAccount[] accounts={new SleeperAccount("BK001",2000),null,new BusTicketAccount("BK002",1200)};
double[] amounts={1200,900,700};
int[] late={10,5,0};
BusTicketAccount.processBatch(accounts,amounts,late);
}
}
class BusTicketAccount{
private static String systemName;
protected String bookingId;
protected double ticketFare;
static{systemName="Fleet Reconciliation";}
public BusTicketAccount(String bookingId,double ticketFare){
if(bookingId==null||bookingId.trim().isEmpty()||ticketFare<0)throw new IllegalArgumentException("Invalid account");
this.bookingId=bookingId;
this.ticketFare=ticketFare;
}
public BusTicketAccount(String bookingId){this(bookingId,0);}
final double calculatePenalty(int minutesLate){
if(minutesLate<0)throw new IllegalArgumentException("Invalid lateness");
int first=Math.min(minutesLate,5);
int second=Math.min(Math.max(minutesLate-5,0),10);
int third=Math.max(minutesLate-15,0);
return ticketFare*(first*0.005+second*0.01+third*0.02);
}
double settle(double penalty){return penalty;}
void processAccount(BusTicketAccount account,double amount,int minutesLate){
if(account==null)throw new IllegalArgumentException("Null account");
account.ticketFare=amount;
System.out.println(account.bookingId+" penalty: "+account.settle(account.calculatePenalty(minutesLate)));
}
static void processBatch(BusTicketAccount[] accounts,double[] amounts,int[] minutesLateArray){
if(accounts==null||amounts==null||minutesLateArray==null||accounts.length!=amounts.length||accounts.length!=minutesLateArray.length)throw new IllegalArgumentException("Parallel arrays must have equal lengths");
int processed=0,nullSkipped=0,sleeper=0,regular=0;
double total=0;
for(int i=0;i<accounts.length;i++){
BusTicketAccount account=accounts[i];
if(account==null){nullSkipped++;continue;}
account.ticketFare=amounts[i];
double penalty=account.settle(account.calculatePenalty(minutesLateArray[i]));
if(account instanceof SleeperAccount)sleeper++;else regular++;
processed++;
total+=penalty;
}
System.out.println(systemName+": "+processed+" processed | "+nullSkipped+" null skipped | "+sleeper+" sleeper | "+regular+" regular | grand total penalties = "+total);
}
}
class SleeperAccount extends BusTicketAccount{
public SleeperAccount(String bookingId,double ticketFare){super(bookingId,ticketFare);}
double settle(double penalty){return penalty*0.85;}
}
