import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File instance = new File();

        ArrayList<File> topLevelContent = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            topLevelContent.add(new File("example_"+i, File.getRandomExtension(), File.getRandomSize()));
        }

        File root = new File("root", topLevelContent);
        System.out.println(root);

        System.out.println(instance.postOrderSearch(root,"XML", 5));
    }
}
