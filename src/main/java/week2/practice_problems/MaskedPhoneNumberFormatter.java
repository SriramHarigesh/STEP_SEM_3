package week2.practice_problems;
import java.util.Scanner;
public class MaskedPhoneNumberFormatter{
    static String maskPhoneNumber(String phone){
        if(phone.length()!=10)return "Invalid phone number";
        for(int i=0;i<phone.length();i++)if(!Character.isDigit(phone.charAt(i)))return "Invalid phone number";
        StringBuilder masked=new StringBuilder("XXXXXX");
        masked.append(phone.substring(6));
        masked.insert(6,"-");
        return masked.toString();
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter phone number: ");
        System.out.println(maskPhoneNumber(sc.nextLine()));
    }
}
