import java.util.*;

public class Weapon extends Item {
    private int attack;

    public Weapon(String id, String name, String description, int attack) {
        super(id, name, description);
        this.attack = attack;
    }

    public boolean isWeapon() {
        return true;
    }

    public int getAttack() {
        return attack;
    }

}