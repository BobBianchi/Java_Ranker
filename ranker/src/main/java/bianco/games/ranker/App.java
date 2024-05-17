package bianco.games.ranker;

import bianco.games.ranker.models.Role;
import bianco.games.ranker.models.User;
import bianco.games.ranker.models.Board;

import java.util.Objects;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        User currentUser = startUp();
        menu(currentUser);
//        User bob = new User(1, "bob", Role.ADMIN);
//        Board board = new Board(1, bob);
//        board.add("Trevor");
//        board.add("Patrick");
//
//        printUser(bob);
//        printBoard(board);
    }

    public static User startUp() {
        System.out.print("Enter username: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return new User(1, input);
    }

    public static void menu(User user) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.printf("Hello %s%n", user.getName());
        do {
            printMenu();
            input = scanner.nextLine();
            switch (input) {
                case "1" ->
                    //do create list
                        System.out.println("Create list Coming soon");
                case "2" ->
                    //do read list
                        System.out.println("Read list coming soon");
                case "3" ->
                    //do update list
                        System.out.println("Update list coming soon");
                case "4" ->
                    //do create list
                        System.out.println("Delete list coming soon");
                default -> {
                    System.out.println("please enter 1-4");
                    input = "";
                }
            }
        } while (Objects.equals(input, ""));
    }
    static void printMenu(){
        System.out.println("1. Create a List");
        System.out.println("2. Read a List");
        System.out.println("3. Update a List");
        System.out.println("4. Delete a List");
    }


    static void printUser(User user) {
        System.out.println("User ID: " + user.getId());
        System.out.println("Username: " + user.getName());
        System.out.println("Role: " + user.getRole());
    }

    static void printBoard(Board board) {
        System.out.printf("%n%s's submission%n", board.getOwner().getName());
        if (board.getUser_submission() != null && !board.getUser_submission().isEmpty()) {
            for (String entry : board.getUser_submission()) {
                System.out.println(entry);
            }
        } else {
            System.out.println("no data");
        }
    }
}
