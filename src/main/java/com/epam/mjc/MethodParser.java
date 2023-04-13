package com.epam.mjc;

import com.sun.jdi.connect.Connector;

import java.util.*;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */

    public MethodSignature parseFunction(String signatureString) {
        String accessModifier = null;
        String returnType = null;
        String methodName = null;
        StringSplitter splitter = new StringSplitter();
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        String[] arrayAccessModifier = new String[]{"public", "private", "protected", "default"};
        Collection col = new ArrayList<String>();
        col.add(" ");
        col.add("(");
        col.add(", ");
        col.add(")");

        List<String> b = splitter.splitByDelimiters(signatureString, col);

        StringJoiner joiner = new StringJoiner(" ");
        for (String variable : b) {
            joiner.add(variable);
        }

        StringTokenizer st01 = new StringTokenizer(joiner.toString(), " ");

        if (st01.hasMoreTokens()) {
            String st11 = st01.nextToken();
            for (String accessModif : arrayAccessModifier) {
                if (st11.equals(accessModif)) {
                    accessModifier = accessModif;
                }
            }

            if (accessModifier == null) {
                returnType = st11;
                methodName = st01.nextToken();
            } else {
                returnType = st01.nextToken();
                methodName = st01.nextToken();
            }

            while (st01.hasMoreTokens()) {
                String key1 = st01.nextToken();
                String key2 = st01.nextToken();
                arguments.add(new MethodSignature.Argument(key1, key2));
            }
        }
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
