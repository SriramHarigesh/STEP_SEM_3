public class TicketHierarchyFoundation{
    static class EventTicket{
        private String attendeeId;
        private double basePrice;
        private double paidAmount;
        public EventTicket(String attendeeId,double basePrice){
            if(attendeeId==null||attendeeId.trim().length()<4)throw new IllegalArgumentException("Invalid attendee ID");
            if(basePrice<=0)throw new IllegalArgumentException("Invalid base price");
            this.attendeeId=attendeeId.trim();
            this.basePrice=basePrice;
        }
        public void pay(double amount){if(amount>0)paidAmount+=amount;}
        public double getBalanceDue(){return Math.max(0,basePrice-paidAmount);}
        public static String registerBatch(String[] attendeeIds,double basePrice){
            int registered=0,rejected=0;
            for(String attendeeId:attendeeIds){
                try{new EventTicket(attendeeId,basePrice);registered++;}
                catch(IllegalArgumentException e){rejected++;}
            }
            return "Registered: "+registered+" | Rejected: "+rejected;
        }
    }
    static class WorkshopTicket extends EventTicket{
        private String track;
        public WorkshopTicket(String attendeeId,double basePrice,String track){
            super(attendeeId,basePrice);
            this.track=track;
        }
        public String getTrack(){return track;}
    }
    public static void main(String[] args){
        WorkshopTicket ticket=new WorkshopTicket("STU2",1200,"AI/ML");
        ticket.pay(500);
        System.out.println(ticket.getBalanceDue());
    }
}
