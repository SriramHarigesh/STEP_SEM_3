package week4.assignment_problems;
public class ExamWeekSurgeFeeCalculator{
public static void main(String[] args){
SurgeFeeCalculator calculator=new SurgeFeeCalculator(1);
System.out.println(calculator.calculateSurgeFee(500,0));
System.out.println(calculator.calculateSurgeFee(500,1));
System.out.println(calculator.calculateSurgeFee(500,16));
}
}
final class SurgeFeeCalculator{
private final double minimumSurgePercent;
public SurgeFeeCalculator(double minimumSurgePercent){
if(minimumSurgePercent<0)throw new IllegalArgumentException("Invalid minimum surge");
this.minimumSurgePercent=minimumSurgePercent;
}
final double calculateSurgeFee(double orderValue,int delayMinutes){
if(orderValue<0||delayMinutes<0)throw new IllegalArgumentException("Invalid input");
if(delayMinutes==0)return 0;
int first=Math.min(delayMinutes,5);
int second=Math.min(Math.max(delayMinutes-5,0),10);
int third=Math.max(delayMinutes-15,0);
double fee=orderValue*(first*0.005+second*0.01+third*0.02);
return Math.max(fee,orderValue*minimumSurgePercent/100);
}
}
