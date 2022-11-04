package za.co.typespeed.completition.Read_World_Info;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ReadAllDataInfo {

    private final String FILE_PATH = "/home/wethinkcode/Problems/brown_fields_project/src/main/resources/text/partOfSpeech";
    public ReadAllDataInfo() {}

    public Properties getWorldInfo() throws IOException {

        String configFilePath = "config.properties";
        FileInputStream propsInput = new FileInputStream(configFilePath);
        Properties prop = new Properties();
        prop.load(propsInput);
        return prop;
    }

    public HashMap<String, String> readArguments(String[] args) throws IOException {

        HashMap<String, String> commandLineArgs = new HashMap<String, String>();

        for(int i = 0 ; i < args.length ; i++){
            if(args[i].equals("text")){
                commandLineArgs.put("text", args[i+1]);
            }if(args[i].equals("-p")){
                commandLineArgs.put("-p", args[i+1]);
            }
        }
        return commandLineArgs;
    }

    public List<String> excuteReadFile() throws IOException {

        List<String> Strings = new ArrayList<>();

        File file = new File( FILE_PATH );
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null){
            Strings.add(line);
        }
        return Strings;
    }
}
