package week1.assignment_problems;
public class MovieReviewWordLengthProfiler{
    static void classifyWordLengths(String review){
        String cleaned=review.replaceAll("[^A-Za-z ]"," ").trim();
        if(cleaned.isEmpty()){
            System.out.println("Short: 0 | Medium: 0 | Long: 0");
            return;
        }
        String[] words=cleaned.split("\\s+");
        int shortWords=0,mediumWords=0,longWords=0;
        for(String word:words){
            if(word.length()<=4) shortWords++;
            else if(word.length()<=8) mediumWords++;
            else longWords++;
        }
        System.out.println("Short: "+shortWords+" | Medium: "+mediumWords+" | Long: "+longWords);
    }
    public static void main(String[] args){
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}
