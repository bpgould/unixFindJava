import java.util.ArrayList;
import java.util.Random;

//TODO: restructure inheritance to inherit helper functions from File such as getRandomSize() and getRandomExtension()
public class Directory {
    // the following variables will be used to generate example directory trees for testing our find api
    int maxFilesPerNode;
    int treeDepth;
    /* we want static final to ensure that these extension options are immutable across directory instances and to save memory
    by reusing the same reference
    */
    final static String[] ACCEPTABLE_FILE_FORMATS = { "png", "jpg", "xml", "txt" };
    File directory;

    /**
     * empty constructor
     */
    Directory(){}

    /**
     * Directory object constructor, supposed to make it easier for testing by requiring only 2 dimensions for input and generating a
     * working directory example to test with
     * @param maxFilesPerNode - max tree width, a binary tree would be 2
     * @param treeDepth - max tree depth
     */
    Directory(int maxFilesPerNode, int treeDepth){
        //TODO: I may need a random object here with a seed, but it should be apparent after a few tests
        int randomNumberOfNodes = (int)(Math.random()*maxFilesPerNode); //generate a number between 0 and maxFilesPerNode
        int randomNumberOfFolders = (int)(Math.random()*randomNumberOfNodes); //generate a number between 0 and randomNumberOfNodes
        File root = new File("root directory", new ArrayList<>()); //generate root node
        this.directory = buildDirectory(randomNumberOfNodes, randomNumberOfFolders, treeDepth, root); //generate directory
    }

    /**
     * builds a pseudo random n-ary tree for testing given some user input dimensions
     * @param randomNumberOfNodes - the maximum number of nodes per level
     * @param randomNumberOfFolders - the maximum number of folders per level, guaranteed to be <= randomNumberOfNodes
     * @param treeDepth - the maximum depth of the tree
     * @param root - the top level folder that the directory will be built under
     * @return - returns the build directory that should be an n-ary tree with, likely with files and folders
     * TODO: this has not been tested, but presents a non-trivial problem on its own: create pseudo random n-ary trees
     */
    public File buildDirectory(int randomNumberOfNodes, int randomNumberOfFolders, int treeDepth, File root){
        while(treeDepth > 0) {
            for (int i = 0; i < randomNumberOfNodes; i++) {
                if(randomNumberOfFolders > 0){
                    //put a random folder in the current level
                    root.folder.add(new File("folder" + "_depth_" + treeDepth + "_number_" + i, buildDirectory(randomNumberOfNodes, randomNumberOfFolders, treeDepth--, root).folder));
                }
                else{
                    //put a random file in the current level
                    root.folder.add(new File("file" + "_depth_" + treeDepth + "_number_" + i, getRandomExtension(), getRandomSize()));
                }
            }
        }
        return root;
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
