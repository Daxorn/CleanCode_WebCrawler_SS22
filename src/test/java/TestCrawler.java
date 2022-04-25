import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCrawler {
    @Test
    public void getPageLinks() throws IOException {
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.getPageLinks("https://forbes.com",0);
        assertEquals(webCrawler.getUrlLink( 0),"https://www.forbes.com/");
    }


}