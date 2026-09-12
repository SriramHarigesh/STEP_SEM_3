public class PatientProfileJavaBean{
    static class PatientProfile{
        private String patientId;
        private String name;
        private boolean discharged;
        private String lockerPinHash;
        public PatientProfile(){this(null,null);}
        public PatientProfile(String name){this(null,name);}
        public PatientProfile(String patientId,String name){
            this.patientId=patientId;
            this.name=name;
        }
        public String getPatientId(){return patientId;}
        public void setPatientId(String patientId){if(this.patientId==null&&patientId!=null&&!patientId.trim().isEmpty())this.patientId=patientId;}
        public String getName(){return name;}
        public void setName(String name){this.name=name;}
        public boolean isDischarged(){return discharged;}
        public void setDischarged(boolean discharged){this.discharged=discharged;}
        public void setLockerPin(String lockerPin){if(lockerPin!=null&&lockerPin.matches("\\d{4,6}"))lockerPinHash=Integer.toHexString(lockerPin.hashCode());}
    }
    public static void main(String[] args){
        PatientProfile patient=new PatientProfile("MT2026-0142","Arjun Iyer");
        System.out.println(patient.getPatientId());
    }
}
