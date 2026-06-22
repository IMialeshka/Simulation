package map;


import java.util.LinkedList;
import java.util.Queue;

public class Path {
    private Coordinates startCoordinate;
    private Coordinates endCoordinate;
    public Integer length;
    private Queue<Coordinates> pathQueue = new LinkedList<>();

    public Path(Coordinates startCoordinate, Coordinates endCoordinate) {
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
        this.length = 0;
        pathQueue.add(startCoordinate);
    }

    public Path(Path path, Coordinates stepPath) {
        this.pathQueue = path.pathQueue;
        this.startCoordinate = path.startCoordinate;
        this.endCoordinate = stepPath;
        this.length = path.length+1;
        this.pathQueue.add(stepPath);
    }

    public Coordinates getStartCoordinate() {
        return startCoordinate;
    }

    public Coordinates getEndCoordinate() {
        return endCoordinate;
    }

    public int getLength() {
        return length;
    }

    public void addStepPath(Coordinates coordinates) {
        this.length = this.length + 1;
        this.endCoordinate = coordinates;
    }

    public Queue<Coordinates> getPathQueue() {
        return pathQueue;
    }
}
