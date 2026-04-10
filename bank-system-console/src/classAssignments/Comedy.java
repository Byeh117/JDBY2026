package classAssignments;

public class Comedy extends MovieList {
    public Comedy(String title) {
        super(title);
    }

    @Override
    public String getGenre() { return "Comedy"; }

    @Override
    public String getStreamingPlatform() { return "Hulu"; }
}