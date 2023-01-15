package com.pokemonteambuilder.app;

/**
 * Hello world!
 *
 */
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import java.io.IOException;

import com.pokemonteambuilder.app.services.PokemonDbScraperService;
import com.pokemonteambuilder.app.services.FileWriterService;

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
                return;
            }
        } else {
            System.out.println("No arguments passed to script. Will not scrape.");
        }
    }
}
