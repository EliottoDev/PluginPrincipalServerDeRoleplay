package me.eliotto.botds.data;

import org.apache.commons.lang.ArrayUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Json {


    private JSONParser parser;
    private JSONObject[] historial = new JSONObject[10];
    private final String CONFIG_PATH = "src/main/java/me/eliotto/botds/data/config.json";

    public Json(){
        parser = new JSONParser();
    }

    public JSONObject getConfig() throws IOException, ParseException, FileNotFoundException {
        ArrayUtils.add(historial, (JSONObject) parser.parse(new FileReader(CONFIG_PATH)));
        return (JSONObject) parser.parse(new FileReader(CONFIG_PATH));
    }
    public JSONObject getData() throws IOException, ParseException, FileNotFoundException {
        return (JSONObject) parser.parse(new FileReader(CONFIG_PATH));
    }
    public JSONObject getItems() throws IOException, ParseException, FileNotFoundException {
        return (JSONObject) parser.parse(new FileReader(CONFIG_PATH));
    }

    public String getServerIP(){
        return "ª";
    }
}
