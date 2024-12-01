import simulation.Simulation;

import java.util.Scanner;

public class Main {
    static boolean exitGame;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (!exitGame) {
            System.out.println("Главное меню");
            System.out.println("Выберите действие:");
            System.out.println("  1 - Новая игра");
            System.out.println("  2 - Выход");
            int selected;
            selected = scanner.nextInt();

            switch (selected) {
                case 1 -> startGame();
                case 2 -> exit();
                default -> System.out.println(" неправильный ввод");
            }
        }
    }

    private static void exit() {
        exitGame = true;
        System.out.println("Игра окончена. Спасибо за игру!");
    }

    private static void startGame() {
        Simulation simulation = new Simulation();
        simulation.run();
    }
}