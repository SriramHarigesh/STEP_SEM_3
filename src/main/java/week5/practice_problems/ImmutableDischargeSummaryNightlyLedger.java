public class ImmutableDischargeSummaryNightlyLedger{
    static class DischargeSummary{
        private final String patientId;
        private final String[] medicationCodes;
        static String ledgerStatus;
        static{ledgerStatus="Nightly discharge ledger ready";}
        public DischargeSummary(String patientId,String[] medicationCodes){
            if(patientId==null||patientId.trim().isEmpty()||medicationCodes==null)throw new IllegalArgumentException("Invalid summary");
            for(String code:medicationCodes)if(code==null||!code.matches("MED-[A-Z]"))throw new IllegalArgumentException("Invalid medication code");
            this.patientId=patientId;
            this.medicationCodes=medicationCodes.clone();
        }
        public String getPatientId(){return patientId;}
        public String[] getMedicationCodes(){return medicationCodes.clone();}
        public DischargeSummary withCorrectedMedication(int index,String newCode){
            String[] corrected=getMedicationCodes();
            corrected[index]=newCode;
            return new DischargeSummary(patientId,corrected);
        }
    }
    static class CriticalCareDischargeSummary extends DischargeSummary{
        private final int icuDays;
        public CriticalCareDischargeSummary(String patientId,String[] medicationCodes,int icuDays){
            super(patientId,medicationCodes);
            if(icuDays<0)throw new IllegalArgumentException("Invalid ICU days");
            this.icuDays=icuDays;
        }
        public int getIcuDays(){return icuDays;}
    }
    public static String processNightlyBatch(DischargeSummary[] summaries){
        int processed=0,nullSkipped=0,criticalCare=0,routine=0;
        if(summaries==null)return "0 processed | 0 null skipped | 0 critical-care | 0 routine";
        for(DischargeSummary summary:summaries){
            if(summary==null){nullSkipped++;continue;}
            processed++;
            if(summary instanceof CriticalCareDischargeSummary)criticalCare++;
            else routine++;
        }
        return processed+" processed | "+nullSkipped+" null skipped | "+criticalCare+" critical-care | "+routine+" routine";
    }
    public static void main(String[] args){
        System.out.println(processNightlyBatch(new DischargeSummary[]{new DischargeSummary("MT001",new String[]{"MED-A"})}));
    }
}
