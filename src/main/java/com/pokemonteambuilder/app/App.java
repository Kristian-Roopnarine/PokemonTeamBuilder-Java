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

public class App 
{
    private final static WebClient webClient = new WebClient(BrowserVersion.CHROME);
    public static void main( String[] args )
    {
        final String URL = "https://pokemondb.net/pokedex/all";
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        String outputString = "";
        try {
            HtmlPage page = webClient.getPage(URL);
            HtmlTable pokedexTable = page.getHtmlElementById("pokedex");
            for (final HtmlTableRow row: pokedexTable.getRows()){
                String rowString = "";
                for(final HtmlTableCell cell: row.getCells()){
                    rowString += cell.getTextContent() + ",";

                }
                outputString += rowString + "\n";
            }
        } catch (IOException e ){
            System.out.println("Error : " + e);
        }

        final Path saveFilePath = Paths.get(System.getProperty("user.dir") + "/test_output.txt");
        try {
            Files.writeString(saveFilePath, outputString, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error saving data : " + e);
        } 
    }
}
