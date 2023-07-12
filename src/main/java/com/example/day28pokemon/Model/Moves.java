package com.example.day28pokemon.Model;

import java.util.List;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moves {
    private List<Move> level;
    private List<Move> tmhm;
    private List<Move> egg;
    private List<Move> gen34;
    private List<Move> tutor;

}
