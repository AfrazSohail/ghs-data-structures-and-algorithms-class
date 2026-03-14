package Classwork.Unit3;

import javax.swing.JFileChooser;
import java.io.File;

public class ChooseFile {
    public static void main(String[] args) {
        JFileChooser file = new JFileChooser(new File(System.getProperty("user.dir")));

        int code = file.showOpenDialog(null);
        if (code == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            System.out.println("You selected: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No file selected.");
        }
    }
}
