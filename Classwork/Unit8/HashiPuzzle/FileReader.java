package HashiPuzzle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFileChooser;

/**
 * Utility class for loading puzzle files via a file chooser dialog.
 *
 * @author AfrazSohail
 */
public class FileReader {

    /**
     * Opens a file chooser dialog and returns the contents of the selected
     * file.
     *
     * <p>
     * The dialog starts in the current working directory. If the user cancels
     * or an I/O error occurs, returns {@code null}.
     *
     * @return the file contents as a string (trimmed), or {@code null} if no
     * file was selected or an error occurred
     */
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
