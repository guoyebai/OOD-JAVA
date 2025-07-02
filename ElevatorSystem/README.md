# Elevator System Design

## Problem Statement

Design an object-oriented elevator system for a building with multiple elevators and floors. The system should handle user requests efficiently and simulate elevator behavior accurately.

---

## Requirements

### Functional Requirements
- Support multiple elevators
- Support multiple floors (e.g., 1–N)
- Handle two types of requests:
    - **External** (floor panel: UP/DOWN button)
    - **Internal** (elevator panel: go to a floor)
- Elevators can be in one of several states:
    - `IDLE`, `MOVING_UP`, `MOVING_DOWN`, `DOORS_OPEN`
- Requests should be serviced efficiently (e.g., batch same-direction requests)

### Non-Functional Requirements
- System should be modular and scalable
- Code should be maintainable and follow SOLID principles
- Fault tolerance: support maintenance mode (future extension)
- Support concurrency (multiple requests at once)

---

## Core Classes

| Class            | Responsibility                                                       |
|------------------|----------------------------------------------------------------------|
| `ElevatorSystem` | Manages all elevators and dispatches external requests               |
| `Elevator`       | Represents a single elevator, handles internal state and floor queue |
| `Request`        | Encapsulates a request: floor + direction                            |
| `Direction`      | Enum: `UP`, `DOWN`, `IDLE`                                           |
| `ElevatorState`  | Enum: `IDLE`, `MOVING`, `DOORS_OPEN`                                 |
| `FloorPanel`     | Simulates the buttons on each floor (optional extension)             |
| `ElevatorPanel`  | Simulates buttons inside the elevator (optional extension)           |

---

## Design Decisions & SOLID Principles

### ✔ Single Responsibility
- `ElevatorSystem` is responsible only for request routing.
- `Elevator` is responsible only for processing its own queue.

### ✔ Open/Closed
- Request scheduling can be modified via strategies without changing `ElevatorSystem`.

### ✔ Liskov Substitution
- We can extend `Elevator` into `ExpressElevator`, etc., without breaking existing logic.

### ✔ Interface Segregation (Optional for I/O layer)
- Separate interfaces can be used for physical buttons vs. API-driven input.

### ✔ Dependency Inversion
- If we later introduce logging or monitoring, we’ll depend on interfaces.

---

## Class Diagram (UML)

+------------------+
| ElevatorSystem |
+------------------+
| List<Elevator> |
+------------------+
| +handleExternalRequest() |
| +handleInternalRequest() |
| +stepAll() |
+--------------------------+
|
v
+----------------+
| Elevator |
+----------------+
| id |
| currentFloor |
| direction |
| state |
| upQueue |
| downQueue |
+----------------+
| +requestFloor()|
| +step() |
+----------------+

---

## Sample Workflow

1. A user presses **UP** on Floor 3.
2. `ElevatorSystem` finds the best elevator and calls `elevator.requestFloor(3)`.
3. The elevator adds Floor 3 to its internal queue.
4. Each elevator is updated via `step()` to move or open doors as needed.

---

## How to Run

Compile and run the simulation using:

```bash
cd ElevatorSystem/src
javac Main.java
java Main
