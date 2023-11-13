package playlistSimulator;

import bridges.data_src_dependent.Song;

// MySong class extends Song and implements Comparable interface
public class MySong extends Song implements Comparable<MySong>{

    // compareTo method is overridden to provide custom comparison logic based on song titles
    @Override
    public int compareTo(MySong o) {
        
        // Using String's compareTo method to compare song titles
        return this.getSongTitle().compareTo(o.getSongTitle());
    }

    // Default constructor
    public MySong(){

        // Calling the superclass's default constructor
        super();
    }

    // Parameterized constructor with artist, song, album, lyrics, and releaseDate parameters
    public MySong(String artist, String song, String album, String lyrics, String releaseDate){

        // Calling the superclass's constructor with the provided parameters
        super(artist, song, album,lyrics, releaseDate);
    }

    // Constructor that takes a Song object as a parameter and creates a MySong object
    public MySong(Song obj){

        // Calling the superclass's constructor with the values from the provided Song object
        super(obj.getArtist(),obj.getSongTitle(), obj.getAlbumTitle(), obj.getLyrics(), obj.getReleaseDate());
    }
}
