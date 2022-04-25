import java.io.IOException;

import java.util.Scanner;

public class MainSuite {

    private static String url = "";
    private static String srcLanguage = "";
    private static String trgLanguage = "";
    private static WebCrawler wbc;

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        runWebCrawler();
    }

    public static void runWebCrawler() {
        try {
            wbc = new WebCrawler();
                greet();
                getUserInputs();
                setLanguages();
                startCrawl();
        }catch(IOException e){
            System.out.println("Main Suite Problem: " + e.getMessage());
        }
    }

    private static void startCrawl() throws IOException {
        try {
            wbc.getPageLinks("https://" + url, 0);
        }catch (IOException e){
            System.out.println("Cannot connect to wbc: " + e.getMessage());
        }
    }
    private static void setLanguages(){
        wbc.setSrcLanguage(srcLanguage);
        wbc.setTrgLanguage(trgLanguage);
    }

    private static void getUserInputs(){
        System.out.println("Please insert a URL to probe:");
        url = scan.nextLine();
        System.out.println("Please insert a source language:");
        srcLanguage = scan.nextLine();
        System.out.println("Please insert a target language:");
        trgLanguage = scan.nextLine();
    }
    private static void greet(){ System.out.println("Welcome to Headings Translator");}
}
