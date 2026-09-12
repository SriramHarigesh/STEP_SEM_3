package week1.practice_problems;
public class ReverseCustomerName{
    static String reverseCustomerName(String customerName){
        char[] letters=customerName.toCharArray();
        for(int i=0,j=letters.length-1;i<j;i++,j--){
            char temp=letters[i];
            letters[i]=letters[j];
            letters[j]=temp;
        }
        return new String(letters);
    }
    public static void main(String[] args){
        String customerName="Sunil";
        System.out.println("Original Name: "+customerName);
        System.out.println("Reversed Name: "+reverseCustomerName(customerName));
    }
}
