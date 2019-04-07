/*
    Each function here I am account for the fact the social network data is 1 index base.
*/


class ADS2Graph {
    
    final private float[][] AdjacencyMatrix;
    int userCount = 0;
    String[] usernames;
    
    public ADS2Graph(String[] users){
        this.userCount = users.length;
        this.usernames = users;
        this.AdjacencyMatrix = new float[this.userCount][this.userCount];
    }
    
    public void addEdge(int userID, int user2ID, float weight){
        this.AdjacencyMatrix[userID][user2ID] = weight;
        this.AdjacencyMatrix[user2ID][userID] = weight;
    }
  
    public boolean isAdjacent(int userID, int user2ID){
        return (this.AdjacencyMatrix[userID][user2ID] > 0);
    } 
    
    public String[] getFreinds(int userID){
        String friends[] = new String[this.userCount - 1]; // Max it can be is all the users & (-1) can't be friends with self.
        int index = 0;
        for(int i = 0; i < this.userCount; i++){
            if(this.isAdjacent(userID, i)){
                friends[index++] = this.usernames[i]; // I think this means noone cna be frirends with id of 1 so this needs looking at.
            }
        }
        
        return friends;

    }
    
    private float getWeight(int userID, int user2ID){
        return this.AdjacencyMatrix[userID][user2ID];
    }
    
    public String[] getMutualFriends(int userID){
        
        //Friend data just stores the id of the user and the strength of the connection.
        FriendData threeClosestMutual[] = new FriendData[3];

        int mutualIndex = 0;
        float currentStrength = 0;
        
        for(int userDest = 0; userDest < this.userCount; userDest++){
            if(!isAdjacent((userID), (userDest)) && userID != userDest){ // Check if not currently a friend.
                boolean visited[] = new boolean[this.userCount];
                float tentative[] = new float[this.userCount];

                for(int i = 0; i < this.userCount; i++){
                    visited[i] = false;
                    tentative[i] = Integer.MAX_VALUE;
                }
                tentative[userID] = 0;
                int currentID = userID;
                
                while(currentID != userDest && tentative[currentID] != Integer.MAX_VALUE){
                    visited[currentID] = true;
                    for(int i = 0; i < this.userCount; i++){
                        if(this.isAdjacent((currentID), (i)) && !visited[i] && tentative[i] > this.getWeight(currentID, i) + tentative[currentID]){
                            tentative[i] = this.getWeight(currentID, i) + tentative[currentID];
                        }
                    }
                    
                    int tempDest = userDest;
                    for(int i = 0; i < this.userCount; i++){
                        if(!visited[i] && (tentative[tempDest] > tentative[i])){
                            tempDest = i;
                            currentStrength = tentative[i];
                        }
                    }
                    
                    currentID = tempDest;
                }
                
                // Return the new array, if the strength is smaller than the ones that are currently in it.
                threeClosestMutual = insertIntoList(currentStrength, currentID, threeClosestMutual);
                
            }  
        }

        //Place the the current friend data object array into a string array of top 3 mutual friends.
        String threeFriends[] = new String[threeClosestMutual.length];
        
        for(int i = 0; i < threeClosestMutual.length; i++){
            threeFriends[i] = this.usernames[threeClosestMutual[i].getID()];
        }
        
        return threeFriends;
    }
    
    //This method inserts the value into the top 3 list if it is smaller than atleast
    //1 of the values inside the list. It will 
    private FriendData[] insertIntoList(float strength, int userID, FriendData[] currentList){
        FriendData currentData = new FriendData(userID, strength);
        for(int i = 2; i >= 0; i--){
            if(currentList[i] == null || currentList[i].getStrength() > currentData.getStrength()){
                FriendData temp = currentList[i];
                currentList[i] = currentData;
                if(i != 2){ //Remove from the list if its smaller than the largest number in the list.
                    currentList[i+1] = temp; 
                }
            }
        }
        
        return currentList;
    }
}
