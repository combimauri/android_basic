package com.example.mauricioarce.addressbook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio Arce on 30/06/2015.
 */
public class ItemsGroup {

    private String text;
    private final List<String> children = new ArrayList<>();

    public ItemsGroup(String text) {
        this.text = text;
    }

    public List<String> getChildren() {
        return children;
    }

    public String getText() {
        return text;
    }

}
