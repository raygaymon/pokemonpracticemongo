package com.example.day28pokemon.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sex {
    private Object male;
    private Object female;
    @Override
    public String toString() {
        return "Male : " + male + " Female : " + female;
    }

    
}
