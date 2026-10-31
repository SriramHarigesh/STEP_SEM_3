public class LibraryPlatform {
    interface Reservable {
        void reserve(String member);
    }
    interface Downloadable {
        void download();
    }
    static abstract class Resource {
        String id;
        Resource(String id) {
            this.id = id;
        }
    }
    static class Book extends Resource implements Reservable {
        String reservedBy;
        Book(String id) {
            super(id);
        }
        public void reserve(String member) {
            reservedBy = member;
        }
    }
    static class EBook extends Resource implements Downloadable {
        int downloads;
        EBook(String id) {
            super(id);
        }
        public void download() {
            downloads++;
        }
    }
    static Resource[] resources = new Resource[100];
    static int size = 0;
    static int find(String id) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = resources[mid].id.compareTo(id);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    static void add(Resource r) {
        if (find(r.id) != -1) {
            System.out.println("duplicate rejected");
            return;
        }
        int i = size - 1;
        while (i >= 0 && resources[i].id.compareTo(r.id) > 0) {
            resources[i + 1] = resources[i];
            i--;
        }
        resources[i + 1] = r;
        size++;
        System.out.println(r.id + " added");
    }
    static void process(String command) {
        String[] p = command.split(" ");
        if (p[0].equals("ADD")) {
            if (p[1].equals("Book")) {
                add(new Book(p[2]));
            } else {
                add(new EBook(p[2]));
            }
        } else if (p[0].equals("RESERVE")) {
            int idx = find(p[1]);
            if (idx == -1) {
                System.out.println(p[1] + " not found");
            } else if (resources[idx] instanceof Reservable) {
                ((Reservable) resources[idx]).reserve(p[3]);
                System.out.println(p[1] + " reserved for " + p[3]);
            } else {
                System.out.println(p[1] + " rejected: reserve unsupported");
            }
        } else if (p[0].equals("DOWNLOAD")) {
            int idx = find(p[1]);
            if (idx == -1) {
                System.out.println(p[1] + " not found");
            } else if (resources[idx] instanceof Downloadable) {
                ((Downloadable) resources[idx]).download();
                System.out.println(p[1] + " downloaded");
            } else {
                System.out.println(p[1] + " rejected: download unsupported");
            }
        } else if (p[0].equals("FIND")) {
            int idx = find(p[1]);
            if (idx == -1) {
                System.out.println(p[1] + " not found");
            } else {
                System.out.println(p[1] + " found at index " + idx);
            }
        }
    }
    public static void main(String[] args) {
        String[] commands = {"ADD Book B1", "ADD Book B1", "ADD EBook E1", "RESERVE B1 by M1", "DOWNLOAD B1", "DOWNLOAD E1", "FIND E1"};
        for (String c : commands) {
            process(c);
        }
    }
}
