
import java.util.*;

public class Weapon extends Item {
    private int attack;
    private int crit;
    private boolean isCrit; 

    public Weapon(String id, String name, String description, int attack, int crit) {
        super(id, name, description);
        this.attack = attack;
        isCrit = false;
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
        return attack;
    }

}