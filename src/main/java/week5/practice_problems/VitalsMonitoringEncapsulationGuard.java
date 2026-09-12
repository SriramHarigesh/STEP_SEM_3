public class VitalsMonitoringEncapsulationGuard{
    static class PatientVitals{
        private double[] readings;
        private int count;
        public PatientVitals(double[] initialReadings){
            readings=new double[500];
            if(initialReadings!=null)for(double reading:initialReadings)recordReading(reading);
        }
        public void recordReading(double reading){if(reading>0&&reading<=45&&count<readings.length)readings[count++]=reading;}
        public double getAverage(){
            if(count==0)return 0;
            double total=0;
            for(int i=0;i<count;i++)total+=readings[i];
            return total/count;
        }
        public double[] getAllReadings(){
            double[] copy=new double[count];
            for(int i=0;i<count;i++)copy[i]=readings[i];
            return copy;
        }
    }
    public static void main(String[] args){
        PatientVitals vitals=new PatientVitals(new double[]{36.5,-2,37.1});
        System.out.println(vitals.getAverage());
    }
}
