package Data;

public class CinemaFilm {
	private String id;
    private String name;
    private float Scord;
    private String posterId;

    public String getPosterId() {
        return posterId;
    }

    public float getScord() {
        return Scord;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public void setScord(float scord) {
        Scord = scord;
    }
}
