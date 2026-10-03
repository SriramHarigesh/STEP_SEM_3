import java.util.*;
public class OnlineExaminationSystem{
    public static void main(String[] args){
        Student student=new Student("Student1");
        Examination exam=new Examination("Math Quiz");
        exam.addQuestion(new MultipleChoiceQuestion("Question 1","A"));
        exam.addQuestion(new MultipleChoiceQuestion("Question 2","B"));
        Attempt attempt=exam.start(student);
        System.out.println("Examination '"+exam.getTitle()+"' started by "+student.getName()+".");
        System.out.println(attempt.answer(1,"A"));
        System.out.println(attempt.answer(2,"C"));
        System.out.println(attempt.submit());
        System.out.println(attempt.result());
    }
}
class Student{
    private final String name;
    public Student(String name){this.name=name;}
    public String getName(){return name;}
}
interface Question{
    boolean evaluate(String answer);
}
class MultipleChoiceQuestion implements Question{
    private final String text;
    private final String correctAnswer;
    public MultipleChoiceQuestion(String text,String correctAnswer){
        this.text=text;
        this.correctAnswer=correctAnswer;
    }
    public boolean evaluate(String answer){return correctAnswer.equalsIgnoreCase(answer);}
}
class TrueFalseQuestion implements Question{
    private final boolean correctAnswer;
    public TrueFalseQuestion(boolean correctAnswer){this.correctAnswer=correctAnswer;}
    public boolean evaluate(String answer){return Boolean.parseBoolean(answer)==correctAnswer;}
}
class Examination{
    private final String title;
    private final List<Question> questions=new ArrayList<>();
    private final Set<Student> submittedStudents=new HashSet<>();
    public Examination(String title){this.title=title;}
    public String getTitle(){return title;}
    public void addQuestion(Question question){questions.add(question);}
    public Attempt start(Student student){
        if(submittedStudents.contains(student))throw new IllegalStateException("Student already submitted an attempt.");
        return new Attempt(this,student);
    }
    public int evaluate(Map<Integer,String> answers){
        int correct=0;
        for(int i=0;i<questions.size();i++){
            String answer=answers.get(i+1);
            if(answer!=null&&questions.get(i).evaluate(answer))correct++;
        }
        return correct;
    }
    public void markSubmitted(Student student){submittedStudents.add(student);}
    public int size(){return questions.size();}
}
class Attempt{
    private final Examination examination;
    private final Student student;
    private final Map<Integer,String> answers=new LinkedHashMap<>();
    private boolean submitted;
    private int correct;
    public Attempt(Examination examination,Student student){
        this.examination=examination;
        this.student=student;
    }
    public String answer(int questionNumber,String answer){
        if(submitted)return "Cannot change answer after submission.";
        answers.put(questionNumber,answer);
        return "Question "+questionNumber+" answered with '"+answer+"'.";
    }
    public String submit(){
        if(submitted)return "Examination '"+examination.getTitle()+"' has already been submitted.";
        correct=examination.evaluate(answers);
        submitted=true;
        examination.markSubmitted(student);
        return "Examination '"+examination.getTitle()+"' submitted successfully.";
    }
    public String result(){
        return "Result for '"+examination.getTitle()+"' attempt: "+correct+"/"+examination.size()+" correct.";
    }
}