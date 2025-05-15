import java.util.*;

public class boss extends enemies {

    private int stages;

    public boss(int h, int d, String n, int dr, int s) {
        super(h, d, n, dr);
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
