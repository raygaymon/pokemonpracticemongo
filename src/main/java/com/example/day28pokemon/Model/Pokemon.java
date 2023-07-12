package com.example.day28pokemon.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    private String id;
    private String name;
    private List<String> type;
    private String url;
    private Stats stats;
    private Moves moves;
    private Map<String, String> damages;
    private Misc misc;

    public Moves createMoveMap (Document moves) {
            Moves m = new Moves();
            m.setLevel(this.setMoveType("level", moves));
            m.setTmhm(this.setMoveType("tmhm", moves));
            m.setGen34(this.setMoveTypeGen34("gen34", moves));
            m.setEgg(this.setMoveTypeTutorEgg("egg", moves));
            m.setTutor(this.setMoveTypeTutorEgg("tutor", moves));
            return m;
    }

    public List<Move> setMoveType (String movetype, Document moveDoc) {
        List<Document> moveDocs = (List<Document>) moveDoc.get(movetype);
        List<Move> moveList = new ArrayList<>();
        for (Document md : moveDocs) {
            Move move = new Move();
            move.setGen(md.getString("gen"));
            move.setLearnedAt(md.get("learnedat"));
            move.setName(md.getString("name"));
            moveList.add(move);
        }
        return moveList;
    }

    public List<Move> setMoveTypeTutorEgg (String movetype, Document moveDoc) {
        List<Document> moveDocs = (List<Document>) moveDoc.get(movetype);
        List<Move> moveList = new ArrayList<>();
        for (Document md : moveDocs) {
            Move move = new Move();
            move.setGen(md.getString("gen"));
            move.setName(md.getString("name"));
            moveList.add(move);
        }
        return moveList;
    }

    public List<Move> setMoveTypeGen34 (String movetype, Document moveDoc) {
        List<Document> moveDocs = (List<Document>) moveDoc.get(movetype);
        List<Move> moveList = new ArrayList<>();
        for (Document md : moveDocs) {
            Move move = new Move();
            move.setMethod(md.getString("method"));
            move.setName(md.getString("name"));
            moveList.add(move);
        }
        return moveList;
    }

    public Map<String, String> createDamages (Document pokemon) {
        Map<String, String> d = new HashMap<>();
        d.put("Normal", pokemon.getString("normal"));
        d.put("Fire",pokemon.getString("fire"));
        d.put("Water", pokemon.getString("water"));
        d.put("Electric", pokemon.getString("electric"));
        d.put("Grass", pokemon.getString("grass"));
        d.put("Ice", pokemon.getString("ice"));
        d.put("Fight", pokemon.getString("fight"));
        d.put("Poison", pokemon.getString("poison"));
        d.put("Ground", pokemon.getString("ground"));
        d.put("Flying", pokemon.getString("flying"));
        d.put("Psychic", pokemon.getString("psychic"));
        d.put("Bug", pokemon.getString("bug"));
        d.put("Rock", pokemon.getString("rock"));
        d.put("Ghost", pokemon.getString("ghost"));
        d.put("Dragon", pokemon.getString("dragon"));
        d.put("Dark", pokemon.getString("dark"));
        d.put("Steel", pokemon.getString("steel"));
        return d;
    }
}
