import java.util.*;
import java.lang.*;

public class HrParkingMiniSystem {
    static class Employee {
        private double salary;

        Employee(double salary) {
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {
        private double teamBonus;

        ManagerEmployee(double salary, double teamBonus) {
            super(salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class ParkingSlot {
        private String slotNo;
        private int capacity;
        private int occupiedCount;

        ParkingSlot(String slotNo, int capacity) {
            this.slotNo = slotNo;
            this.capacity = capacity;
        }

        boolean allot() {
            if (occupiedCount >= capacity) {
                return false;
            }
            occupiedCount++;
            return true;
        }
    }

    static class CompanyEmployeeRecord {
        private String name;
        private String empId;
        private Employee employee;
        private ParkingSlot slot;
        static int totalRecords;

        CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
            this.name = name;
            this.empId = empId;
            this.employee = employee;
            this.slot = slot;
            totalRecords++;
        }

        String fullProfile() {
            double pay;
            if (employee instanceof ManagerEmployee) {
                pay = ((ManagerEmployee) employee).effectiveSalary();
            } else {
                pay = employee.getSalary();
            }
            String slotStatus = slot == null ? "no parking assigned" : slot.slotNo;
            return name + " | Pay: Rs " + pay + " | Slot: " + slotStatus;
        }
    }

    public static void main(String[] args) {
        ParkingSlot slot1 = new ParkingSlot("A1", 1);
        ParkingSlot slot2 = new ParkingSlot("A2", 1);
        slot1.allot();
        slot2.allot();

        CompanyEmployeeRecord[] records = {
            new CompanyEmployeeRecord("Divya", "E001", new ManagerEmployee(70000, 8000), slot1),
            new CompanyEmployeeRecord("Karan", "E002", new Employee(40000), slot2),
            new CompanyEmployeeRecord("Meera", "E003", new Employee(10000), null)
        };

        for (CompanyEmployeeRecord record : records) {
            System.out.println(record.fullProfile());
        }
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
