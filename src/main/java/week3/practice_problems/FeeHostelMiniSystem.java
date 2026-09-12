package week1.practice_problems;

public class FeeHostelMiniSystem {
    static class FeeAccount {
        private double totalFee;
        private double amountPaid;

        FeeAccount(double totalFee) {
            this.totalFee = totalFee;
        }

        void pay(double amount) {
            if (amount <= 0) {
                System.out.println("Payment rejected: amount must be positive.");
                return;
            }
            amountPaid += amount;
        }

        double getDue() {
            return Math.max(0, totalFee - amountPaid);
        }
    }

    static class HostelFeeAccount extends FeeAccount {
        HostelFeeAccount(double totalFee) {
            super(totalFee);
        }

        void payInTwoInstallments(double amount) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class HostelRoom {
        private String roomNo;
        private int beds;
        private int occupied;

        HostelRoom(String roomNo, int beds) {
            this.roomNo = roomNo;
            this.beds = beds;
        }

        boolean allot() {
            if (occupied >= beds) {
                return false;
            }
            occupied++;
            return true;
        }
    }

    static class SrmStudent {
        private String name;
        private String regNo;
        private HostelFeeAccount feeAccount;
        private HostelRoom room;
        static int totalStudents;

        SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = room;
            totalStudents++;
        }

        String fullStatus() {
            String roomStatus = room == null ? "unallotted" : room.roomNo;
            return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomStatus;
        }
    }

    public static void main(String[] args) {
        HostelRoom room1 = new HostelRoom("C-214", 1);
        HostelRoom room2 = new HostelRoom("C-507", 1);
        room1.allot();
        room2.allot();

        HostelFeeAccount raviFee = new HostelFeeAccount(200000);
        HostelFeeAccount anithaFee = new HostelFeeAccount(180000);
        HostelFeeAccount karthikFee = new HostelFeeAccount(200000);
        raviFee.payInTwoInstallments(60000);
        karthikFee.pay(-5000);

        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA001", raviFee, room1),
            new SrmStudent("Anitha", "RA002", anithaFee, room2),
            new SrmStudent("Karthik", "RA003", karthikFee, null)
        };

        for (SrmStudent student : students) {
            System.out.println(student.fullStatus());
        }
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
