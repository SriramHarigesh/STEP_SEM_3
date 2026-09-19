public class RaceFamilyTree{
    static class RaceEntry{
        protected String bibNumber;
        protected double entryFee;
        protected double paidAmount;
        public RaceEntry(String bibNumber,double entryFee){this.bibNumber=bibNumber;this.entryFee=entryFee;}
        public double getBalanceDue(){return Math.max(0,entryFee-paidAmount);}
        public String announce(){return "Race Entry | Bib: "+bibNumber+" | Balance: "+getBalanceDue();}
    }
    static class RunnerEntry extends RaceEntry{
        protected String category;
        public RunnerEntry(String bibNumber,double entryFee,String category){super(bibNumber,entryFee);this.category=category;}
        @Override public String announce(){return "Runner Entry | Bib: "+bibNumber+" | Category: "+category+" | Balance: "+getBalanceDue();}
    }
    static class EliteRunnerEntry extends RunnerEntry{
        private double sponsorBonus;
        public EliteRunnerEntry(String bibNumber,double entryFee,String category,double sponsorBonus){super(bibNumber,entryFee,category);this.sponsorBonus=sponsorBonus;}
        @Override public String announce(){return "Elite Runner | Bib: "+bibNumber+" | Category: "+category+" | Sponsor Bonus: "+sponsorBonus+" | Balance: "+getBalanceDue();}
    }
    static class RelayTeamEntry extends RaceEntry{
        private int teamSize;
        public RelayTeamEntry(String bibNumber,double entryFee,int teamSize){super(bibNumber,entryFee);this.teamSize=teamSize;}
        public int getTeamSize(){return teamSize;}
        @Override public String announce(){return "Relay Team | Bib: "+bibNumber+" | Team Size: "+teamSize+" | Balance: "+getBalanceDue();}
    }
    public static String classifyGeneration(RaceEntry entry){
        if(entry instanceof EliteRunnerEntry)return "Multilevel descendant (3 generations deep)";
        if(entry instanceof RelayTeamEntry)return "Hierarchical sibling (independent branch)";
        return "Base or direct descendant";
    }
    public static double getTotalBalanceDue(RaceEntry[] entries){
        double total=0;
        for(RaceEntry entry:entries)total+=entry.getBalanceDue();
        return total;
    }
    public static void main(String[] args){System.out.println(new EliteRunnerEntry("BIB3001",150,"Elite Full Marathon",500).announce());}
}
