package items;

public class Key extends Item {
    private String room;
    
    public Key (String id, String name, String description, String type, String room){
        super(id, name, description, type);
        this.room = room;
    }

    public String getRoom(){
        return room;
    }

}
