import java.util.*;
import humans.*;
import items.*;

public class CommandParser {

    public static boolean confirmingQuit = false;
    public static String pendingRoomToUnlock = null;

    public static boolean parse(AdventureGUI gui, String input, Player player, Map<String, Room> rooms) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        if (words.length == 0) {
            AdventureGUI.printText("Please enter a command.");
            return false;
        }

        String command = words[0];
        Room currentRoom = rooms.get(player.getCurrentRoomId());

        if (confirmingQuit) {
            confirmingQuit = false;
            if (command.equalsIgnoreCase("yes")) {
                AdventureGUI.printText("BYE!!");
                AdventureGUI.inputField.setEditable(false);
                return true;
            } else {
                AdventureGUI.printText("Quit cancelled. Continuing game.... i knew u werent a quitter...");
                return false;
            }
        }

        switch (command) {
            case "kill":
                if (words.length < 2) {
                    AdventureGUI.printText("who?");
                } else if (currentRoom.getNPCs().size() == 0) {
                    AdventureGUI.printText("You can't attack air. ");
                } else {
                    AdventureGUI.printText("Player Stats: " + player.stats());
                    NPC monster = currentRoom.getNPCs().get(0);
                    AdventureGUI.printText("Monster Stats: " + monster.stats());
                    String targetName = words[1];
                    NPC target = currentRoom.getNPCs().get(0); // adjust if multiple

                    // Optional: Check that name matches target
                    if (!target.getName().equalsIgnoreCase(targetName)) {
                        AdventureGUI.printText("No one named " + targetName + " is here.");
                    }

                    Weapon weapon = null;
                    for (Item item : player.getInventory()) {
                        if (item instanceof Weapon) {
                            weapon = (Weapon) item;
                            break;
                        }
                    }
                    if (weapon == null) {
                        AdventureGUI.printText("You have no weapon to use!");
                    }

                    AdventureGUI.printText("You swing your " + weapon.getName() + " at the " + target.getName() + "!");

                    if (target.ifDodge()) {
                        AdventureGUI.printText("The monster dodged your attack!");
                    } else {
                        int damage = weapon.getAttack();
                        int newHealth = target.getHealth() - damage;
                        target.setHealth(newHealth);

                        AdventureGUI.printText("You hit the monster for " + damage + " damage!");
                        AdventureGUI.printText("Monster health is now " + target.getHealth());

                        if (newHealth <= 0) {
                            AdventureGUI.printText("You have defeated the monster!");
                            // Optionally remove the monster from room
                            currentRoom.getNPCs().remove(target);
                        } else {
                            AdventureGUI.printText("The monster attacks you!");

                            int playerHealth = player.getHealth() - target.getDamage();
                            player.setHealth(playerHealth);
                            AdventureGUI.printText("You took " + target.getDamage() + " damage.");
                            AdventureGUI.printText("Your health is now " + player.getHealth());

                            if (playerHealth <= 0) {
                                AdventureGUI.printText("You died. Game over.");
                                return true;
                            }
                        }
                    }
                }
                return false;

            // an run away function that didnt work
            // AdventureGUI.printText("> ");
            // String selection = AdventureGUI.getInput();

            // int index = pop.indexOf(selection);
            // Item w = pop.get(index);

            // if (monster.ifDodge()) {
            // AdventureGUI.printText("The monster has dodge your attack");
            // } else {

            // if (monster.getHealth() <= w.getAttack()) {
            // AdventureGUI.printText("You have defeated the monster");
            // return false;
            // } else {

            // monster.setHealth(monster.getHealth() - monster.getDamage());

            // }

            // AdventureGUI.printText("The monster is going to attack you");

            // if (player.getHealth() <= monster.getDamage()) {
            // AdventureGUI.printText("You have been defeated by the monster. Game over.");
            // return true;
            // } else {
            // player.setHealth(player.getHealth() - monster.getDamage());
            // }

            // }
            // }
            // AdventureGUI.printText("you have ran away");
            // return true;\

            case "solve":
                if (pendingRoomToUnlock != null) {
                    Room targetRoom = rooms.get(pendingRoomToUnlock);
                    String guess = input.substring(input.indexOf(" ") + 1).trim(); // handles multi-word answers
                    String correct = targetRoom.getCurrentRiddleAnswer();

                    if (guess.equalsIgnoreCase(correct)) {
                        AdventureGUI.printText("Correct! You may now enter the room.");
                        targetRoom.setIsLocked();
                        targetRoom.clearCurrentRiddle();
                        pendingRoomToUnlock = null;
                    } else {
                        AdventureGUI.printText("Wrong answer.");
                    }
                }
                return false;

            case "go":
                if (words.length < 2) {
                    AdventureGUI.printText("Go where?");
                } else {
                    String direction = words[1].toLowerCase();

                    Map<String, String> directionAliases = new HashMap<>();
                    directionAliases.put("left", "west");
                    directionAliases.put("right", "east");
                    directionAliases.put("forward", "south");
                    directionAliases.put("back", "north");

                    if (directionAliases.containsKey(direction)) {
                        direction = directionAliases.get(direction);
                    }
                    
                    // Room currentRoom = rooms.get(player.getCurrentRoomId());
                    String nextRoomId = currentRoom.getExits().get(direction);

                    if (nextRoomId == null) {
                        AdventureGUI.printText("You can't go that way.");
                        return false;
                    }

                    Room nextRoom = rooms.get(nextRoomId);
                    if (nextRoom == null) {
                        AdventureGUI.printText("The next room could not be found.");
                        return false;
                    }

                    if (nextRoomId != null) {
                        nextRoom = rooms.get(nextRoomId);

                        if (nextRoom.isRiddle()) {
                            pendingRoomToUnlock = nextRoomId;
                            String riddleQ = nextRoom.generateRiddle();
                            AdventureGUI.printText("This room has a riddle you must solve to enter:");
                            AdventureGUI.printText(riddleQ);
                            AdventureGUI.printText("Respond with: solve [your answer]");

                        } else if (nextRoom.getIsLocked() == true && !(nextRoom.getKeyID().equals(""))) {
                            AdventureGUI.printText(
                                    "The " + nextRoomId + " seems to be locked, but you could open it with a key.");

                        } else if (!currentRoom.getNPCs().isEmpty()) {
                            NPC monster = currentRoom.getNPCs().get(0);
                            if (monster.getHealth() > 0 && (monster.getType().equalsIgnoreCase("boss") || monster.getType().equalsIgnoreCase("miniboss"))) {
                                AdventureGUI.printText("You shall not pass ~ Gandalf \nYou must defeat the " + monster.getType() + " " + monster.getName());
                            }
                        }
                        
                        else {
                            player.setCurrentRoomId(nextRoomId);
                            AdventureGUI.printText("You move " + direction + ".");
                            currentRoom = rooms.get(player.getCurrentRoomId());
                            AdventureGUI.printText(currentRoom.getLongDescription());
                        }

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
                    for (int i = 2; i < words.length; i++) {
                        itemName += " " + words[i];
                    }
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
                AdventureGUI.printText(
                        "Available commands: go [direction], look, take [item], drop [item], use [item], talk to [NPC], inventory, help, use [Item], kill [NPC], quit, read[item], open");
                return false;
            case "quit":
                confirmingQuit = true;
                AdventureGUI.printText("Are you sure you want to quit? Type 'yes' to confirm");
                return true;
            case "talk":
                if (words.length < 3) {
                    AdventureGUI.printText("talk to who?");
                } else {
                    String npcName = words[2];
                    for (int i = 3; i < words.length; i++) {
                        npcName += " " + words[i];
                    }
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
                        AdventureGUI.printText("There is no " + npcName + " here");
                    }
                }
                return false;
            case "use":
                if (words.length < 2) {
                    AdventureGUI.printText("use what?");
                } else {
                    String itemName = words[1];
                    for (int i = 2; i < words.length; i++) {
                        itemName += " " + words[i];
                    }
                    Item itemToUse = null;
                    // Potions potionToUse = null;
                    for (Item item : player.getInventory()) {
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            itemToUse = item;
                            break;
                        }
                    }

                    if (itemToUse != null) {
                        if (itemToUse instanceof Potions) {
                            int addHeart = itemToUse.getAddHeart();
                            if (addHeart != 0) {
                                player.setHealth(player.getHealth() + addHeart);
                                AdventureGUI.printText("Your health increased by " + addHeart);
                            } else {
                                AdventureGUI.printText("You can't use that item");
                            }
                        } else if (itemToUse instanceof Weapon) {

                            Item w = itemToUse;

                            NPC monster = currentRoom.getNPCs().get(0);

                            AdventureGUI.printText("You are using a weapon with an attack of " + player.getAttack());
                            if (monster.ifDodge()) {
                                AdventureGUI.printText("The monster has dodged your attack");
                            } else {

                                if (monster.getHealth() <= w.getAttack()) {
                                    AdventureGUI.printText("You have defeated the monster");
                                    return false;
                                } else {

                                    monster.setHealth(monster.getHealth() - monster.getDamage());
                                    AdventureGUI.printText("Monster stats: " + monster.stats());

                                }

                            }

                            AdventureGUI.printText("The monster is going to attack you");
                            AdventureGUI.printText(
                                    "The monster has used an attack with a damage of " + monster.getDamage());

                            if (player.getHealth() <= monster.getDamage()) {
                                AdventureGUI.printText("You have been defeated by the monster. Game over.");
                                return true;
                            } else {
                                player.setHealth(player.getHealth() - monster.getDamage());

                                AdventureGUI.printText("Player Stats: " + player.stats());
                            }

                            return false;

                        }
                    } else {
                        AdventureGUI.printText("You don't have that item");
                    }
                }
                return false;

            case "read":
                if (words.length < 2) {
                    AdventureGUI.printText("read what?");
                } else {
                    String itemName = words[1];
                    if (!"notebook".equalsIgnoreCase(itemName)) {
                        AdventureGUI.printText("You can't read that item!");
                    } else {
                        Item itemToRead = null;
                        for (Item item : player.getInventory()) {
                            if (item.getName().equalsIgnoreCase(itemName)) {
                                itemToRead = item;
                                break;
                            }
                        }
                        for (Item item : currentRoom.getItems()) {
                            if (item.getName().equalsIgnoreCase(itemName)) {
                                itemToRead = item;
                                break;
                            }
                        }

                        if (itemToRead != null) {
                            AdventureGUI.printText(itemToRead.getDescription());
                        } else {
                            AdventureGUI.printText("There is no notebook here!");
                        }
                    }
                }
                return false;

            case "open":
                // Please double check - supposed to use the key to open door
                if (words.length < 2) {
                    AdventureGUI.printText("open what?");

                } else if (words.length < 4) {
                    AdventureGUI.printText("open the door with what?");

                } else {
                    String targetRoom = words[1];
                    int iOfWith = 0;
                    while (!words[iOfWith].equals("with")) {
                        iOfWith++;
                    }
                    for (int i = 2; i < iOfWith; i++) {
                        targetRoom += " " + words[i];
                    }
                    Room roomToOpen = rooms.get(targetRoom);
                    if (roomToOpen == null) {
                        AdventureGUI.printText("There is no room called " + targetRoom + ".");
                    } else {
                        // riddle ill fix later - daisy
                        String keyName = words[3];
                        for (int i = iOfWith + 2; i < words.length; i++) {
                            keyName += " " + words[i];
                        }
                        Key keyToUse = null;
                        for (Item item : player.getInventory()) {
                            if (item.getName().equalsIgnoreCase(keyName) && item instanceof Key) {
                                keyToUse = (Key) item;
                                break;
                            }
                        }
                        if (keyToUse == null) {
                            AdventureGUI.printText("You don't have a key named " + keyName + ".");
                        }

                        else if (keyToUse.getId().equals(roomToOpen.getKeyID())) {
                            roomToOpen.setIsLocked();
                            keyToUse.used();
                            AdventureGUI.printText("You unlocked the " + targetRoom + "!");
                        } else {
                            AdventureGUI.printText("That key doesn't seem to fit this door...");
                        }

                        if (keyToUse.getUsed() == true) {
                            player.removeItem(keyToUse);
                        }
                    }
                }
                return false;

            default:
                AdventureGUI.printText("I don't understand that command.");
                return false;
        }
    }
}
