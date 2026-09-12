package week4.assignment_problems;
public class NightlyMultiKitchenReconciliation{
public static void main(String[] args){
DeliveryAccount[] accounts={new PremiumDeliveryAccount("STU001",500),null,new DeliveryAccount("STU002",300)};
double[] amounts={500,400,300};
int[] delays={10,5,0};
DeliveryAccount.processBatch(accounts,amounts,delays);
}
}
class DeliveryAccount{
private static String systemName;
protected String studentId;
protected double orderValue;
static{systemName="Campus Delivery Reconciliation";}
public DeliveryAccount(String studentId,double orderValue){
if(studentId==null||studentId.trim().isEmpty()||orderValue<0)throw new IllegalArgumentException("Invalid account");
this.studentId=studentId;
this.orderValue=orderValue;
}
public DeliveryAccount(String studentId){
this(studentId,0);
}
final double calculateSurgeFee(int delayMinutes){
if(delayMinutes<0)throw new IllegalArgumentException("Invalid delay");
int first=Math.min(delayMinutes,5);
int second=Math.min(Math.max(delayMinutes-5,0),10);
int third=Math.max(delayMinutes-15,0);
return orderValue*(first*0.005+second*0.01+third*0.02);
}
double settle(double fee){
return fee;
}
void processAccount(DeliveryAccount account,double amount,int delayMinutes){
if(account==null)throw new IllegalArgumentException("Null account");
account.orderValue=amount;
double fee=account.settle(account.calculateSurgeFee(delayMinutes));
System.out.println(account.studentId+" surge fee: "+fee);
}
static void processBatch(DeliveryAccount[] accounts,double[] amounts,int[] delayMinutesArray){
if(accounts==null||amounts==null||delayMinutesArray==null||accounts.length!=amounts.length||accounts.length!=delayMinutesArray.length)throw new IllegalArgumentException("Parallel arrays must have equal lengths");
int processed=0,nullSkipped=0,premium=0,regular=0;
double total=0;
for(int i=0;i<accounts.length;i++){
DeliveryAccount account=accounts[i];
if(account==null){nullSkipped++;continue;}
account.orderValue=amounts[i];
double fee=account.settle(account.calculateSurgeFee(delayMinutesArray[i]));
if(account instanceof PremiumDeliveryAccount)premium++;else regular++;
processed++;
total+=fee;
}
System.out.println(systemName+": "+processed+" processed | "+nullSkipped+" null skipped | "+premium+" premium | "+regular+" regular | grand total surge fees = "+total);
}
}
class PremiumDeliveryAccount extends DeliveryAccount{
public PremiumDeliveryAccount(String studentId,double orderValue){super(studentId,orderValue);}
double settle(double fee){return fee*0.8;}
}
