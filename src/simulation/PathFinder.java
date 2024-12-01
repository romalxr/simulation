package simulation;

import entity.Entity;
import entity.Grass;

import java.util.*;
import java.util.function.Predicate;

import static entity.Entity.*;

public class PathFinder {

    GameMap map;
    public PathFinder(GameMap map) {
        this.map = map;
    }
    Set<Cell> used = new HashSet<>();

    public List<Cell[]> findMoves() {
        List<Cell[]> moves = new ArrayList<>();
        for (Cell cell : map.getEntityPositions()) {
            Cell next = null;
            if (isPredator(map.entityAt(cell))) {
                next = findPath(cell, this::isPreyNear);
            } else if (isHerbivore(map.entityAt(cell))) {
                next = findPath(cell, this::isGrassNear);
            }
            if (next != null) {
                moves.add(new Cell[] {cell, next});
                used.add(next);
            }
        }
        return moves;
    }

    private Cell findPath(Cell cell, Predicate<Cell> check) {

        Queue<Cell[]> queue = new ArrayDeque<>();
        queue.add(new Cell[] {cell, null});
        Set<Cell> usedLocal = new HashSet<>();
        usedLocal.add(cell);

        while (!queue.isEmpty()) {
            Cell[] pair = queue.poll();
            Cell pos = pair[0];
            Cell move = pair[1];

            if (check.test(pos)) {
                return move;
            } else {
                for (Cell next : emptyCellsNear(pos)) {
                    if (usedLocal.contains(next)) continue;
                    Cell firstMove = move == null ? next : move;
                    queue.add(new Cell[] {next, firstMove});
                    usedLocal.add(next);
                }
            }
        }

        return null;
    }

    private List<Cell> emptyCellsNear(Cell cell) {
        List<Cell> empties = new ArrayList<>();
        for (Cell near : nearCells(cell)) {
            if (!used.contains(near) && map.entityAt(near) == null) {
                empties.add(near);
            }
        }
        return empties;
    }

    private List<Cell> nearCells(Cell cell) {
        int height = map.getHeight();
        int width = map.getWidth();
        int x = cell.getX();
        int y = cell.getY();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        List<Cell> list = new ArrayList<>();
        for (int[] delta : directions) {
            int newX = x + delta[0];
            int newY = y + delta[1];
            if (newX < 0 || newY < 0 || newX >= height || newY >= width) {
                continue;
            }
            list.add(new Cell(newX, newY));
        }
        return list;
    }

    private boolean isPreyNear(Cell cell) {
        for (Cell near : nearCells(cell)) {
            if (isHerbivore(map.entityAt(near))) {
                return true;
            }
        }
        return false;
    }

    private boolean isGrassNear(Cell cell) {
        for (Cell near : nearCells(cell)) {
            if (isGrass(map.entityAt(near))) {
                return true;
            }
        }
        return false;
    }

}
