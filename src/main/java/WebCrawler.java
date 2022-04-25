import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class WebCrawler {
    private final LinkedList<String>urlLinks;  //Here are stored all the links and headings found in the page
    private final LinkedList<String> headings;
    private int MAX_DEPTH = 2;

    private String srcLanguage;
    private String trgLanguage;

    private int depth;

    public WebCrawler() {
            urlLinks = new LinkedList<>();
            headings = new LinkedList<>();
    }

    public void getPageLinks(String Url, int tempDepth) throws IOException {
            Document doc = Jsoup.connect(Url).get();
            Elements availableLinksOnPage = doc.select("a[href]");

                if (!urlLinks.contains(Url) && tempDepth <= MAX_DEPTH) {

                    urlLinks.add(Url);

                    getHeadings(Url);

                    writeToFile();

                    depth = tempDepth++;

                    for (Element link : availableLinksOnPage) { //for each of the URLs execute recursively the function
                        if(tempDepth < MAX_DEPTH){
                            getPageLinks(link.attr("abs:href"), tempDepth);
                        }else{
                            break;
                        }
                    }
                }
    }


    public void setMAX_DEPTH(int MAX_DEPTH) {
        this.MAX_DEPTH = MAX_DEPTH;
    }

    public void getHeadings(String Url) throws IOException {
            Document doc = Jsoup.connect(Url).get();
            for(int headerCounter = 1; headerCounter < 6; headerCounter++ ) {
                Elements headers = doc.select("h" + headerCounter);
                for (Element header : headers) {
                    headings.add("--> H" + headerCounter + ": " + Translator.getTranslation(header.text(),srcLanguage,trgLanguage));
                }
            }
    }
    public void writeToFile() throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.md"));

            writer.write("input: <a>" + urlLinks.get(0) + " </a>\n");
            writer.write("<br>depth: " + depth + "\n");
            writer.write("<br>source language: " + srcLanguage + "\n");
            writer.write("<br>target language: " + trgLanguage + "\n");
            writer.write("<br>summary: " + "\n");
            for (String heading : headings) {
                writer.write(heading+"\n");
            }
            writer.close();

    }

    public void setSrcLanguage(String src){
      srcLanguage = src;
    }
    public void setTrgLanguage(String trg){
        trgLanguage = trg;
    }
}