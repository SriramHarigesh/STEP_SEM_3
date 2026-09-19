public class FestWideTicketIssuanceSettlementEngine{
    static class EventTicket{
        private static int ticketsIssued;
        public final String ticketId;
        private double balanceDue;
        private String paymentMode;
        public EventTicket(double basePrice){
            if(basePrice<=0)throw new IllegalArgumentException("Invalid base price");
            ticketsIssued++;
            ticketId="TCK-"+(1000+ticketsIssued);
            balanceDue=basePrice;
        }
        public void pay(double amount){if(amount>0)balanceDue=Math.max(0,balanceDue-amount);}
        public void pay(double amount,String mode){paymentMode=mode;pay(amount);}
        public double getBalanceDue(){return balanceDue;}
        public String getPaymentMode(){return paymentMode;}
        public static int getTicketsIssued(){return ticketsIssued;}
        public static boolean isValidPromoCode(String code){
            if(code==null||code.length()!=5||code.charAt(0)!='F'||!Character.isUpperCase(code.charAt(4)))return false;
            for(int i=1;i<=3;i++)if(!Character.isDigit(code.charAt(i)))return false;
            return true;
        }
    }
    static class GroupTicket extends EventTicket{
        private int groupSize;
        public GroupTicket(double basePrice,int groupSize){
            super(basePrice);
            if(groupSize<=0)throw new IllegalArgumentException("Invalid group size");
            this.groupSize=groupSize;
        }
        public int getGroupSize(){return groupSize;}
    }
    public static String processNightlySettlement(EventTicket[] tickets){
        int processed=0,skipped=0,group=0,individual=0;
        for(EventTicket ticket:tickets){
            if(ticket==null){skipped++;continue;}
            processed++;
            if(ticket instanceof GroupTicket)group++;
            else individual++;
        }
        return processed+" processed | "+skipped+" null skipped | "+group+" group | "+individual+" individual";
    }
    public static void main(String[] args){System.out.println(EventTicket.isValidPromoCode("F123A"));}
}
