import java.util.*;
public class ElectiveSeatRush{
    public static void main(String[] args){
        Elective elective=new Elective("Cloud Computing",4,2);
        EnrollmentService service=new EnrollmentService();
        Student asha=new RegularStudent("Asha",20);
        Student ravi=new HonorsStudent("Ravi",22);
        Student neha=new ExchangeStudent("Neha",12);
        Student kiran=new RegularStudent("Kiran",22);
        System.out.println(service.enroll(elective,asha));
        System.out.println(service.enroll(elective,ravi));
        System.out.println(service.enroll(elective,neha));
        System.out.println(service.enroll(elective,kiran));
        System.out.println(service.drop(elective,asha));
    }
}
interface CreditPolicy{
    int getCreditLimit();
    String getType();
}
class RegularPolicy implements CreditPolicy{
    public int getCreditLimit(){return 24;}
    public String getType(){return "Regular";}
}
class HonorsPolicy implements CreditPolicy{
    public int getCreditLimit(){return 28;}
    public String getType(){return "Honors";}
}
class ExchangePolicy implements CreditPolicy{
    public int getCreditLimit(){return 20;}
    public String getType(){return "Exchange";}
}
class Student{
    private final String name;
    private int currentCredits;
    private final CreditPolicy policy;
    public Student(String name,int currentCredits,CreditPolicy policy){
        this.name=name;
        this.currentCredits=currentCredits;
        this.policy=policy;
    }
    public String getName(){return name;}
    public int getCurrentCredits(){return currentCredits;}
    public CreditPolicy getPolicy(){return policy;}
    public boolean canAdd(int credits){return currentCredits+credits<=policy.getCreditLimit();}
    public void addCredits(int credits){currentCredits+=credits;}
    public void removeCredits(int credits){currentCredits-=credits;}
}
class RegularStudent extends Student{
    public RegularStudent(String name,int currentCredits){super(name,currentCredits,new RegularPolicy());}
}
class HonorsStudent extends Student{
    public HonorsStudent(String name,int currentCredits){super(name,currentCredits,new HonorsPolicy());}
}
class ExchangeStudent extends Student{
    public ExchangeStudent(String name,int currentCredits){super(name,currentCredits,new ExchangePolicy());}
}
class Elective{
    private final String name;
    private final int credits;
    private final int capacity;
    private final List<Student> enrolled=new ArrayList<>();
    private final Queue<Student> waitlist=new ArrayDeque<>();
    public Elective(String name,int credits,int capacity){
        this.name=name;
        this.credits=credits;
        this.capacity=capacity;
    }
    public String getName(){return name;}
    public int getCredits(){return credits;}
    public boolean isEnrolled(Student student){return enrolled.contains(student);}
    public boolean isWaitlisted(Student student){return waitlist.contains(student);}
    public boolean hasSpace(){return enrolled.size()<capacity;}
    public void enroll(Student student){enrolled.add(student);student.addCredits(credits);}
    public void waitlist(Student student){waitlist.offer(student);}
    public Student drop(Student student){
        if(!enrolled.remove(student))return null;
        student.removeCredits(credits);
        return student;
    }
    public Student pollWaitlist(){return waitlist.poll();}
}
class EnrollmentService{
    public String enroll(Elective elective,Student student){
        if(elective.isEnrolled(student)||elective.isWaitlisted(student))return "Enrollment failed: "+student.getName()+" is already enrolled or waitlisted.";
        if(!student.canAdd(elective.getCredits()))return "Enrollment failed: "+student.getName()+" would exceed the "+student.getPolicy().getType()+" credit limit ("+(student.getCurrentCredits()+elective.getCredits())+"/"+student.getPolicy().getCreditLimit()+").";
        if(elective.hasSpace()){
            elective.enroll(student);
            return student.getName()+" enrolled in "+elective.getName()+" (credits: "+student.getCurrentCredits()+"/"+student.getPolicy().getCreditLimit()+").";
        }
        elective.waitlist(student);
        return elective.getName()+" is full.\n"+student.getName()+" added to waitlist (position 1).";
    }
    public String drop(Elective elective,Student student){
        Student dropped=elective.drop(student);
        if(dropped==null)return "Drop failed: "+student.getName()+" is not enrolled.";
        String result=student.getName()+" dropped "+elective.getName()+" (credits: "+student.getCurrentCredits()+"/"+student.getPolicy().getCreditLimit()+").";
        while(elective.hasSpace()){
            Student next=elective.pollWaitlist();
            if(next==null)break;
            if(next.canAdd(elective.getCredits())){
                elective.enroll(next);
                result+="\n"+next.getName()+" promoted from waitlist and enrolled in "+elective.getName()+" (credits: "+next.getCurrentCredits()+"/"+next.getPolicy().getCreditLimit()+").";
                break;
            }
        }
        return result;
    }
}