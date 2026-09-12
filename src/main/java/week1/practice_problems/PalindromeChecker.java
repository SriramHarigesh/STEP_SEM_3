package week1.practice_problems;
public class PalindromeChecker{
    static boolean isPalindromeIterative(String text){
        int left=0,right=text.length()-1;
        while(left<right){
            if(text.charAt(left)!=text.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
    static boolean isPalindromeRecursive(String text){
        if(text.length()<=1) return true;
        if(text.charAt(0)!=text.charAt(text.length()-1)) return false;
        return isPalindromeRecursive(text.substring(1,text.length()-1));
    }
    static boolean isPalindromeArrayReversal(String text){
        char[] letters=text.toCharArray();
        char[] reversed=new char[letters.length];
        for(int i=0;i<letters.length;i++) reversed[i]=letters[letters.length-1-i];
        return text.equals(new String(reversed));
    }
    static String result(boolean value){
        return value?"Palindrome":"Not Palindrome";
    }
    public static void main(String[] args){
        String text="madam";
        System.out.println("Iterative: "+result(isPalindromeIterative(text))+" | Recursive: "+result(isPalindromeRecursive(text))+" | Array Reversal: "+result(isPalindromeArrayReversal(text)));
    }
}
