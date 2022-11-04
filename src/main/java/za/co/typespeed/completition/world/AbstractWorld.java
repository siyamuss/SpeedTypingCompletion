package za.co.typespeed.completition.world;

public class AbstractWorld implements IWorld{
    public static final Position CENTRE = new Position(0);
    Position position;

    public AbstractWorld(){
        this.position = CENTRE;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean updatePosition(int nrSteps, JoinedUser target) {

        int newX = this.position.getX();

//        if (Direction.NORTH.equals(this.currentDirection)) {
//            newY = newY + nrSteps;
//        }else if(Direction.SOUTH.equals(this.currentDirection)) {
//            newY = newY - nrSteps;
//        }else if(Direction.WEST.equals(this.currentDirection)) {
//            newX = newX - nrSteps;
//        }else if(Direction.EAST.equals(this.currentDirection)) {
//            newX = newX + nrSteps;
//        }

        Position newPosition = new Position(newX);
        return false;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean isNewPositionAllowed(Position position) {
        return false;
    }

    @Override
    public void SetPositions(int topLeftX) {

    }
}