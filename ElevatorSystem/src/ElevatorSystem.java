import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private List<Elevator> elevators;

    public ElevatorSystem(int numberOfElevators) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }
    }

    public void handleExternalRequest(int floor, Direction direction) {
        Elevator best = findBestElevator(floor, direction);
        System.out.println("Assigning floor " + floor + " " + direction + " to Elevator " + best.getId());
        best.requestFloor(floor);
    }

    public void handleInternalRequest(int elevatorId, int floor) {
        elevators.get(elevatorId).requestFloor(floor);
    }

    public void stepAll() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    private Elevator findBestElevator(int floor, Direction direction) {
        for (Elevator e : elevators) {
            if (e.getState() == ElevatorState.IDLE) {
                return e;
            }
        }
        return elevators.get(0);
    }
}

