package bianco.games.ranker;

import bianco.games.ranker.models.User;
import bianco.games.ranker.models.Board;

//Import statments
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class App {
    //Hard coded list of QBs to sort
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

    //Create global FILENAME variable
    static String FILENAME = "submissions.txt";

    public static void main(String[] args) {
        //main runs startUp() to get username then menu() for all following logic
        User currentUser = startUp();
        menu(currentUser);
    }

    //creates a user, right now we only use the users name. In future, we would want to create a unique ID for each user
    public static User startUp() {
        System.out.print("Enter username: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        //user ID not used at the moment
        return new User(1, input);
    }

    //takes user through different options. Only create list has functionality at the moment
    public static void menu(User user) {
        //initialize scanner and empty string input for getting username
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.printf("Hello %s%n", user.getName());
        //do while loop to printMenu() and continue doing so while there is no valid input
        do {
            printMenu();
            input = scanner.nextLine();
            //switch statement handles user's menu choice
            switch (input) {
                case "1" ->
                    //do create list
                        doCreate(user);
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
                    //if user enters anything else remind them of valid inputs and reset input to empty string ""
                    System.out.println("please enter 1-4");
                    input = "";
                }
            }
            //ensures menu will reprint if invalid input is entered after error message
        } while (Objects.equals(input, ""));
    }

    //console menu output
    static void printMenu() {
        System.out.println("1. Create a List");
        System.out.println("2. Read a List");
        System.out.println("3. Update a List");
        System.out.println("4. Delete a List");
    }


    //unused
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

    //A/B comparison logic
    static void doCreate(User user) {
        //initilize variables and copy QB_MASTER so we can alter it as we go
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
            //get yes/no or y/n from user
            System.out.printf("%nIs %s better than %s? ", qbList.get(masterIndex), submission.get(submissionIndex));
            String input = scanner.nextLine();
            //handle no
            if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                submission.add(submissionIndex + 1, qbList.get(masterIndex));
                //check if done sorting
                if (masterIndex >0) {
                    masterIndex--;
                } else {
                    break;
                }
                //commented out "print" methods, used while testing
                    //printMasterIndex(qbList, masterIndex);
                submissionIndex = submission.size() - 1; // reset submission index to far right element
                    // printSubmissionIndex(submission, submissionIndex);
                    // printSubmission(submission);
                //handle yes
            } else if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                //if no more qb in submission add to front of arraylist
                if (submissionIndex == 0) {
                    submission.add(0, qbList.get(masterIndex));
//                    printSubmission(submission);
                    //check if done sorting
                    if (masterIndex >0) {
                        masterIndex--;
                    } else {
                        break;
                    }
//                    printMasterIndex(qbList, masterIndex);
                    submissionIndex = submission.size() - 1; //reset submission index to far right element
//                    printSubmissionIndex(submission, submissionIndex);
                    //if yes and not at end of list
                } else {
                    submissionIndex--;
//                    printSubmissionIndex(submission, submissionIndex);
                }
                //handle anything except "y", "yes", "n", or "no"
            } else {
                System.out.println("invalid input");
            }
        }
        //write submission
        writeSubmission(submission, user);
    }

    static void writeSubmission(ArrayList<String> submission, User user) {
        File outputFile = new File(FILENAME);
        submission.add(0, user.getName());
        try {
            Files.write(outputFile.toPath(), submission, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
        catch (IOException ex) {
            System.out.println("Error writing to File: " + ex.getMessage());
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

