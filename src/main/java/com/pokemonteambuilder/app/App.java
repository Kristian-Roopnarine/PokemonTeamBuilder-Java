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
import java.io.IOException;

public class App 
{
    private final static WebClient webClient = new WebClient(BrowserVersion.CHROME);
    public static void main( String[] args )
    {
        final String URL = "https://pokemondb.net/pokedex/all";
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        try {
            HtmlPage page = webClient.getPage(URL);
            HtmlTable pokedexTable = page.getHtmlElementById("pokedex");
            for (final HtmlTableRow row: pokedexTable.getRows()){
                System.out.println(row.getTextContent());
                /*
                for (final HtmlTableCell cell: row.getCells()){
                    System.out.println(cell.asNormalizedText());
                }
                */
            }
        } catch (IOException e ){
            System.out.println("Error : " + e);
        }
    }
}
