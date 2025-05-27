package items;

public class Keys extends Item {
    private String room;
    
    public Keys (String id, String name, String description, String type, String room){
        super(id, name, description, type);
        this.room = room;
    }

    public String getRoom(){
        return room;
    }

}
