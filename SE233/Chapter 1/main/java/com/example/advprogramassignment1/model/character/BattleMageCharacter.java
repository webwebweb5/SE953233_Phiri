package com.example.advprogramassignment1.model.character;

import com.example.advprogramassignment1.model.DamageType;

public class BattleMageCharacter extends BasedCharacter{
    // Exercise 2. Create a new character type namely Battlemage, whose fullHp and basedPower are both
    // 40. Note that you have to find your own image for the character and the image size must be
    // equal to that of the other two character types.
    public BattleMageCharacter(String name,String imgpath,int basedDef,int basedRes) {
        this.name = name;
        this.type = DamageType.battlemage;
        this.imgpath = imgpath;
        this.fullHP = 40;
        this.basedPow = 40;
        this.basedDef = basedDef;
        this.basedRes = basedRes;
        this.hp = this.fullHP;
        this.power = this.basedPow;
        this.defense = basedDef;
        this.resistance = basedRes;
    }
}
