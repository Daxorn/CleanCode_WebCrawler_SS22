import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Locale;

public class Translator {
    public static String getTranslation(String toTranslate, String srcLang, String trgLang) throws IOException{
    String translated;
    Runtime runtime = Runtime.getRuntime();
    srcLang = getLanguageAcronym(srcLang);
    trgLang = getLanguageAcronym(trgLang);

        Process translate = runtime.exec("curl https://api-free.deepl.com/v2/translate \n"
                + "-d auth_key=40d04072-cf15-ca24-3658-42dd1279fb94:fx -d \"text=" + toTranslate + "\"\n"
                + "\"-d source_lang" + srcLang + "\" -d\"target_lang=" + trgLang + "\"");

        translated = IOUtils.toString(translate.getInputStream());
        if(translated.length()>45){
            translated = translated.substring(58,translated.length()-4);
        }


    return translated;
    }

    public static String getLanguageAcronym(String input){
        String output = "";
        input = input.toLowerCase(Locale.ROOT);
        switch (input) {
            case "english" -> output = "EN";
            case "german" -> output = "DE";
            case "russian" -> output = "RU";
            case "italian" -> output = "IT";
        }

        return output;
    }
}
