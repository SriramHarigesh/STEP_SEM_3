package week4.practice_problems;
public class TieredBoardingPenaltyCalculator{
public static void main(String[] args){
BoardingPenaltyCalculator calculator=new BoardingPenaltyCalculator(1);
System.out.println(calculator.calculatePenalty(1000,0));
System.out.println(calculator.calculatePenalty(1000,1));
System.out.println(calculator.calculatePenalty(1000,16));
}
}
final class BoardingPenaltyCalculator{
private final double minimumPenaltyPercent;
public BoardingPenaltyCalculator(double minimumPenaltyPercent){
if(minimumPenaltyPercent<0)throw new IllegalArgumentException("Invalid minimum penalty");
this.minimumPenaltyPercent=minimumPenaltyPercent;
}
final double calculatePenalty(double ticketFare,int minutesLate){
if(ticketFare<0||minutesLate<0)throw new IllegalArgumentException("Invalid input");
if(minutesLate==0)return 0;
int first=Math.min(minutesLate,5);
int second=Math.min(Math.max(minutesLate-5,0),10);
int third=Math.max(minutesLate-15,0);
double penalty=ticketFare*(first*0.005+second*0.01+third*0.02);
return Math.max(penalty,ticketFare*minimumPenaltyPercent/100);
}
}
