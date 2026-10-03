import java.util.*;
public class SmartLabControlPanel{
    public static void main(String[] args){
        Device ac=new Device("Lab AC");
        Device lights=new Device("Ceiling Lights");
        Device projector=new Device("Projector");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());
        projector.addCapability(new PowerCapability());
        Scene lectureMode=new Scene("Lecture Mode");
        lectureMode.addStep("Power",true);
        lectureMode.addStep("Brightness",40);
        lectureMode.addStep("Temperature",24);
        System.out.println(lectureMode.execute(Arrays.asList(ac,lights,projector)));
        System.out.println(ac.setCapabilityValue("Temperature",12));
        System.out.println(projector.addCapability(new BrightnessCapability()));
        System.out.println(projector.setCapabilityValue("Brightness",70));
    }
}
interface Capability{
    String getName();
    String setValue(Object value,String deviceName);
}
class PowerCapability implements Capability{
    private boolean on;
    public String getName(){return "Power";}
    public String setValue(Object value,String deviceName){
        if(!(value instanceof Boolean))return "Rejected: Invalid power value.";
        on=(Boolean)value;
        return deviceName+": "+(on?"ON":"OFF")+".";
    }
}
class BrightnessCapability implements Capability{
    private int brightness;
    public String getName(){return "Brightness";}
    public String setValue(Object value,String deviceName){
        if(!(value instanceof Number))return "Rejected: Invalid brightness value.";
        int v=((Number)value).intValue();
        if(v<0||v>100)return "Rejected: "+deviceName+" brightness must be between 0% and 100%.";
        brightness=v;
        return deviceName+": brightness set to "+v+"%.";
    }
}
class TemperatureCapability implements Capability{
    private double temperature;
    public String getName(){return "Temperature";}
    public String setValue(Object value,String deviceName){
        if(!(value instanceof Number))return "Rejected: Invalid temperature value.";
        double v=((Number)value).doubleValue();
        if(v<16||v>30)return "Rejected: "+deviceName+" temperature must be between 16°C and 30°C.";
        temperature=v;
        return deviceName+": temperature set to "+String.format("%.0f",v)+"°C.";
    }
}
class Device{
    private final String name;
    private final Map<String,Capability> capabilities=new LinkedHashMap<>();
    public Device(String name){this.name=name;}
    public String getName(){return name;}
    public String addCapability(Capability capability){
        capabilities.put(capability.getName(),capability);
        return name+": "+capability.getName()+" capability added.";
    }
    public boolean hasCapability(String name){return capabilities.containsKey(name);}
    public String setCapabilityValue(String name,Object value){
        Capability capability=capabilities.get(name);
        if(capability==null)return name+" capability not supported by "+this.name+".";
        return capability.setValue(value,this.name);
    }
}
class SceneStep{
    private final String capabilityName;
    private final Object value;
    public SceneStep(String capabilityName,Object value){
        this.capabilityName=capabilityName;
        this.value=value;
    }
    public int apply(List<Device> devices){
        int count=0;
        for(Device device:devices){
            if(device.hasCapability(capabilityName)){
                System.out.println(device.setCapabilityValue(capabilityName,value));
                count++;
            }
        }
        return count;
    }
}
class Scene{
    private final String name;
    private final List<SceneStep> steps=new ArrayList<>();
    public Scene(String name){this.name=name;}
    public void addStep(String capabilityName,Object value){steps.add(new SceneStep(capabilityName,value));}
    public String execute(List<Device> devices){
        System.out.println("Scene '"+name+"' started.");
        int count=0;
        for(SceneStep step:steps)count+=step.apply(devices);
        return "Scene '"+name+"' completed: "+count+" actions applied.";
    }
}