package rover;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileInput {
    private String path;

    public FileInput(String file_path) {
        path = file_path;
    }

    public List<String> openFile() {
        FileReader fr;
        try {
            fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            List<String> textData = new ArrayList<String>();
            String aLine;
            while ((aLine = br.readLine()) != null) {
                textData.add(aLine);
            }
            br.close();
            return textData;

            }
        catch (FileNotFoundException e) {
            System.out.println("Warning: File could not be found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Warning: A problem has occured during the file-reading process!");
            e.printStackTrace();
        }
        return null;
    }
}
