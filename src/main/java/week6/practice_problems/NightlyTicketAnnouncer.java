public class NightlyTicketAnnouncer{
    static class EventTicket{
        protected double balance;
        public EventTicket(double balance){this.balance=balance;}
        public String printTicket(){return "Standard | Balance: "+balance;}
    }
    static class WorkshopTicket extends EventTicket{
        private String track;
        public WorkshopTicket(double balance,String track){super(balance);this.track=track;}
        public String getTrack(){return track;}
        @Override public String printTicket(){return "Workshop | Track: "+track+" | Balance: "+balance;}
    }
    public static String batchPrint(EventTicket[] tickets){
        StringBuilder report=new StringBuilder();
        for(EventTicket ticket:tickets){
            report.append(ticket.printTicket());
            if(ticket instanceof WorkshopTicket){
                WorkshopTicket workshop=(WorkshopTicket)ticket;
                report.append(" [Track via downcast: ").append(workshop.getTrack()).append("]");
            }
            report.append(" | ");
        }
        return report.toString();
    }
    public static void main(String[] args){System.out.println(batchPrint(new EventTicket[]{new EventTicket(500),new WorkshopTicket(1200,"AI/ML")}));}
}
