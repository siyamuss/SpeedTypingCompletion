package za.co.typespeed.completition.Commands;

import java.io.IOException;
import java.sql.SQLException;

import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.Read_World_Info.ReadAllDataInfo;
import za.co.typespeed.completition.world.JoinedUser;

public class StartTypingCommand extends Command{

    @Override
    public Json execute(JoinedUser target) {

        ReadAllDataInfo speechFile = new ReadAllDataInfo();
        Json jData = new Json();

        try {
            jData.setMap("Start typing ->",speechFile.excuteReadFile().get(6) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        timer.startTime();
        return jData;
    }

    public StartTypingCommand(String command) throws SQLException {
        super("forward",command);
    }
}

