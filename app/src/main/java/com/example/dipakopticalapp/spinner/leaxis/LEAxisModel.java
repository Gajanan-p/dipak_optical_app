package com.example.dipakopticalapp.spinner.leaxis;

import com.example.dipakopticalapp.spinner.reva.REVAModel;

import java.util.ArrayList;

public class LEAxisModel {
    private int id;
    private String name;

    public LEAxisModel(int id, String name) {
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

    public static ArrayList<LEAxisModel> getLEAxisModelList() {

        ArrayList list = new ArrayList();

        list.add(new LEAxisModel( 1,"5"));

        list.add(new LEAxisModel( 2,"10"));

        list.add(new LEAxisModel( 3,"15"));

        list.add(new LEAxisModel( 4,"20"));

        list.add(new LEAxisModel( 5,"25"));

        list.add(new LEAxisModel( 6,"30"));

        list.add(new LEAxisModel( 7,"35"));

        list.add(new LEAxisModel( 8,"40"));

        list.add(new LEAxisModel( 9,"45"));

        list.add(new LEAxisModel( 10,"50"));

        list.add(new LEAxisModel( 11,"55"));

        list.add(new LEAxisModel( 12,"60"));

        list.add(new LEAxisModel( 13,"65"));

        list.add(new LEAxisModel( 14,"70"));

        list.add(new LEAxisModel( 15,"75"));

        list.add(new LEAxisModel( 16,"80"));

        list.add(new LEAxisModel( 17,"85"));

        list.add(new LEAxisModel( 18,"90"));

        list.add(new LEAxisModel( 19,"95"));

        list.add(new LEAxisModel( 20,"100"));

        list.add(new LEAxisModel( 21,"105"));

        list.add(new LEAxisModel( 22,"110"));

        list.add(new LEAxisModel( 23,"115"));

        list.add(new LEAxisModel( 24,"120"));

        list.add(new LEAxisModel( 25,"125"));

        list.add(new LEAxisModel( 26,"130"));

        list.add(new LEAxisModel( 27,"135"));

        list.add(new LEAxisModel( 28,"140"));

        list.add(new LEAxisModel( 29,"145"));

        list.add(new LEAxisModel( 30,"150"));

        list.add(new LEAxisModel( 31,"155"));

        list.add(new LEAxisModel( 32,"160"));

        list.add(new LEAxisModel( 33,"165"));

        list.add(new LEAxisModel( 34,"170"));

        list.add(new LEAxisModel( 35,"175"));

        list.add(new LEAxisModel( 36,"180"));


        return list;

    }
}
