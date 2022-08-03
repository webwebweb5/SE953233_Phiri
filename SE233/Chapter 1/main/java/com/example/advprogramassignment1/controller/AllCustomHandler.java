package com.example.advprogramassignment1.controller;

import com.example.advprogramassignment1.Launcher;
import com.example.advprogramassignment1.model.DamageType;
import com.example.advprogramassignment1.model.character.BasedCharacter;
import com.example.advprogramassignment1.model.item.Armor;
import com.example.advprogramassignment1.model.item.BasedEquipment;
import com.example.advprogramassignment1.model.item.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class AllCustomHandler {
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());
            // Exercise 5. Automatically unequip all the equipments after the character is regenerated.
            BasedCharacter equip = Launcher.getMainCharacter();
            unEquip(equip);
            Launcher.refreshPane();
        }
    }
    // Exercise 4
    public static class UnEquip implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            BasedCharacter equip = Launcher.getMainCharacter();
            unEquip(equip);
            Launcher.refreshPane();
        }
    }
    public static void unEquip(BasedCharacter equip) {
        Launcher.setEquippedWeapon(null);
        Launcher.setAllEquipments(GenItemList.setUpItemList());
        equip.unequipWeapon();
        Launcher.setEquippedArmor(null);
        Launcher.setAllEquipments(GenItemList.setUpItemList());
        equip.unequipArmor();
    }
    public static void onDragDetected(MouseEvent event, BasedEquipment equipment, ImageView imgView) {
        Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imgView.getImage());
        ClipboardContent content = new ClipboardContent();
        content.put(equipment.DATA_FORMAT, equipment);
        db.setContent(content);
        event.consume();
    }

    public static void onDragOver(DragEvent event, String type) {
        Dragboard dragboard = event.getDragboard();
        BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type)) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    }

    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        if(dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
            BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
            BasedCharacter character = Launcher.getMainCharacter();
            if(retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
                // Exercise 6. All the weapons have their own class determining by the DamageType enum. Our task is to
                // to allow only weapons with the same class as the character be equipped. Note for BattleMage,
                // we allow it to equip any weapon but no armor can be equipped.
                if(((Weapon)retrievedEquipment).getDamageType() == DamageType.physical && (character.getType() == DamageType.magical)){
                    Launcher.refreshPane();
                    return;
                }
                if(((Weapon)retrievedEquipment).getDamageType() == DamageType.magical && (character.getType() == DamageType.physical)){
                    Launcher.refreshPane();
                    return;
                }
                if(((Weapon)retrievedEquipment).getDamageType() == DamageType.battlemage && (character.getType() != DamageType.battlemage)){
                    Launcher.refreshPane();
                    return;
                }
                if (Launcher.getEquippedWeapon() != null) {
                    allEquipments.add(Launcher.getEquippedWeapon());
                }
                Launcher.setEquippedWeapon((Weapon) retrievedEquipment);
                character.equipWeapon((Weapon) retrievedEquipment);
            } else {
                if(retrievedEquipment.getClass().getSimpleName().equals("Armor") && (character.getType() == DamageType.battlemage)){
                    return;
                }
                if (Launcher.getEquippedArmor() != null) {
                    allEquipments.add(Launcher.getEquippedArmor());
                }
                Launcher.setEquippedArmor((Armor) retrievedEquipment);
                character.equipArmor((Armor) retrievedEquipment);
            }
            // End Exercise 6.

            Launcher.setMainCharacter(character);
            Launcher.setAllEquipments(allEquipments);
            Launcher.refreshPane();
            ImageView imgView = new ImageView();
            if (imgGroup.getChildren().size() != 1) {
                imgGroup.getChildren().remove(1);
                Launcher.refreshPane();
            }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName());
            imgView.setImage(new Image(Launcher.class.getResource(retrievedEquipment.getImagepath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragCompleted = true;
        }
        event.setDropCompleted(dragCompleted);
    }
    public static void onEquipDone(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
        int pos = -1;
        // Exercise 3. Put the dragged item back to in the inventory list, if it is not successfully dropped.
        if (event.getTransferMode() != TransferMode.MOVE) {
            allEquipments.add(retrievedEquipment);
        }
        for(int i = 0; i < allEquipments.size(); i++) {
            if (allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                pos = i;
            }
        }
        if (pos != -1) {
            allEquipments.remove(pos);
        }
        Launcher.setAllEquipments(allEquipments);
        Launcher.refreshPane();
    }

}
