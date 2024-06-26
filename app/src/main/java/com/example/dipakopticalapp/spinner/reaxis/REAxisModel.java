package com.example.dipakopticalapp.spinner.reaxis;

import com.example.dipakopticalapp.spinner.leaxis.LEAxisModel;
import com.example.dipakopticalapp.spinner.reva.REVAModel;

import java.util.ArrayList;

public class REAxisModel {
    private int id;
    private String name;

    public REAxisModel(int id, String name) {
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

    public static ArrayList<REAxisModel> getREAxisModelList() {

        ArrayList list = new ArrayList();

        list.add(new REAxisModel( 1,"5"));

        list.add(new REAxisModel( 2,"10"));

        list.add(new REAxisModel( 3,"15"));

        list.add(new REAxisModel( 4,"20"));

        list.add(new REAxisModel( 5,"25"));

        list.add(new REAxisModel( 6,"30"));

        list.add(new REAxisModel( 7,"35"));

        list.add(new REAxisModel( 8,"40"));

        list.add(new REAxisModel( 9,"45"));

        list.add(new REAxisModel( 10,"50"));

        list.add(new REAxisModel( 11,"55"));

        list.add(new REAxisModel( 12,"60"));

        list.add(new REAxisModel( 13,"65"));

        list.add(new REAxisModel( 14,"70"));

        list.add(new REAxisModel( 15,"75"));

        list.add(new REAxisModel( 16,"80"));

        list.add(new REAxisModel( 17,"85"));

        list.add(new REAxisModel( 18,"90"));

        list.add(new REAxisModel( 19,"95"));

        list.add(new REAxisModel( 20,"100"));

        list.add(new REAxisModel( 21,"105"));

        list.add(new REAxisModel( 22,"110"));

        list.add(new REAxisModel( 23,"115"));

        list.add(new REAxisModel( 24,"120"));

        list.add(new REAxisModel( 25,"125"));

        list.add(new REAxisModel( 26,"130"));

        list.add(new REAxisModel( 27,"135"));

        list.add(new REAxisModel( 28,"140"));

        list.add(new REAxisModel( 29,"145"));

        list.add(new REAxisModel( 30,"150"));

        list.add(new REAxisModel( 31,"155"));

        list.add(new REAxisModel( 32,"160"));

        list.add(new REAxisModel( 33,"165"));

        list.add(new REAxisModel( 34,"170"));

        list.add(new REAxisModel( 35,"175"));

        list.add(new REAxisModel( 36,"180"));

        return list;

    }
}
