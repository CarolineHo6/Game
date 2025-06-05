package items;

import java.util.*;

public class Weapon extends Item {
    private int attack;
    private int crit;
    private boolean isCrit;
    private boolean isBroken;

    public Weapon(String id, String name, String description, String type, int attack, int crit) {
        super(id, name, description, type);
        this.attack = attack;
        isCrit = false;
        isBroken = false;
    }

    public boolean isWeapon() {
        return true;
    }

    // Critical attack added to attack
    public int getAttack() {
        if (Math.random() < 0.05) {
            isCrit = true;
        }

        // if (isCrit)
        // return attack + crit;

        // if (Math.random() < broken) {
        // isBroken = true;
        // attack = 0;
        // }

        return attack;
    }

    // see if your weapon will break
    public boolean getBreak() {
        if (Math.random() < 0.1) {
            isBroken = true;
        }
        return isBroken;
    }

}