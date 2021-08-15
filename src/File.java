import java.util.LinkedList;
import java.util.List;

public class File {
    String name;
    String extension;
    int size;
    List<File> folder = new LinkedList<>();
    /**
     * File constructor, to be used when creating a node that is only a file
     * @param name  - human-readable name of the file
     * @param extension - file extension type such as .jpg, .xml, etc.
     * @param size - size in MB of the file
     */
    File(String name, String extension, int size){
        this.name = name;
        this.extension = extension;
        this.size = size;
    }
    /**
     * Folder constructor, overloads File constructor
     * @param name - human-readable name of the folder
     * @param folder - list containing other folder and file nodes
     */
    File(String name, List<File> folder){
        this.name = name;
        this.folder = folder;
    }
    //some files are special and also include other files, these are folder files
}
