import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //work with instances of the subclass here
        File example1 = new File("example", "png", 5);

        List<File> topLevelContent = new LinkedList<File>();

        topLevelContent.add(example1);

        File root = new File("root", topLevelContent);
    }
}
