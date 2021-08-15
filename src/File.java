import java.util.ArrayList;

public class File {
    String name;
    String extension;
    int size;
    ArrayList<File> folder;
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
     * @param folder - arraylist containing other folder and file nodes
     */
    File(String name, ArrayList<File> folder){
        this.name = name;
        this.folder = folder;
    }
    //some files are special and also include other files, these are folder files
}
