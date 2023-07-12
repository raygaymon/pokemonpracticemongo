package com.example.day28pokemon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Move {
    private Object learnedAt;
    private Object name;
    private Object gen;
    private Object method;

    public Move(String name, String genOrMethod) {
        this.name = name;
        this.gen = genOrMethod;
    }


    
}
