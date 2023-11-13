package cmsc256.Projects.Project3;

import bridges.base.SLelement;
import bridges.connect.Bridges;
import bridges.connect.DataSource;

import java.util.*;

public class SongList implements cmsc256.Projects.Project3.List<MySong>, Iterable<MySong> {
    public String getSongsByArtist(String artist){

        SLelement<MySong> current = firstSong;
        Iterator<MySong> iterator1 = current.iterator();
        MySong obj = new MySong();
        String list = "";
        ArrayList<MySong> playlist = new ArrayList<>();
        while (iterator1.hasNext()){
            obj = iterator1.next();
            if (obj.getArtist().equals(artist)){
                playlist.add(obj);
            }
        }
        if (playlist.isEmpty()) {
            return "There were no songs by " + artist + " in this playlist";
        }
        Collections.sort(playlist, new Comparator1());
        for (int i = 0; i < playlist.size(); i++) {
            obj = playlist.get(i);
            list = list + "Title: " + obj.getSongTitle() + " Album: " + obj.getAlbumTitle() + "\n";
        }
        return list;
    }

    public class Comparator1 implements Comparator<MySong>{
        public int compare(MySong firstSong, MySong secondSong){
            int num = firstSong.compareTo(secondSong);
            return num;
        }
    }

    public static void main(String args[]){
        Bridges bridges = new Bridges(3, "Sofanyas", "299816294237");
        DataSource ds = bridges.getDataSource();
        List<MySong> song = null;

    }

    private String playlistName;
    private SLelement<MySong> firstSong;
    private SLelement<MySong> lastSong;

    private SLelement<MySong> currentSong;
    private int size;

    public SongList(){
        clear();
    }

    public SongList(String playlist){
        clear();
        this.playlistName = playlist;
    }

    public SongList(String playlist, MySong song){
        this.playlistName = playlist;
        this.firstSong = new SLelement<>(song,lastSong);
        this.lastSong = firstSong;
        this.size = 1;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    @Override
    public Iterator<MySong> iterator() {
        return new SongIterator();

    }

    private class SongIterator implements Iterator<MySong> {
        SLelement<MySong> pointer;
        int counter;
        SongIterator(){
            pointer = firstSong;
            counter = 0;
        }


        @Override
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        public MySong next() {
            if(hasNext() == false){
                throw new NoSuchElementException();
            }
            MySong temp = pointer.getValue();
            pointer = pointer.getNext();
            counter++;
            return temp;
        }
    }

    @Override
    public void clear() {
        this.size=0;
        playlistName="";
        firstSong= null;
        lastSong=null;
    }


    @Override
    public boolean insert(MySong it, int position) {
        if (position < 0 || position > size()) {
            throw new IllegalArgumentException();
        }
        SLelement<MySong> current = firstSong;
        SLelement<MySong> prev = null;
        int count = 0;
        if(position == 0){
            SLelement<MySong> temp = new SLelement<>(it);
            firstSong = temp;
            return true;
        }
        while( current.getNext() != null && count <= position){
            prev = current;
            current = current.getNext();
            count++;
            if(count == position){
                SLelement<MySong> temp = new SLelement<>(it, current); // sets the next node
                prev.setNext(temp);
                size++;
                return true;
            }
        }


        return false;
    }

    @Override
    public boolean add(MySong song) {
        size++;
        SLelement<MySong> addition = new SLelement<>(song);
        if(firstSong==null){
            firstSong = addition;
            //lastSong=firstSong;
            return true;
        }
        SLelement<MySong> current = firstSong;
        while(current.getNext() != null){
            current = current.getNext();
        }
        current.setNext(addition);

        return true;
    }
    // What do I remove from??????????????
    @Override
    public MySong remove(int position) {
        if(position < 0 || position > size()){
            throw new IllegalArgumentException();
        }
       size--;
        SLelement<MySong> current = firstSong;
        SLelement<MySong> prev = null;
        if (size == 1) {
            firstSong = null;
        }
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
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(MySong target) {

        SLelement<MySong> temp = firstSong;
        Iterator<MySong> iterator1 = temp.iterator();
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
    public MySong getValue(int position) {
        if (position < 0 || position > size){
            throw new IllegalArgumentException();
        }

        SLelement<MySong> current = firstSong;
        for ( int i = 0; i < position; i++){
            current = current.getNext();
        }
        return current.getValue();
    }
}
