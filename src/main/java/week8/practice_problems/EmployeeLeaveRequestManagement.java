import java.time.*;
import java.util.*;
public class EmployeeLeaveRequestManagement{
    public static void main(String[] args){
        Employee john=new FullTimeEmployee("John Doe");
        Employee jane=new PartTimeEmployee("Jane Smith");
        LeaveManager manager=new LeaveManager();
        LeaveRequest r1=manager.submit(john,LocalDate.of(2024,10,10),LocalDate.of(2024,10,12));
        System.out.println(r1.getMessage());
        System.out.println(manager.review(r1,true));
        LeaveRequest r2=manager.submit(jane,LocalDate.of(2024,11,1),LocalDate.of(2024,11,5));
        System.out.println(r2.getMessage());
        System.out.println(manager.changeToPending(r1));
    }
}
abstract class Employee{
    private final String name;
    public Employee(String name){this.name=name;}
    public String getName(){return name;}
    public abstract int getLeaveLimit();
}
class FullTimeEmployee extends Employee{
    public FullTimeEmployee(String name){super(name);}
    public int getLeaveLimit(){return 30;}
}
class PartTimeEmployee extends Employee{
    public PartTimeEmployee(String name){super(name);}
    public int getLeaveLimit(){return 15;}
}
class ContractEmployee extends Employee{
    public ContractEmployee(String name){super(name);}
    public int getLeaveLimit(){return 10;}
}
enum LeaveStatus{PENDING,APPROVED,REJECTED}
class LeaveRequest{
    private final Employee employee;
    private final LocalDate start;
    private final LocalDate end;
    private LeaveStatus status=LeaveStatus.PENDING;
    public LeaveRequest(Employee employee,LocalDate start,LocalDate end){
        this.employee=employee;
        this.start=start;
        this.end=end;
    }
    public String getMessage(){
        return "Leave request submitted by "+employee.getName()+" for "+start+" to "+end+". Status: "+status+".";
    }
    public String review(boolean approve){
        if(status!=LeaveStatus.PENDING)return "Cannot review: Request is already "+status+".";
        status=approve?LeaveStatus.APPROVED:LeaveStatus.REJECTED;
        return "Leave request for "+employee.getName()+" "+status.toString().toLowerCase()+". Status: "+status+".";
    }
    public LeaveStatus getStatus(){return status;}
    public String getEmployeeName(){return employee.getName();}
}
class LeaveManager{
    public LeaveRequest submit(Employee employee,LocalDate start,LocalDate end){
        if(!start.isBefore(end))throw new IllegalArgumentException("Invalid dates.");
        return new LeaveRequest(employee,start,end);
    }
    public String review(LeaveRequest request,boolean approve){return request.review(approve);}
    public String changeToPending(LeaveRequest request){
        if(request.getStatus()!=LeaveStatus.PENDING)return "Cannot change status: "+request.getStatus()+" request cannot revert to Pending.";
        return "Status is already Pending.";
    }
}