package playlistSimulator;

import bridges.base.SLelement;
import bridges.connect.Bridges;
import bridges.connect.DataSource;

import java.util.*;

 //method in order to connect to bridges
    protected void main(String args[]){
        Bridges bridges = new Bridges(3, "Sofanyas", "299816294237");
        DataSource ds = bridges.getDataSource();
        List<MySong> song = null;

    }

// A custom comparator for comparing MySong objects based on their titles.
    public class Comparator1 implements Comparator<MySong>{
        
        // Compares two MySong objects based on their titles.
        public int compare(MySong firstSong, MySong secondSong){
            
            // Delegate the comparison to MySong's compareTo method
            int num = firstSong.compareTo(secondSong);
            
            return num;
        }
    }

public class SongList implements cmsc256.Projects.Project3.List<MySong>, Iterable<MySong> {
   
    // The name of the playlist
    private String playlistName;
    // The first song in the playlist
    private SLelement<MySong> firstSong;
    // The last song in the playlist
    private SLelement<MySong> lastSong;
    // The current song being processed
    private SLelement<MySong> currentSong;
    // The number of songs in the playlist
    private int size;
    
    public String getSongsByArtist(String artist){

        // Initialize variables
        SLelement<MySong> current = firstSong;
        
        Iterator<MySong> iterator1 = current.iterator();
        
        MySong obj = new MySong();
        
        String list = "";
        
        ArrayList<MySong> playlist = new ArrayList<>();
        
        // Iterate through the songs in the list
        while (iterator1.hasNext()){
            obj = iterator1.next();

            // Check if the current song's artist matches the specified artist
            if (obj.getArtist().equals(artist)){
                
                playlist.add(obj);
            }
        }

        // If no songs are found for the specified artist, return a message
        if (playlist.isEmpty()) {
            
            return "There were no songs by " + artist + " in this playlist";
        }

        // Sort the playlist by song titles using the provided Comparator1
        Collections.sort(playlist, new Comparator1());
        
        // Build a formatted string with song titles and albums
        for (int i = 0; i < playlist.size(); i++) {
            
            obj = playlist.get(i);
            list = list + "Title: " + obj.getSongTitle() + " Album: " + obj.getAlbumTitle() + "\n";  
        }

        // Return the formatted list
        return list;
    }

    // Default constructor to create an empty SongList.
    public SongList(){
        clear();
    }

    // Constructor to create a SongList with a specified playlist name.
    public SongList(String playlist){
        clear();
        this.playlistName = playlist;
    }

    // Constructor to create a SongList with a specified playlist name and an initial song.
    public SongList(String playlist, MySong song){
        
        this.playlistName = playlist;
        this.firstSong = new SLelement<>(song,lastSong);
        this.lastSong = firstSong;
        this.size = 1;
    }

    // Sets the name of the playlist.
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    // Gets the name of the playlist.
    public String getPlaylistName() {
        return playlistName;
    }

    @Override
    // Returns an iterator over the elements in the playlist.
    public Iterator<MySong> iterator() {
        return new SongIterator();

    }

    // Iterator implementation for traversing the songs in the playlist.
    private class SongIterator implements Iterator<MySong> {

        // Current position in the iteration
        SLelement<MySong> pointer;
        // Counter to keep track of the number of elements visited
        int counter;
        
        //Constructs a SongIterator and initializes the pointer and counter.
        SongIterator(){
            pointer = firstSong;
            counter = 0;
        }


        @Override
        // Checks if there is a next song in the playlist.
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        // Retrieves the next song in the playlist.
        public MySong next() {
            
            // Handles case of no next SLelement
            if(hasNext() == false){
                throw new NoSuchElementException();
            }

            // Get the value of the current SLelement
            MySong temp = pointer.getValue();
            // Move the pointer to the next SLelement
            pointer = pointer.getNext();
             // Increment the counter
            counter++;
            
            return temp;
        }
    }

    @Override
    // Clears the playlist, removing all songs and resetting properties.
    public void clear() {
        this.size=0;
        playlistName="";
        firstSong= null;
        lastSong=null;
    }

    @Override
    // Inserts a new song at the specified position in the playlist.
    public boolean insert(MySong it, int position) {
        
        // Handles out of bounds input
        if (position < 0 || position > size()) {
            throw new IllegalArgumentException();
        }
        
        SLelement<MySong> current = firstSong;
        SLelement<MySong> prev = null;
        int count = 0;

        // Insert at the beginning of the playlist
        if(position == 0){
            
            SLelement<MySong> temp = new SLelement<>(it);
            firstSong = temp;
            
            return true;
        }

        // Traverse the playlist to the specified position
        while( current.getNext() != null && count <= position){
            
            prev = current;
            current = current.getNext();
            count++;

            // Insert the song at the specified position
            if(count == position){
                
                SLelement<MySong> temp = new SLelement<>(it, current); // sets the next node
                prev.setNext(temp);
                size++;
                
                return true;
            }
        }

        // Insertion was unsuccessful
        return false;
    }

    @Override
    // Adds a new song to the end of the playlist.
    public boolean add(MySong song) {
        
        size++;
        SLelement<MySong> addition = new SLelement<>(song);
        
        // If the playlist is empty, set the firstSong to the new addition
        if(firstSong==null){
            firstSong = addition;
            return true;
        }

        // Traverse the playlist to find the last song and add the new song
        SLelement<MySong> current = firstSong;
        
        while(current.getNext() != null){
            current = current.getNext();
        }
        
        current.setNext(addition);

        return true;
    }
   
    @Override
    // Removes a song from the playlist at the specified position.
    public MySong remove(int position) {
        
        if(position < 0 || position > size()){
            throw new IllegalArgumentException();
        }
        
        size--;
        SLelement<MySong> current = firstSong;
        SLelement<MySong> prev = null;

        // Remove the first song in the playlist
        if (size == 1) {
            firstSong = null;
        }

        // Traverse the playlist to the specified position and remove the song
        while (position > 0){
            
            prev = current;
            current = current.getNext();
            position--;
        }
        
        if (prev == null){
            
            firstSong = null;
        }else{
            
            prev.setNext((current.getNext()));
        }
        
        return current.getValue();
    }

    @Override
    // Returns the number of songs in the playlist.
    public int size() {
        
        return size;
    }

    @Override
    // Checks if the playlist is empty.
    public boolean isEmpty() {
        
        if(size == 0){
            
            return true;
        }
        
        return false;
    }

    @Override
    // Checks if the playlist contains a specific song.
    public boolean contains(MySong target) {

        SLelement<MySong> temp = firstSong;
        Iterator<MySong> iterator1 = temp.iterator();
        
        // Iterate through the playlist to check if it contains the target song
        while(iterator1.hasNext()){
            
            MySong current;
            current = iterator1.next();
            
            if (current.equals(target)){
                
                return true;
            }
        }
        
        return false;
    }

    @Override
    // Returns the song at the specified position in the playlist.
    public MySong getValue(int position) {
        
        if (position < 0 || position > size){
            
            throw new IllegalArgumentException();
        }

        SLelement<MySong> current = firstSong;

        // Traverse the playlist to the specified position
        for ( int i = 0; i < position; i++){
            
            current = current.getNext();
        }
        
        return current.getValue();
    }
}
