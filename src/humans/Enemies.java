package humans;

import java.util.*;

public class Enemies extends NPC {
    private int health;
    private int damage;
    private String name;
    private int dodgeRange;

    public Enemies(int h, int d, String n, int dr, String name, String currentRoom, String description, String talk,
            String id) {
        super(name, currentRoom, true, description, talk, id);
        health = h;
        damage = d;
        name = n;
        dodgeRange = dr;
    }

    public int getHealth() {
        return health;
    }

    public void stats() {
        System.out.println("Name: " + name + "Health: " + health + " Damage: " + damage);
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

    public boolean ifDodge() {

        int x = (int) (Math.random() * dodgeRange);
        int y = (int) (Math.random() * dodgeRange);

        return x == y;

    }

}
