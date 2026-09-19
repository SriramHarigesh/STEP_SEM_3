public class RaceWideBibIssuanceSettlementEngine{
    static class RaceEntry{
        private static int bibCounter;
        public final String entryCode;
        private double balanceDue;
        private String paymentMode;
        public RaceEntry(String bibNumber,double entryFee){
            if(bibNumber==null||bibNumber.trim().length()<4||entryFee<=0)throw new IllegalArgumentException("Invalid entry");
            bibCounter++;
            entryCode="ENT-"+(1000+bibCounter);
            balanceDue=entryFee;
        }
        public void pay(double amount){if(amount>0)balanceDue=Math.max(0,balanceDue-amount);}
        public void pay(double amount,String mode){paymentMode=mode;System.out.println("Paying via "+mode);pay(amount);}
        public double getBalanceDue(){return balanceDue;}
        public String getPaymentMode(){return paymentMode;}
        public static int getBibCounter(){return bibCounter;}
        public static boolean isValidDiscountCode(String code){
            if(code==null||code.length()!=5||code.charAt(0)!='M'||!Character.isUpperCase(code.charAt(4)))return false;
            for(int i=1;i<=3;i++)if(!Character.isDigit(code.charAt(i)))return false;
            return true;
        }
    }
    static class RelayTeamEntry extends RaceEntry{
        private int teamSize;
        public RelayTeamEntry(String bibNumber,double entryFee,int teamSize){
            super(bibNumber,entryFee);
            if(teamSize<=0)throw new IllegalArgumentException("Invalid team size");
            this.teamSize=teamSize;
        }
        public int getTeamSize(){return teamSize;}
    }
    public static String settleNight(RaceEntry[] entries){
        int processed=0,skipped=0,relay=0,individual=0;
        for(RaceEntry entry:entries){
            if(entry==null){skipped++;continue;}
            processed++;
            if(entry instanceof RelayTeamEntry)relay++;
            else individual++;
        }
        return processed+" processed | "+skipped+" null skipped | "+relay+" relay | "+individual+" individual";
    }
    public static void main(String[] args){System.out.println(RaceEntry.isValidDiscountCode("M123A"));}
}
