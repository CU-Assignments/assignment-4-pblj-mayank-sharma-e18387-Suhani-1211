import java.util.*;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private final boolean[] seats = new boolean[TOTAL_SEATS];

    public synchronized boolean bookSeat(int seatNumber, String customerType) {
        if (seatNumber < 0 || seatNumber >= TOTAL_SEATS) {
            System.out.println(customerType + " booking failed: Invalid seat number " + seatNumber);
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customerType + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(customerType + " booking failed: Seat " + seatNumber + " is already booked");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customerType;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customerType, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerType);
    }
}

public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        List<Thread> threads = new ArrayList<>();

        threads.add(new BookingThread(system, 2, "VIP", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(system, 2, "Regular", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(system, 5, "VIP", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(system, 5, "Regular", Thread.NORM_PRIORITY));
        threads.add(new BookingThread(system, 7, "VIP", Thread.MAX_PRIORITY));
        threads.add(new BookingThread(system, 7, "Regular", Thread.NORM_PRIORITY));

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All bookings completed.");
    }
}
