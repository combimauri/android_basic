package com.example.mauricioarce.catfacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio Arce on 16/07/2015.
 */
public class Facts {

    private List<String> catFacts;

    public Facts() {
        catFacts = new ArrayList<>();
    }

    public List<String> getCatFacts() {
        return catFacts;
    }

    public void addFacts(String fact) {
        catFacts.add(fact);
    }
}
