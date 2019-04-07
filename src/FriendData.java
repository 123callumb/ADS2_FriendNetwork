/**
 *
 * @author callumbeckwith
 * 
 * This is just a small class so I can do heap sorts on users and make sure the order of the 
 * mutual friend list displays with the most possible mutual friend at the top.
 */
public class FriendData {
    
    final private int userID;
    final private float strength;
    
    public FriendData(int userID, float strength){
        this.userID = userID;
        this.strength = strength;
    }
    
    public float getStrength(){
        return this.strength;
    }
    
    // Again I don't know how needed this is. But it's here :)
    public int getID(){
        return this.userID;
    }
    
}
