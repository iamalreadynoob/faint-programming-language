package basics;

import java.util.ArrayList;

public class Shape
{

    public static ArrayList<ArrayList<String>> clear(ArrayList<ArrayList<String>> tokens, ArrayList<Integer> indexes)
    {
        ArrayList<ArrayList<String>> clearedTokens = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++)
            if (!indexes.contains(i))
                clearedTokens.add(tokens.get(i));

        return clearedTokens;
    }

}
