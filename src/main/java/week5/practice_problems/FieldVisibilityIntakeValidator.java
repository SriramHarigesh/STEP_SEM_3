public class FieldVisibilityIntakeValidator{
    public static String classifyAccess(String fieldModifier,String accessorContext){
        if(fieldModifier.equals("public"))return "ALLOWED";
        if(fieldModifier.equals("private"))return accessorContext.equals("SAME_CLASS")?"ALLOWED":"DENIED";
        return accessorContext.equals("SAME_CLASS")||accessorContext.equals("SAME_PACKAGE")?"ALLOWED":"DENIED";
    }
    public static String summarizeBatch(String[][] attempts){
        int allowed=0,denied=0;
        for(String[] attempt:attempts){
            if(classifyAccess(attempt[0],attempt[1]).equals("ALLOWED"))allowed++;
            else denied++;
        }
        return "Allowed: "+allowed+" | Denied: "+denied;
    }
    static class PatientRecord{
        private String patientId;
        String wardCode;
        protected double vitalsScore;
        public String facilityName;
        public PatientRecord(String patientId,String wardCode,double vitalsScore,String facilityName){
            if(patientId==null||patientId.trim().length()<4)throw new IllegalArgumentException("Invalid patient ID");
            this.patientId=patientId.trim();
            this.wardCode=wardCode;
            this.vitalsScore=vitalsScore;
            this.facilityName=facilityName;
        }
    }
    public static void main(String[] args){
        System.out.println(summarizeBatch(new String[][]{{"protected","SAME_PACKAGE"},{"public","DIFFERENT_PACKAGE"}}));
    }
}
