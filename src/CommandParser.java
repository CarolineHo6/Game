import java.util.*;

import humans.Enemies;
import humans.NPC;

public class CommandParser {

    public boolean parse(String input, Player player, Map<String, Room> rooms) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        if (words.length == 0) {
            System.out.println("Please enter a command.");
            return false;
        }

        String command = words[0];
        Room currentRoom = rooms.get(player.getCurrentRoomId());
        Enemies monster = (Enemies) currentRoom.randomGenerateMonster();

        if (monster != null && currentRoom.randomGenerateMonster().getIsHostility() == true) {

            System.out.println("Would you like to fight the monster?");

            Scanner sc = new Scanner(System.in);
            System.out.println("Fight or Flee");

            System.out.println("> ");
            String decision = sc.nextLine();

            if (decision.equals("Fight") || decision.equals("fight")) {

                while (true) {
                    System.out.println(x.getDescription());
                    System.out.println("Please select your weapon");
                    System.out.print("Inventory: ");
                    ArrayList<Item> pop = player.getInventory();
                    System.out.println("fist, ");
                    for (int i = 0; i < pop.size(); i++) {
                        if (pop.get(i).isWeapon() == true) {
                            System.out.println(pop.get(i).getName() + " - " + pop.get(i).getAttack());
                        }
                    }

                    System.out.println("> ");
                    String selection = sc.nextLine();

                    int index = pop.indexOf(selection);
                    Item w = pop.get(index);

                    if (monster.getHealth() <= w.getAttack()) {
                        System.out.println("You have defeated the monster");
                        return false;
                    } else {

                        monster.setHealth(monster.getHealth() - monster.getDamage());

                    }

                    System.out.println("The monster is going to attack you");

                    if (player.getHealth() <= monster.getDamage()) {
                        System.out.println("You have been defeated by the monster. Game over.");
                        return true;
                    } else {
                        player.setHealth(player.getHealth() - monster.getDamage());
                    }

                }
            }
            System.out.println("you have ran away");
            return true;

        }

        switch (command) {
            case "go":
                if (words.length < 2) {
                    System.out.println("Go where?");
                } else {
                    String direction = words[1];
                    // Room currentRoom = rooms.get(player.getCurrentRoomId());
                    String nextRoomId = currentRoom.getExits().get(direction);
                    if (nextRoomId != null) {
                        player.setCurrentRoomId(nextRoomId);
                        System.out.println("You move " + direction + ".");
                        currentRoom = rooms.get(player.getCurrentRoomId());
                        System.out.println(currentRoom.getLongDescription());

                    } else {
                        System.out.println("You can't go that way.");
                    }
                }
                return false;
            case "look":
                // Room currentRoom = rooms.get(player.getCurrentRoomId());
                System.out.println(currentRoom.getLongDescription());
                return false;
            case "inventory":
                if (player.getInventory().isEmpty()) {
                    System.out.println("Your inventory is empty.");
                } else {
                    System.out.println("You are carrying:");
                    for (Item item : player.getInventory()) {
                        System.out.println("- " + item.getName());
                    }
                }
                return false;
            case "take":
                if (words.length < 2) {
                    System.out.println("Take what?");
                } else {
                    String itemName = words[1];
                    // Room room = rooms.get(player.getCurrentRoomId());
                    Item itemToTake = null;
                    for (Item item : currentRoom.getItems()) {
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            itemToTake = item;
                            break;
                        }
                    }
                    if (itemToTake != null) {
                        currentRoom.removeItem(itemToTake);
                        player.addItem(itemToTake);
                        System.out.println("You take the " + itemToTake.getName() + ".");
                    } else {
                        System.out.println("There is no " + itemName + " here.");
                    }
                }
                return false;
            case "drop":
                if (words.length < 2) {
                    System.out.println("Drop what?");
                } else {
                    String itemName = words[1];
                    Item itemToDrop = null;
                    for (Item item : player.getInventory()) {
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            itemToDrop = item;
                            break;
                        }
                    }
                    if (itemToDrop != null) {
                        player.removeItem(itemToDrop);
                        // Room room = rooms.get(player.getCurrentRoomId());
                        currentRoom.addItem(itemToDrop);
                        System.out.println("You drop the " + itemToDrop.getName() + ".");
                    } else {
                        System.out.println("You don't have a " + itemName + ".");
                    }
                }
                return false;
            case "help":
                System.out
                        .println("Available commands: go [direction], look, take [item], drop [item], inventory, help");
                return false;
            case "quit":
                // TODO make a confirmation and add a scanner
                Scanner sc = new Scanner(System.in);

                System.out.println("Are you sure?");
                System.out.println("Please return yes or no");
                String in = sc.nextLine();

                if (in.equals("yes") || in.equals("Yes")) {
                    System.out.println("BYE!");
                    return true;
                }
                // do something?
                return false;

            default:
                System.out.println("I don't understand that command.");
                return false;
        }
    }
}
