import java.util.*;
public class CodeSprintJudgingDesk{
    public static void main(String[] args){
        Hackathon hackathon=new Hackathon("Code Sprint");
        Student asha=new Student("Asha");
        Student ravi=new Student("Ravi");
        Student neha=new Student("Neha");
        Student kiran=new Student("Kiran");
        Team byteBusters=new Team("ByteBusters",new InnovationTrack());
        Team soloCoder=new Team("SoloCoder",new OpenTrack());
        System.out.println(hackathon.registerTeam(byteBusters,Arrays.asList(asha,ravi,neha)));
        System.out.println(hackathon.registerTeam(soloCoder,Arrays.asList(kiran)));
        Project project=new Project("SmartAttend");
        System.out.println(hackathon.submitProject(byteBusters,project));
        Judge judge=new Judge("Judge1");
        System.out.println(hackathon.recordScore(judge,project,8,7,9));
        System.out.println(hackathon.publishResults());
        System.out.println(hackathon.recordScore(judge,project,10,7,9));
    }
}
enum HackathonState{OPEN,JUDGING,PUBLISHED}
class Student{
    private final String name;
    public Student(String name){this.name=name;}
    public String getName(){return name;}
}
interface ScoringRule{
    double calculate(double idea,double execution,double presentation);
    String getName();
}
class InnovationTrack implements ScoringRule{
    public double calculate(double idea,double execution,double presentation){
        return idea*0.5+execution*0.3+presentation*0.2;
    }
    public String getName(){return "Innovation";}
}
class OpenTrack implements ScoringRule{
    public double calculate(double idea,double execution,double presentation){
        return (idea+execution+presentation)/3.0;
    }
    public String getName(){return "Open";}
}
class Team{
    private final String name;
    private final ScoringRule scoringRule;
    private final List<Student> members=new ArrayList<>();
    private Project project;
    public Team(String name,ScoringRule scoringRule){
        this.name=name;
        this.scoringRule=scoringRule;
    }
    public boolean addMembers(List<Student> students){
        if(students.size()<2||students.size()>4)return false;
        members.addAll(students);
        return true;
    }
    public String getName(){return name;}
    public List<Student> getMembers(){return Collections.unmodifiableList(members);}
    public ScoringRule getScoringRule(){return scoringRule;}
    public Project getProject(){return project;}
    public void setProject(Project project){this.project=project;}
}
class Project{
    private final String name;
    private Score score;
    public Project(String name){this.name=name;}
    public String getName(){return name;}
    public Score getScore(){return score;}
    public void setScore(Score score){this.score=score;}
}
class Judge{
    private final String name;
    public Judge(String name){this.name=name;}
    public String getName(){return name;}
}
class Score{
    private final Judge judge;
    private final double idea;
    private final double execution;
    private final double presentation;
    public Score(Judge judge,double idea,double execution,double presentation){
        this.judge=judge;
        this.idea=idea;
        this.execution=execution;
        this.presentation=presentation;
    }
    public double finalScore(ScoringRule rule){
        return rule.calculate(idea,execution,presentation);
    }
}
class Hackathon{
    private final String name;
    private final List<Team> teams=new ArrayList<>();
    private final Set<Student> registeredStudents=new HashSet<>();
    private HackathonState state=HackathonState.OPEN;
    public Hackathon(String name){this.name=name;}
    public String registerTeam(Team team,List<Student> students){
        if(state!=HackathonState.OPEN)return "Registration failed: Hackathon is not open.";
        if(students.size()<2||students.size()>4)return "Registration failed: A team must have 2 to 4 members.";
        for(Student student:students){
            if(registeredStudents.contains(student))return "Registration failed: Student "+student.getName()+" already belongs to a team.";
        }
        team.addMembers(students);
        teams.add(team);
        registeredStudents.addAll(students);
        state=HackathonState.JUDGING;
        state=HackathonState.OPEN;
        return "Team "+team.getName()+" registered ("+students.size()+" members, "+team.getScoringRule().getName()+" track).";
    }
    public String submitProject(Team team,Project project){
        if(!teams.contains(team))return "Submission failed: Team is not registered.";
        if(team.getProject()!=null)return "Submission failed: Team can submit only one project.";
        team.setProject(project);
        return "Project '"+project.getName()+"' submitted by "+team.getName()+".";
    }
    public String recordScore(Judge judge,Project project,double idea,double execution,double presentation){
        if(state==HackathonState.PUBLISHED)return "Rescore rejected: Results have already been published.";
        if(project.getScore()!=null)return "Rescore rejected: Project already has a score.";
        if(idea<0||idea>10||execution<0||execution>10||presentation<0||presentation>10)return "Score rejected: Ratings must be between 0 and 10.";
        project.setScore(new Score(judge,idea,execution,presentation));
        return "Score recorded for '"+project.getName()+"'. Final score: "+String.format("%.2f",findTeam(project).getScoringRule().calculate(idea,execution,presentation))+".";
    }
    private Team findTeam(Project project){
        for(Team team:teams)if(team.getProject()==project)return team;
        return null;
    }
    public String publishResults(){
        state=HackathonState.PUBLISHED;
        return "Results published.";
    }
}