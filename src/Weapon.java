
import java.util.*;

public class Weapon extends Item {
    private int attack;
    private int crit;
    private boolean isCrit;
    // private double broken;
    // private boolean isBroken;


    public Weapon(String id, String name, String description, int attack, int crit, double broken) {
        super(id, name, description, 0);
        this.attack = attack;
        isCrit = false;
        // this.broken = broken;
        // isBroken = false;
    }

    public boolean isWeapon() {
        return true;
    }

    public int getAttack() {
        if (Math.random() < 0.05)
        {
            isCrit = true;
        }

        if (isCrit)
            return attack + crit;

        // if (Math.random() < broken) {
        //     isBroken = true;
        //     attack = 0;
        // }

        return attack;
    }

}