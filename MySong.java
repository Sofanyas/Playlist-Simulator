package playlistSimulator;

//import connect.DataSource;
import bridges.data_src_dependent.Song;

public class MySong extends Song implements Comparable<MySong>{

    @Override
    public int compareTo(MySong o) {
        return this.getSongTitle().compareTo(o.getSongTitle());
    }

    public MySong(){
        super();
    }

    public MySong(String artist, String song, String album, String lyrics, String releaseDate){
        super(artist, song, album,lyrics, releaseDate);
    }

    public MySong(Song obj){
        super(obj.getArtist(),obj.getSongTitle(), obj.getAlbumTitle(), obj.getLyrics(), obj.getReleaseDate());
    }


}
