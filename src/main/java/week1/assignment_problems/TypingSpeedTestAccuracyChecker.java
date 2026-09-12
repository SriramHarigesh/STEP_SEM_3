package week1.assignment_problems;
public class TypingSpeedTestAccuracyChecker{
    static void checkTypingAccuracy(String original,String typed){
        if(original.length()!=typed.length()){
            System.out.println("Both texts must have equal length");
            return;
        }
        int matched=0;
        int firstMismatch=-1;
        for(int i=0;i<original.length();i++){
            if(original.charAt(i)==typed.charAt(i)) matched++;
            else if(firstMismatch==-1) firstMismatch=i;
        }
        double accuracy=original.length()==0?100:(double)matched/original.length()*100;
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%",matched,original.length(),accuracy);
        if(firstMismatch==-1) System.out.println(" | No Mismatches");
        else System.out.println(" | First Mismatch at position "+(firstMismatch+1)+" ('"+original.charAt(firstMismatch)+"' vs '"+typed.charAt(firstMismatch)+"')");
    }
    public static void main(String[] args){
        checkTypingAccuracy("hello world","hello worlt");
    }
}
