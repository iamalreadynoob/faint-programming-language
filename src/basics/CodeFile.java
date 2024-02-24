package basics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CodeFile
{

    private ArrayList<Map<String, String>> importings;
    private Map<String, Map<String, String>> globalVariables, functions;

    public CodeFile()
    {
        importings = new ArrayList<>();
        globalVariables = new HashMap<>();
        functions = new HashMap<>();
    }

    //location will not include extension (.fai)
    public void addImporting(String location, String name)
    {
        Map<String, String> importing = new HashMap<>();
        importing.put("location", location);
        importing.put("name", name);
        importings.add(importing);
    }

    public void addGlobalVariable(String datatype, String name, String value, String isNull, String isArray)
    {
        Map<String, String> features = new HashMap<>();
        features.put("datatype", datatype);
        features.put("isArray", isArray);
        features.put("isNull", isNull);
        features.put("value", value);

        globalVariables.put(name, features);
    }
}
