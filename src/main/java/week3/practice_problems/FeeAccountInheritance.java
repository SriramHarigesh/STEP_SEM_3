
import java.util.*;
import java.lang.*;
public class FeeAccountInheritance {
    static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
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
        HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        void payInTwoInstallments(double amount) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class ScholarshipFeeAccount extends FeeAccount {
        private double scholarshipPercent;

        ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
            super(regNo, totalFee, amountPaid);
            this.scholarshipPercent = Math.max(0, Math.min(100, scholarshipPercent));
        }

        double effectiveDue() {
            return getDue() * (1 - scholarshipPercent / 100);
        }
    }

    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA001", 150000, 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("RA002", 200000, 0);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("RA003", 180000, 0, 20);
        hostel.payInTwoInstallments(60000);

        FeeAccount[] accounts = {plain, hostel, scholarship};
        for (FeeAccount account : accounts) {
            if (account instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount scholarshipAccount = (ScholarshipFeeAccount) account;
                System.out.println("Scholarship account effective due: Rs " + scholarshipAccount.effectiveDue());
            } else if (account instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + account.getDue());
            } else {
                System.out.println("Plain account due: Rs " + account.getDue());
            }
        }
    }
}
