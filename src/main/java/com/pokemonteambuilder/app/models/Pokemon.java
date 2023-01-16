package com.pokemonteambuilder.app.models;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Pokemon {
    private String pokemonNumber;
    private String name;
    private String[] types;
    private String totalStat;
    private String hp;
    private String atk;
    private String defense;
    private String spAtk;
    private String spDef;
    private String speed;

    public Pokemon(String pokemonNumber, String name, String[] types, String totalStat, String hp, String atk, String defense, String spAtk, String spDef, String speed){
        this.pokemonNumber = pokemonNumber;
        this.name = name;
        this.types = types;
        this.totalStat = totalStat;
        this.hp = hp;
        this.atk = atk;
        this.defense = defense;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;
    }

    public static Pokemon find(ArrayList<Pokemon> pokemonList, String pokemonToValidate) throws NoSuchElementException{
        return pokemonList.stream().filter(pokemon -> pokemon.getName().equalsIgnoreCase(pokemonToValidate)).findFirst().get();
    }

    public String getPokemonNumber(){
        return pokemonNumber;
    }

    public String getName(){
        return name;
    }
    
    public String[] getTypes(){
        return types;
    }

    public String getTotalStat(){
        return totalStat;
    }

    public String getHp(){
        return hp;
    }

    public String getAtk(){
        return atk;
    }
    
    public String getDefense(){
        return defense;
    }

    public String getSpAtk(){
        return spAtk;
    }

    public String getSpDef(){
        return spDef;
    }

    public String getSpeed(){
        return speed;
    }

}
