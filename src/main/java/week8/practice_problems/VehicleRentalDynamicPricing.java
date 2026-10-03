import java.util.*;
public class VehicleRentalDynamicPricing{
    public static void main(String[] args){
        Customer customer=new Customer("Customer1");
        RentalService service=new RentalService();
        Vehicle luxury=new LuxuryCar("Luxury Car A");
        Vehicle standard=new StandardCar("Standard Car B");
        System.out.println(service.rent(customer,luxury,3));
        System.out.println(service.rent(customer,standard,5));
        System.out.println(service.returnVehicle(luxury));
    }
}
class Customer{
    private final String name;
    public Customer(String name){this.name=name;}
}
abstract class Vehicle{
    private final String name;
    private boolean available=true;
    public Vehicle(String name){this.name=name;}
    public abstract double calculateCharge(int days);
    public String getName(){return name;}
    public boolean isAvailable(){return available;}
    public void setAvailable(boolean available){this.available=available;}
}
class StandardCar extends Vehicle{
    public StandardCar(String name){super(name);}
    public double calculateCharge(int days){return days*50;}
}
class LuxuryCar extends Vehicle{
    public LuxuryCar(String name){super(name);}
    public double calculateCharge(int days){return days*100;}
}
class SUV extends Vehicle{
    public SUV(String name){super(name);}
    public double calculateCharge(int days){return days*80;}
}
class Rental{
    private final Customer customer;
    private final Vehicle vehicle;
    private final int days;
    private final double amount;
    public Rental(Customer customer,Vehicle vehicle,int days){
        this.customer=customer;
        this.vehicle=vehicle;
        this.days=days;
        this.amount=vehicle.calculateCharge(days);
    }
    public Vehicle getVehicle(){return vehicle;}
    public double getAmount(){return amount;}
}
class RentalService{
    private final List<Rental> rentals=new ArrayList<>();
    public String rent(Customer customer,Vehicle vehicle,int days){
        if(!vehicle.isAvailable())return "Rental failed: "+vehicle.getName()+" is not available.";
        if(days<=0)return "Rental failed: Duration must be positive.";
        Rental rental=new Rental(customer,vehicle,days);
        rentals.add(rental);
        vehicle.setAvailable(false);
        return vehicle.getName()+" rented for "+days+" days. Total charge: $"+String.format("%.2f",rental.getAmount())+".";
    }
    public String returnVehicle(Vehicle vehicle){
        if(vehicle.isAvailable())return vehicle.getName()+" is already available.";
        vehicle.setAvailable(true);
        return vehicle.getName()+" returned. Now available.";
    }
}