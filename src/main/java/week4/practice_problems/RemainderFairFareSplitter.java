package week4.practice_problems;
public class RemainderFairFareSplitter{
public static void main(String[] args){
double[] shares=new FareSplitter("TRIP001",100000,3).fareBreakdown();
for(double share:shares)System.out.println(share);
}
}
class FareSplitter{
private String tripId;
private double totalFare;
private int passengerCount;
public FareSplitter(String tripId,double totalFare,int passengerCount){
if(tripId==null||tripId.trim().isEmpty()||totalFare<0||passengerCount<=0)throw new IllegalArgumentException("Invalid split");
this.tripId=tripId;
this.totalFare=totalFare;
this.passengerCount=passengerCount;
}
public FareSplitter(String tripId,double totalFare){this(tripId,totalFare,2);}
public FareSplitter(String tripId){this(tripId,0,2);}
double[] fareBreakdown(){
long paisa=Math.round(totalFare*100);
long base=paisa/passengerCount;
long remainder=paisa%passengerCount;
double[] shares=new double[passengerCount];
for(int i=0;i<passengerCount;i++)shares[i]=(base+(i>=passengerCount-remainder?1:0))/100.0;
return shares;
}
boolean isConfirmationOverdue(int confirmed,int expected){return expected>0&&confirmed<expected;}
}
