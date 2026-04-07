package HashiPuzzle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;

public class FileReader {
     public static String openFile() {
        String currentFolder = java.nio.file.Paths.get("").toAbsolutePath().toString();
        JFileChooser chooser = new JFileChooser(currentFolder);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                return Files.readString(file.toPath()).strip();
            } catch (IOException ignored) {
            }
        }
        return null;
    }
}
