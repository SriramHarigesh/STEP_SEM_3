import java.util.*;
import java.lang.*;

public class AttendanceSystem {
    static class SrmStudent {
        private String name;
        private String regNo;
        private int attendance;

        SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        void addAttendanceUpdate(int newAttendance) {
            if (newAttendance < 0 || newAttendance > 100) {
                System.out.println("Attendance must be between 0 and 100.");
                return;
            }
            attendance = newAttendance;
        }

        boolean isEligible() {
            return attendance >= 75;
        }

        static double classAverage(SrmStudent[] students) {
            if (students == null || students.length == 0) {
                return 0;
            }
            double total = 0;
            for (SrmStudent student : students) {
                total += student.attendance;
            }
            return total / students.length;
        }

        String getName() {
            return name;
        }

        int getAttendance() {
            return attendance;
        }
    }

    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA001", 82),
            new SrmStudent("Anitha", "RA002", 68),
            new SrmStudent("Karthik", "RA003", 91),
            new SrmStudent("Meera", "RA004", 74),
            new SrmStudent("Suresh", "RA005", 60)
        };

        for (SrmStudent student : students) {
            String status = student.isEligible() ? "Eligible" : "Detained";
            System.out.println(student.getName() + " - " + student.getAttendance() + "% - " + status);
        }
        System.out.printf("Class average: %.1f%%%n", SrmStudent.classAverage(students));
    }
}
