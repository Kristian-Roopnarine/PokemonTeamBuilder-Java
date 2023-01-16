package com.pokemonteambuilder.app;

/**
 * Hello world!
 *
 */
import com.gargoylesoftware.htmlunit.WebClient;
import com.opencsv.CSVReader;
import com.gargoylesoftware.htmlunit.BrowserVersion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.pokemonteambuilder.app.services.PokemonDbScraperService;
import com.pokemonteambuilder.app.services.FileWriterService;
import com.pokemonteambuilder.app.models.Pokemon;

public class App {

    public static void main(String[] args) {
        if (args.length > 0) {
            String firstArg = args[0];
            if (firstArg.equals("SCRAPE")) {
                System.out.println("Scraping Pokemon data first.");
                WebClient webClient = new WebClient(BrowserVersion.CHROME);
                PokemonDbScraperService pokemonDbScraperService = new PokemonDbScraperService(webClient);
                FileWriterService fileWriterService = new FileWriterService();
                try {
                    String outputString = pokemonDbScraperService.scrapePokemonData();
                    fileWriterService.saveToFile("all_pokemon_data.csv", outputString);
                } catch (IOException e){
                    System.out.println("Exception : " + e);
                } finally {
                    webClient.close();
                }
            }
        } else {
            System.out.println("No arguments passed to script. Will not scrape.");
        }

        System.out.println("Starting team builder application");

        // read pokemon from file
        ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
        try {
            FileReader fileReader = new FileReader("all_pokemon_data.csv");
            CSVReader csvReader = new CSVReader(fileReader);
            // first record is header
            String[] nextRecord = csvReader.readNext();
            // 970,Glimmora,Rock Poison,525,83,55,90,130,81,86
            while ((nextRecord = csvReader.readNext()) != null){
                String number = nextRecord[0];
                String name = nextRecord[1];
                String[] types = nextRecord[2].split(" ");
                String totalStat = nextRecord[3];
                String hp = nextRecord[4];
                String atk = nextRecord[5];
                String defense = nextRecord[6];
                String spAtk = nextRecord[7];
                String spDef = nextRecord[8];
                String speed = nextRecord[9];
                Pokemon currPokemon = new Pokemon(
                        number,
                        name,
                        types,
                        totalStat,
                        hp,
                        atk,
                        defense,
                        spAtk,
                        spDef,
                        speed
                        );
                pokemonList.add(currPokemon);
            }
            csvReader.close();
        } catch (FileNotFoundException e){
            System.out.println("Could not find file");
        } catch (IOException e){
            System.out.println("Error with input: " + e);
        }
        ArrayList<Pokemon> pokemonTeam = new ArrayList<Pokemon>();
        Scanner sc = new Scanner(System.in);
        while (pokemonTeam.size() < 6){
            System.out.print("Please enter the name of the pokemon you'd like to add to your team >>> ");
            String currPokemonStr = sc.nextLine();
            System.out.println("You've entered " + currPokemonStr + ". Searching pokemon list to see if this is a valid pokemon.");
            try {
                Pokemon pokemon = Pokemon.find(pokemonList, currPokemonStr);
                System.out.println("Found the pokemon " + pokemon.getName() + ". Adding to your team.");
                pokemonTeam.add(pokemon);
            } catch (NoSuchElementException e){
                System.out.println("There was a problem finding the pokemon " + currPokemonStr + ". Please ensure you enter a valid pokemon name.");
            }
            System.out.println("You have " + pokemonTeam.size() + " currently on your team.");
        }
        sc.close();
    }
}
