package classAssignments;

public class Action extends MovieList {
    public Action(String title) {
        super(title);
    }

    @Override
    public String getGenre() { return "Action"; }

    @Override
    public String getStreamingPlatform() { return "Netflix"; }
}
