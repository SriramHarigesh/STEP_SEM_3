public class LateWithdrawalPenaltyAuditTrail{
    static class RaceEntry{
        private double entryFee;
        private double paidAmount;
        private double[] lateFeeHistory=new double[10];
        private int lateFeeCount;
        public RaceEntry(String bibNumber,double entryFee){this.entryFee=entryFee;}
        public void pay(double amount){if(amount>0)paidAmount+=amount;}
        public double getBalanceDue(){return Math.max(0,entryFee-paidAmount);}
        protected void applyLateFee(double amount){
            if(amount>0&&lateFeeCount<lateFeeHistory.length){entryFee+=amount;lateFeeHistory[lateFeeCount++]=amount;}
        }
        public double[] getLateFeeHistory(){
            double[] copy=new double[lateFeeCount];
            for(int i=0;i<lateFeeCount;i++)copy[i]=lateFeeHistory[i];
            return copy;
        }
    }
    static class RunnerEntry extends RaceEntry{
        public RunnerEntry(String bibNumber,double entryFee,String category){super(bibNumber,entryFee);}
        @Override protected void applyLateFee(double amount){super.applyLateFee(amount*2);}
    }
    public static void main(String[] args){
        RunnerEntry entry=new RunnerEntry("BIB2001",80,"Open 10K");
        entry.pay(30);
        entry.applyLateFee(20);
        System.out.println(entry.getBalanceDue());
    }
}
