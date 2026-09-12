package week4.practice_problems;
import java.util.HashSet;
public class BusTicketBookingValidator{
public static void main(String[] args){
String[][] bookings={{"Divya","Chennai"},{"","Bangalore"},{"Ravi123","Pune"},{"Divya","Chennai"},{" "," "}};
BusTicket.processBatch(bookings);
}
}
class BusTicket{
private String passengerName;
private String destination;
private boolean checkedIn;
public BusTicket(String passengerName,String destination){
if(!validName(passengerName)||!validDestination(destination))throw new IllegalArgumentException("Invalid booking");
this.passengerName=passengerName.trim();
this.destination=destination.trim();
}
private static boolean validName(String value){return value!=null&&value.trim().matches("[A-Za-z ]+");}
private static boolean validDestination(String value){return value!=null&&value.trim().matches("[A-Za-z ]+");}
void markCheckedIn(){if(checkedIn)System.out.println("Ticket already checked in");else{checkedIn=true;System.out.println("Checked in");}}
static void processBatch(String[][] rawBookings){
int valid=0,rejected=0,duplicates=0;
HashSet<String> accepted=new HashSet<>();
if(rawBookings!=null)for(String[] booking:rawBookings){
try{
if(booking==null||booking.length<2)throw new IllegalArgumentException();
BusTicket ticket=new BusTicket(booking[0],booking[1]);
String key=ticket.passengerName.toLowerCase()+"|"+ticket.destination.toLowerCase();
if(accepted.add(key))valid++;else duplicates++;
}catch(IllegalArgumentException e){rejected++;}
}
System.out.println("Valid: "+valid+" | Rejected: "+rejected+" | Duplicates skipped: "+duplicates);
}
}
