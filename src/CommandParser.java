import java.util.*;

import humans.Enemies;
import humans.NPC;

public class CommandParser {

    public static boolean parse(AdventureGUI gui, String input, Player player, Map<String, Room> rooms) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        if (words.length == 0) {
            AdventureGUI.printText("Please enter a command.");
            return false;
        }

        String command = words[0];
        Room currentRoom = rooms.get(player.getCurrentRoomId());
        // assume only 1 npc per room?
        Enemies monster = (Enemies) currentRoom.getNPCs().get(0); //actually idk what im doing

        if (monster != null && monster.getIsHostility() == true) {

            AdventureGUI.printText("Would you like to fight the monster?");

            AdventureGUI.printText("Fight or Flee");

            AdventureGUI.printText("> ");
            String decision = gui.getInput();

            if (decision.equals("Fight") || decision.equals("fight")) {

                while (true) {
                    monster.stats();
                    player.stats();
                    AdventureGUI.printText("Please select your weapon");
                    System.out.print("Inventory: ");
                    ArrayList<Item> pop = player.getInventory();
                    AdventureGUI.printText("fist, ");
                    for (int i = 0; i < pop.size(); i++) {
                        if (pop.get(i).isWeapon() == true) {
                            AdventureGUI.printText(pop.get(i).getName() + " - " + pop.get(i).getAttack());
                        }
                    }

                    AdventureGUI.printText("> ");
                    String selection = gui.getInput();

                    int index = pop.indexOf(selection);
                    Item w = pop.get(index);

                    if (monster.getHealth() <= w.getAttack()) {
                        AdventureGUI.printText("You have defeated the monster");
                        return false;
                    } else {

                        monster.setHealth(monster.getHealth() - monster.getDamage());

                    }

                    AdventureGUI.printText("The monster is going to attack you");

                    if (player.getHealth() <= monster.getDamage()) {
                        AdventureGUI.printText("You have been defeated by the monster. Game over.");
                        return true;
                    } else {
                        player.setHealth(player.getHealth() - monster.getDamage());
                    }

                }
            }
            AdventureGUI.printText("you have ran away");
            return true;

        }

        switch (command) {
            case "go":
                if (words.length < 2) {
                    AdventureGUI.printText("Go where?");
                } else {
                    String direction = words[1];
                    // Room currentRoom = rooms.get(player.getCurrentRoomId());
                    String nextRoomId = currentRoom.getExits().get(direction);
                    if (nextRoomId != null) {
                        player.setCurrentRoomId(nextRoomId);
                        AdventureGUI.printText("You move " + direction + ".");
                        currentRoom = rooms.get(player.getCurrentRoomId());
                        AdventureGUI.printText(currentRoom.getLongDescription());

                    } else {
                        AdventureGUI.printText("You can't go that way.");
                    }
                }
                return false;
            case "look":
                // Room currentRoom = rooms.get(player.getCurrentRoomId());
                AdventureGUI.printText(currentRoom.getLongDescription());
                return false;
            case "inventory":
                if (player.getInventory().isEmpty()) {
                    AdventureGUI.printText("Your inventory is empty.");
                } else {
                    AdventureGUI.printText("You are carrying:");
                    for (Item item : player.getInventory()) {
                        AdventureGUI.printText("- " + item.getName());
                    }
                }
                return false;
            case "take":
                if (words.length < 2) {
                    AdventureGUI.printText("Take what?");
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
                        AdventureGUI.printText("You take the " + itemToTake.getName() + ".");
                    } else {
                        AdventureGUI.printText("There is no " + itemName + " here.");
                    }
                }
                return false;
            case "drop":
                if (words.length < 2) {
                    AdventureGUI.printText("Drop what?");
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
                        AdventureGUI.printText("You drop the " + itemToDrop.getName() + ".");
                    } else {
                        AdventureGUI.printText("You don't have a " + itemName + ".");
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

                AdventureGUI.printText("Are you sure?");
                AdventureGUI.printText("Please return yes or no");
                String in = sc.nextLine();

                if (in.equals("yes") || in.equals("Yes")) {
                    AdventureGUI.printText("BYE!");
                    return true;
                }
                // do something?
                return false;
            case "talk":
                if (words.length < 2) {
                    AdventureGUI.printText("talk to who?");
                } else {
                    String npcName = words[2];
                    NPC npcToTalk = null;
                    for (NPC npc : currentRoom.getNPCs()) {
                        if (npc.getName().equalsIgnoreCase(npcName)) {
                            npcToTalk = npc;
                            break;
                        }
                    }
                    if (npcToTalk != null) {
                        AdventureGUI.printText(npcToTalk.getTalk());
                    } else {
                        AdventureGUI.printText("There is no " + npcName + " here.");
                    }
                }

            default:
                AdventureGUI.printText("I don't understand that command.");
                return false;
        }
    }
}
