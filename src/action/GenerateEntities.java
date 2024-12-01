package action;
import entity.*;
import simulation.Cell;
import simulation.GameMap;

import java.util.Random;

public class GenerateEntities implements Action {

    GameMap map;
    public GenerateEntities(GameMap map) {
        this.map = map;
    }

    @Override
    public void run() {
        generateEntities();
    }

    private void generateEntities() {
        int height = map.getHeight();
        int width = map.getWidth();

        generateEntity((height - 1) / 3, Tree.class);
        generateEntity((height - 1) / 3, Rock.class);
        generateEntity(height - 1, Herbivore.class);
        generateEntity((height - 1) / 2, Predator.class);
        generateEntity((width - 1) * 2, Grass.class);
    }

    private <T extends Entity> void generateEntity(int maxAmount, Class<T> clazz) {
        int cnt = new Random().nextInt(maxAmount) + 1;
        for (int i = 0; i < cnt; i++) {
            Cell cell;
            Entity newEntity;
            try {
                newEntity = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            do {
                cell = generateNewCell();
            } while (!map.placeEntity(cell, newEntity));
        }
    }

    private Cell generateNewCell() {
        int x = new Random().nextInt(map.getHeight()) - 1;
        int y = new Random().nextInt(map.getWidth()) - 1;
        return new Cell(x, y);
    }
}
