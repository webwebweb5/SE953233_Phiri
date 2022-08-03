package com.example.advprogramassignment1.controller;

import com.example.advprogramassignment1.model.DamageType;
import com.example.advprogramassignment1.model.item.Armor;
import com.example.advprogramassignment1.model.item.BasedEquipment;
import com.example.advprogramassignment1.model.item.Weapon;

import java.util.ArrayList;

public class GenItemList {
        public static ArrayList<BasedEquipment> setUpItemList() {
            ArrayList<BasedEquipment> itemLists = new ArrayList<BasedEquipment>(6);
            itemLists.add(new Weapon("Sword", 10, DamageType.physical, "assets/sword1.png"));
            itemLists.add(new Weapon("Gun", 20, DamageType.physical, "assets/gun1.png"));
            itemLists.add(new Weapon("Staff", 30, DamageType.magical, "assets/staff1.png"));
            itemLists.add(new Armor("Shirt", 0, 50, "assets/shirt1.png"));
            itemLists.add(new Armor("Armor", 50, 0, "assets/armor1.png"));
            // Exercise 1. Add one more item to the item list, its image size should be equal to that of the other images.
            itemLists.add(new Weapon("Axe", 60, DamageType.battlemage, "assets/axe1.png"));
            return itemLists;
        }
    }

