import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TestCrawler {

    private WebCrawler webCrawler;

    @BeforeEach
    public void setUp() {
        webCrawler = new WebCrawler();
    }


    @Test
    public void getPageLinks() throws Exception {

        webCrawler = new WebCrawler();

        webCrawler.setSrcLanguage("english");
        webCrawler.setTrgLanguage("german");
        webCrawler.setMAX_DEPTH(2);
        webCrawler.getPageLinks("https://forbes.com",0);
        assertEquals(webCrawler.getUrlLink( 0),"https://www.forbes.com/");
    }

    @Test
    public void checkOutputFile() throws Exception {

        webCrawler = new WebCrawler();

        webCrawler.setSrcLanguage("english");
        webCrawler.setTrgLanguage("german");
        webCrawler.setMAX_DEPTH(2);

        File file = new File("output.md");

        boolean exist = false;

        if (file.exists()){
            exist = true;
        }
        assertTrue(exist);

        boolean isEmpty = false;

        if (file.length() == 0 ){
            isEmpty = true;
        }

        isEmpty = false;

        webCrawler.getPageLinks("https:/www.forbes.com",0);

        if (file.length() == 0 ){
            isEmpty = true;
        }

        assertFalse(isEmpty);
    }

    @AfterEach
    public void tearDown(){
        webCrawler = null ;
    }

    public static void main(String[] args) throws Exception {
        TestCrawler testCrawler = new TestCrawler();

        testCrawler.getPageLinks();
        testCrawler.checkOutputFile();
    }



}