import java.util.Stack;
import java.util.Arrays;
public class CanteenPriceStreak{
static int[] priceStreak(int[] price){
int[] result=new int[price.length];
Stack<Integer> stack=new Stack<>();
for(int i=0;i<price.length;i++){
while(!stack.empty()&&price[stack.peek()]<=price[i]) stack.pop();
if(stack.empty()) result[i]=i+1;
else result[i]=i-stack.peek();
stack.push(i);
}
return result;
}
public static void main(String[] args){
int[] price={100,80,60,70,60,75,85};
System.out.println(Arrays.toString(priceStreak(price)));
int[] second={10,20,30};
System.out.println(Arrays.toString(priceStreak(second)));
}
}
