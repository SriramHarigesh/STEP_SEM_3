public class RaceDayAnnouncerBoard{
    static class RaceEntry{
        protected String bibNumber;
        protected double balance;
        public RaceEntry(String bibNumber,double balance){this.bibNumber=bibNumber;this.balance=balance;}
        public String announce(){return "Race Entry | Bib: "+bibNumber+" | Balance: "+balance;}
    }
    static class RunnerEntry extends RaceEntry{
        private String category;
        public RunnerEntry(String bibNumber,double balance,String category){super(bibNumber,balance);this.category=category;}
        @Override public String announce(){return "Runner Entry | Bib: "+bibNumber+" | Category: "+category+" | Balance: "+balance;}
    }
    static class RelayTeamEntry extends RaceEntry{
        private int teamSize;
        public RelayTeamEntry(String bibNumber,double balance,int teamSize){super(bibNumber,balance);this.teamSize=teamSize;}
        public int getTeamSize(){return teamSize;}
        @Override public String announce(){return "Relay Team | Bib: "+bibNumber+" | Team Size: "+teamSize+" | Balance: "+balance;}
    }
    public static String announceAll(RaceEntry[] entries){
        StringBuilder report=new StringBuilder();
        for(RaceEntry entry:entries){
            report.append(entry.announce());
            if(entry instanceof RelayTeamEntry){
                RelayTeamEntry relay=(RelayTeamEntry)entry;
                report.append(" [Team size via downcast: ").append(relay.getTeamSize()).append("]");
            }
            report.append(" | ");
        }
        return report.toString();
    }
    public static void main(String[] args){
        RaceEntry[] entries={new RunnerEntry("BIB2001",90,"Open 10K"),new RelayTeamEntry("BIB4001",300,4)};
        System.out.println(announceAll(entries));
    }
}
