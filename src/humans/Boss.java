package humans;

import java.util.*;

public class Boss extends Enemies {

    private int stages;

    public Boss(int h, int d, int dr, int s, String name, String currentRoom, String description, String talk,
            String id, String type) {
        super(h, d, dr, name, currentRoom, description, talk, id, type);
        stages = s;
    }

    public void nextStage(int addDamage, int addHealth) {
        super.setHealth(addHealth);
        super.setDamage(addDamage);
        stages--;
    }

    public int getStages() {
        return stages;
    }

}
