package Data;

public class Collection {
	private String name;
    private String id;
    private String posterId;
    private String scord;
    private String type;
    private String director;
    private String time;

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

    public String getPosterId() {
        return posterId;
    }

    public String getDirector() {
        return director;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public String getScord() {
        return scord;
    }

    public String getType() {
        return type;
    }

    public void setScord(String scord) {
        this.scord = scord;
    }

    public String getTime() {
        return time;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }
}
