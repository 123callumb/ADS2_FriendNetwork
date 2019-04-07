/**
 *
 * @author callumbeckwith
 * 
 * This class is a list of all the users. You can get users from an id. The user class acts as a node in the list.
 * I still don't know if a list is the best data structure for this as going through a load of users will take a
 * while if their id is fairly high. 
 */
public class UserList {
    
    final private UserNode headNode;
    final private UserNode tailNode;
    private int userCount = 0;
    
    public UserList(){
        this.headNode = new UserNode(null);
        this.tailNode = new UserNode(null);
        this.headNode.setNext(tailNode);
        this.tailNode.setPrev(headNode);
    }
    
    public UserNode getUser(int userID){
        // Use a while loop here incase the userID is not in a chronological order.
        UserNode current = this.headNode;
        boolean found = false;
        while(current.hasNext() && !found){
            current = current.getNext();
            if(current.getData().getID() == userID){
                found = true;
            }
        }
        return found ? current : null;
    }
    
    public int getUserIDByName(String username) throws NullPointerException { // Is this fine say the method throws this exception?
        UserNode current = this.headNode;
        boolean found = false;
        while(current.hasNext() && !found){
            current = current.getNext();
            if(current.getData().getName().equals(username)){
                found = true;
            }
        }
        return found ? current.getData().getID() : -1; // Return null int if the user doesn't exist.
    }
    
    // Push onto the list.
    public void insertUser(String name, int userID){
        if(userID > -1){
            UserData data = new UserData(name, userID);
            UserNode currentLast = this.tailNode.getPrev();
            UserNode newNode = new UserNode(data);
            currentLast.setNext(newNode);
            this.tailNode.setPrev(newNode);
            newNode.setPrev(currentLast);
            newNode.setNext(this.tailNode);
            this.userCount++;
        }else{
            System.err.print("Error: User ID cannot be less than 0");
        }
    }
    
    public String[] getUsersAsStrings(){
        String users[] = new String[this.userCount];
        UserNode current = this.headNode;
        int i = 0;
        while(current.hasNext()){
            current = current.getNext();
            users[i++] = current.getData().getName();
        }
        
        return users;
    }
    
    public int getUserCount(){
        return this.userCount;
    }
    
}
