package me.palombo.gamecore.map;

import java.util.HashMap;
import java.util.List;

public class Map {

    private String name;
    private String author;
    private String zip;

    private List<String> data;

    public Map(String name, String author, String zip, List<String> data) {
        this.name = name;
        this.author = author;
        this.zip = zip;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getZip() {
        return zip;
    }

    public List<String> getData() {
        return data;
    }

    public HashMap<String, String> getHashMapData(){
        HashMap<String, String> hm = new HashMap<String, String>();
        for (String s : getData()){
            String key = s.split(" ")[0];
            String value = s.substring(key.length() + 1); // + 1 for the space
            hm.put(key, value);
            //System.out.println(key + " : '" + value + "'");
        }
        return hm;
    }
}
