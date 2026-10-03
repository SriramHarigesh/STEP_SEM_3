public class FleetMaintenanceTracker{
    public static void main(String[] args){
        Forklift f=new Forklift("FL-22");
        HeavyDutyForklift hd=new HeavyDutyForklift("HD-9");
        f.addMileage(120);
        System.out.println(f.getMileage());
        System.out.println(f.performMaintenance());
        System.out.println(hd.performMaintenance());
        System.out.println(getInsuranceIfApplicable(f));
        ServiceableVehicle ref=hd;
        System.out.println(getInsuranceIfApplicable(ref));
    }
    static String getInsuranceIfApplicable(ServiceableVehicle v){
        if(v instanceof Insurable){
            Insurable i=(Insurable)v;
            return i.getInsuranceInfo();
        }
        return "No insurance record exists";
    }
}
abstract class ServiceableVehicle{
    private double mileage;
    public ServiceableVehicle(){
        mileage=0;
    }
    public abstract String performMaintenance();
    double getMileage(){
        return mileage;
    }
    void addMileage(double km){
        if(km>=0){
            mileage+=km;
        }
    }
}
interface Insurable{
    String getInsuranceInfo();
}
class Forklift extends ServiceableVehicle implements Insurable{
    protected final String assetTag;
    public Forklift(String assetTag){
        this.assetTag=assetTag;
    }
    @Override
    public String performMaintenance(){
        return "Forklift "+assetTag+": hydraulic and fork inspection complete";
    }
    @Override
    public String getInsuranceInfo(){
        return "Insured under fleet policy - Asset "+assetTag;
    }
}
class HeavyDutyForklift extends Forklift{
    public HeavyDutyForklift(String assetTag){
        super(assetTag);
    }
    @Override
    public String performMaintenance(){
        return super.performMaintenance()+" | high-pressure hydraulic check complete";
    }
}