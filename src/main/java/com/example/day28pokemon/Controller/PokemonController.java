package com.example.day28pokemon.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.day28pokemon.Model.Moves;
import com.example.day28pokemon.Model.Pokemon;
import com.example.day28pokemon.Model.Stats;
import com.example.day28pokemon.Service.PokemonService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {
    @Autowired
    private PokemonService service;
    
    @GetMapping("/types")
    public String showTypes (Model m) {
        List<String> types = service.getTypes();
        m.addAttribute("types", types);
        return "homepage";
    }

    @GetMapping("/types/{type}")
    public String getPokemonsByType (@PathVariable("type") String type, Model m) {
        System.out.println(type);
        AggregationResults<Document> pokemonDocs = service.loadPokemonByType(type);

        List<Pokemon> pokemons = new ArrayList<>();

        for (Document d : pokemonDocs) {
            Pokemon p = new Pokemon();
            p.setId(d.getString("id"));
            p.setName(d.getString("name"));
            p.setUrl(d.getString("img"));
            pokemons.add(p);
        }
        if(pokemons.isEmpty()){
            System.out.println("fucking loser");
        }

        m.addAttribute("pokemons", pokemons);
        return "pokemons";
    }

    @GetMapping("/{pokemon}")
    public String findPokemon (@PathVariable("pokemon") String name, Model m) {
        List<Document> foundPokemon = service.findPokemon(name);

        List<Pokemon> pokemon = new ArrayList<>();

        for (Document d : foundPokemon) {
            Pokemon p = new Pokemon();
            Document stat= (Document) d.get("stats");
            Document moves = (Document) d.get("moves");
            Document damages = (Document) d.get("damages");
            Document misc = (Document) d.get("misc");
            Stats s = new Stats();
            s.setAll(stat, s);
            p.setId(d.getString("id"));
            p.setName(d.getString("name"));
            p.setUrl(d.getString("img"));
            p.setStats(s);
            p.setType(d.getList("type", String.class));
            p.setMoves(p.createMoveMap(moves));
            p.setDamages(p.createDamages(damages));
            p.setMisc(service.setMisc(misc));
            pokemon.add(p);
        }

        m.addAttribute("pokemon", pokemon);
        return "pokemon";

    }

}
