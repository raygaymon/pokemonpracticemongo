package com.example.day28pokemon.Model;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
    private Object hp;
    private Object attack;
    private Object defense;
    private Object spattack;
    private Object spdefense;
    private Object speed;

    @Override
    public String toString() {
        return "HP: " + hp + "\nAttack: " + attack + "\nDefense: " + defense + "\nSpecial Attack: " + spattack
                + "\nSpecial Defense: " + spdefense + "\nSpeed: " + speed;
    }

    public void setAll (Document stat, Stats s) {
        s.setHp(stat.get("hp"));
        s.setAttack(stat.get("attack"));
        s.setDefense(stat.get("defense"));
        s.setSpattack(stat.get("spattack"));
        s.setSpdefense(stat.get("spdefense"));
        s.setSpeed(stat.get("speed"));
    }
}
