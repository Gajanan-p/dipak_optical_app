package com.example.dipakopticalapp.spinner.gender;



import java.util.ArrayList;

public class GenderModel {
    private int id;
    private String name;

    public GenderModel(int id, String name) {
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

    public static ArrayList<GenderModel> getGenderModelList() {

        ArrayList list = new ArrayList();

        list.add(new GenderModel( 1,"Male"));

        list.add(new GenderModel( 2,"Female"));

        list.add(new GenderModel( 3,"Other"));

        return list;

    }
}
