package week2.practice_problems;
import java.util.Scanner;
public class BankTransactionReferenceGeneratorValidator{
    static String normalizeReference(String raw){
        String reference=raw.trim();
        if(reference.length()<3)return reference;
        return reference.substring(0,3).toUpperCase()+reference.substring(3);
    }
    static String validateAndFormat(String reference){
        if(reference.length()!=14)return "Invalid: reference must be exactly 14 characters";
        for(int i=0;i<3;i++)if(!Character.isLetter(reference.charAt(i)))return "Invalid: bank code must be 3 letters";
        for(int i=3;i<14;i++)if(!Character.isDigit(reference.charAt(i)))return "Invalid: date and sequence must contain only digits";
        StringBuilder result=new StringBuilder();
        result.append("[").append(reference.substring(0,3)).append("] DATE: ").append(reference.substring(3,5)).append("/").append(reference.substring(5,7)).append("/").append(reference.substring(7,9)).append(" | SEQ: ").append(reference.substring(9));
        return result.toString();
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter transaction reference: ");
        System.out.println(validateAndFormat(normalizeReference(sc.nextLine())));
    }
}
