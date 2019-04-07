
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * You can also develop help functions and new classes for your system. You 
 * can change the skeleton code if you need but you do not allow to remove the
 * methods provided in this class.
 */
public class SocialNetwork {
    
    private ADS2Graph userNetwork;//You may use different container for the social network data
    final private UserList userNames;//   I replaced the ADSList with this
    private String[] userNamesAsString;
   
    public SocialNetwork(){
        this.userNames = new UserList();
    }
    
    /**
     * Loading social network data from files. 
     * The dataset contains two separate files for user names (NameList.csv) and 
     * the network distributions (SocialNetworkData.csv).
     * Use file I/O functions to load the data.You need to choose suitable data 
     * structure and algorithms for an effective loading function
     */
    public void Load() {
        
        int counter = 0;
        try(Scanner scanner = new Scanner(new File("NameList.csv"))){
            //Set scanner to seperate per line
            scanner.useDelimiter("\r\n");
            while((scanner.hasNext())){
                String name = scanner.next();
               this.userNames.insertUser(name, counter++); 
            }
        }catch(FileNotFoundException e){
            System.err.print(e);
        }
        
        this.userNamesAsString = this.userNames.getUsersAsStrings();
        this.userNetwork = new ADS2Graph(this.userNamesAsString);
        
        try(Scanner scanner = new Scanner(new File("SocialNetworkData.csv"))){
            String[] currentLine;
            scanner.useDelimiter("\r\n");
            while(scanner.hasNext()){
                currentLine = scanner.next().split(",");
                int userID = Integer.parseInt(currentLine[1]) - 1; // - 1 for 1 index 
                int friendID = Integer.parseInt(currentLine[0]) - 1;
                float strength = Float.parseFloat(currentLine[2]);
                this.userNetwork.addEdge(userID, friendID, strength);
            }
            
        }catch(FileNotFoundException e){
            System.err.print(e);
        }
        
    }
    
    /**
     * Locating a user from the network
     * @param fullName users full name as a String
     * @return return the ID based on the user list. return -1 if the user do not exist
     * based on your algorithm, you may also need to locate the reference 
     * of the node from the graph. 
     */
    public int FindUserID(String fullName){
        return this.userNames.getUserIDByName(fullName);
    }
    
   
    /**
     * Listing all the friends belongs to the user
     * You need to retrieval all the directly linked nodes and return their full names.
     * Current Skeleton only have some dummy data. You need to replace the output
     * by using your own algorithms.
     * @param currentUserName use FindUserID or other help functions to locate 
     * the user in the graph first.
     * @return You need to return all the user names in a String Array directly
     * linked to the users node.
     */
    public String[] GetMyFriends(String currentUserName){
        int userID = this.userNames.getUserIDByName(currentUserName);
        String[] myFriends = this.userNetwork.getFreinds(userID);
        return myFriends;
    }
    
    /**
     * Listing the top 3 recommended friends for the user
     * In the task, you need to calculate the shortest distance between the 
     * current user and all other non-directly linked users. Pick up the top three 
     * closest candidates and return their full names.
     * Use some help functions for sorting and shortest distance algorithms 
     * @param currentUserName use FindUserID or other help functions to locate 
     * the user in the graph first.
     * @return You need to return all the user names in a String Array containing 
     * top 3 closest candidates. 
     */
    public String[] GetRecommended (String currentUserName){
        int userID = this.userNames.getUserIDByName(currentUserName);
        String[] recommended = this.userNetwork.getMutualFriends(userID);
        return recommended;
    }
    
    
    
}
