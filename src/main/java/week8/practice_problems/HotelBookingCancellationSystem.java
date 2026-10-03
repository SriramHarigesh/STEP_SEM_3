import java.time.*;
import java.util.*;
public class HotelBookingCancellationSystem{
    public static void main(String[] args){
        Hotel hotel=new Hotel();
        Room deluxe=new DeluxeRoom("Deluxe Room 101");
        Room standard=new StandardRoom("Standard Room 205");
        hotel.addRoom(deluxe);
        hotel.addRoom(standard);
        Customer customer=new Customer("Customer1");
        LocalDate d1=LocalDate.of(2024,12,1);
        LocalDate d2=LocalDate.of(2024,12,5);
        LocalDate d3=LocalDate.of(2024,12,3);
        LocalDate d4=LocalDate.of(2024,12,7);
        System.out.println(hotel.book(customer,deluxe,d1,d2));
        System.out.println(hotel.book(customer,standard,d3,d4));
        System.out.println(hotel.book(customer,deluxe,d3,d4));
        System.out.println(hotel.cancel(0));
    }
}
class Customer{
    private final String name;
    public Customer(String name){this.name=name;}
}
abstract class Room{
    private final String name;
    public Room(String name){this.name=name;}
    public String getName(){return name;}
    public abstract double calculatePrice(long nights);
}
class StandardRoom extends Room{
    public StandardRoom(String name){super(name);}
    public double calculatePrice(long nights){return nights*150;}
}
class DeluxeRoom extends Room{
    public DeluxeRoom(String name){super(name);}
    public double calculatePrice(long nights){return nights*200;}
}
class SuiteRoom extends Room{
    public SuiteRoom(String name){super(name);}
    public double calculatePrice(long nights){return nights*300;}
}
enum ReservationStatus{ACTIVE,CANCELLED}
class Reservation{
    private final Room room;
    private final Customer customer;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final double price;
    private ReservationStatus status=ReservationStatus.ACTIVE;
    public Reservation(Room room,Customer customer,LocalDate checkIn,LocalDate checkOut){
        this.room=room;
        this.customer=customer;
        this.checkIn=checkIn;
        this.checkOut=checkOut;
        this.price=room.calculatePrice(ChronoUnitHelper.nights(checkIn,checkOut));
    }
    public boolean overlaps(LocalDate start,LocalDate end){
        return status==ReservationStatus.ACTIVE&&checkIn.isBefore(end)&&start.isBefore(checkOut);
    }
    public void cancel(){status=ReservationStatus.CANCELLED;}
    public Room getRoom(){return room;}
    public double getPrice(){return price;}
}
class ChronoUnitHelper{
    static long nights(LocalDate start,LocalDate end){return java.time.temporal.ChronoUnit.DAYS.between(start,end);}
}
class Hotel{
    private final List<Room> rooms=new ArrayList<>();
    private final List<Reservation> reservations=new ArrayList<>();
    public void addRoom(Room room){rooms.add(room);}
    public String book(Customer customer,Room room,LocalDate start,LocalDate end){
        if(!rooms.contains(room))return "Booking failed: Room not found.";
        if(!start.isBefore(end))return "Booking failed: Invalid date range.";
        for(Reservation reservation:reservations){
            if(reservation.getRoom()==room&&reservation.overlaps(start,end))return "Booking failed: "+room.getName()+" is not available for "+start+" to "+end+".";
        }
        Reservation reservation=new Reservation(room,customer,start,end);
        reservations.add(reservation);
        return room.getName()+" booked from "+start+" to "+end+". Total price: $"+String.format("%.2f",reservation.getPrice())+".";
    }
    public String cancel(int index){
        if(index<0||index>=reservations.size())return "Cancellation failed: Reservation not found.";
        reservations.get(index).cancel();
        return "Reservation for "+reservations.get(index).getRoom().getName()+" cancelled successfully.";
    }
}