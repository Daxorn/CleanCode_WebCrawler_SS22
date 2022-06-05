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
        assertEquals("https://www.forbes.com/",webCrawler.getUrlLink( 0));
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
    @Test
    public void testLanguages(){
        String testSrc = "English";
        String testTrg = "German";
        webCrawler.setSrcLanguage(testSrc);
        webCrawler.setSrcLanguage(testTrg);

        assertEquals(testSrc, webCrawler.getSrcLanguage());
        assertEquals(testTrg, webCrawler.getTrgLanguage());
    }

    @Test
    public void testMaxDepth(){
        int maxDepth = 2;
        webCrawler.setMAX_DEPTH(maxDepth);

        assertEquals(maxDepth,webCrawler.getMAX_DEPTH());
    }

    @AfterEach
    public void tearDown(){
        webCrawler = null ;
    }

    public static void main(String[] args) throws Exception {
        TestCrawler testCrawler = new TestCrawler();
        testCrawler.getPageLinks();
        testCrawler.checkOutputFile();
        testCrawler.testLanguages();
    }



}