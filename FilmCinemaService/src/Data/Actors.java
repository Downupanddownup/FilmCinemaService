package Data;

public class Actors {
	private String id;
    private String name;
    private String portraitId;
    private String role;

    public String getId() {
        return id;
    }

    public String getPortraitId() {
        return portraitId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPortraitId(String portraitId) {
        this.portraitId = portraitId;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
