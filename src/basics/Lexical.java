package basics;

import java.util.ArrayList;

public class Lexical
{

    public static ArrayList<Integer> detectImportings(ArrayList<ArrayList<String>> tokens)
    {
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++)
        {
            if (tokens.get(i).get(0).equals("load") && (tokens.get(i).size() == 2 || tokens.get(i).size() == 3))
                indexes.add(i);
        }

        return indexes;
    }

    public static CodeFile analyzeImportings(CodeFile file, ArrayList<ArrayList<String>> tokens)
    {
        for (int i = 0; i < tokens.size(); i++)
        {
            String location = "";
            String name = "";

            if (tokens.get(i).size() == 2)
            {
                String rawPath = tokens.get(i).get(1).substring(1, tokens.get(i).get(1).length() - 1);
                location = rawPath.substring(rawPath.lastIndexOf('/'));
                name = rawPath.substring(rawPath.lastIndexOf('/') + 1);
            }
            else if (tokens.get(i).size() == 3)
            {
                location = tokens.get(i).get(1).substring(1, tokens.get(i).get(1).length() - 1);
                name = tokens.get(i).get(2).substring(1, tokens.get(i).get(2).length() - 1);
            }

            file.addImporting(location, name);
        }

        return file;
    }

    public static ArrayList<Integer> detectGlobalVariables(ArrayList<ArrayList<String>> tokens)
    {
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++)
            if (tokens.get(i).get(0).equals("var"))
                indexes.add(i);

        return indexes;
    }

    public static CodeFile analyzeGlobalVariables(CodeFile file, ArrayList<ArrayList<String>> tokens)
    {
        

        return file;
    }

}
