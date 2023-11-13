# Playlist-Simulator
## Description
Powered by the Bridges API, this program leverages a linked list data structure to efficiently manage a collection of Song objects, each encapsulating details like title, artist, and album. Orchestrated by the SongList class, adhering to a List interface, it facilitates operations like insertion, removal, and seamless iteration through the song catalog, emulating a playlist environment and replicating the fluid experience of organizing and navigating through a digital playlist.

## Usage
To use the SongList class, simply create a new instance and call its methods to manipulate the list. Here's an example:
```
public class SongListExample {

    public static void main(String[] args) {
        // Create a new playlist
        SongList myPlaylist = new SongList("My Awesome Playlist");

        // Add songs to the playlist
        MySong song1 = new MySong("Artist1", "Song1", "Album1", "Lyrics1", "2021-01-01");
        MySong song2 = new MySong("Artist2", "Song2", "Album2", "Lyrics2", "2021-02-01");

        myPlaylist.add(song1);
        myPlaylist.add(song2);

        // Display the playlist name
        System.out.println("Playlist Name: " + myPlaylist.getPlaylistName());

        // Display all songs in the playlist
        System.out.println("Songs in the Playlist:");
        for (MySong song : myPlaylist) {
            System.out.println("Title: " + song.getSongTitle() + ", Album: " + song.getAlbumTitle());
        }

        // Get the number of songs in the playlist
        System.out.println("Number of Songs: " + myPlaylist.size());

        // Check if the playlist is empty
        System.out.println("Is Playlist Empty? " + myPlaylist.isEmpty());
    }
}
```

# Song Comparator 

## Description
This is a Java program that implements the Comparator interface to sort a list of Song objects by their titles in ascending order. This program uses the Bridges API to access the Song class.

## Dependencies
To run this program, you will need the following:

- Java Development Kit (JDK)
- Bridges API

## Usage
This program is used in unison with MySong.java

## Acknowledgements
This project uses the Bridges API 

## Credits
This project was created by Sofanyas Genene on October 7th, 2022
