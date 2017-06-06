package me.palombo.gamecore.util;

public class M {

    public static final String ARROW_CHARACTER = "»";

    public static String regular(String msg){
        return msg;
    }

    public static String error(String msg){
        return "§c" + msg;
    }

    public static String arrow(String msg){
        return "§8» §7" + msg;
    }

    public static String line(String prefix) {
        return prefix + "§m" + "-----------------------------------------------------";
    }

}