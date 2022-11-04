package za.co.typespeed.completition.Commands;

import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.Read_World_Info.ReadAllDataInfo;
import za.co.typespeed.completition.Result.AddResult;
import za.co.typespeed.completition.Server.ErrorHandling;
import za.co.typespeed.completition.StopWatch.Timer;
import za.co.typespeed.completition.rdbms.DatabaseManager;
import za.co.typespeed.completition.world.JoinedUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class Command {

    private static List<String> speechFileData;

    public static void setIsPlayerRegistered(boolean isPlayerRegistered) {
        Command.isPlayerRegistered = isPlayerRegistered;
    }

    public static boolean isIsPlayerRegistered() {
        return isPlayerRegistered;
    }

    private static boolean isPlayerRegistered = false;

    private static int Max_Rounds_Of_Play = 3;

    public static DatabaseManager databaseManager;
    private String name;
    private String command;

    public static final Timer timer = new Timer();

    public String getCommand() {
        return command;
    }

    public abstract Json execute(JoinedUser target);

    public Command(String robot, String command ) throws SQLException {
        this.name = robot;
        this.command = command;
        initDatabaseManager();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public static Command create(String argName, String command, String password) throws SQLException {

        String instruction = checkCommandInTextFile( argName , command );

        switch (instruction.toLowerCase()){
//            case "shutdown":
//            case "off":
//                return new ShutdownCommand();
            case "myresult":
                return new MyResultCommand(instruction);
            case "result":
                return new ResultCommand(instruction);
            case "start":
                if(speechFileData == null){
                    initSpeechFile();
                }
                return new StartTypingCommand(instruction);
            case "submit":
                return new RegisterCommand(instruction);
            case "players":
                return new ViewAllPlayersCommand(instruction);
            case "password":
                return new PasswordCommand(command);
            default:
                // added error handling command class
                return new ErrorHandling("Unsupported command",command);
        }
    }

    private static void initSpeechFile(){
        ReadAllDataInfo speechFile = new ReadAllDataInfo();
        try {
            speechFileData = speechFile.excuteReadFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String checkCommandInTextFile( String user , String command ) throws SQLException {
        if(speechFileData != null ) {
            for (String line : speechFileData) {
                if (line.toLowerCase().equals(command) && Max_Rounds_Of_Play > 1) {
                    new AddResult(command, timer.endTime(), user);
                    Max_Rounds_Of_Play -= 1;
                    return "start";
                }
            }
            if( Max_Rounds_Of_Play > 1 ){
                return "start";
            }else{
                return "result";
            }
        }else if ( !isPlayerRegistered ){
            return "submit";
        }else if( command.contains("password")){
            return "password";
        }
        return command;
    }

    private void initDatabaseManager(){
        try {
            databaseManager = new DatabaseManager("jdbc:sqlite:src/main/resources/MyTables/Players.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

