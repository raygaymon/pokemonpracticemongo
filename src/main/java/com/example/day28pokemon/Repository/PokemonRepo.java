package com.example.day28pokemon.Repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PokemonRepo {
    @Autowired
    private MongoTemplate template;

    public List<Document> loadPokemon () {
        AggregationOperation unwindTypes = Aggregation.unwind("type");
        GroupOperation groupByTypes = Aggregation.group("type")
                                        .push("id").as("id")
                                        .push("name").as("name")
                                        .push("img").as("imageurl");
        ProjectionOperation projectTypeSummary = Aggregation.project("id", "name", "imageurl");
        SortOperation sortById = Aggregation.sort(Sort.by(Direction.ASC, "id"));
        Aggregation pipeline = Aggregation.newAggregation(unwindTypes, groupByTypes, projectTypeSummary, sortById);
        AggregationResults<Document> results = template.aggregate(pipeline, "pokemon", Document.class);
        return results.getMappedResults();
    }

    public AggregationResults<Document> loadPokemonbyType (String type) {
        AggregationOperation unwindTypes = Aggregation.unwind("type");
        MatchOperation matchType = Aggregation.match(
            Criteria.where("type").is(type)
            );                                
        ProjectionOperation projectTypeSummary = Aggregation.project("id", "name", "img");
        SortOperation sortById = Aggregation.sort(Sort.by(Direction.ASC, "id"));
        Aggregation pipeline = Aggregation.newAggregation(unwindTypes, matchType, projectTypeSummary, sortById);
        AggregationResults<Document> results = template.aggregate(pipeline, "pokemon", Document.class);

        return results;
    }

    public List<String> getAllTypes () {
        AggregationOperation unwindTypes = Aggregation.unwind("type");
        Aggregation pipeline = Aggregation.newAggregation(unwindTypes);
        AggregationResults<Document> results = template.aggregate(pipeline, "pokemon", Document.class);
        List<String> types = new ArrayList<>();
        for (Document d : results.getMappedResults()) {
            String type = d.getString("type");
            if (!types.contains(type)){
                types.add(type);
            }
        }

        if(types.isEmpty()) {
            System.out.println("Lmao you suck its emtpy");
        }

        return types.stream().distinct().toList();
    }

    public List<Document> getPokemon (String name) {
        Criteria c = Criteria.where("name").is(name);
        Query q = Query.query(c);
        return template.find(q, Document.class, "pokemon");
    }

    // public List<Document> getPokemonMoves (String name, String moveType) {
    //     Criteria c = Criteria.where("name").is(name);
    //     Query q = Query.query(c);
    //     List<Document> pokemon = template.find(q, Document.class, "pokemon");
    //     List<Document> moves = new ArrayList<>();
    //     for (Document d : pokemon) {

    //     }
    // }

    
}
