package simulation;

import action.Action;
import action.AttackEntities;
import action.GenerateEntities;
import action.MoveEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    boolean gameEnd = false;
    GameMap map;
    Renderer renderer;
    List<Action> initActions = new ArrayList<>();
    List<Action> turnActions = new ArrayList<>();

    public Simulation() {
        map = new GameMap();
        renderer = new Renderer(map);
        initActionLists();
    }

    private void initActionLists() {
        initActions.add(new GenerateEntities(map));
        turnActions.add(new MoveEntities(map));
        turnActions.add(new AttackEntities(map));
    }

    public void run() {
        runInitActions();
        runSimulation(1);
        Scanner scanner = new Scanner(System.in);
        while (!gameEnd) {
            System.out.println("Меню игры");
            System.out.println("Выберите действие:");
            System.out.println("  1 - Продолжить симуляцию");
            System.out.println("  2 - Завершить игру");
            int selected;
            selected = scanner.nextInt();

            switch (selected) {
                case 1 -> runSimulation(10);
                case 2 -> exitToMain();
                default -> System.out.println(" неправильный ввод");
            }
        }
    }

    private void runSimulation(int turnCount) {
        for (int turn = 0; turn < turnCount; turn++) {
            runTurnActions();
            renderer.draw();
            if (turn != turnCount - 1) {
                drawMenuPlaceholder();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void runInitActions() {
        for (Action action : initActions) {
            action.run();
        }
    }

    private void runTurnActions() {
        for (Action action : turnActions) {
            action.run();
        }
    }

    private void drawMenuPlaceholder() {
        int menuHeight = 4;
        for (int i = 0; i < menuHeight; i++) System.out.println();
    }

    private void exitToMain() {
        gameEnd = true;
    }
}
