package rover;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter a path:");
            String path = sc.nextLine();
            FileInput ri = new FileInput(path);
            List<String> roverData = ri.openFile();


        }



}
