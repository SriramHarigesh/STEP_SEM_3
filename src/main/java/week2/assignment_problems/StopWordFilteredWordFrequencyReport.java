package week2.assignment_problems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
public class StopWordFilteredWordFrequencyReport{
    static void printFilteredWordFrequency(String feedback){
        String[] stopWords={"the","was","and","a","is","of","in"};
        String cleaned=feedback.toLowerCase().replace(".","").replace(",","").replace("!","").replace("?","");
        String[] words=cleaned.split("\\s+");
        HashMap<String,Integer> frequency=new HashMap<>();
        for(String word:words){
            boolean isStopWord=false;
            for(String stopWord:stopWords)if(word.equals(stopWord))isStopWord=true;
            if(!isStopWord&&!word.isEmpty())frequency.put(word,frequency.getOrDefault(word,0)+1);
        }
        ArrayList<String> keys=new ArrayList<>(frequency.keySet());
        Collections.sort(keys,new Comparator<String>(){
            public int compare(String first,String second){
                return frequency.get(second)-frequency.get(first);
            }
        });
        for(String key:keys)System.out.println(key+": "+frequency.get(key));
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter feedback: ");
        printFilteredWordFrequency(sc.nextLine());
    }
}
