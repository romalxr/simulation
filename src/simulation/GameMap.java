package simulation;

import entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameMap {
    private static final int DEFAULT_H = 10;
    private static final int DEFAULT_W = 30;
    private final int height;
    private final int width;
    Map<Cell, Entity> entities = new HashMap<>();

    public GameMap() {
        this.height = DEFAULT_H;
        this.width = DEFAULT_W;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Entity entityAt(Cell cell) {
        return entities.get(cell);
    }

    public boolean placeEntity(Cell cell, Entity entity) {
        if (entity == null) {
            return false;
        }
        if (placeUsed(cell)) {
            return false;
        }
        entities.put(cell, entity);
        return true;
    }

    public Entity removeEntity(Cell cell) {
        return entities.remove(cell);
    }

    public List<Cell> getEntityPositions() {
        return new ArrayList<>(entities.keySet());
    }

    public boolean placeUsed(Cell cell) {
        return entities.get(cell) != null;
    }

}
