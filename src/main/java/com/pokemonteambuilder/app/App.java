package com.pokemonteambuilder.app;

/**
 * Hello world!
 *
 */
import com.gargoylesoftware.htmlunit.WebClient;
public class App 
{
    private final static WebClient webClient = new WebClient();
    public static void main( String[] args )
    {
        System.out.println(webClient);
        System.out.println( "Hello World!" );
    }
}
