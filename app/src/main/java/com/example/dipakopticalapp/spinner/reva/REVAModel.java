package com.example.dipakopticalapp.spinner.reva;

import java.util.ArrayList;

public class REVAModel {
    private int id;
    private String name;

    public REVAModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<REVAModel> getREVAModelList() {

        ArrayList list = new ArrayList();

        list.add(new REVAModel( 1,"6/6"));

        list.add(new REVAModel( 2,"6/9"));

        list.add(new REVAModel( 3,"6/12"));

        list.add(new REVAModel( 4,"6/24"));

        return list;

    }
}
