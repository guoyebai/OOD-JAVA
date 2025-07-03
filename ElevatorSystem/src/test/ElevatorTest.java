package elevatorsystem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElevatorTest {
    private Elevator elevator;

    @BeforeEach
    public void setup() {
        elevator = new Elevator(1);
    }

    @Test
    public void testInitialState() {
        assertEquals(0, elevator.getCurrentFloor());
        assertEquals(ElevatorState.IDLE, elevator.getState());
        assertEquals(Direction.IDLE, elevator.getDirection());
    }
}
