package com.f;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // إعداد قراءة المدخلات من المستخدم
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your code (type 'END' on a new line to finish):");

        // قراءة المدخلات من المستخدم حتى يتم إدخال 'END'
        StringBuilder codeBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equalsIgnoreCase("END")) {
            codeBuilder.append(line).append("\n");
        }

        // تحويل المدخلات إلى نص
        String code = codeBuilder.toString();

        // تحليل وتفسير النص
        List<Lexer.Token> tokens = Lexer.tokenize(code);
        Interpreter interpreter = new Interpreter(tokens);
        interpreter.interpret();

        // إغلاق الماسح الضوئي
        scanner.close();
    }
}
