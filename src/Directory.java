import java.util.ArrayList;

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
        int randomNumberOfNodes = (int)(Math.random()*maxFilesPerNode); //generate a number between 0 and maxFilesPerNode
        int randomNumberOfFolders = (int)(Math.random()*randomNumberOfNodes); //generate a number between 0 and randomNumberOfNodes
        this.directory = buildDirectory(randomNumberOfNodes, randomNumberOfFolders, treeDepth); //generate directory
    }

    public File buildDirectory(int randomNumberOfNodes, int randomNumberOfFolders, int treeDepth){
        int depthCount = 0;
        while(depthCount < treeDepth) {
            //this may be a bit more complicated, let's seek online help here
            for (int i = 0; i < randomNumberOfNodes; i++) {
                if(randomNumberOfFolders > 0){
                    //put a random folder in the current level
                    directory.folder.add(new File("folder" + "_depth_" + depthCount + "_number_" + i, buildDirectory(randomNumberOfNodes, randomNumberOfFolders, treeDepth--).folder));
                }
                else{
                    //put a random file in the current level
                    directory.folder.add(new File("file" + "_depth_" + depthCount + "_number_" + i, getRandomExtension, getRandomSize));
                }
            }
        }

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
