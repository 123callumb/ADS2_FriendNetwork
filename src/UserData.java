/**
 *
 * @author callumbeckwith
 * 
 * This class is for the user data such as their current friends. This is useful for when we log the user in and 
 * require their data.
 * We can also do calculations inside this class such as a heap sort for finding their closest friends and their
 * closest mutual friends.
 */
public class UserData {
    
    final private String username; 
    final private int userID;

    // We need the username and id to even start it. 
    public UserData(String name, int userID){
        this.username = name;
        this.userID = userID;
    }
    
    public String getName(){
        return this.username;
    }
    
    //  Don't really know if this is needed.
    public int getID(){
        return this.userID;
    }
    
}

