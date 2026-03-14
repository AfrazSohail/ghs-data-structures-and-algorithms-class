
public class Playlist {
    public Track first; // head
    public Track current;

    public Playlist(Track track) {
        this.first = track;
        this.current = track;
    }

    public boolean addEnd(Track track) {
        if (first == null) {
            this.first = track;
            this.current = track;
            return true;
        }

        Track runner = first;
        while (runner.next != null) {
            runner = runner.next;
        }
        runner.next = track;
        runner.next.next = null;
        return true;
    }

    public boolean addFirst(Track track) {
        if (track == null) {
            return false;
        }

        track.next = first;
        first = track;
        return true;
    }

    @Override
    public String toString() {
        if (first == null) {
            return "Empty Playlist";
        }

        String output = "";
        Track runner = first;
        while (runner != null) {
            output += runner + "\n";
            runner = runner.next;
        }
        return output + "current: " + current;
    }

    public boolean add(Track newTrack, String prev) {
        if (newTrack == null) {
            return false;
        }

        Track runner = first;
        Track temp = new Track(prev, 0);
        while (runner != null && !runner.equals(temp)) {
            runner = runner.next;
        }

        if (runner == null) {
            return false;
        }

        newTrack.next = runner.next;
        runner.next = newTrack;
        return true;
    }

    public boolean add(Track newTrack, int index) {
        if (newTrack == null) {
            return false;
        }

        if (index <= 0) {
            return addFirst(newTrack);
        }

        Track runner = first;
        while (runner != null && index > 0) {
            runner = runner.next;
            index--;
        }

        if (runner == null) {
            return false;
        }

        newTrack.next = runner.next;
        runner.next = newTrack;
        return true;
    }

    public boolean remove(String title) {
        if (first == null) {
            return false;
        }

        Track temp = new Track(title, 0);
        if (first.equals(temp)) {
            first = first.next;
            return true;
        }

        Track runner = first;
        while (runner.next != null && !runner.next.equals(temp)) {
            runner = runner.next;
        }

        if (runner.next == null) {
            return false;
        }

        runner.next = runner.next.next;
        return true;
    }

    public boolean remove(int index) {
        if (first == null || index < 0) {
            return false;
        }

        if (index == 0) {
            first = first.next;
            return true;
        }

        Track runner = first;
        while (runner.next != null && index > 1) {
            runner = runner.next;
            index--;
        }

        if (runner.next == null) {
            return false;
        }

        runner.next = runner.next.next;
        return true;
    }
}
