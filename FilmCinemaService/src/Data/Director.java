package Data;

public class Director {
	private String id;
    private String name;
    private String portraitId;

    public void setPortraitId(String portraitId) {
        this.portraitId = portraitId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortraitId() {
        return portraitId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
