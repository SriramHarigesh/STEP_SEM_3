package week2.practice_problems;
import java.util.Scanner;
public class FileExtensionValidator{
    static String validateFileExtension(String filename){
        int position=filename.lastIndexOf('.');
        if(position==-1||position==filename.length()-1)return "Rejected - invalid file type";
        String extension=filename.substring(position+1);
        if(extension.equalsIgnoreCase("pdf")||extension.equalsIgnoreCase("docx")||extension.equalsIgnoreCase("zip"))return "Accepted";
        return "Rejected - invalid file type";
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter filename: ");
        System.out.println(validateFileExtension(sc.nextLine()));
    }
}
