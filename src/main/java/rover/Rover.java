package rover;
public class Rover {

    private Plateau plateau;
    private Position position;
    private Cardinal cardinal;
    private String name;

    public Rover(String name) {
        this.name = name;
    }

    private void turnLeft() {
        switch (cardinal) {
            case EAST: cardinal = Cardinal.NORTH; break;
            case NORTH: cardinal = Cardinal.WEST; break;
            case SOUTH: cardinal = Cardinal.EAST; break;
            case WEST: cardinal = Cardinal.SOUTH; break;
            default: throw new RuntimeException("Should not get here!");
        }
    }

    private void turnRight() {
        switch (cardinal) {
            case EAST: cardinal = Cardinal.SOUTH; break;
            case NORTH: cardinal = Cardinal.EAST; break;
            case SOUTH: cardinal = Cardinal.WEST; break;
            case WEST: cardinal = Cardinal.NORTH; break;
            default: throw new RuntimeException("Should not get here!");
        }
    }

    private void moveForward() {
        Position newPosition = position.moveForward(cardinal);
        if (!newPosition.IsOnPlateau(plateau)) {
            throw new PositionNotOnPlateauException(plateau, newPosition);
        }
        position = newPosition;
    }

    public void dropRover(Plateau plateau, String args) {
        String[] parts = args.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Cardinal cardinal = ToCardinal(parts[2].toCharArray()[0]);
        dropRover(plateau, new Position(x, y), cardinal);
    }

    public void dropRover(Plateau plateau, Position position, Cardinal cardinal) {
        if (!position.IsOnPlateau(plateau)) {
            throw new PositionNotOnPlateauException(plateau, position);
        }

        if (plateau.isOccupied(position)) {
            throw new RuntimeException("Already occupied by a rover!");
        }

        this.plateau = plateau;
        this.position = position;
        this.cardinal = cardinal;

        plateau.addRover(this);
    }

    public void dropRover(Plateau plateau, int posX, int posY, char Cardinal) {
        dropRover(plateau, new Position(posX, posY), ToCardinal(Cardinal));
    }

    public String reportStatus() {
        StringBuilder sb = new StringBuilder(name);
        sb.append(" ");
        sb.append(reportPosition());
        return sb.toString();
    }

    public String reportPosition() {
        if (position == null || cardinal == null) {
            return "Not dropped yet.";
        }
        return position.toString() + " " + FromCardinal(cardinal);
    }

    public void processMouvements(Mouvement[] instructions) {
        for (Mouvement i : instructions) {
            processMouvement(i);
        }
    }

    public boolean hasPosition(Position pos) {
        return position.isEqual(pos);
    }

  private void processMouvement(Mouvement mouvement) {
        if (position == null || cardinal == null) {
            throw new NotDroppedException();
        }

        switch (mouvement) {
            case LEFT: turnLeft(); break;
            case MOVE: moveForward(); break;
            case RIGHT: turnRight(); break;
            default: throw new RuntimeException("Should not get here!");
        }
    }

    private static char FromCardinal(Cardinal cardinal) {
        switch (cardinal) {
            case NORTH: return 'N';
            case WEST: return 'W';
            case SOUTH: return 'S';
            case EAST: return 'E';
            default: throw new RuntimeException("Unsupported cardinal '" + cardinal + "'!");
        }
    }

    private static Cardinal ToCardinal(char cardinal) {
        switch (cardinal) {
            case 'N': return Cardinal.NORTH;
            case 'W': return Cardinal.WEST;
            case 'S': return Cardinal.SOUTH;
            case 'E': return Cardinal.EAST;
            default: throw new RuntimeException("Unsupported character '" + cardinal + "'!");
        }
    }


}
