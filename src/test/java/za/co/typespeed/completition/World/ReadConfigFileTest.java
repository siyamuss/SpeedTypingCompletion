package za.co.typespeed.completition.World;


import org.junit.jupiter.api.Test;
import za.co.typespeed.completition.Read_World_Info.ReadAllDataInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadConfigFileTest {

    @Test
    void readConfigFileTestSize() throws IOException {
        ReadAllDataInfo read = new ReadAllDataInfo();
        assertEquals("1", read.getWorldInfo().getProperty("size"));
    }
    @Test
    void readConfigFileTestPort() throws IOException {
        ReadAllDataInfo read = new ReadAllDataInfo();
        assertEquals("5000", read.getWorldInfo().getProperty("port"));
    }
    @Test
    void readConfigFileTestObstacles() throws IOException {
        ReadAllDataInfo read = new ReadAllDataInfo();
        assertEquals("none", read.getWorldInfo().getProperty("obstacles"));
    }
    @Test
    void readConfigFileTestWidth() throws IOException {
        ReadAllDataInfo read = new ReadAllDataInfo();
        assertEquals("1", read.getWorldInfo().getProperty("width"));
    }
    @Test
    void readConfigFileTestHeight() throws IOException {
        ReadAllDataInfo read = new ReadAllDataInfo();
        assertEquals("1", read.getWorldInfo().getProperty("height"));
    }




}