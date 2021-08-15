import java.util.ArrayList;

public class Directory {
    //create utility methods to create a directory (n-ary tree) and implement the find method here

    public ArrayList<File> postOrderSearch(File root) {
        ArrayList<File> matchingFiles = new ArrayList<File>();
        if(root == null){
            return matchingFiles;
        }
        for(File f : root.folder){
            postOrderSearch(f);
        }
        matchingFiles.add(root);
        return matchingFiles;
    }
}
