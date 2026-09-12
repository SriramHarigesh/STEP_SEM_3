import java.util.*;
import java.lang.*;

public class ParkingSlotAllocation {
    static class ParkingSlot {
        private String slotNo;
        private int capacity;
        private int occupiedCount;

        ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        void allot(String vehicleNo) {
            if (occupiedCount < capacity) {
                occupiedCount++;
                System.out.println(vehicleNo + " allotted to slot " + slotNo);
            }
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        if (slots == null) {
            return null;
        }
        for (ParkingSlot slot : slots) {
            if (slot != null && slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }
        return null;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);
        if (slot == null) {
            System.out.println("No slots available for " + vehicleNo);
            return;
        }
        slot.allot(vehicleNo);
    }

    public static void main(String[] args) {
        ParkingSlot[] slots = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };

        // Only the array reference is copied; its entries still point to the original ParkingSlot objects.
        safeAllot(slots, "TN09AB1234");
        safeAllot(slots, "TN09AB1234");
    }
}
