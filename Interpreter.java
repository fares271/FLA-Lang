package com.f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// تعريف كلاس Interpreter الذي يقوم بتفسير التوكنات
class Interpreter {
    private final List<Lexer.Token> tokens; // قائمة التوكنات
    private int current = 0; // مؤشر للتوكن الحالي
    private final Map<String, Integer> variables = new HashMap<>(); // خريطة لتخزين المتغيرات وقيمها

    // المُنشئ الذي يستقبل قائمة التوكنات
    public Interpreter(List<Lexer.Token> tokens) {
        this.tokens = tokens;
    }

    // تفسير التوكنات
    public void interpret() {
        while (current < tokens.size()) {
            parseStatement(); // تحليل الجملة الحالية
        }
    }

    // تحليل الجملة
    private void parseStatement() {
        Lexer.Token token = tokens.get(current); // الحصول على التوكن الحالي
        switch (token.type) {
            case LET:
                parseLet();
                break;
            case ADD:
                parseAdd();
                break;
            case SUBTRACT:
                parseSubtract();
                break;
            case PRINT:
                parsePrint();
                break;
            case MULTIPLAY:
                parseMult();
                break;
            default:
                throw new RuntimeException("Unexpected token: " + token);
        }
    }

    // تحليل جملة let
    private void parseLet() {
        current++; // تخطي 'let'
        String variableName = tokens.get(current).value; // الحصول على اسم المتغير
        current++; // تخطي اسم المتغير
        current++; // تخطي '='
        // تأكد من أن القيمة التالية هي عدد صحيح
        String valueString = tokens.get(current).value;
        int value;
        try {
            value = Integer.parseInt(valueString); // تحويل القيمة إلى عدد صحيح
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format: " + valueString);
        }
        current++; // تخطي العدد
        variables.put(variableName, value); // تخزين المتغير وقيمته
        current++; // تخطي ';'
    }

    // تحليل جملة add
    private void parseAdd() {
        current++; // تخطي 'add'
        String var1 = tokens.get(current).value; // الحصول على اسم المتغير الأول
        current++; // تخطي المتغير الأول
        String var2 = tokens.get(current).value; // الحصول على اسم المتغير الثاني
        current++; // تخطي المتغير الثاني
        int result = variables.getOrDefault(var1, 0) + variables.getOrDefault(var2, 0); // حساب المجموع
        variables.put(var1, result); // تخزين النتيجة في المتغير الأول
        current++; // تخطي ';'
    }

    // تحليل جملة subtract
    private void parseSubtract() {
        current++; // تخطي 'subtract'
        String var1 = tokens.get(current).value; // الحصول على اسم المتغير الأول
        current++; // تخطي المتغير الأول
        String var2 = tokens.get(current).value; // الحصول على اسم المتغير الثاني
        current++; // تخطي المتغير الثاني
        int result = variables.getOrDefault(var1, 0) - variables.getOrDefault(var2, 0); // حساب الفرق
        variables.put(var1, result); // تخزين النتيجة في المتغير الأول
        current++; // تخطي ';'
    }

    // تحليل جملة print
    private void parsePrint() {
        current++; // تخطي 'print'
        String variableName = tokens.get(current).value; // الحصول على اسم المتغير
        current++; // تخطي المتغير
        // تحقق من وجود المتغير في الجدول قبل الطباعة
        if (variables.containsKey(variableName)) {
            System.out.println(variables.get(variableName)); // طباعة قيمة المتغير
        } else {
            System.out.println("Undefined variable: " + variableName); // طباعة رسالة خطأ
        }
        current++; // تخطي ';'
    }

    // تحليل جملة multiply
    private void parseMult() {
        current++; // تخطي 'multiply'
        String var1 = tokens.get(current).value; // الحصول على اسم المتغير الأول
        current++; // تخطي المتغير الأول
        String var2 = tokens.get(current).value; // الحصول على اسم المتغير الثاني
        current++; // تخطي المتغير الثاني
        int result = variables.getOrDefault(var1, 0) * variables.getOrDefault(var2, 0); // حساب حاصل الضرب
        variables.put(var1, result); // تخزين النتيجة في المتغير الأول
        current++; // تخطي ';'
    }
}
