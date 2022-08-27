package com.codex.learning.utility;

import java.util.ArrayList;
import java.util.Arrays;

public class Dialogue {

    private ArrayList<String> dialogueContainer = new ArrayList<>(
            Arrays.asList(new String[]{"Hi I am your lolo, JediGrandpa", "I have a task for you to do", "But first lets study first on the topic", "tite hehe xD",
                    // Stage 1
                    // syntax
                    "The first topic is Syntax", "The syntax of Java refers to the set of rules defining how a Java program is written and interpreted.",
                    "The syntax is mostly derived from C and C++. Unlike in C++, in Java there are no global functions or variables" ,
                    "But there are data members which are also regarded as global variables.",
                    "For example this is a proper syntax declaration" + "\n\npublic class Main {\n" + "  public static void main(String[] args) {\n" + "    System.out.println(\"Hello World\");\n" + "  }\n" + "}",
                    "Every line of code that runs in Java must be inside a class.", "In our example, we named the class Main. A class should always start with an uppercase first letter.",
                    "Java is case-sensitive: \"MyClass\" and \"myclass\" has different meaning.", "The main() method is required and you will see it in every Java program:",
                    "Any code inside the main() method will be executed.", "For now, just remember that every Java program has a class name which must match the filename, and that every program must contain the main() method.",
                    // comments
                    "Our second topic is Comments", "Comments can be used to explain Java code, and to make it more readable.", "It can also be used to prevent execution when testing alternative code.",
                    "Single-line comments start with two forward slashes (//).", "Any text between // and the end of the line is ignored by Java (will not be executed).",
                    "This example uses a single-line comment before a line of code:" + "\n// This is a comment", "Multi-line comments start with /* and ends with */.", "Any text between /* and */ will be ignored by Java.",
                    "This example uses a multi-line comment (a comment block) to explain the code:" + "\n\n/* The code below will print the words Hello World\n" +"to the screen, and it is amazing */",
                    // variables
                    "Our third topic is Variables", "Variables are containers for storing data values.", "In Java, there are different types of variables",
                    "String - stores text, such as \"Hello\". String values are surrounded by double quotes", "For example: " + "\n\nString name =\"Hello\";",
                    "int - stores integers (whole numbers), without decimals, such as 123 or -123", "For example:" + "\n\nint num = 123;",
                    "double - stores point numbers, with decimals, such as 19.99 or -19.99", "For example:" + "\n\ndouble num = 19.99;",
                    "char - stores single characters, such as 'a' or 'B'. Char values are surrounded by single quotes", "For example:" + "\n\nchar letter = 'a';",
                    "boolean - stores values with two states: true or false", "For example:" + "\n\nboolean bool = true;",
                    "If you don't want others (or yourself) to overwrite existing values, use the final keyword (this will declare the variable as \"final\" or \"constant\", which means unchangeable and read-only):",
                    "For example" + "\n\nfinal int myNum = 15;",
                    // data types
                    "Our fourth topic is Data types","Data types are divided into two groups:" + "\n\nPrimitive data types - includes byte, short, int, long, float, double, boolean and char" + "\n\nNon-primitive data types - such as String, Arrays and Classes",
                    "A primitive data type specifies the size and type of variable values, and it has no additional methods.", "There are eight primitive data types in Java:" + "\n\nData Type     " + " Size" +
                    "\nbyte " + "              1 byte" + "\nshort " + "            2 bytes" + "\nint " + "                  4 bytes" +"\nlong " + "               8 bytes" +
                    "\nfloat " + "             4 bytes" + "\ndouble " + "          8 bytes" + "\nboolean " + "        1 bit" + "\nchar " + "              2 bytes",
                    "Non-primitive data types are called reference types because they refer to objects.", "Non-primitive types are created by the programmer and is not defined by Java (except for String).",
                    "Non-primitive types can be used to call methods to perform certain operations, while primitive types cannot.", "non-primitive types have all the same size.",
                    // type casting
                    "Our fourth topic is Type casting", "Type casting is when you assign a value of one primitive data type to another type.", "In Java, there are two types of casting:",
                    "Widening Casting (automatically) - converting a smaller type to a larger type size\n" +
                            "byte -> short -> char -> int -> long -> float -> double", "For example:" + "\n\n int myInt = 9;\n" + "double myDouble = myInt;",
                    "Narrowing Casting (manually) - converting a larger type to a smaller size type\n" +
                            "double -> float -> long -> int -> char -> short -> byte", "For example:" + "double myDouble = 9.78d;\n" + "int myInt = (int) myDouble",
                    // operators
                    "Our fifth topic is Operators", "Operators are used to perform operations on variables and values.", "Java divides the operators into the following groups:" +
                    "\n\nArithmetic operators" + "\nAssignment operators" + "\nComparison operators" + "\nLogical operators" + "\nBitwise operators",
                    "Arithmetic operators are used to perform common mathematical operations." + "\n\n+ Addition - Adds together two values " + "\n- Subtraction - Subtracts one value from another" +
                            "\n* Multiplication - Multiplies two values" + "\n/ Division - Divides one value by another" + "\n% Modulus - Returns the division remainder" + "\n++ Increment - Increases the value of a variable by 1" +
                            "\n-- Decrement - Decreases the value of a variable by 1", "Assignment operators are used to assign values to variables." +
                    "\nOperator       Example     Same As" + "\n        =                  x = 5               x = 5" + "\n        +=                x += 3             x = x + 3" +
                    "\n        -=                x -= 3             x = x - 3" + "\n        *=                x *= 3             x = x * 3" + "\n        /=               x /= 3             x = x / 3",
                    "Operator       Example     Same As" + "\n        %=               x %= 3             x = x % 3" + "\n        &=               x &= 3             x = x & 3" +
                            "\n        |=                 x |= 3               x = x | 3" + "\n        ^=               x ^= 3             x = x ^ 3" + "\n        >>=              x >>= 3            x = x >> 3" +
                            "\n        <<=              x <<= 3            x = x << 3", "Comparison operators are used to compare two values:" + "\n\n== -> Equal to -> x == y" + "\n!= -> Not equal -> x != y" +
                    "\n> -> Greater than -> x > y" + "\n< -> Less than -> x < y" + "\n>= -> Greater than or equal to -> x >= y" + "\n<= -> Less than or equal to -> x <= y",
                    "Logical operators are used to determine the logic between variables or values:" + "\n\n&& - Logical and - Returns true if both statements are true" + "\n|| - Logical or - Returns true if one of the statements is true" +
                            "\n! - Logical not - Reverse the result, returns false if the result is true",
                    // Stage 2
                    // Conditional statements
                    
                    // Looping Statements

                    // Arrays

                    // Methods

                    // Parameters

                    // Parameters Overloading

                    // Stage 3
                    // Classes
                    "Our first topic is Classes", "Everything in Java is associated with classes and objects, along with its attributes and methods.", "For example: in real life, a car is an object. The car has attributes, such as weight and color, and methods, such as drive and brake.",
                    "A Class is like an object constructor, or a \"blueprint\" for creating objects.", "To create a class, use the keyword class:" + "\n\nExample" + "\npublic class Main {\n" + "  int x = 5;\n" + "}" + "\n\nRemember that a class should always start with an uppercase first letter, and that the name of the java file should match the class name.",
                    "",
                    // Objects
                    "Our last topic is Objects", "In Java, an object is created from a class. We have already created the class named Main, so now we can use this to create objects", "To create an object of Main, specify the class name, followed by the object name, and use the keyword new:" + "\n\nFor example:" +
                    "\npublic class Main {\n" + "  int x = 5;\n" + "\n" + "  public static void main(String[] args) {\n" + "    Main myObj = new Main();\n" + "    System.out.println(myObj.x);\n" + "  }\n" + "}",
                    "You can create multiple objects of one class:" + "\nExample" + "\n\npublic static void main(String[] args) {\n" + "    Main myObj1 = new Main();  // Object 1\n" + "    Main myObj2 = new Main();  // Object 2\n" + "    System.out.println(myObj1.x);\n" + "    System.out.println(myObj2.x);\n" + "  }"
            }));
    private boolean statementEnd;
    float myFloatNum = 5.99f;
    public Dialogue(){

        statementEnd = false;

    }

    public String reader(int nextStatement){

        if(nextStatement == dialogueContainer.size()){
            System.out.println("true");
            setStatementEnd(true);
        }else{
            setStatementEnd(false);
            return dialogueContainer.get(nextStatement);

        }

        return "";
    }

    public ArrayList<String> getDialogueContainer() {
        return dialogueContainer;
    }

    public void setDialogueContainer(ArrayList<String> dialogueContainer) {
        this.dialogueContainer = dialogueContainer;
    }

    public boolean isStatementEnd() {
        return statementEnd;
    }

    public void setStatementEnd(boolean statementEnd) {
        this.statementEnd = statementEnd;
    }
}
