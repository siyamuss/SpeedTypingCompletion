package za.co.wethinkcode.robotworlds.maze;

import za.co.wethinkcode.robotworlds.world.Position;
import za.co.wethinkcode.robotworlds.world.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomMaze implements Maze {
    List obstacle ;
    Random random;


    public RandomMaze(){
        this.random=new Random();
        this.obstacle = new ArrayList();
        createRandomObstacles();
    }


    @Override
    public List<Obstacle> getObstacles() {
        return this.obstacle;
    }


    @Override
    public boolean blocksPath(Position a, Position b) {
        return false;
    }


    public List<Obstacle> createRandomObstacles(){
        int randomNumber= random.nextInt(10);
        for (int i = 0; i < randomNumber; i++) {
            int obstacleX =random.nextInt(201)-100;
            int obstacleY=random.nextInt(401)-200;
        this.obstacle.add(new SquareObstacle(obstacleX,obstacleY));

    }return this.obstacle;
    }

//    public void removeObstacle(Robot target){
//        for (int i = 0; i <getObstacles().size(); i++) {
//            if (target.getWorld().getPosition() == getObstacles().get(i)){
//                getObstacles().remove(i);
//            }
//        }
//
//    }
}
