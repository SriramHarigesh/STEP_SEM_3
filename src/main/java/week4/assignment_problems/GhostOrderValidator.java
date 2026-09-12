package week4.assignment_problems;
public class GhostOrderValidator{
public static void main(String[] args){
String[][] rawOrders={{"Ravi","Paneer Butter Masala"},{"","Chole Bhature"},{"Meera"," "},{"Divya","Veg Biryani"}};
FoodOrder.processBatch(rawOrders);
}
}
class FoodOrder{
private String studentName;
private String dishName;
private boolean delivered;
public FoodOrder(String studentName,String dishName){
if(!valid(studentName)||!valid(dishName))throw new IllegalArgumentException("Invalid order");
this.studentName=studentName.trim();
this.dishName=dishName.trim();
}
private static boolean valid(String value){
return value!=null&&!value.trim().isEmpty();
}
void markDelivered(){
if(delivered)System.out.println("Order already delivered");
else{delivered=true;System.out.println("Order delivered to "+studentName);}
}
static void processBatch(String[][] rawOrders){
int valid=0,rejected=0;
if(rawOrders!=null)for(String[] order:rawOrders){
try{
if(order==null||order.length<2)throw new IllegalArgumentException();
new FoodOrder(order[0],order[1]);
valid++;
}catch(IllegalArgumentException e){rejected++;}
}
System.out.println("Valid: "+valid+" | Rejected: "+rejected);
}
}
