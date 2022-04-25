
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;


public class TestTranslator {

    private Translator translator;

    @BeforeEach
    public void setUp(){
        translator = new Translator();
    }

    @Test
    public void getTextTranslated() throws IOException {

        String englishString = "Hello World!";
        String germanString = "Hallo Welt!";

        String english = "english";
        String german = "german";

        assertEquals(germanString, translator.getTranslation(englishString,english,german));
        assertEquals(englishString, translator.getTranslation(germanString,german,english));
    }

    @AfterAll
    public void tearDown(){
        translator = null ;
    }

    public static void main(String[] args) throws IOException {
        TestTranslator testTranslator = new TestTranslator();
        testTranslator.getTextTranslated();
    }
}


