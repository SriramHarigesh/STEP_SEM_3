import java.util.Stack;
public class QuizScoringFormula{
static int precedence(char operator){
if(operator=='+'||operator=='-') return 1;
return 2;
}
static void calculate(Stack<Integer> numbers,Stack<Character> operators){
int b=numbers.pop();
int a=numbers.pop();
char operator=operators.pop();
if(operator=='+') numbers.push(a+b);
else if(operator=='-') numbers.push(a-b);
else if(operator=='*') numbers.push(a*b);
else numbers.push(a/b);
}
static int evaluate(String expr){
Stack<Integer> numbers=new Stack<>();
Stack<Character> operators=new Stack<>();
int i=0;
while(i<expr.length()){
char ch=expr.charAt(i);
if(ch==' '){
i++;
}else if(Character.isDigit(ch)){
int number=0;
while(i<expr.length()&&Character.isDigit(expr.charAt(i))){
number=number*10+(expr.charAt(i)-'0');
i++;
}
numbers.push(number);
}else if(ch=='('){
operators.push(ch);
i++;
}else if(ch==')'){
while(!operators.empty()&&operators.peek()!='(') calculate(numbers,operators);
operators.pop();
i++;
}else{
while(!operators.empty()&&operators.peek()!='('&&precedence(operators.peek())>=precedence(ch)) calculate(numbers,operators);
operators.push(ch);
i++;
}
}
while(!operators.empty()) calculate(numbers,operators);
return numbers.pop();
}
public static void main(String[] args){
System.out.println(evaluate("2 * (3 + 4) - 10 / 5"));
System.out.println(evaluate("(8 - 2) * (1 + 2) / 4"));
}
}
