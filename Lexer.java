package com.f;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Lexer {
    public enum TokenType {
        LET, ADD, SUBTRACT, PRINT, NUMBER, IDENTIFIER, ASSIGN, SEMICOLON, WHITESPACE, MULTIPLAY
    }

    public static class Token {
        public final TokenType type;
        public final String value;

        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s: '%s'", type.name(), value);
        }
    }

    private static final Pattern tokenPatterns = Pattern.compile(
        "(let)|(add)|(subtract)|(print)|([0-9]+)|([a-zA-Z][a-zA-Z0-9]*)|(=)|(;)|(mult)|(\\s+)"
    );

    public static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {
            if (matcher.group(1) != null) tokens.add(new Token(TokenType.LET, matcher.group(1)));
            else if (matcher.group(2) != null) tokens.add(new Token(TokenType.ADD, matcher.group(2)));
            else if (matcher.group(3) != null) tokens.add(new Token(TokenType.SUBTRACT, matcher.group(3)));
            else if (matcher.group(4) != null) tokens.add(new Token(TokenType.PRINT, matcher.group(4)));
            else if (matcher.group(5) != null) tokens.add(new Token(TokenType.NUMBER, matcher.group(5)));
            else if (matcher.group(6) != null) tokens.add(new Token(TokenType.IDENTIFIER, matcher.group(6)));
            else if (matcher.group(7) != null) tokens.add(new Token(TokenType.ASSIGN, matcher.group(7)));
            else if (matcher.group(8) != null) tokens.add(new Token(TokenType.SEMICOLON, matcher.group(8)));
            else if (matcher.group(10) != null) {
                // تجاهل المسافات والرموز البيضاء
            }
            				else if(matcher.group(9) != null) tokens.add(new Token(TokenType.MULTIPLAY, matcher.group(9)));
            				
        }
        return tokens;
    }
}
