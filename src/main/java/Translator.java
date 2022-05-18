import org.apache.commons.io.IOUtils;

import java.util.Locale;

public class Translator {
    public String srcLang;
    public String trgLang;


    public Translator(String srcLang, String trgLang){
       setLang(srcLang,trgLang);
    }

    public String getTranslation(String toTranslate) {
        try {
            Runtime runtime = Runtime.getRuntime();

            Process translate = runtime.exec("curl https://api-free.deepl.com/v2/translate \n"
                    + "-d auth_key=40d04072-cf15-ca24-3658-42dd1279fb94:fx -d \"text=" + toTranslate + "\"\n"
                    + "\"-d source_lang" + srcLang + "\" -d\"target_lang=" + trgLang + "\"");

            String translated = IOUtils.toString(translate.getInputStream());

            translated = translated.substring(58, translated.length() - 4);
            //Since the response from the DeeplAPI is a JSON file, the translated text is stored after 58 chars
            //in the JSON file, and the last 4 chars are not needed. Using variables for such thing seemed a waste
            //of memory, hence the "Magical Numbers


            return translated;
        } catch (Exception e) {
            System.out.println("Translation Problem: " + e.getMessage());
            return "No translation executed";
        }
    }

    public void setLang(String srcLang, String trgLang){
        this.srcLang = getLanguageAcronym(srcLang);
        this.trgLang = getLanguageAcronym(trgLang);
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
