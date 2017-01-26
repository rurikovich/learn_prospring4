package org.learn.prospring4.chapter14;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

/**
 * Created by rurik on 25.01.2017.
 */
public class ListScriptEngines {

    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        for (ScriptEngineFactory factory : manager.getEngineFactories()) {
            String engineName = factory.getEngineName();
            String languageName = factory.getLanguageName();
            String version = factory.getLanguageVersion();
            System.out.println("Engine name: " + engineName + " Language: " + languageName + "version: " + version);
        }
    }


}
