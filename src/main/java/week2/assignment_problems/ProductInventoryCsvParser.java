package week2.assignment_problems;
import java.util.Scanner;
public class ProductInventoryCsvParser{
    static void parseInventoryRecord(String csvLine){
        String[] fields=csvLine.split(",");
        if(fields.length!=3){
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Product: "+fields[0].trim()+" | SKU: "+fields[1].trim()+" | Qty: "+fields[2].trim());
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Product,SKU,Quantity: ");
        parseInventoryRecord(sc.nextLine());
    }
}
