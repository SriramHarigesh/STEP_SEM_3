import java.util.*;
public class CustomerRegistry {
    static class Customer {
        int id;
        String name;
        Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Customer c = (Customer) o;
            return id == c.id && name.equals(c.name);
        }
        public int hashCode() {
            return Objects.hash(id, name);
        }
        public String toString() {
            return "(" + id + ", " + name + ")";
        }
    }
    static Set<Customer> registry = new HashSet<>();
    static void add(Customer c, String branch) {
        if (registry.add(c)) {
            System.out.println("true");
        } else {
            System.out.println("false (duplicate rejected)");
        }
    }
    public static void main(String[] args) {
        add(new Customer(101, "Asha"), "Branch A");
        add(new Customer(101, "Asha"), "Branch B");
        add(new Customer(102, "Ravi"), "Branch A");
        System.out.println("unique count " + registry.size());
        System.out.println("contains: " + registry.contains(new Customer(101, "Asha")));
    }
}
