package HashiPuzzle;

public class Driver {
    public static void main(String[] args) {
        String text = FileReader.openFile();
        System.out.println(text);
        Map map = new Map(text);
        System.out.println(map);
        System.out.println(Hashier.isSolution(map));
    }
}
