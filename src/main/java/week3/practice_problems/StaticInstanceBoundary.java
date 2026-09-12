

public class StaticInstanceBoundary {
    static class BrokenSrmStudent {
        static String name;
        static String regNo;
        static int attendance;

        BrokenSrmStudent(String name, String regNo, int attendance) {
            BrokenSrmStudent.name = name;
            BrokenSrmStudent.regNo = regNo;
            BrokenSrmStudent.attendance = attendance;
        }

        String getName() {
            return name;
        }
    }

    static class SrmStudent {
        private String name;
        private String regNo;
        private int attendance;
        static String university = "SRM University";
        static int admissionCount = 10;

        SrmStudent(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            admissionCount++;
            this.regNo = String.format("RA231100301%03d", admissionCount);
        }

        void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + (admissionCount - 10));
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenSrmStudent broken1 = new BrokenSrmStudent("Ravi", "RA001", 82);
        BrokenSrmStudent broken2 = new BrokenSrmStudent("Meera", "RA002", 74);
        System.out.println(broken1.getName());
        System.out.println(broken2.getName());
        System.out.println("Ravi's data was overwritten because the fields are shared.\n");

        System.out.println("Fixed version:");
        SrmStudent student1 = new SrmStudent("Ravi", 82);
        SrmStudent student2 = new SrmStudent("Meera", 74);
        student1.printIdCard();
        student2.printIdCard();
        SrmStudent.printTotalAdmissions();
    }
}
