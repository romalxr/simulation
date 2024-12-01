package action;

import entity.Entity;
import simulation.Cell;
import simulation.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static entity.Entity.isHerbivore;
import static entity.Entity.isPredator;

public class AttackEntities implements Action {

    GameMap map;
    public AttackEntities(GameMap map) {
        this.map = map;
    }

    @Override
    public void run() {
        for (Cell cell : map.getEntityPositions()) {
            Entity entity = map.entityAt(cell);
            if (isPredator(entity)) {
                attackNear(cell, entity, Entity::isHerbivore);
            } else if (isHerbivore(entity)) {
                attackNear(cell, entity, Entity::isGrass);
            }
        }
    }

    private void attackNear(Cell cell, Entity entity, Predicate<Entity> check) {
        int height = map.getHeight();
        int width = map.getWidth();
        int x = cell.getX();
        int y = cell.getY();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] delta : directions) {
            int newX = x + delta[0];
            int newY = y + delta[1];
            if (newX < 0 || newY < 0 || newX >= height || newY >= width) {
                continue;
            }
            Cell near = new Cell(newX, newY);
            Entity nearEntity = map.entityAt(near);
            if (check.test(nearEntity)) {
                int attackStr = entity.getAttackRating();

                nearEntity.incHealth(-1 * attackStr);
                int health = nearEntity.getHealth();
                if (health <= 0) {
                    map.removeEntity(near);
                }
                return;
            }
        }
    }
}
