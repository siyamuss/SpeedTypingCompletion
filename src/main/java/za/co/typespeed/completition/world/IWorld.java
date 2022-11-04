package za.co.typespeed.completition.world;

/**
 * Your Text and Turtle worlds must implement this interface.
 */
public interface IWorld {

    Position CENTRE = new Position(0);

    /**
     * Updates the position of your robot in the world by moving the nrSteps in the robots current direction.
     *
     * @param nrSteps  steps to move in current direction
     * @param target
     * @return true if this does not take the robot over the world's limits, or into an obstacle.
     */
    boolean updatePosition(int nrSteps, JoinedUser target);

    /**
     * Retrieves the current position of the robot
     */
    Position getPosition();

    void setPosition(Position position);

    /**
     * Checks if the new position will be allowed, i.e. falls within the constraints of the world, and does not overlap an obstacle.
     * @param position the position to check
     * @return true if it is allowed, else false
     */
    boolean isNewPositionAllowed(Position position);

    void SetPositions(int topLeftX);

}


