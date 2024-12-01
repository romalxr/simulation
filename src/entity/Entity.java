package entity;

public abstract class Entity {

    int health = 1;
    int attackRating = 1;

    public String getImage() {
        return "*";
    }

    public int getHealth() {
        return health;
    }

    public void incHealth(int size) {
        health += size;
    }

    public int getAttackRating() {
        return attackRating;
    }

    public static boolean isHerbivore(Entity entity) {
        return entity instanceof Herbivore;
    }

    public static boolean isPredator(Entity entity) {
        return entity instanceof Predator;
    }

    public static boolean isGrass(Entity entity) {
        return entity instanceof Grass;
    }
}
