package com.pokemonteambuilder.app.services;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import java.io.IOException;
import java.util.List;

public class PokemonDbScraperService {
    private WebClient webClient;
    private String POKEMON_DB_URL = "https://pokemondb.net/pokedex/all";

    public PokemonDbScraperService(WebClient webClient){
        this.webClient = webClient;
        this.webClient.getOptions().setJavaScriptEnabled(false);
        this.webClient.getOptions().setCssEnabled(false);
    }

    private HtmlPage getHtml() throws IOException {
        return this.webClient.getPage(this.POKEMON_DB_URL);
    } 

    private HtmlTable getPokdexTable(String htmlId, HtmlPage scrapedHtml){
        return scrapedHtml.getHtmlElementById(htmlId);
    }

    private String getOutputDataString(HtmlTable pokedexTable){
        String outputString = "";
        List<HtmlTableRow> rows = pokedexTable.getRows();
        for(int i = 0; i < rows.size(); i++){
            HtmlTableRow currRow = rows.get(i);
            List<HtmlTableCell> currCells = currRow.getCells();
            String[] cellStrData = new String[currCells.size()];
            for(int j = 0; j < currCells.size(); j++){
                cellStrData[j] = currCells.get(j).getTextContent();
            }
            String rowString = String.join(",", cellStrData);
            outputString += rowString + "\n";
        }
        return outputString;
    }

    public String scrapePokemonData() throws IOException {
        HtmlPage page = getHtml();
        HtmlTable pokedexTable = getPokdexTable("pokedex", page);
        String output = getOutputDataString(pokedexTable);
        return output;
    }

}
