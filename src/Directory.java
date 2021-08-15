import java.util.ArrayList;
import java.util.Random;

public class Directory {
    // the following variables will be used to generate example directory trees for testing our find api
    int maxFilesPerNode;
    int treeDepth;
    /* we want static final to ensure that these extension options are immutable across directory instances and to save memory
    by reusing the same reference
    */
    final static String[] ACCEPTABLE_FILE_FORMATS = { "png", "jpg", "xml", "txt" };
    File directory;

    Directory(int maxFilesPerNode, int treeDepth){
        //TODO: I may need a random object here with a seed, but it should be apparent after a few tests
        int randomNumberOfNodes = (int)(Math.random()*maxFilesPerNode); //generate a number between 0 and maxFilesPerNode
        int randomNumberOfFolders = (int)(Math.random()*randomNumberOfNodes); //generate a number between 0 and randomNumberOfNodes
        File root = new File("root directory", new ArrayList<File>()); //generate root node
        this.directory = buildDirectory(randomNumberOfNodes, randomNumberOfFolders, treeDepth, root); //generate directory
    }

    public File buildDirectory(int randomNumberOfNodes, int randomNumberOfFolders, int treeDepth, File root){
        int depthCount = 0;

        while(depthCount < treeDepth) {
            //this may be a bit more complicated, let's seek online help here
            for (int i = 0; i < randomNumberOfNodes; i++) {
                if(randomNumberOfFolders > 0){
                    //put a random folder in the current level
                    root.folder.add(new File("folder" + "_depth_" + depthCount + "_number_" + i, buildDirectory(randomNumberOfNodes, randomNumberOfFolders, treeDepth--).folder));
                }
                else{
                    //put a random file in the current level
                    root.folder.add(new File("file" + "_depth_" + depthCount + "_number_" + i, getRandomExtension(), getRandomSize()));
                }
            }
        }

    }

    /**
     * helper function for generating a random file extension when creating new files
     * @return - returns a string file extension from the array of supported file types
     */
    public String getRandomExtension(){
        int randomIndex = (int)(Math.random() * ACCEPTABLE_FILE_FORMATS.length);
        return ACCEPTABLE_FILE_FORMATS[randomIndex];
    }

    /**
     * helper function for generating test trees, provides a random file size when constructing new Files
     * @return - returns a normally distributed file size, distributed about 5 with a variance of 1
     */
    public int getRandomSize(){
        Random r = new Random();
        /* default mean is 1 so by adding 4, we shift the new mean of the distribution to 5, SD=Variance=1
        most values will be between 2 and 8
        */
        return (int)Math.round(r.nextGaussian()+4);
    }

    /**
     * @param root - pass the top node of the directory to do the DFS beneath, I have chosen post order
     * @param extensionType - file extension type that you are searching for
     * @param maxSize - maximum allowed size of the file
     * @return returns an arraylist of Files that can be printed to the user with after overriding toString
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
}
