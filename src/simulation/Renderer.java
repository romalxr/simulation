package simulation;

public class Renderer {
    GameMap map;

    public Renderer(GameMap map) {
        this.map = map;
    }

    public void draw() {
        for (int i = 0; i < 50; i++) System.out.println();
        for (int x = 0; x < map.getHeight(); x++) {
            for (int y = 0; y < map.getWidth(); y++) {
                var entity = map.entityAt(new Cell(x, y));
                if (entity != null) {
                    System.out.print(entity.getImage());
                } else {
                    System.out.print("\ud83d\udca2");
                }
            }
            System.out.println();
        }
    }
}
