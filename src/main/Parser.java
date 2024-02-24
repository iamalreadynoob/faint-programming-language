package main;

import basics.CodeFile;
import basics.Lexical;
import basics.Semantics;
import basics.Shape;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Parser
{

    protected static void getRawCode(File[] files, Map<String, String> codes)
    {
        try
        {
            for (File file: files)
            {
                if (file.getName().endsWith(".fai"))
                {
                    String code = "";

                    BufferedReader reader = new BufferedReader(new FileReader(file));

                    String line;
                    while ((line = reader.readLine()) != null)
                        code += line;

                    reader.close();

                    codes.put(file.getName(), code);
                }
            }
        }
        catch (IOException e){}

    }

    protected static CodeFile examine(String code)
    {
        CodeFile codeFile = new CodeFile();

        int loc = 0;

        ArrayList<String> commands = new ArrayList<>();

        while (loc < code.length())
        {
            String command = "";

            while (loc < code.length())
            {
                if (code.charAt(loc) == ';')
                {
                    loc++;
                    break;
                }
                else if (code.charAt(loc) == '\'' || code.charAt(loc) == '"')
                {
                    char sym = code.charAt(loc);
                    command += code.charAt(loc);
                    loc++;

                    while (loc < code.length())
                    {
                        if (code.charAt(loc) == sym && Semantics.isSymbol(code,loc)) break;

                        command += code.charAt(loc);
                        loc++;
                    }

                    if (loc < code.length() && Semantics.isSymbol(code, loc))
                    {
                        command += code.charAt(loc);
                        loc++;
                    }

                    break;
                }
                else if (code.charAt(loc) == '{')
                {
                    int requiredClosedBrackets = 1;

                    command += code.charAt(loc);
                    loc++;

                    while (loc < code.length() && requiredClosedBrackets > 0)
                    {
                        if (code.charAt(loc) == '\'' || code.charAt(loc) == '"')
                        {
                            char sym = code.charAt(loc);
                            command += code.charAt(loc);
                            loc++;

                            while (loc < code.length())
                            {
                                if (code.charAt(loc) == sym && Semantics.isSymbol(code,loc)) break;

                                command += code.charAt(loc);
                                loc++;
                            }

                            if (loc < code.length() && Semantics.isSymbol(code, loc))
                            {
                                command += code.charAt(loc);
                                loc++;
                            }
                        }
                        else
                        {
                            if (code.charAt(loc) == '{') requiredClosedBrackets++;
                            else if (code.charAt(loc) == '}') requiredClosedBrackets--;

                            command += code.charAt(loc);
                            loc++;
                        }
                    }

                    break;
                }
                else
                {
                    command += code.charAt(loc);
                    loc++;
                }
            }

            commands.add(command);
        }

        ArrayList<ArrayList<String>> tokens = new ArrayList<>();
        for (String cmd: commands)
            tokens.add(Semantics.tokenize(cmd));

        //handle tokens about importing
        ArrayList<Integer> importingIndexes = Lexical.detectImportings(tokens);
        ArrayList<ArrayList<String>> importingTokens = new ArrayList<>();

        for (Integer index: importingIndexes)
            importingTokens.add(tokens.get(index));

        tokens = Shape.clear(tokens, importingIndexes);

        codeFile = Lexical.analyzeImportings(codeFile, tokens);

        //handle tokens about global variables
        ArrayList<Integer> globalVarIndexes = Lexical.detectGlobalVariables(tokens);
        ArrayList<ArrayList<String>> globalVarTokens = new ArrayList<>();

        for (Integer index: globalVarIndexes)
            globalVarTokens.add(tokens.get(index));

        tokens = Shape.clear(tokens, globalVarIndexes);

        codeFile = Lexical.analyzeGlobalVariables(codeFile, globalVarTokens);

        return codeFile;
    }

}
