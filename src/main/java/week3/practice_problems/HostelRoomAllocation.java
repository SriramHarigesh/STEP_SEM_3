

public class HostelRoomAllocation {
    static class HostelRoom {
        private String roomNo;
        private int beds;
        private int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        void allot(String name) {
            if (occupied < beds) {
                occupied++;
                System.out.println(name + " allotted to room " + roomNo);
            }
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) {
            return null;
        }
        for (HostelRoom room : rooms) {
            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }
        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);
        if (room == null) {
            System.out.println("No rooms available for " + studentName);
            return;
        }
        room.allot(studentName);
    }

    public static void main(String[] args) {
        HostelRoom[] rooms = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };

        // The array reference is copied into the method, but its elements still refer to the same HostelRoom objects.
        safeAllot(rooms, "Divya");
        safeAllot(rooms, "Divya");
    }
}
