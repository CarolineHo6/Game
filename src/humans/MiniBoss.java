package humans;

import java.util.*;

public class MiniBoss extends NPC {
    private int health;
    private int damage;
    private String name;
    private int dodgeRange;

    public MiniBoss(int h, int d, int dr, String name, String currentRoom, String description, String talk,
            String id, String type) {
        super(name, currentRoom, description, talk, id, type);
        health = h;
        damage = d;
        this.name = name;
        dodgeRange = dr;
    }

    public int getHealth() {
        return health;
    }

    public String stats() {
        return "Name: " + name + " Health: " + health + " Damage: " + damage;
    }

    public void setHealth(int m) {
        health = m;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int x) {
        damage = x;
    }

    public String getName(int m) {
        return name;
    }

    // dodges attack if two numbers equal
    public boolean ifDodge() {

        int x = (int) (Math.random() * dodgeRange);
        int y = (int) (Math.random() * dodgeRange);

        return x == y;

    }

}
