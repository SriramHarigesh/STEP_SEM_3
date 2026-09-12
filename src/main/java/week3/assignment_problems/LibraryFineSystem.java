import java.util.*;
import java.lang.*;

public class LibraryFineSystem {
    static class BookIssue {
        private String title;
        private String borrowerName;
        private int daysOverdue;

        BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = daysOverdue;
        }

        double fineAmount() {
            return daysOverdue > 0 ? daysOverdue * 5.0 : 0;
        }

        boolean isSeverelyOverdue() {
            return daysOverdue > 14;
        }

        static double totalFineCollected(BookIssue[] issues) {
            double total = 0;
            if (issues != null) {
                for (BookIssue issue : issues) {
                    if (issue != null) {
                        total += issue.fineAmount();
                    }
                }
            }
            return total;
        }
    }

    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Aditi", 18),
            new BookIssue("Effective Java", "Rohan", 5),
            new BookIssue("Refactoring", "Meera", 0),
            new BookIssue("DSA Handbook", "Karthik", 21),
            new BookIssue("Design Patterns", "Divya", 9)
        };

        for (BookIssue issue : issues) {
            String status = issue.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(issue.title + " - " + issue.daysOverdue + " days - " + status);
        }

        // totalFineCollected is static because it calculates across many issues; fineAmount belongs to one BookIssue object.
        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}
