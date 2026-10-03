import java.util.*;
public class SwiftShipParcelTracker{
    public static void main(String[] args){
        Customer customer=new Customer("Customer1");
        Parcel parcel=new Parcel("P101",2,new ExpressShipping());
        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());
        ParcelService service=new ParcelService();
        System.out.println(service.book(customer,parcel));
        System.out.println(service.updateStatus(parcel,ParcelStatus.PICKED_UP));
        System.out.println(service.cancel(parcel));
        System.out.println(service.updateStatus(parcel,ParcelStatus.IN_TRANSIT));
        System.out.println(service.updateStatus(parcel,ParcelStatus.DELIVERED));
    }
}
class Customer{
    private final String name;
    public Customer(String name){this.name=name;}
    public String getName(){return name;}
}
enum ParcelStatus{BOOKED,PICKED_UP,IN_TRANSIT,OUT_FOR_DELIVERY,DELIVERED,CANCELLED}
interface ShippingType{
    double calculateCharge(double weight);
    String getName();
}
class StandardShipping implements ShippingType{
    public double calculateCharge(double weight){return 40+10*weight;}
    public String getName(){return "Standard";}
}
class ExpressShipping implements ShippingType{
    public double calculateCharge(double weight){return 80+15*weight;}
    public String getName(){return "Express";}
}
class FragileShipping implements ShippingType{
    private final ShippingType standard=new StandardShipping();
    public double calculateCharge(double weight){return standard.calculateCharge(weight)+50;}
    public String getName(){return "Fragile";}
}
interface NotificationChannel{
    String notify(String parcelId,ParcelStatus status);
}
class SmsChannel implements NotificationChannel{
    public String notify(String parcelId,ParcelStatus status){return "[SMS] "+parcelId+" is now "+status+".";}
}
class EmailChannel implements NotificationChannel{
    public String notify(String parcelId,ParcelStatus status){return "[Email] "+parcelId+" is now "+status+".";}
}
class Parcel{
    private final String id;
    private final double weight;
    private final ShippingType shippingType;
    private ParcelStatus status=ParcelStatus.BOOKED;
    private final List<NotificationChannel> channels=new ArrayList<>();
    public Parcel(String id,double weight,ShippingType shippingType){
        this.id=id;
        this.weight=weight;
        this.shippingType=shippingType;
    }
    public String getId(){return id;}
    public double getCharge(){return shippingType.calculateCharge(weight);}
    public double getWeight(){return weight;}
    public String getShippingTypeName(){return shippingType.getName();}
    public ParcelStatus getStatus(){return status;}
    public void subscribe(NotificationChannel channel){channels.add(channel);}
    public String changeStatus(ParcelStatus next){
        if(next.ordinal()!=status.ordinal()+1)return "Invalid transition: "+status+" → "+next+" is not allowed.";
        status=next;
        StringBuilder result=new StringBuilder();
        for(NotificationChannel channel:channels){
            result.append(channel.notify(id,status)).append("\n");
        }
        return result.toString().trim();
    }
    public String cancel(){
        if(status!=ParcelStatus.BOOKED)return "Cancellation failed: "+id+" can be cancelled only while BOOKED.";
        status=ParcelStatus.CANCELLED;
        return "Parcel "+id+" cancelled.";
    }
}
class ParcelService{
    public String book(Customer customer,Parcel parcel){
        StringBuilder result=new StringBuilder();
        result.append("Parcel ").append(parcel.getId()).append(" booked (").append(parcel.getShippingTypeName()).append(", ").append(parcel.getWeight()).append(" kg). Charge: ₹").append(String.format("%.2f",parcel.getCharge())).append(".");
        return result.toString()+"\n"+parcel.changeStatus(ParcelStatus.BOOKED);
    }
    public String updateStatus(Parcel parcel,ParcelStatus status){return parcel.changeStatus(status);}
    public String cancel(Parcel parcel){return parcel.cancel();}
}