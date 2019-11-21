package com.avyay.json;

import java.io.IOException;
import java.io.StringReader;
import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;


/**
 * @author Avyay Natarajan
 */
final class Parser {

    private static Pattern json_whitespace_compressor =
        Pattern.compile("(?!\\s+(?=(?:(?:[^\"]*\"){2})*[^\"]*\"[^\"]*$))(\\s+)");
    private static Pattern numeric_pattern = Pattern.compile("^[.]|[0-9]+$");

    private static boolean only_contains_numerics(String s)
    {
        return numeric_pattern.matcher(s).find();
    }

    static JSON.Value parseValue(String source)
    {
        source = json_whitespace_compressor.matcher(source).replaceAll("");
        if (source.startsWith("{") && source.endsWith("}"))
            return parseObject(source);
        else if (source.startsWith("[") && source.endsWith("]"))
            return parseArray(source);
        else if (source.startsWith("\"") && source.endsWith("\""))
            return parseString(source);
        else if (source.startsWith("\'") && source.endsWith("\'"))
            return parseString(source);
        else if (only_contains_numerics(source))
            return parseNumber(source);
        return null;
    }

    private static HashMap<Character, Character> closer = new HashMap<>();
    static {
        closer.put('{', '}');
        closer.put('[', ']');
        closer.put('\"', '\"');
        closer.put('\'', '\'');
    }

    private static List<Character> parenthesis_open = Arrays.asList(new Character[] {'{', '[', '\''});
    private static List<Character> parenthesis_closed = Arrays.asList(new Character[] {'}', ']', '\''});

    private static class JSONObjectBuilder
    {
        /**
         * @field stage
         * @param -1 = halted
         * @param 0 = read open quote
         * @param 1 = construct keyBuilder
         * @param 2 = read opening character for value
         * @param 3 = construct valueBuilder
         * @param 4 = flush
         */
        private int stage = 0;
        private JSON.Object out = new JSON.Object();
        private volatile StringBuilder keyBuilder;
        private volatile StringBuilder valueBuilder;
        private Stack<Character> enclosers = new Stack<>();
        private final char opening_encloser;

        public JSONObjectBuilder(char first)
        {
            opening_encloser = first;
            refresh();
        }

        public void refresh()
        {
            keyBuilder = new StringBuilder();
            valueBuilder = new StringBuilder();
        }

        public void append()
        {
            out.put(keyBuilder.toString(), parseValue(valueBuilder.toString()));
        }

        public void readKey(char c)
        {
            if (c == '\"') {
                stage++;
                return;
            }
            keyBuilder.append(c);
        }

        /* TODO
            figure out what the heck is going on in this method because every test case of adding
            characters to the StringBuilder works, but actual implementation for some reason does
            not and it's really annoying - Avyay
         */
        public void readValue(char c)
        {
            if (parenthesis_open.contains(c))
                enclosers.push(c);
            if (enclosers.size() > 0) {
                System.out.println(enclosers + (enclosers.size() > 0 ? " > " + matching_closer(enclosers.peek()) : "") + " ?=" + c);

                if (c == matching_closer(enclosers.peek())) {
                    valueBuilder.append(c);
                    enclosers.pop();
                }

                System.out.println(valueBuilder);
                if (enclosers.size() == 0) {
                    stage++;
                    return;
                }
            } else valueBuilder.append(c);
        }

        private static char matching_closer(char c)
        {
            return parenthesis_closed.get(parenthesis_open.indexOf(c));
        }

        private static char matching_opener(char c)
        {
            return parenthesis_open.get(parenthesis_closed.indexOf(c));
        }

        public void read(char c)
        {
            //System.out.print("pre_stage=" + stage + ", char=" + c);
            switch (stage)
            {
                case 0:
                {
                    if (c == '\"')
                    stage++;
                    break;
                }
                case 1:
                {
                    readKey(c);
                    break;
                }
                case 2:
                {
                    if (c == ':')
                        stage++;
                    break;
                }
                case 3:
                {
                    readValue(c);
                    break;
                }
                case 4:
                {
                    if (c == ',') {
                        stage = 0;
                        append();
                        refresh();
                    } else if (c == matching_closer(opening_encloser))
                    {
                        append();
                        refresh();
                    }
                }
            }
            //System.out.println(", post_stage=" + stage);
        }

        public JSON.Object product()
        {
            return out;
        }
    }

    static JSON.Object parseObject(String source)
    {
        JSONObjectBuilder builder = null;
        StringReader reader = new StringReader(source);
        try
        {
            if (!reader.ready()) return null;
            int ch = reader.read();
            builder = new JSONObjectBuilder((char) ch);

            int stage = -1;
            StringBuilder key = new StringBuilder();
            StringBuilder value = new StringBuilder();

            while (ch != -1)
            {
                ch = reader.read();
                char current = (char) ch;
                synchronized (builder) {
                    builder.read(current);
                }
            }
        }
        catch (IOException e) { }
        finally {
            reader.close();
        }
        return builder.product();
    }

    static JSON.Array parseArray(String source)
    {
        // TODO implement
        return new JSON.Array();
    }

    static JSON.String parseString(String source)
    {
        return new JSON.String(source.substring(1, source.length() - 1));
    }

    static JSON.Value parseNumber(String source)
    {
        if (source.contains(""))
            return new JSON.Float(Float.parseFloat(source));
        return new JSON.Int(Integer.parseInt(source));
    }
}
