/**
 *
 * @author callumbeckwith
 * 
 * This is 
 */
public class UserNode {
    
    private UserNode prev;
    private UserNode next;
    final private UserData data; // May not need to be final in future if I use update methods.
    
    public UserNode(UserData data){
       this.prev = null;
       this.next = null;
       this.data = data;
    }
    
    public UserData getData(){
        return this.data;
    }
    
    public void setNext(UserNode node){
        this.next = node;
    }
    
    public UserNode getNext(){
        return this.next;
    }
    
    public boolean hasNext(){
        return this.next.getData() != null;
    }
    
    public void setPrev(UserNode node){
        this.prev = node;
    }
    
    public UserNode getPrev(){
        return this.prev;
    }
    
    public boolean hasPrev(){
        return this.prev != null;
    }
}
