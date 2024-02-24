package basics;

import java.util.ArrayList;

public class Semantics
{

    public static boolean isSymbol(String code, int index)
    {
        boolean flag = true;

        int loc = index - 1;
        int amount = 0;

        while (loc > -1 && code.charAt(loc) == '\\')
        {
            loc--;
            amount++;
        }

        if (amount % 2 == 1) flag = false;

        return flag;
    }

    public static ArrayList<String> tokenize(String command)
    {
        ArrayList<String> tokens = new ArrayList<>();

        ArrayList<Character> separators = getSeparators();
        int loc = 0;

        while (loc < command.length())
        {
            if (command.charAt(loc) != ' ')
            {
                String token = "";

                while (loc < command.length())
                {
                    if (command.charAt(loc) == '\'' || command.charAt(loc) == '"')
                    {
                        char sym = command.charAt(loc);
                        token += command.charAt(loc);
                        loc++;

                        while (loc < command.length())
                        {
                            if (command.charAt(loc) == sym && Semantics.isSymbol(command, loc)) break;

                            token += command.charAt(loc);
                            loc++;
                        }

                        if (loc < command.length() && Semantics.isSymbol(command, loc))
                        {
                            token += command.charAt(loc);
                            loc++;
                        }

                        break;
                    }
                    else if (separators.contains(command.charAt(loc)))
                    {
                        if (token.equals(""))
                        {
                            token += command.charAt(loc);
                            loc++;
                        }

                        break;
                    }
                    else if (command.charAt(loc) == ' ')
                    {
                        loc++;
                        break;
                    }
                    else
                    {
                        token += command.charAt(loc);
                        loc++;
                    }
                }

                tokens.add(token);
            }
            else loc++;
        }

        return tokens;
    }

    private static ArrayList<Character> getSeparators()
    {
        ArrayList<Character> separators = new ArrayList<>();

        separators.add(';');    separators.add('!');    separators.add('\'');   separators.add('"');
        separators.add('^');    separators.add('#');    separators.add('+');    separators.add('-');
        separators.add('/');    separators.add('&');    separators.add('|');    separators.add('*');
        separators.add('{');    separators.add('}');    separators.add('[');    separators.add(']');
        separators.add('(');    separators.add(')');    separators.add('@');    separators.add('_');
        separators.add('=');    separators.add('<');    separators.add('>');    separators.add('.');
        separators.add(':');

        return separators;
    }

}
