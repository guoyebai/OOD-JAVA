package elevatorsystem;

public class Main {
    public static void main(String[] args) {
        ElevatorSystem system = new ElevatorSystem(2);

        // Simulate requests
        system.handleExternalRequest(3, Direction.UP);
        system.handleExternalRequest(1, Direction.DOWN);
        system.handleInternalRequest(0, 5);

        // Simulate time steps
        for (int i = 0; i < 10; i++) {
            System.out.println("== Step " + i + " ==");
            system.stepAll();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

