package com.example.dipakopticalapp.spinner.leva;

import com.example.dipakopticalapp.spinner.reva.REVAModel;

import java.util.ArrayList;

public class LEVAModel {
    private int id;
    private String name;

    public LEVAModel(int id, String name) {
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

    public static ArrayList<LEVAModel> getLEVAModelList() {

        ArrayList list = new ArrayList();

        list.add(new LEVAModel( 1,"6/6"));

        list.add(new LEVAModel( 2,"6/9"));

        list.add(new LEVAModel( 3,"6/12"));

        list.add(new LEVAModel( 4,"6/24"));

        return list;

    }
}
