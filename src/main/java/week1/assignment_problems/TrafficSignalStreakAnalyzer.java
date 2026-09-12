package week1.assignment_problems;
public class TrafficSignalStreakAnalyzer{
    static void findLongestStreak(String signalLog){
        if(signalLog.isEmpty()){
            System.out.println("Signal log is empty");
            return;
        }
        int longest=1;
        int current=1;
        char longestColor=signalLog.charAt(0);
        for(int i=1;i<signalLog.length();i++){
            if(signalLog.charAt(i)==signalLog.charAt(i-1)) current++;
            else current=1;
            if(current>longest){
                longest=current;
                longestColor=signalLog.charAt(i);
            }
        }
        System.out.println("Longest Streak: '"+longestColor+"' repeated "+longest+" times");
    }
    public static void main(String[] args){
        findLongestStreak("RRGGGYRR");
    }
}
