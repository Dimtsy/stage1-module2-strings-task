package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */

    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuffer sb = new StringBuffer();

        for (String variable : delimiters) {
               sb.append(variable);
       }
        StringTokenizer st01 = new StringTokenizer( source, sb.toString());
        List<String> splitDelimiters = new ArrayList<>();
        while (st01.hasMoreTokens())
            splitDelimiters.add(st01.nextToken());
        return splitDelimiters;
    }
}
