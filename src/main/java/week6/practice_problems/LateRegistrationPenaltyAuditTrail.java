public class LateRegistrationPenaltyAuditTrail{
    static class EventTicket{
        private double basePrice;
        private double paidAmount;
        private double[] lateFeeHistory=new double[10];
        private int lateFeeCount;
        public EventTicket(double basePrice){this.basePrice=basePrice;}
        public void pay(double amount){if(amount>0)paidAmount+=amount;}
        public double getBalanceDue(){return Math.max(0,basePrice-paidAmount);}
        protected void applyLateFee(double amount){
            if(amount>0&&lateFeeCount<lateFeeHistory.length){basePrice+=amount;lateFeeHistory[lateFeeCount++]=amount;}
        }
        public double[] getLateFeeHistory(){
            double[] copy=new double[lateFeeCount];
            for(int i=0;i<lateFeeCount;i++)copy[i]=lateFeeHistory[i];
            return copy;
        }
    }
    static class WorkshopTicket extends EventTicket{
        public WorkshopTicket(double basePrice){super(basePrice);}
        @Override protected void applyLateFee(double amount){super.applyLateFee(amount*2);}
    }
    public static void main(String[] args){
        WorkshopTicket ticket=new WorkshopTicket(1200);
        ticket.pay(1200);
        ticket.applyLateFee(100);
        System.out.println(ticket.getBalanceDue());
    }
}
