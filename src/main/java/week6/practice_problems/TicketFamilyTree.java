public class TicketFamilyTree{
    static class EventTicket{
        protected String attendeeId;
        protected double basePrice;
        protected double paidAmount;
        public EventTicket(String attendeeId,double basePrice){this.attendeeId=attendeeId;this.basePrice=basePrice;}
        public double getBalanceDue(){return Math.max(0,basePrice-paidAmount);}
        public String printTicket(){return "Standard Event Ticket | Balance Due: "+getBalanceDue();}
    }
    static class WorkshopTicket extends EventTicket{
        protected String track;
        public WorkshopTicket(String attendeeId,double basePrice,String track){super(attendeeId,basePrice);this.track=track;}
        @Override public String printTicket(){return "Workshop Ticket | Track: "+track+" | Balance Due: "+getBalanceDue();}
    }
    static class PremiumWorkshopTicket extends WorkshopTicket{
        private double kitFee;
        public PremiumWorkshopTicket(String attendeeId,double basePrice,String track,double kitFee){super(attendeeId,basePrice,track);this.kitFee=kitFee;}
        @Override public String printTicket(){return "Premium Workshop Ticket | Track: "+track+" | Kit Fee: "+kitFee+" | Balance Due: "+getBalanceDue();}
    }
    static class HackathonTicket extends EventTicket{
        private String teamName;
        public HackathonTicket(String attendeeId,double basePrice,String teamName){super(attendeeId,basePrice);this.teamName=teamName;}
        @Override public String printTicket(){return "Hackathon Ticket | Team: "+teamName+" | Balance Due: "+getBalanceDue();}
    }
    public static String classifyGeneration(EventTicket ticket){
        if(ticket instanceof PremiumWorkshopTicket)return "Multilevel descendant (3 generations deep)";
        if(ticket instanceof HackathonTicket)return "Hierarchical sibling (independent branch)";
        return "Base or direct descendant";
    }
    public static double getTotalBalanceDue(EventTicket[] tickets){
        double total=0;
        for(EventTicket ticket:tickets)total+=ticket.getBalanceDue();
        return total;
    }
    public static void main(String[] args){System.out.println(new PremiumWorkshopTicket("STU3",2000,"Cloud Native",300).printTicket());}
}
