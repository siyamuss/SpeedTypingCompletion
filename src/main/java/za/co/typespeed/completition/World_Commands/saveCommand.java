package za.co.typespeed.completition.World_Commands;


import java.util.Scanner;

public class saveCommand {

    private final String prompt = "How would you like to save your world? e.g. World1  ";

    public saveCommand(String insert) {
        String command = getInput();

//        String[] args = {insert, command, String.valueOf(Restore_Data_World.sizeOfWorld)
//                , String.valueOf(Restore_Data_World.obstaclePosition)};
//
//        execute(args);
    }

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        String input = scanner.nextLine();
        while (input.isBlank()) {
            input = scanner.nextLine();
        }
        return input;
    }
    public void execute(String[] command){
//        RobotWorldDb fd = new RobotWorldDb( command );

    }
}
