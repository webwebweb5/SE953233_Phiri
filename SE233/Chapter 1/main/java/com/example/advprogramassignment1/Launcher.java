package com.example.advprogramassignment1;

import com.example.advprogramassignment1.controller.GenCharacter;
import com.example.advprogramassignment1.controller.GenItemList;
import com.example.advprogramassignment1.model.character.BasedCharacter;
import com.example.advprogramassignment1.model.item.Armor;
import com.example.advprogramassignment1.model.item.BasedEquipment;
import com.example.advprogramassignment1.model.item.Weapon;
import com.example.advprogramassignment1.view.CharacterPane;
import com.example.advprogramassignment1.view.EquipPane;
import com.example.advprogramassignment1.view.InventoryPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Launcher extends Application {
    private static Scene mainScene;
    private static BasedCharacter mainCharacter = null;

    private static ArrayList<BasedEquipment> allEquipments = null;
    private static Weapon equippedWeapon = null;
    private static Armor equippedArmor = null;
    private static CharacterPane characterPane = null;
    private static EquipPane equipPane = null;
    private static InventoryPane inventoryPane = null;

    public static ArrayList<BasedEquipment> getAllEquipments() {
        return allEquipments;
    }

    public static void setAllEquipments(ArrayList<BasedEquipment> allEquipments) {
        Launcher.allEquipments = allEquipments;
    }

    public static Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public static void setEquippedWeapon(Weapon equippedWeapon) {
        Launcher.equippedWeapon = equippedWeapon;
    }

    public static Armor getEquippedArmor() {
        return equippedArmor;
    }

    public static void setEquippedArmor(Armor equippedArmor) {
        Launcher.equippedArmor = equippedArmor;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Intro to RPG");
        primaryStage.setResizable(false);
        primaryStage.show();
        mainCharacter = GenCharacter.setUpCharacter();
        allEquipments = GenItemList.setUpItemList();

        Pane mainPane = getMainPane();
        mainScene = new Scene(mainPane);
        primaryStage.setScene(mainScene);
    }
    public Pane getMainPane() {
        BorderPane mainPane = new BorderPane();
        characterPane = new CharacterPane();
        equipPane = new EquipPane();
        inventoryPane = new InventoryPane();
        refreshPane();
        mainPane.setCenter(characterPane);
        mainPane.setLeft(equipPane);
        mainPane.setBottom(inventoryPane);
        return mainPane;
    }

    public static void refreshPane() {
        characterPane.drawPane(mainCharacter);
        equipPane.drawPane(equippedWeapon,equippedArmor);
        inventoryPane.drawPane(allEquipments);
    }

    public static BasedCharacter getMainCharacter() {
        return mainCharacter;
    }

    public static void setMainCharacter(BasedCharacter mainCharacter) {
        Launcher.mainCharacter = mainCharacter;
    }

    public static void main(String[] args) {
        launch(args);
    }
}