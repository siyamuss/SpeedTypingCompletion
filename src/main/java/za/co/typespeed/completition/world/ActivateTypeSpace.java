package za.co.typespeed.completition.world;

import za.co.typespeed.completition.Read_World_Info.ReadAllDataInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ActivateTypeSpace {
    private String[] commandLineArguments;
    private Properties configFile;

    private List<String> DataFile;

    public List<String> getDataFile() {
        return DataFile;
    }
    public ActivateTypeSpace(String[] args) {
        this.commandLineArguments = args;
    }

    public ActivateTypeSpace activate_Space() throws IOException {

        ReadAllDataInfo read = new ReadAllDataInfo();
        configFile = read.getWorldInfo();
        HashMap<String, String> argsList = read.readArguments(commandLineArguments);
        DataFile = read.excuteReadFile();

        Port port = new Port( argsList , configFile );
        port.setPort();

        return this;
    }
}
