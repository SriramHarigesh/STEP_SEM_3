import java.util.*;
public class FoodOrderFlexiblePaymentSystem{
    public static void main(String[] args){
        Customer customer=new Customer("Customer1");
        Restaurant restaurant=new Restaurant("Food Corner");
        restaurant.addFood(new FoodItem("Pizza",100));
        restaurant.addFood(new FoodItem("Soda",30));
        restaurant.addFood(new FoodItem("Burger",120));
        Order order1=new Order(123,customer,restaurant);
        System.out.println(order1.addItem("Pizza",2));
        System.out.println(order1.addItem("Soda",1));
        Order empty=new Order(122,customer,restaurant);
        System.out.println(empty.place(new CreditCardPayment()));
        System.out.println(order1.place(new CreditCardPayment()));
        Order order2=new Order(124,customer,restaurant);
        System.out.println(order2.addItem("Burger",1));
        System.out.println(order2.place(new DigitalWalletPayment()));
    }
}
class Customer{
    private final String name;
    public Customer(String name){this.name=name;}
    public String getName(){return name;}
}
class FoodItem{
    private final String name;
    private final double price;
    public FoodItem(String name,double price){this.name=name;this.price=price;}
    public String getName(){return name;}
    public double getPrice(){return price;}
}
class Restaurant{
    private final String name;
    private final Map<String,FoodItem> menu=new HashMap<>();
    public Restaurant(String name){this.name=name;}
    public void addFood(FoodItem item){menu.put(item.getName(),item);}
    public FoodItem getFood(String name){return menu.get(name);}
}
class LineItem{
    private final FoodItem item;
    private final int quantity;
    public LineItem(FoodItem item,int quantity){this.item=item;this.quantity=quantity;}
    public double total(){return item.getPrice()*quantity;}
    public String getMessage(){return item.getName()+" (Qty "+quantity+")";}
}
interface IPaymentMethod{
    boolean pay(double amount);
    String getName();
}
class CreditCardPayment implements IPaymentMethod{
    public boolean pay(double amount){return true;}
    public String getName(){return "Credit Card";}
}
class DigitalWalletPayment implements IPaymentMethod{
    public boolean pay(double amount){return false;}
    public String getName(){return "Digital Wallet";}
}
class CashOnDeliveryPayment implements IPaymentMethod{
    public boolean pay(double amount){return true;}
    public String getName(){return "Cash on Delivery";}
}
enum OrderStatus{CREATED,PAID,PENDING_PAYMENT}
class Order{
    private final int id;
    private final Customer customer;
    private final Restaurant restaurant;
    private final List<LineItem> items=new ArrayList<>();
    private OrderStatus status=OrderStatus.CREATED;
    public Order(int id,Customer customer,Restaurant restaurant){
        this.id=id;
        this.customer=customer;
        this.restaurant=restaurant;
        System.out.println("Order created.");
    }
    public String addItem(String name,int quantity){
        FoodItem item=restaurant.getFood(name);
        if(item==null||quantity<=0)return "Cannot add item.";
        LineItem lineItem=new LineItem(item,quantity);
        items.add(lineItem);
        return "Added "+lineItem.getMessage()+".";
    }
    public String place(IPaymentMethod payment){
        if(items.isEmpty())return "Cannot place order: Order must contain at least one item.";
        System.out.println("Order placed successfully.");
        double total=0;
        for(LineItem item:items)total+=item.total();
        if(payment.pay(total)){
            status=OrderStatus.PAID;
            System.out.println("Payment via "+payment.getName()+" successful.");
            return "Order status: Paid. Notification: Order #"+id+" placed and paid.";
        }
        status=OrderStatus.PENDING_PAYMENT;
        System.out.println("Payment via "+payment.getName()+" failed.");
        return "Order status: Pending Payment. Notification: Order #"+id+" placed, awaiting payment.";
    }
}