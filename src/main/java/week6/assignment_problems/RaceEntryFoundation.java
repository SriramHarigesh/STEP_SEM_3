public class RaceEntryFoundation{
    static class RaceEntry{
        private String bibNumber;
        private double entryFee;
        private double paidAmount;
        public RaceEntry(String bibNumber,double entryFee){
            if(bibNumber==null||bibNumber.trim().length()<4)throw new IllegalArgumentException("Invalid bib number");
            if(entryFee<=0)throw new IllegalArgumentException("Invalid entry fee");
            this.bibNumber=bibNumber.trim();
            this.entryFee=entryFee;
        }
        public void pay(double amount){if(amount>0)paidAmount+=amount;}
        public double getBalanceDue(){return Math.max(0,entryFee-paidAmount);}
        public static String registerBatch(String[] bibNumbers,double entryFee){
            int registered=0,rejected=0;
            for(String bibNumber:bibNumbers){
                try{new RaceEntry(bibNumber,entryFee);registered++;}
                catch(IllegalArgumentException e){rejected++;}
            }
            return "Registered: "+registered+" | Rejected: "+rejected;
        }
    }
    static class RunnerEntry extends RaceEntry{
        private String category;
        public RunnerEntry(String bibNumber,double entryFee,String category){super(bibNumber,entryFee);this.category=category;}
        public String getCategory(){return category;}
    }
    public static void main(String[] args){System.out.println(RaceEntry.registerBatch(new String[]{"BIB1","B1","BIB2"},80));}
}
