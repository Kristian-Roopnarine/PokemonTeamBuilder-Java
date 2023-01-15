package com.pokemonteambuilder.app;

/**
 * Hello world!
 *
 */
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class App {
    private final static WebClient webClient = new WebClient(BrowserVersion.CHROME);

    public static void main(String[] args) {
        if (args.length > 0) {
            String firstArg = args[0];
            // Can't use firstArg == SCRAPE because java will compare the references of the
            // two string not the values
            if (firstArg.equals("SCRAPE")) {
                System.out.println("Scraping Pokemon data first.");
                final String URL = "https://pokemondb.net/pokedex/all";
                webClient.getOptions().setJavaScriptEnabled(false);
                webClient.getOptions().setCssEnabled(false);
                String outputString = "";
                try {
                    HtmlPage page = webClient.getPage(URL);
                    HtmlTable pokedexTable = page.getHtmlElementById("pokedex");
                    List<HtmlTableRow> rows = pokedexTable.getRows();
                    // Example of the Row data
                    // Number, Name (could have other form), Types (could be multiple)
                    // Index 3-9 are the stats
                    // 003,Venusaur Mega Venusaur,Grass Poison,625,80,100,123,122,120,80,
                    for (int i = 1; i < rows.size(); i++) {
                        HtmlTableRow currRow = rows.get(i);
                        List<HtmlTableCell> currCells = currRow.getCells();
                        String[] cellData = new String[currCells.size()];
                        for (int j = 0; j < currCells.size(); j++) {
                            cellData[j] = currCells.get(j).getTextContent();
                        }
                        String rowString = String.join(",", cellData);
                        outputString += rowString + "\n";
                    }
                } catch (IOException e) {
                    System.out.println("Error : " + e);
                }

                final Path saveFilePath = Paths.get(System.getProperty("user.dir") + "/test_output.csv");
                try {
                    Files.writeString(saveFilePath, outputString, StandardCharsets.UTF_8);
                } catch (IOException e) {
                    System.out.println("Error saving data : " + e);
                }
                return;
            }
        } else {
            System.out.println("No arguments passed to script. Will not scrape.");
        }
    }
}
