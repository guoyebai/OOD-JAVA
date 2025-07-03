package elevatorsystem;

import java.util.TreeSet;

public class Elevator {
    private int id;
    private int currentFloor;
    private ElevatorState state;
    private  Direction direction;
    private TreeSet<Integer> upStops;
    private TreeSet<Integer> downStops;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.direction = Direction.IDLE;
        this.upStops = new TreeSet<>();
        this.downStops = new TreeSet<>((a, b) -> b - a); // descending order
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void requestFloor(int floor) {
        if (floor > currentFloor) {
            upStops.add(floor);
        } else if (floor < currentFloor) {
            downStops.add(floor);
        }
    }

    public void step() {
        if (direction == Direction.UP) {
            currentFloor++;
            if (upStops.contains(currentFloor)) {
                upStops.remove(currentFloor);
                System.out.println("[Elevator " + id + "] Opening dorrs at floor " + currentFloor);
                state = ElevatorState.DOORS_OPEN;
            }
        } else if (direction == Direction.DOWN) {
            currentFloor--;
            if (downStops.contains(currentFloor)) {
                downStops.remove(currentFloor);
                System.out.println("[Elevator " + id + "] Opening doors at floor " + currentFloor);
                state = ElevatorState.DOORS_OPEN;
            }
        }

        updateDirection();
    }
    private void updateDirection() {
        if (!upStops.isEmpty()) {
            direction = Direction.UP;
            state = ElevatorState.MOVING;
        } else if (!downStops.isEmpty()) {
            direction = Direction.DOWN;
            state = ElevatorState.MOVING;
        } else {
            direction = Direction.IDLE;
            state = ElevatorState.IDLE;
        }
    }

    public ElevatorState getState() {
        return state;
    }

    public Direction getDirection() {
        return direction;
    }
}

