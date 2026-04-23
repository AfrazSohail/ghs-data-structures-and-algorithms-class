package HashiPuzzle;

import javax.swing.SwingUtilities;

public class Driver {

    public static void main(String[] args) {
        String text = FileReader.openFile();
        if (text == null) {
            return;
        }

        Map map = new Map(text);
        boolean isSolution = Hashier.isSolution(map);
        System.out.println(isSolution);
        SwingUtilities.invokeLater(() -> new PuzzleFrame(map, isSolution));
    }
}
