package bianco.games.ranker.models;

import java.util.ArrayList;

public class Board {
    private int id;
    private User owner;
    private ArrayList<String> user_submission = new ArrayList<>();

    public Board(int id, User owner, ArrayList<String> userSubmission) {
        this.id = id;
        this.owner = owner;
        user_submission = userSubmission;
    }

    public Board() {
    }

    public Board(int id, User owner) {
        this.id = id;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<String> getUser_submission() {
        return user_submission;
    }

    public void setUser_submission(ArrayList<String> user_submission) {
        this.user_submission = user_submission;
    }

    public boolean add(String string) {
        user_submission.add(string);
        return true;
    }
}
