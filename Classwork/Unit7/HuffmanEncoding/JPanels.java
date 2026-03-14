package Classwork.Unit7.HuffmanEncoding;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.*;

public class JPanels {
    // static

    public static String openFile() {
        JFileChooser chooser = new JFileChooser(new File(
                "C:\\Users\\bondu\\Coding\\ghs-data-structures-and-algorithms-class-main\\Classwork\\Unit7\\HuffmanEncoding"));
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                String contents = Files.readString(file.toPath());
                return contents;
            } catch (IOException e) {
            }
        }

        return null;
    }
}
