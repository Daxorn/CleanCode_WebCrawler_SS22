
import java.util.Scanner;

public class MainSuite extends Thread {

    private static String url;
    private static String srcLanguage = "";
    private static String trgLanguage = "";
    private static WebCrawler wbc;
    private Thread t;


    static Scanner scan = new Scanner(System.in);

    public void run(){
            runWebCrawler();
            System.out.println("Thread exiting.");

    }
    public void start(){
        System.out.println("Starting thread");
        if (t == null){
            t = new Thread(this);
            t.start();
        }
    }
    public static void runWebCrawler() {
        synchronized (wbc) {
            try {
                wbc = new WebCrawler();
                greet();
                getUserInputs();
                setLanguages();
                startCrawl();
            } catch (Exception e) {
                System.out.println("Main Suite Problem: " + e.getMessage());
            }
        }
    }

    private static void startCrawl() {
        try {
            wbc.crawlLinks(url);
        }catch (Exception e){
            System.out.println("Cannot connect to wbc: " + e.getMessage());
        }
    }
    private static void setLanguages(){
        wbc.setSrcLanguage(srcLanguage);
        wbc.setTrgLanguage(trgLanguage);
    }

    private static void getUserInputs(){
        System.out.println("Please insert the URL to probe:");
        url = scan.nextLine();
        System.out.println("Please insert a source language:");
        srcLanguage = scan.nextLine();
        System.out.println("Please insert a target language:");
        trgLanguage = scan.nextLine();
    }

    private static void greet(){ System.out.println("Welcome to Headings Translator");}
}
