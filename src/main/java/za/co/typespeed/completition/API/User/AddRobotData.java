package za.co.typespeed.completition.API.User;

import za.co.typespeed.completition.API.Quates;

import java.util.*;

public class AddRobotData implements Quates<RobotQuate> {

    private Map<String, RobotQuate> Robots;

    private String robotName;

    public AddRobotData() {

        Robots = new HashMap<>();

//        for (Map.Entry<String, Json> set : MultiServer.listOfplayers.entrySet()) {
//            this.robotName = set.getKey();
//            this.add(set.getKey(),RobotQuate.create(set.getValue(), set.getKey()));
//        }
    }


    @Override
    public RobotQuate get(String id) {
        return Robots.get(id);
    }


    @Override
    public List<RobotQuate> all() {
        return new ArrayList<>(Robots.values());
    }


    @Override
    public boolean add(String t, RobotQuate world) {
        this.Robots.put(t, world);
        return false;
    }

}
