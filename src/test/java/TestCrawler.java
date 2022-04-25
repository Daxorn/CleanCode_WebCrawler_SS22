import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCrawler {

    private WebCrawler webCrawler;

    @BeforeEach
    public void setUp(){
        webCrawler = new WebCrawler();
    }


    @Test
    public void getPageLinks() throws IOException {

        webCrawler = new WebCrawler();

        webCrawler.setSrcLanguage("english");
        webCrawler.setTrgLanguage("german");
        webCrawler.setMAX_DEPTH(2);
        webCrawler.getPageLinks("https://forbes.com",0);
        assertEquals(webCrawler.getUrlLink( 0),"https://www.forbes.com/");
    }

    @Test
    public void checkOutputFile() throws IOException {

        webCrawler = new WebCrawler();

        webCrawler.setSrcLanguage("english");
        webCrawler.setTrgLanguage("german");
        webCrawler.setMAX_DEPTH(2);

        File file = new File("output.md");

        boolean exist = false;

        if (file.exists()){
            exist = true;
        }
        assertEquals(true,exist);

        boolean isEmpty = false;

        if (file.length() == 0 ){
            isEmpty = true;
        }

        isEmpty = false;

        webCrawler.getPageLinks("https:/www.forbes.com",0);

        if (file.length() == 0 ){
            isEmpty = true;
        }

        assertEquals(false, isEmpty);
    }

    @AfterEach
    public void tearDown(){
        webCrawler = null ;
    }

    public static void main(String[] args) throws IOException {
        TestCrawler testCrawler = new TestCrawler();

        testCrawler.getPageLinks();
        testCrawler.checkOutputFile();
    }



}