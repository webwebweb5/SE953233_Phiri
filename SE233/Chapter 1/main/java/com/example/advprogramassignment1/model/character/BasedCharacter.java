package com.example.advprogramassignment1.model.character;

import com.example.advprogramassignment1.model.DamageType;
import com.example.advprogramassignment1.model.item.Armor;
import com.example.advprogramassignment1.model.item.Weapon;

public class BasedCharacter {
    protected String name, imgpath;
    protected DamageType type;
    protected Integer fullHP, basedPow, basedDef, basedRes;
    protected Integer hp, power, defense, resistance;
    protected Weapon weapon;
    protected Armor armor;

    public void equipWeapon(Weapon weapon){
        this.weapon = weapon;
        this.power = this.basedPow + weapon.getPower();
    }

    public void equipArmor(Armor armor) {
        this.armor = armor;
        this.defense = this.basedDef + armor.getDefense();
        this.resistance = this.basedRes + armor.getResistance();
    }

    public String getName() {
        return name;
    }

    public String getImgpath() {
        return imgpath;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getFullHP() {
        return fullHP;
    }

    public Integer getPower() {
        return power;
    }

    @Override
    public String toString() {
        return name;
    }

    public Integer getDefense() {
        return defense;
    }

    public Integer getResistance() {
        return resistance;
    }
    public DamageType getType() {
        return type;
    }

    public void unequipWeapon() {
        this.power = basedPow;
    }

    public void unequipArmor() {
        this.defense = this.basedDef;
        this.resistance = this.basedRes;
    }

}
