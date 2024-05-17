package bianco.games.ranker.models;



public class User {
    private Role role;
    //TODO set default to 1
    private int id;
    private String name;

    public User(int id, String name, Role role) {
        this.role = role;
        this.id = id;
        this.name = name;
    }

    public User(int id, String name) {
        this.role = bianco.games.ranker.models.Role.MEMBER;
        this.id = id;
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
