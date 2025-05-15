import java.util.*;

public class enemies extends NPC {
    private int health;
    private int damage;
    private String name;
    private int dodgeRange;

    // caroline fix this, I forgot to add the super stuff.
    public enemies(int h, int d, String n, int dr) {

        health = h;
        damage = d;
        name = n;
        dodgeRange = dr;
    }

    public int getHealth() {
        return health;
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
