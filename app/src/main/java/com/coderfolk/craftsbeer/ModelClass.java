package com.coderfolk.craftsbeer;

public class ModelClass {
    String abv;
    String ibu;
    int id;
    String name;
    String style;
    float ounces;

    public ModelClass(String abv, String ibu, int id, String name, String style, float ounces){
        this.abv=abv;
        this.ibu=ibu;
        this.id=id;
        this.name=name;
        this.style=style;
        this.ounces=ounces;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public float getOunces() {
        return ounces;
    }

    public void setOunces(float ounces) {
        this.ounces = ounces;
    }
}
