public class CommunityLibraryCheckoutSystem{
    public static void main(String[] args){
        Textbook t=new Textbook("Java Fundamentals");
        Magazine m=new Magazine("Tech Monthly");
        DigitalPass d=new DigitalPass("E-Journal Access");
        LibraryItem ref=t;
        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());
        System.out.println(m.getLoanPeriodDays());
        System.out.println(reserveIfSupported(m));
        System.out.println(reserveIfSupported(d));
        System.out.println(reserveIfSupported(ref));
        processCheckouts(new LibraryItem[]{t,m});
    }
    static void processCheckouts(LibraryItem[] items){
        for(LibraryItem item:items){
            System.out.println(item.getLoanPeriodDays());
        }
    }
    static String reserveIfSupported(Object o){
        if(o instanceof Reservable){
            Reservable r=(Reservable)o;
            return r.reserve();
        }
        return "Reservation not supported";
    }
}
abstract class LibraryItem{
    private static int counter=1000;
    private final String itemId;
    public LibraryItem(){
        itemId="LI-"+(++counter);
    }
    public abstract int getLoanPeriodDays();
    String getItemId(){
        return itemId;
    }
}
interface Renewable{
    String renew();
}
interface Reservable{
    String reserve();
}
class Textbook extends LibraryItem implements Renewable,Reservable{
    private final String title;
    public Textbook(String title){
        this.title=title;
    }
    @Override
    public int getLoanPeriodDays(){
        return 14;
    }
    @Override
    public String renew(){
        return title+" renewed";
    }
    @Override
    public String reserve(){
        return title+" reserved";
    }
}
class Magazine extends LibraryItem implements Renewable{
    private final String title;
    public Magazine(String title){
        this.title=title;
    }
    @Override
    public int getLoanPeriodDays(){
        return 7;
    }
    @Override
    public String renew(){
        return title+" renewed";
    }
}
class DigitalPass implements Renewable{
    private final String resourceName;
    public DigitalPass(String resourceName){
        this.resourceName=resourceName;
    }
    @Override
    public String renew(){
        return resourceName+" renewed";
    }
}