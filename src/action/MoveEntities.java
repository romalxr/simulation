package action;

import entity.Entity;
import simulation.Cell;
import simulation.GameMap;
import simulation.PathFinder;

import java.util.List;

public class MoveEntities implements Action {

    GameMap map;
    public MoveEntities(GameMap map) {
        this.map = map;
    }

    @Override
    public void run() {
        PathFinder pathFinder = new PathFinder(map);
        List<Cell[]> moves = pathFinder.findMoves();
        for (Cell[] pair : moves) {
            Cell pos = pair[0];
            Cell next = pair[1];
            if (!map.placeUsed(next)) {
                Entity entity = map.removeEntity(pos);
                map.placeEntity(next, entity);
            } else {
                System.err.println("cell already used " + next);
            }
        }
    }
}
