import java.util.ArrayList;
import java.util.Random;

public class File {
    String name;
    String extension;
    int size;
    ArrayList<File> folder;
    final static String[] ACCEPTABLE_FILE_FORMATS = { "png", "jpg", "xml", "txt" };

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

    /**
     * Override default toString for testing, this will allow us to easily print the input tree nodes and output file array
     * @return - returns string version of File objects, including the special case folder
     */
    @Override
    public String toString(){
        //the case of a folder
        if(this.folder != null){
            return "Type: Folder, Name: " + this.name + ", Items in folder: " + this.folder.size();
        }
        else{
            return "Type: File, Name: " + this.name + ", Extension: " + this.extension + ", Size (MB): " + this.size;
        }
    }

    /**
     * @param root - pass the top node of the directory to do the DFS beneath, I have chosen post order
     * @param extensionType - file extension type that you are searching for
     * @param maxSize - maximum allowed size of the file
     * @return returns an arraylist of Files that can be printed to the user with after overriding toString
     * TODO: I would like to refactor this to the Directory class in order to parameterize and optimize search given a known directory structure
     */
    public ArrayList<File> postOrderSearch(File root, String extensionType, int maxSize) {
        ArrayList<File> matchingFiles = new ArrayList<>();
        if(root == null){
            return matchingFiles;
        }
        for(File f : root.folder){
            postOrderSearch(f, extensionType, maxSize);
        }
        //only add files to our list if they match the search criteria
        if(root.extension.equals(extensionType) && root.size < maxSize) {
            matchingFiles.add(root);
        }
        return matchingFiles;
    }

    /**
     * helper function for generating a random file extension when creating new files
     * static because we may want to use this function without an object instance
     * @return - returns a string file extension from the array of supported file types
     */
    public static String getRandomExtension(){
        int randomIndex = (int)(Math.random() * ACCEPTABLE_FILE_FORMATS.length);
        return ACCEPTABLE_FILE_FORMATS[randomIndex];
    }

    /**
     * helper function for generating test trees, provides a random file size when constructing new Files
     * static because we may want to use this function without an object instance
     * @return - returns a normally distributed file size, distributed about 5 with a variance of 1
     */
    public static int getRandomSize(){
        Random r = new Random();
        /* default mean is 1 so by adding 4, we shift the new mean of the distribution to 5, SD=Variance=1
        most values will be between 2 and 8 (+3 SD, -3 SD)
        */
        return (int)Math.round(r.nextGaussian()+4);
    }
}
