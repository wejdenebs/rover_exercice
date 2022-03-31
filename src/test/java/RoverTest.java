import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class RoverTest {

    private Plateau plateau = new Plateau(5, 5);

    @Test
    public void moving_rover_one_should_succeed() {
        Rover rover = new Rover("Opportunity");
        rover.dropRover(plateau, 1, 2, 'N');

        //rover.processInstructions("LMLMLMLMM");

        rover.processInstructions(convertToArray("LMLMLMLMM"));

        String report = rover.reportPosition();
        assertEquals("3 3 E", report);
    }

    private static Instruction[] convertToArray(String instructions) {
        return new Instructions(instructions).getInstructions();
    }



}
