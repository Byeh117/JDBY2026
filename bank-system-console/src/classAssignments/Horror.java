package classAssignments;

public class Horror extends MovieList {
    public Horror(String title) {
        super(title);
    }

    @Override
    public String getGenre() { return "Horror"; }

    @Override
    public String getStreamingPlatform() { return "Shudder"; }
}
