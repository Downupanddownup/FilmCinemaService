package Data;

public class Critic {
	private String id;
    private String name;
    private String portraitId;
    private float scord;
    private String date;
    private String praise;
    private String content;
    private boolean isPraise;

    public void setPraise(boolean praise) {
        isPraise = praise;
    }

    public boolean isPraise() {
        return isPraise;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortraitId() {
        return portraitId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScord() {
        return scord;
    }

    public void setPortraitId(String portraitId) {
        this.portraitId = portraitId;
    }

    public void setScord(float scord) {
        this.scord = scord;
    }

    public String getDate() {
        return date;
    }

    public String getPraise() {
        return praise;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }
}
