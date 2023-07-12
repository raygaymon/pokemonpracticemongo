package com.example.day28pokemon.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Misc {
    private Sex sex;
    private Abilities abilities;
    private String classification;
    private String height;
    private String weight;
    private Object eggSteps;
    private Object expGrowth;
    private Object happiness;
    private Object captureRate;
    private Object evPoints;
    private String fleeFlag;
    private String entreeForestLevel;
  
}
