import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<File> topLevelContent = new ArrayList<File>();
        for(int i = 0; i < 10; i++){
            topLevelContent.add(new File("example_"+i, File.getRandomExtension(), File.getRandomSize()));
        }

        File root = new File("root", topLevelContent);
        System.out.println(root);

        System.out.println(root.postOrderSearch(root,"XML", 5));
    }
}
