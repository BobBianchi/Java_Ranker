package bianco.games.ranker;

import bianco.games.ranker.models.Role;
import bianco.games.ranker.models.User;
import bianco.games.ranker.models.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class App {
    static ArrayList<String> QB_MASTER = new ArrayList<>(Arrays.asList(
            "Patrick Mahomes",
            "Josh Allen",
            "Justin Herbert"
//            "Lamar Jackson",
//            "Joe Burrow",
//            "Jared Goff",
//            "Dak Prescott",
//            "Trevor Lawrence",
//            "Tua Tagovailoa",
//            "Jalen Hurts",
//            "Brock Purdy",
//            "CJ Stroud",
//            "Jordan Love"
    ));

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
                        doCreate();
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

    static void doCreate() {
        //initilize variables and copy QB_MASTER
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> submission = new ArrayList<>();
        int submissionIndex = 0;
        ArrayList<String> qbList = new ArrayList<>(QB_MASTER);
        int masterIndex = qbList.size() - 1;

        //add last qb in list to submission and remove it from qbList,
        submission.add(qbList.get(masterIndex));
        qbList.remove(masterIndex);
        masterIndex--;

        //A/B logic
        while (masterIndex > -1) {
            System.out.printf("%nIs %s better than %s? ", qbList.get(masterIndex), submission.get(submissionIndex));
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                submission.add(submissionIndex + 1, qbList.get(masterIndex));
                if (masterIndex >0) {
                    masterIndex--;
                } else {
                    break;
                }
                printMasterIndex(qbList, masterIndex);
                submissionIndex = submission.size() - 1;
                printSubmissionIndex(submission, submissionIndex);
                printSubmission(submission);
            } else if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                if (submissionIndex == 0) {
                    submission.add(0, qbList.get(masterIndex));
                    printSubmission(submission);
                    if (masterIndex >0) {
                        masterIndex--;
                    } else {
                        break;
                    }
                    printMasterIndex(qbList, masterIndex);
                    submissionIndex = submission.size() - 1;
                    printSubmissionIndex(submission, submissionIndex);
                } else {
                    submissionIndex--;
                    printSubmissionIndex(submission, submissionIndex);
                }
            } else {
                System.out.println("invalid input");
            }
        }
        System.out.println("#####################");
        System.out.printf("Final submission: %s%n", submission.toString());
        System.out.println("#####################");
    }

    static void printSubmission(ArrayList<String> submission) {
        System.out.printf("Current List: %s%n", submission.toString());
    }

    static void printMasterIndex(ArrayList<String> list, int index) {
        System.out.printf("Master Index : %s(%s)  ", index, list.get(index) );
    }
    static void printSubmissionIndex(ArrayList<String> list, int index) {
        System.out.printf("Submission Index : %s(%s)  ", index, list.get(index) );
    }
}
