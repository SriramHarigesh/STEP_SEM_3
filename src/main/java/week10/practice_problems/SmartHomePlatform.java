import java.util.*;
public class SmartHomePlatform {
    interface Dimmable {
        void dim(int level);
    }
    interface Schedulable {
        void schedule(String time);
    }
    interface EnergyMonitor {
        int energy();
    }
    static abstract class Device {
        String id;
        boolean on;
        Device(String id) {
            this.id = id;
        }
    }
    static class Light extends Device implements Dimmable, Schedulable {
        int level;
        String time;
        Light(String id) {
            super(id);
        }
        public void dim(int level) {
            this.level = level;
        }
        public void schedule(String time) {
            this.time = time;
        }
    }
    static class Fan extends Device implements Schedulable {
        String time;
        Fan(String id) {
            super(id);
        }
        public void schedule(String time) {
            this.time = time;
        }
    }
    static class Plug extends Device implements EnergyMonitor {
        int kwh;
        Plug(String id, int kwh) {
            super(id);
            this.kwh = kwh;
        }
        public int energy() {
            return kwh;
        }
    }
    static void process(Map<String, Device> devices, String command) {
        String[] parts = command.split(" ");
        String cmd = parts[0];
        Device d = devices.get(parts[1]);
        if (d == null) {
            System.out.println(parts[1] + " not found");
            return;
        }
        if (cmd.equals("ON")) {
            d.on = true;
            System.out.println(d.id + " is ON");
        } else if (cmd.equals("OFF")) {
            d.on = false;
            System.out.println(d.id + " is OFF");
        } else if (cmd.equals("DIM")) {
            if (d instanceof Dimmable) {
                ((Dimmable) d).dim(Integer.parseInt(parts[2]));
                System.out.println(d.id + " dimmed to " + parts[2]);
            } else {
                System.out.println(d.id + " rejected: DIM unsupported");
            }
        } else if (cmd.equals("SCHEDULE")) {
            if (d instanceof Schedulable) {
                ((Schedulable) d).schedule(parts[2]);
                System.out.println(d.id + " scheduled " + parts[2]);
            } else {
                System.out.println(d.id + " rejected: SCHEDULE unsupported");
            }
        } else if (cmd.equals("ENERGY")) {
            if (d instanceof EnergyMonitor) {
                System.out.println(d.id + " energy " + ((EnergyMonitor) d).energy() + " kWh");
            } else {
                System.out.println(d.id + " rejected: ENERGY unsupported");
            }
        } else {
            System.out.println("Unknown command");
        }
    }
    public static void main(String[] args) {
        Map<String, Device> devices = new LinkedHashMap<>();
        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new Plug("P1", 12));
        String[] commands = {"ON L1", "DIM L1 40", "DIM F1 30", "SCHEDULE F1 22:00", "ENERGY P1", "ENERGY L1"};
        for (String c : commands) {
            process(devices, c);
        }
    }
}
