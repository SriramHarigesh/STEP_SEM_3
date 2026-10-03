public class CheckoutPaymentHandler{
    public static void main(String[] args){
        CreditCardPayment cc=new CreditCardPayment("4471");
        CashPayment cash=new CashPayment();
        PaymentMethod ref=cc;
        System.out.println(cc.processPayment(250.0));
        System.out.println(cash.processPayment(40.0));
        System.out.println(cc.processPayment(250.0,"Birthday gift"));
        printConfirmation(ref,250.0);
    }
    static void printConfirmation(PaymentMethod payment,double amount){
        System.out.println(payment.processPayment(amount));
    }
}
abstract class PaymentMethod{
    private static int counter=1000;
    private final String transactionId;
    public PaymentMethod(){
        transactionId="TXN-"+(++counter);
    }
    public abstract String processPayment(double amount);
    String processPayment(double amount,String note){
        return processPayment(amount)+" ("+note+")";
    }
    public String getTransactionId(){
        return transactionId;
    }
}
class CreditCardPayment extends PaymentMethod{
    private final String cardNumberLastFour;
    public CreditCardPayment(String cardNumberLastFour){
        this.cardNumberLastFour=cardNumberLastFour;
    }
    public String processPayment(double amount){
        return "Charged $"+amount+" to card ending "+cardNumberLastFour+" - Txn "+getTransactionId();
    }
}
class CashPayment extends PaymentMethod{
    public CashPayment(){}
    public String processPayment(double amount){
        return "Received $"+amount+" in cash - Txn "+getTransactionId();
    }
}