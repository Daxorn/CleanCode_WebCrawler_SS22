

import java.util.LinkedList;
import java.util.Scanner;

public class MainSuite {

    private static LinkedList<String> urls;
    private static String srcLanguage = "";
    private static String trgLanguage = "";
    private static WebCrawler wbc;
    private static int numberOfSites = 0;

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        runWebCrawler();
    }

    public static void runWebCrawler() {
        try {
                wbc = new WebCrawler();
                greet();
                getUserInputs();
                setLanguages();
                startCrawl();
        }catch(Exception e){
            System.out.println("Main Suite Problem: " + e.getMessage());
        }
    }

    private static void startCrawl() {
        try {
            wbc.crawl(urls);
        }catch (Exception e){
            System.out.println("Cannot connect to wbc: " + e.getMessage());
        }
    }
    private static void setLanguages(){
        wbc.setSrcLanguage(srcLanguage);
        wbc.setTrgLanguage(trgLanguage);
    }

    private static void getUserInputs(){
        System.out.println("Please insert the number of URLs to probe:");
        numberOfSites = scan.nextInt();
        getUrls();
        System.out.println("Please insert a source language:");
        srcLanguage = scan.nextLine();
        System.out.println("Please insert a target language:");
        trgLanguage = scan.nextLine();
    }
    private static void getUrls(){
        for(int i = 0; i < numberOfSites; i++){
            System.out.println("Please insert an URL to probe:");
            urls.add(scan.nextLine());
        }
    }
    private static void greet(){ System.out.println("Welcome to Headings Translator");}
}
