package com.pokemonteambuilder.app;

/**
 * Hello world!
 *
 */
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
            System.out.println(page.asXml());
        } catch (IOException e ){
            System.out.println("Error : " + e);
        }
    }
}
