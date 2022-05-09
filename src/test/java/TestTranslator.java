
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;


public class TestTranslator {

    private Translator translator;

    @BeforeEach

    public void setUp(){
        translator = new Translator("english", "german");
    }

    @Test
    public void getTextTranslated() throws IOException {

        String englishString = "Hello World!";
        String germanString = "Hallo Welt!";

        String english = "english";
        String german = "german";
        Translator ts = new Translator(english,german);
        assertEquals(germanString, ts.getTranslation(englishString));
        ts.setLang(german,english);
        assertEquals(englishString, ts.getTranslation(germanString));
    }

    @AfterAll
    public void tearDown(){
        translator = null ;
    }
}


