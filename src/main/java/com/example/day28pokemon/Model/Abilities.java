package com.example.day28pokemon.Model;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abilities {
    private List<String> normal;
    private List<String> hidden;

    @Override
    public String toString() {
        return normal.toString().replace("[", "").replace("]", "") + " <br /> " + hidden.toString().replace("]", "").replace("[", "") + " (Hidden)" ;
    }

    
}
