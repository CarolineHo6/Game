import java.util.*;
import humans.*;
import items.*;

public class CommandParser {

    public static boolean parse(AdventureGUI gui, String input, Player player, Map<String, Room> rooms) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        if (words.length == 0) {
            AdventureGUI.printText("Please enter a command.");
            return false;
        }

        String command = words[0];
        Room currentRoom = rooms.get(player.getCurrentRoomId());
        // // TODO fix attack - Daisy I swear the logic works i just need to put it in
        // the
        // // case

        // // assume only 1 npc per room?
        // NPC monster = currentRoom.getNPCs().get(0);

        // if (monster != null && monster.getIsHostility() == true) {

        // AdventureGUI.printText("A monster has appeared");

        // AdventureGUI.printText("Would you like to fight the monster?");

        // AdventureGUI.printText("Fight or Flee");

        // AdventureGUI.printText("> ");
        // String decision = gui.getInput();

        // if (decision.equals("Fight") || decision.equals("fight")) {

        // while (true) {
        // monster.stats();
        // player.stats();
        // AdventureGUI.printText("Please select your weapon");
        // System.out.print("Inventory: ");
        // ArrayList<Item> pop = player.getInventory();
        // AdventureGUI.printText("fist, ");
        // for (int i = 0; i < pop.size(); i++) {
        // if (pop.get(i).isWeapon() == true) {
        // AdventureGUI.printText(pop.get(i).getName() + " - " +
        // pop.get(i).getAttack());
        // }
        // }

        // AdventureGUI.printText("> ");
        // String selection = gui.getInput();

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
        // return true;

        // }

        switch (command) {
            case "kill":
                if (words.length < 2) {
                    AdventureGUI.printText("who?");
                } else {
                    String attack = words[1];
                    AdventureGUI.printText("Player Stats: " + player.stats());
                    NPC monster = currentRoom.getNPCs().get(0);
                    AdventureGUI.printText("Monster Stats: " + monster.stats());
                    AdventureGUI.printText("Please select your weapon");
                }

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
                // return true;

            case "go":
                if (words.length < 2) {
                    AdventureGUI.printText("Go where?");
                } else {
                    String direction = words[1];
                    // Room currentRoom = rooms.get(player.getCurrentRoomId());
                    String nextRoomId = currentRoom.getExits().get(direction);

                    if (nextRoomId != null) {
                        Room nextRoom = rooms.get(nextRoomId);

                        if (nextRoom.isRiddle()) {
                            AdventureGUI.printText("This room has a riddle that you must solve to enter");
                            AdventureGUI.printText("Would you like to answer the riddle? Please answer yes or no");
                            AdventureGUI.printText("> ");
                            String response = gui.getInput();

                            if (response.equalsIgnoreCase("yes")) {

                                String[] riddle = currentRoom.generateRandomRiddle().split(" ");

                                AdventureGUI.printText(riddle[0]);

                                while (true) {
                                    AdventureGUI.printText("Please respond with the right answer");
                                    AdventureGUI.printText("> ");

                                    String res = gui.getInput(); // response

                                    if (res.equals(riddle[1])) {
                                        AdventureGUI.printText("congradulations, you may enter the room now");
                                        currentRoom.removeRiddle(riddle[0]);
                                        break;
                                    } else {

                                        AdventureGUI.printText("Wrong");
                                        AdventureGUI.printText(riddle[0]);
                                        AdventureGUI.printText("Would you like to try again? please input");
                                        res = gui.getInput();
                                        if (res.equalsIgnoreCase("yes")) {
                                            return true;
                                        } else if (res.equalsIgnoreCase("no")) {
                                            return false;
                                        }
                                    }
                                }
                                return false;
                            } else {
                                AdventureGUI.printText(
                                        "You have chosen not to solve the riddle. Can't solve it? Try reading more");
                                // TODO more??
                                return false;
                            }

                        } else if (nextRoom.getIsLocked()) {
                            AdventureGUI.printText(
                                    "The " + nextRoomId + " seems to be locked, but you could open it with a key.");

                        } else {
                            player.setCurrentRoomId(nextRoomId);
                            AdventureGUI.printText("You move " + direction + ".");
                            currentRoom = rooms.get(player.getCurrentRoomId());
                            AdventureGUI.printText(currentRoom.getLongDescription());
                        }

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
                AdventureGUI.printText(
                        "Available commands: go [direction], look, take [item], drop [item], use [item], talk to [NPC], inventory, help, use [Item], kill [NPC]");
                return false;
            case "quit":
                // TODO make a confirmation and add a scanner

                AdventureGUI.printText("Are you sure?");
                AdventureGUI.printText("Please return yes or no");
                AdventureGUI.printText("> ");
                String in = gui.getInput();

                if (in.equalsIgnoreCase("yes")) {
                    AdventureGUI.printText("BYE!");
                    return true;
                }
                // do something?
                return false;
            case "talk":
                if (words.length < 3) {
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
                        AdventureGUI.printText("There is no " + npcName + " here");
                    }
                }
                return false;
            case "use":
                if (words.length < 2) {
                    AdventureGUI.printText("use what?");
                } else {
                    String itemName = words[1];
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
                    Room roomToOpen = rooms.get(targetRoom);
                    if (roomToOpen == null) {
                        AdventureGUI.printText("There is no room called " + targetRoom + ".");
                    }
                    // moved riddle to "go" command but kept it here in case we want it here too
                    // if (currentRoom.isRiddle()) {
                    // AdventureGUI.printText("This room has a riddle that you must solve to
                    // enter");
                    // AdventureGUI.printText("Would you like to answer the riddle? Please answer
                    // yes or no");
                    // AdventureGUI.printText("> ");
                    // String response = gui.getInput();

                    // if (response.equalsIgnoreCase("yes")) {

                    // String[] riddle = currentRoom.generateRandomRiddle().split(" ");

                    // AdventureGUI.printText(riddle[0]);

                    // while (true) {
                    // AdventureGUI.printText("Please respond with the right answer");
                    // AdventureGUI.printText("> ");

                    // String res = gui.getInput(); // response

                    // if (res.equals(riddle[1])) {
                    // AdventureGUI.printText("congradulations, you may enter the room now");
                    // break;
                    // } else {

                    // AdventureGUI.printText("Wrong");
                    // AdventureGUI.printText(riddle[0]);
                    // AdventureGUI.printText("Would you like to try again? please input");
                    // res = gui.getInput();
                    // if (res.equalsIgnoreCase("yes")) {
                    // return true;
                    // } else if (res.equalsIgnoreCase("no")) {
                    // return false;
                    // }
                    // }
                    // }
                    // return false;
                    // }
                    // else {
                    // AdventureGUI.printText("You have chosen not to solve the riddle");
                    // // TODO more??
                    // return false;
                    // }
                    // }
                    else {
                        // riddle ill fix later - daisy
                        String keyName = words[3];
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

                        if (keyToUse.getId().equals(roomToOpen.getKeyID())) {
                            roomToOpen.setIsLocked(false);
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