package classAssignments;

public abstract class MovieList implements Streamable {
    private String title;

    public MovieList(String title) {
        this.title = title;
    }

    public abstract String getGenre();

    public String getMovieInfo() {
        return title + " | Genre: " + getGenre()
                + " | Stream on: " + getStreamingPlatform();
    }

    public String getTitle() { return title; }
}
