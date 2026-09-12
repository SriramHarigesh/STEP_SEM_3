package week2.practice_problems;
import java.util.Scanner;
public class CsvStudentRecordParser{
    static void parseStudentRecord(String csvLine){
        String[] fields=csvLine.split(",");
        if(fields.length!=3){
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Name: "+fields[0].trim()+" | Roll No: "+fields[1].trim()+" | Dept: "+fields[2].trim());
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Name,RollNumber,Department: ");
        parseStudentRecord(sc.nextLine());
    }
}
