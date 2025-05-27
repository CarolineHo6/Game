package items;

public class Key extends Item {
    private String room;
    private boolean used;
    
    public Key (String id, String name, String description, String type, String room, boolean used){
        super(id, name, description, type);
        this.room = room;
        this.used = false;
    }

    public String getRoom(){
        return room;
    }

    public void used() {
        used = true;
    }

    public boolean getUsed() {
        return used;
    }

}
