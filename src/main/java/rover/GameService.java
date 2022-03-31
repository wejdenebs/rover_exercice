package rover;

import java.util.ArrayList;

public class GameService {

    private String movements;
    public GameService(String movements) {
        this.movements = movements;
    }

    public Mouvement[] getMouvements() throws UnknownOrientationException {
        ArrayList<Mouvement> result = new ArrayList<Mouvement>();

        for (char c: movements.toCharArray()) {
            switch (c) {
                case 'L': result.add(Mouvement.LEFT); break;
                case 'R': result.add(Mouvement.RIGHT); break;
                case 'M': result.add(Mouvement.MOVE); break;

                default: throw new UnknownOrientationException(c);
            }
        }

        return result.toArray(new Mouvement[result.size()]);
    }
}
