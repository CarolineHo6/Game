public class Weapon extends Item{
    private int damage;
    private int crit;
    private boolean isCrit;
    

    public Weapon(int damage, int crit){
        super(id, name, description)
        this.damage = damage;
        this.crit = crit; 
        this.isCrit = false;
    }

    public int getDamage(){
        return damage;
    
    }

    public String attack(){
        if (Math.random() < 0.05)
        {
            isCrit = true;

            if(isCrit)
                return "You did" + (damage + crit) + "damage";
        }
        return "You did" + damage + "damage";


    }
}
