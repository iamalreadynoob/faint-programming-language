package main;

import basics.CodeFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main
{

    public static void main(String[] args)
    {
        if (args.length == 3 && args[0].equals("compile"))
        {
            File dir = new File(args[1] + "/src");
            Map<String, String> srcCodes = new HashMap<>();

            if (dir.isDirectory())
            {
                Parser.getRawCode(dir.listFiles(), srcCodes);
                File mainFile = new File(args[1] + "/src/" + args[2]);
                if (!srcCodes.isEmpty() && mainFile.isFile() && mainFile.exists())
                {
                    CodeFile main = Parser.examine(srcCodes.get("test-1.fai"));
                }
                else
                {
                    //TODO: handle exception
                }
            }
            else
            {
                //TODO: handle exception
            }


        }
    }

}
