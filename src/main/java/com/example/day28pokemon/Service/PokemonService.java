package com.example.day28pokemon.Service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import com.example.day28pokemon.Model.Abilities;
import com.example.day28pokemon.Model.Misc;
import com.example.day28pokemon.Model.Sex;
import com.example.day28pokemon.Repository.PokemonRepo;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepo repo;

    public List<String> getTypes (){
        return repo.getAllTypes();
    }

    public AggregationResults<Document> loadPokemonByType (String type) {
        return repo.loadPokemonbyType(type);
    }

    public List<Document> loadAllPokemon () {
        return repo.loadPokemon();
    }

    public List<Document> findPokemon (String name) {
        return repo.getPokemon(name);
    }

    public Sex extractSex (Document d) {
        Document getSex = (Document) d.get("sex");
        Sex s = new Sex();
        s.setMale(getSex.get("male"));
        s.setFemale(getSex.get("female"));
        
        return s;
    }

    public Abilities extractAbilities(Document d) {
        Document abilitiesDoc = (Document) d.get("abilities");
        List<String> normalA = abilitiesDoc.getList("normal", String.class);
        List<String> hiddenA = abilitiesDoc.getList("hidden", String.class);
        Abilities a = new Abilities();
        a.setHidden(hiddenA);
        a.setNormal(normalA);
        return a;
    }

    public List<String> extractEV (Document d) {
        return d.getList("evpoints", String.class);
    }

    public Misc setMisc (Document d) {
        Misc m = new Misc();
        m.setSex(this.extractSex(d));
        m.setAbilities(this.extractAbilities(d));
        m.setClassification(d.getString("classification"));
        m.setEvPoints(this.extractEV(d));
        m.setHeight(d.getString("height"));
        m.setWeight(d.getString("weight"));
        m.setEggSteps(d.get("eggsteps"));
        m.setCaptureRate(d.get("capturerate"));
        m.setExpGrowth(d.get("expgrowth"));
        m.setHappiness(d.get("happiness"));
        m.setFleeFlag(d.getString("fleeflag"));
        m.setEntreeForestLevel(d.getString("entreeforestlevel"));
        return m;
    }

}
