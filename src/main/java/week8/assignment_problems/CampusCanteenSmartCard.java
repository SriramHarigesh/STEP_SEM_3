import java.util.*;
public class CampusCanteenSmartCard{
    public static void main(String[] args){
        SmartCard card=new SmartCard("C-2045",new HostellerPlan());
        System.out.println(card.topUp(500));
        System.out.println(card.purchase("Veg Thali",120));
        System.out.println(card.purchase("Cold Coffee",60));
        System.out.println(card.purchase("Snacks",400));
        System.out.println(card.refund("Veg Thali"));
        System.out.println(card.refund("Veg Thali"));
        System.out.println(card.miniStatement());
    }
}
interface PricingPlan{
    double price(double basePrice);
    String getName();
}
class DayScholarPlan implements PricingPlan{
    public double price(double basePrice){return basePrice;}
    public String getName(){return "Day Scholar";}
}
class HostellerPlan implements PricingPlan{
    public double price(double basePrice){return basePrice*0.90;}
    public String getName(){return "Hosteller";}
}
class StaffPlan implements PricingPlan{
    public double price(double basePrice){return basePrice*0.80;}
    public String getName(){return "Staff";}
}
enum CardState{ACTIVE,BLOCKED}
class Transaction{
    private final double amount;
    private final String description;
    public Transaction(double amount,String description){
        this.amount=amount;
        this.description=description;
    }
    public double getAmount(){return amount;}
    public String getDescription(){return description;}
}
class SmartCard{
    private final String cardNumber;
    private final PricingPlan plan;
    private final List<Transaction> transactions=new ArrayList<>();
    private final Set<String> refundedPurchases=new HashSet<>();
    private CardState state=CardState.ACTIVE;
    private double balance;
    public SmartCard(String cardNumber,PricingPlan plan){
        this.cardNumber=cardNumber;
        this.plan=plan;
    }
    public String topUp(double amount){
        if(state==CardState.BLOCKED)return "Top-up rejected: Card is blocked.";
        if(amount<100)return "Top-up rejected: Minimum top-up is ₹100.00.";
        if(balance+amount>5000)return "Top-up rejected: Maximum balance is ₹5000.00.";
        record(amount,"Top-up");
        return cardNumber+" topped up with ₹"+String.format("%.2f",amount)+". Balance: ₹"+String.format("%.2f",balance)+".";
    }
    public String purchase(String item,double basePrice){
        if(state==CardState.BLOCKED)return "Purchase failed: Card is blocked.";
        double charged=plan.price(basePrice);
        if(charged>balance)return "Purchase failed: Insufficient balance (required ₹"+String.format("%.2f",charged)+", available ₹"+String.format("%.2f",balance)+").";
        record(-charged,item);
        return item+" purchased for ₹"+String.format("%.2f",charged)+". Balance: ₹"+String.format("%.2f",balance)+".";
    }
    public String refund(String item){
        for(int i=transactions.size()-1;i>=0;i--){
            Transaction transaction=transactions.get(i);
            if(transaction.getDescription().equals(item)&&transaction.getAmount()<0){
                if(refundedPurchases.contains(item))return "Refund rejected: "+item+" has already been refunded.";
                double amount=-transaction.getAmount();
                record(amount,"Refund: "+item);
                refundedPurchases.add(item);
                return "Refund of ₹"+String.format("%.2f",amount)+" for "+item+" processed. Balance: ₹"+String.format("%.2f",balance)+".";
            }
        }
        return "Refund rejected: Purchase not found.";
    }
    public void block(){state=CardState.BLOCKED;}
    public void unblock(){state=CardState.ACTIVE;}
    public String miniStatement(){
        StringBuilder result=new StringBuilder("Mini-statement for "+cardNumber+": ");
        for(int i=0;i<transactions.size();i++){
            double amount=transactions.get(i).getAmount();
            if(i>0)result.append(", ");
            result.append(amount>=0?"+":"").append(String.format("%.2f",amount));
        }
        result.append(" = ₹").append(String.format("%.2f",balance)).append(".");
        return result.toString();
    }
    private void record(double amount,String description){
        transactions.add(new Transaction(amount,description));
        balance+=amount;
    }
}