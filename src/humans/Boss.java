package humans;
import java.util.*;
 
public class Boss extends Enemies {
 
    private int stages;
 
    public Boss(int h, int d, String n, int dr, int s) {
        super(h, d, n, dr, n, n, n, n);
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
 
 
