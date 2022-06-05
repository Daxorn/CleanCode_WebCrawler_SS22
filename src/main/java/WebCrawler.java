import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.*;

public class WebCrawler{
    private final LinkedList<String> urlLinks;
    private final LinkedList<String> headings;
    private int MAX_DEPTH = 2;

    private String srcLanguage;

    private String trgLanguage;

    private int depth;
    private Elements availableLinksOnPage;

    public WebCrawler() {
            urlLinks = new LinkedList<>();
            headings = new LinkedList<>();
    }

    public void crawlLinks(String url) throws Exception {
        try {
                getPageLinks("https://" + url, 0);
        }catch(Exception e){
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.md"));
            writer.write("Could not crawl | " + e.getMessage());
            writer.close();
        }
    }

    public void getPageLinks(String Url, int tempDepth) throws Exception {

           getLinksFrom(Url);
                if (!urlLinks.contains(Url) && tempDepth <= MAX_DEPTH) {

                    urlLinks.add(Url);

                    getTranslatedHeadingsFrom(Url);

                    writeToFile();

                    depth = tempDepth++;

                    getChildrenLinks(availableLinksOnPage,tempDepth);
                }
    }
    public void getLinksFrom(String Url) throws Exception {
        try {
            Document doc = Jsoup.connect(Url).get();
            availableLinksOnPage = doc.select("a[href]");
        }catch (Exception e){
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.md"));
            writer.write("Could not get links from: " + Url + " | " + e.getMessage());
            writer.close();
        }
    }
    public void getChildrenLinks(Elements links, int depth) throws Exception {
        try {
            for (Element link : links) {
                if (depth < MAX_DEPTH) {
                    getPageLinks(link.attr("abs:href"), depth);
                } else {
                    break;
                }
            }
        }catch (Exception e){
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.md"));
            writer.write("Could not get children links  | " + e.getMessage());
            writer.close();
        }
    }

    public void setMAX_DEPTH(int MAX_DEPTH) {
        this.MAX_DEPTH = MAX_DEPTH;
    }

    public void getTranslatedHeadingsFrom(String Url) throws Exception {
        try {
            Document doc = Jsoup.connect(Url).get();
            Translator ts = new Translator(srcLanguage, trgLanguage);
            for (int headerCounter = 1; headerCounter < 6; headerCounter++) {
                Elements headers = doc.select("h" + headerCounter);
                for (Element header : headers) {
                    headings.add("--> H" + headerCounter + ": " + ts.getTranslation(header.text()));
                }
            }
        }catch (Exception e){
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.md"));
            writer.write("Could not translate headings | " + e.getMessage());
            writer.close();
        }
    }

    public void writeToFile() throws Exception {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.md"));

            writer.write("input: <a>" + urlLinks.get(0) + " </a>\n");
            writer.write("<br>depth: " + depth + "\n");
            writer.write("<br>source language: " + srcLanguage + "\n");
            writer.write("<br>target language: " + trgLanguage + "\n");
            writer.write("<br>summary:\n ");
            for (String heading : headings) {
                writer.write("<br>" + heading + "\n");
            }
            headings.clear();
            writer.close();
        }catch(Exception e){
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.md"));
            writer.write("Could not write to file | " + e.getMessage());
            writer.close();
        }
    }


    public String getUrlLink(int index){
        return urlLinks.get(index);
    }
    public void setSrcLanguage(String src){
      srcLanguage = src;
    }
    public void setTrgLanguage(String trg){
        trgLanguage = trg;
    }
}