public class ConnectedHomeControlPanel{
    public static void main(String[] args){
        WashingMachine wm=new WashingMachine(500.0);
        Refrigerator fridge=new Refrigerator(150.0);
        MobileApp app=new MobileApp("HomeConnect App");
        RemoteControllable ref=wm;
        System.out.println(wm.activate());
        System.out.println(wm.connect("HomeConnect"));
        System.out.println(fridge.activate());
        System.out.println(app.connect("HomeConnect"));
        connectAll(new RemoteControllable[]{ref,app},"HomeConnect");
        HomeDevice device=wm;
        System.out.println(getConsumptionIfTrackable(device));
        System.out.println(getConsumptionIfTrackable(fridge));
    }
    static void connectAll(RemoteControllable[] items,String appId){
        for(RemoteControllable item:items){
            System.out.println(item.connect(appId));
        }
    }
    static double getConsumptionIfTrackable(HomeDevice d){
        if(d instanceof EnergyTrackable){
            EnergyTrackable e=(EnergyTrackable)d;
            return e.getConsumptionWatts();
        }
        return 0.0;
    }
}
abstract class HomeDevice{
    private static int counter=1000;
    private final String serialNumber;
    public HomeDevice(){
        serialNumber="HD-"+(++counter);
    }
    public abstract String activate();
    String getSerialNumber(){
        return serialNumber;
    }
}
interface RemoteControllable{
    String connect(String appId);
}
interface EnergyTrackable{
    double getConsumptionWatts();
}
class WashingMachine extends HomeDevice implements RemoteControllable,EnergyTrackable{
    private final double consumptionWatts;
    public WashingMachine(double consumptionWatts){
        this.consumptionWatts=consumptionWatts;
    }
    @Override
    public String activate(){
        return "Washing machine "+getSerialNumber()+" started a cycle";
    }
    @Override
    public String connect(String appId){
        return getSerialNumber()+" connected to "+appId;
    }
    @Override
    public double getConsumptionWatts(){
        return consumptionWatts;
    }
}
class Refrigerator extends HomeDevice implements EnergyTrackable{
    private final double consumptionWatts;
    public Refrigerator(double consumptionWatts){
        this.consumptionWatts=consumptionWatts;
    }
    @Override
    public String activate(){
        return "Refrigerator "+getSerialNumber()+" started";
    }
    @Override
    public double getConsumptionWatts(){
        return consumptionWatts;
    }
}
class MobileApp implements RemoteControllable{
    private final String appName;
    public MobileApp(String appName){
        this.appName=appName;
    }
    @Override
    public String connect(String appId){
        return appName+" connected to "+appId;
    }
}