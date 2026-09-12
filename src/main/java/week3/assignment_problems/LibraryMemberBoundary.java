import java.util.*;
import java.lang.*;

public class LibraryMemberBoundary {
    static class BrokenLibraryMember {
        // name must not be static because each member has a different name.
        static String name;
        // memberId must not be static because each member needs a unique ID.
        static String memberId;
        // booksIssued must not be static because it is different for every member.
        static int booksIssued;

        BrokenLibraryMember(String name, String memberId, int booksIssued) {
            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }

        String getName() {
            return name;
        }
    }

    static class LibraryMember {
        private String name;
        private String memberId;
        private int booksIssued;
        static String libraryName = "Central Library";
        static int memberCount = 1000;

        LibraryMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
            memberCount++;
            this.memberId = "LM-" + memberCount;
        }

        void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        static void printTotalMembers() {
            System.out.println("Total members: " + (memberCount - 1000));
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenLibraryMember broken1 = new BrokenLibraryMember("Aditi", "LM-1001", 2);
        BrokenLibraryMember broken2 = new BrokenLibraryMember("Rohan", "LM-1002", 3);
        System.out.println(broken1.getName());
        System.out.println(broken2.getName());
        System.out.println("Aditi's data was overwritten because the fields are shared.\n");

        System.out.println("Fixed version:");
        LibraryMember member1 = new LibraryMember("Aditi", 2);
        LibraryMember member2 = new LibraryMember("Rohan", 3);
        member1.printMemberCard();
        member2.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}
