package com.codex.learning.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dialogue {




    private ArrayList<ArrayList<String>> introductionDialogue = new ArrayList<>(Arrays.asList(
            new ArrayList<String>(Arrays.asList("Hi I am, JediGrandpa", "I have a task for you to do", "But first lets study first on the topic",
                    //syntax
                    "The first topic is Syntax", "The syntax of Java refers to the set of rules defining how a Java program is written and interpreted.",
                    "The syntax is mostly derived from C and C++. Unlike in C++, in Java there are no global functions or variables" ,
                    "But there are data members which are also regarded as global variables.",
                    "For example this is a proper syntax declaration" + "\n\npublic class Main {\n" + "  public static void main(String[] args) {\n" + "    System.out.println(\"Hello World\");\n" + "  }\n" + "}",
                    "Every line of code that runs in Java must be inside a class.", "In our example, we named the class Main. A class should always start with an uppercase first letter.",
                    "Java is case-sensitive: \"MyClass\" and \"myclass\" has different meaning.", "The main() method is required and you will see it in every Java program:",
                    "Any code inside the main() method will be executed.", "For now, just remember that every Java program has a class name which must match the filename, and that every program must contain the main() method.",
                    //comments
                    "Our second topic is Comments", "Comments can be used to explain Java code, and to make it more readable.", "It can also be used to prevent execution when testing alternative code.",
                    "Single-line comments start with two forward slashes (//).", "Any text between // and the end of the line is ignored by Java (will not be executed).",
                    "This example uses a single-line comment before a line of code:" + "\n// This is a comment", "Multi-line comments start with /* and ends with */.", "Any text between /* and */ will be ignored by Java.",
                    "This example uses a multi-line comment (a comment block) to explain the code:" + "\n\n/* The code below will print the words Hello World\n" +"to the screen, and it is amazing */",
                    "As for your task, you will be going to the COMPUTER beside me.", "After finishing all the questions in the COMPUTER, you can now proceed to the PLAYROOM on my RIGHT",
                    "The PLAYROOM however contains a random MINI-GAME that will test your CODING CAPABILITIES")),
            new ArrayList<String>(Arrays.asList(
                    //variables
                    "Once again I am JediGrandpa,", "I have a task for you to do", "But first lets study on the topic",
                    "Our topic for this stage will be Variables", "Variables are containers for storing data values.", "In Java, there are different types of variables",
                    "String - stores text, such as \"Hello\". String values are surrounded by double quotes", "For example: " + "\n\nString name =\"Hello\";",
                    "int - stores integers (whole numbers), without decimals, such as 123 or -123", "For example:" + "\n\nint num = 123;",
                    "double - stores point numbers, with decimals, such as 19.99 or -19.99", "For example:" + "\n\ndouble num = 19.99;",
                    "char - stores single characters, such as 'a' or 'B'. Char values are surrounded by single quotes", "For example:" + "\n\nchar letter = 'a';",
                    "boolean - stores values with two states: true or false", "For example:" + "\n\nboolean bool = true;",
                    "If you don't want others (or yourself) to overwrite existing values, use the final keyword (this will declare the variable as \"final\" or \"constant\", which means unchangeable and read-only):",
                    "For example" + "\n\nfinal int myNum = 15;",
                    //data types
                    "Our fourth topic is Data types","Data types are divided into two groups:" + "\n\nPrimitive data types - includes byte, short, int, long, float, double, boolean and char" + "\n\nNon-primitive data types - such as String, Arrays and Classes",
                    "A primitive data type specifies the size and type of variable values, and it has no additional methods.", "There are eight primitive data types in Java:" + "\n\nData Type     " + " Size" +
                            "\nbyte " + "              1 byte" + "\nshort " + "            2 bytes" + "\nint " + "                  4 bytes" +"\nlong " + "               8 bytes" +
                            "\nfloat " + "             4 bytes" + "\ndouble " + "          8 bytes" + "\nboolean " + "        1 bit" + "\nchar " + "              2 bytes",
                    "Non-primitive data types are called reference types because they refer to objects.", "Non-primitive types are created by the programmer and is not defined by Java (except for String).",
                    "Non-primitive types can be used to call methods to perform certain operations, while primitive types cannot.", "non-primitive types have all the same size.",
                    // type casting
                    "Proceeding to the next topic, Type Casting", "Type casting is when you assign a value of one primitive data type to another type.", "In Java, there are two types of casting:",
                    "Widening Casting (automatically) - converting a smaller type to a larger type size\n" +
                            "byte -> short -> char -> int -> long -> float -> double", "For example:" + "\n\n int myInt = 9;\n" + "double myDouble = myInt;",
                    "Narrowing Casting (manually) - converting a larger type to a smaller size type\n" +
                            "double -> float -> long -> int -> char -> short -> byte", "For example:" + "double myDouble = 9.78d;\n" + "int myInt = (int) myDouble")),
            new ArrayList<String>(Arrays.asList(
                    //operators
                    "You may know who I am already and I have a task for you to do again", "This is for you to master the topic do not complain", "now then, lets study on the next topic",
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
                            "\n! - Logical not - Reverse the result, returns false if the result is true"
            )),
            new ArrayList<String>(Arrays.asList(
                    "We have met once again and this will probably be the last I would see you :(",
                    "Our last meeting will check your mastery on the first stage. ",
                    "There will be a quiz that includes all the topics we have covered from the first stage",
                    "After proving your mastery on the topic and passing the quiz",
                    "You will be able to proceed to the next stage, which is the School!"
            )),
            new ArrayList<String>(Arrays.asList(
                    // Conditional Statements
                    "Our first topic is Conditional statements", "Java has the following conditional statements:" + "\n\nif, else, else if, and switch",
                    "Use if to specify a block of code to be executed, if a specified condition is true" + "\nfor example: " + "\nint x = 20;\n" + "int y = 18;\n" + "if (x > y) {\n" + "  System.out.println(\"x is greater than y\");\n" + "}",
                    "Use else to specify a block of code to be executed, if the same condition is false" + "\nfor example: " + "\nint time = 20;\n" + "if (time < 18) {\n" + "  System.out.println(\"Good day.\");\n" + "} else {\n" + "  System.out.println(\"Good evening.\");\n" + "}",
                    "Use else if to specify a new condition to test, if the first condition is false" + "\nfor example: " + "int time = 22;\n" + "\nif (time < 10) {\n" + "  System.out.println(\"Good morning.\");\n" + "} else if (time < 20) {\n" + "  System.out.println(\"Good day.\");\n" + "} else {\n" + "  System.out.println(\"Good evening.\");\n" + "}",
                    "There is also a short-hand if else, which is known as the ternary operator because it consists of three operands.", "It can be used to replace multiple lines of code with a single line, and is most often used to replace simple if else statements:",
                    "For example" + "int time = 20;\n" + "String result = (time < 18) ? \"Good day.\" : \"Good evening.\";\n" + "System.out.println(result);",
                    "Use switch to specify many alternative blocks of code to be executed", "The switch expression is evaluated once.", "he value of the expression is compared with the values of each case.", "If there is a match, the associated block of code is executed.", "The break and default keywords are optional",
                    "When Java reaches a break keyword, it breaks out of the switch block.\n" + "This will stop the execution of more code and case testing inside the block.\n" + "When a match is found, and the job is done, it's time for a break. There is no need for more testing.",
                    "The default keyword specifies some code to run if there is no case match:", "Example" + "\n\nint day = 4;\n" + "switch (day) {\n" + "  case 6:\n" + "    System.out.println(\"Today is Saturday\");\n" + "    break;\n" + "  case 7:\n" +
                            "    System.out.println(\"Today is Sunday\");\n" + "    break;\n" + "  default:\n" + "    System.out.println(\"Looking forward to the Weekend\");\n" + "}"
            )),

            new ArrayList<String>(Arrays.asList(
                    // Looping Statements
                    "Our second topic is Looping Statements", "Loops can execute a block of code as long as a specified condition is reached.", "Loops are handy because they save time, reduce errors, and they make code more readable.", "The while loop loops through a block of code as long as a specified condition is true:",
                    "Example:" + "\n\nint i = 0;\n" + "while (i < 5) {\n" + "  System.out.println(i);\n" + "  i++;\n" + "}",
                    "The do/while loop is a variant of the while loop. This loop will execute the code block once, before checking if the condition is true, then it will repeat the loop as long as the condition is true.",
                    "Example:" + "\n\nint i = 0;\n" + "do {\n" + "  System.out.println(i);\n" + "  i++;\n" + "}\n" + "while (i < 5);",
                    "When you know exactly how many times you want to loop through a block of code, use the for loop instead of a while loop:", "Syntax\n" + "for (statement 1; statement 2; statement 3) {" + "}" + "\nStatement 1 is executed (one time) before the execution of the code block.\n" + "Statement 2 defines the condition for executing the code block.\n" + "Statement 3 is executed (every time) after the code block has been executed.",
                    "Example" + "\n\nfor (int i = 0; i < 5; i++) {\n" + "  System.out.println(i);\n" + "}",
                    "There is also a \"for-each\" loop, which is used exclusively to loop through elements in an array:", "Example" + "\n\nString[] cars = {\"Volvo\", \"BMW\", \"Ford\", \"Mazda\"};\n" + "for (String i : cars) {\n" + "  System.out.println(i);\n" + "}",
                    "You have already seen the break statement used in an earlier chapter of this tutorial. It was used to \"jump out\" of a switch statement.", "The break statement can also be used to jump out of a loop.",
                    "Example" + "\n\nfor (int i = 0; i < 10; i++) {\n" + "  if (i == 4) {\n" + "    break;\n" + "  }\n" + "  System.out.println(i);\n" + "}",
                    "You can also use break in while loops:", "Example" + "\n\nint i = 0;\n" + "while (i < 10) {\n" + "  System.out.println(i);\n" + "  i++;\n" + "  if (i == 4) {\n" + "    break;\n" + "  }\n" + "}",
                    "The continue statement breaks one iteration (in the loop), if a specified condition occurs, and continues with the next iteration in the loop.",
                    "Example" + "\n\nfor (int i = 0; i < 10; i++) {\n" + "  if (i == 4) {\n" + "    continue;\n" + "  }\n" + "  System.out.println(i);\n" + "}",
                    "You can also use continue in while loops:", "Example" + "int i = 0;\n" + "while (i < 10) {\n" + "  if (i == 4) {\n" + "    i++;\n" + "    continue;\n" + "  }\n" + "  System.out.println(i);\n" + "  i++;\n" + "}"
            )),

            new ArrayList<String>(Arrays.asList(
                    // Arrays
                    "Our third topic is Arrays", "Arrays are used to store multiple values in a single variable, instead of declaring separate variables for each value.","To declare an array, define the variable type with square brackets:" + "\n\nFor example:" + "\nString[] elements;",
                    "To insert values to it, we can use an array literal - place the values in a comma-separated list, inside curly braces:" + "\n\nFor example:" + "\nString[] elements = {\"Air\", \"Water\", \"Earth\", \"Fire\"};", "To create an array of integers, you could write:" + "\nint[] myNum = {1, 2, 3, 4};",
                    "Array indexes start with 0: [0] is the first element. [1] is the second element, etc.", "You access an array element by referring to the index number." + "\n\nFor example" + "\nString[] elements = {\"Air\", \"Water\", \"Earth\", \"Fire\"};" + "\nSystem.out.println(elements[0]);",
                    "To change the value of a specific element, refer to the index number:" + "\n\nFor example:" + "\nString[] elements = {\"Air\", \"Water\", \"Earth\", \"Fire\"};" + "\nelements[0] = \"metal\";" + "\nSystem.out.println(elements[0]);",
                    "To find out how many elements an array has, use the length property:" + "\n\nFor example:" + "\nString[] elements = {\"Air\", \"Water\", \"Earth\", \"Fire\"};" + "\nSystem.out.println(elements.length);",
                    "You can loop through the array elements with the for loop, and use the length property to specify how many times the loop should run." + "\n\nFor example:" + "\nString[] elements = {\"Air\", \"Water\", \"Earth\", \"Fire\"};\n" + "for (int i = 0; i < elements.length; i++) {\n" + "  System.out.println(elements[i]);\n" + "}",
                    "There is also a \"for-each\" loop, which is used exclusively to loop through elements in arrays:" + "\n\nFor example:" + "\nString[] elements = {\"Air\", \"Water\", \"Earth\", \"Fire\"};\n" + "for (String i : elements) {\n" + "  System.out.println(i);\n" + "}",
                    "A multidimensional array is an array of arrays." + "\nTo create a two-dimensional array, add each array within its own set of curly braces:" + "\n\nFor example:" + "\nint[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };",
                    "To access the elements of the myNumbers array, specify two indexes: one for the array, and one for the element inside that array. This example accesses the third element (2) in the second array (1) of myNumbers:", "For example" + "\n\nint[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };\n" + "int x = myNumbers[1][2];",
                    "We can also use a for loop inside another for loop to get the elements of a two-dimensional array", "For example" + "\n\n  public static void main(String[] args) {\n" + "    int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };\n" + "    for (int i = 0; i < myNumbers.length; ++i) {\n" + "      for(int j = 0; j < myNumbers[i].length; ++j) {\n" +
                            "        System.out.println(myNumbers[i][j]);\n" + "      }\n" + "    }\n" + "  }"
            )),

            new ArrayList<String>(Arrays.asList(
                    // Methods
                    "Our fourth topic is Methods", "A method is a block of code which only runs when it is called." + "\nYou can pass data, known as parameters, into a method.", "Methods are used to perform certain actions, and they are also known as functions.",
                    "A method must be declared within a class. It is defined with the name of the method, followed by parentheses (). Java provides some pre-defined methods, such as System.out.println(), but you can also create your own methods to perform certain actions:",
                    "Create a method inside Main:" + "\n\n Example:" + "\npublic class Main {\n" + "  static void myMethod() {\n" + "    // code to be executed\n" + "  }\n" + "}",
                    "To call a method in Java, write the method's name followed by two parentheses () and a semicolon;", "For example" + "\n\npublic class Main {\n" + "  static void myMethod() {\n" + "    System.out.println(\"I just got executed!\");\n" + "  }\n" + "\n" + "  public static void main(String[] args) {\n" +
                            "    myMethod();\n" + "  }\n" + "}"
            )),

            new ArrayList<String>(Arrays.asList(
                    // Parameters
                    "Our fifth topic is Parameters","Information can be passed to methods as parameter. Parameters act as variables inside the method.","Parameters are specified after the method name, inside the parentheses. You can add as many parameters as you want, just separate them with a comma.",
                    "For example" + "\n\npublic class Main {\n" + "  static void myMethod(String fname) {\n" + "    System.out.println(fname + \" Refsnes\");\n" + "  }\n" + "\n" + "  public static void main(String[] args) {\n" + "    myMethod(\"Liam\");\n" + "  }\n" + "}",
                    "You can have as many parameters as you like:", "For example" + "\n\npublic class Main {\n" + "  static void myMethod(String fname, int age) {\n" + "    System.out.println(fname + \" is \" + age);\n" + "  }\n" + "\n" + "  public static void main(String[] args) {\n" + "    myMethod(\"Liam\", 5);\n" + "  }\n" + "}",
                    "It is common to use if...else statements inside methods:", "For example" + "\n\npublic class Main {\n" + "\n" + "  static void checkAge(int age) {\n" + "\n" + "    if (age < 18) {\n" + "      System.out.println(\"Access denied - You are not old enough!\");\n" + "\n" +
                            "    } else {\n" + "      System.out.println(\"Access granted - You are old enough!\");\n" + "    }\n" + "\n" + "  }\n" + "\n" + "  public static void main(String[] args) {\n" + "    checkAge(20);\n" + "  }\n" + "}"
            )),
            new ArrayList<String>(Arrays.asList(
                    // Parameters Overloading
                    "Our Sixth topic is Parameters overloading", "multiple methods can have the same name with different parameters:" + "\n\nFor example:" + "\nint myMethod(int x)\n" + "float myMethod(float x)\n" + "double myMethod(double x, double y)",
                    "Instead of defining two methods that should do the same thing, it is better to overload one.", "For example" + "\n\nstatic int plusMethod(int x, int y) {\n" + "  return x + y;\n" + "}\n" + "\n" +
                            "static double plusMethod(double x, double y) {\n" + "  return x + y;\n" + "}\n" + "\n" +
                            "public static void main(String[] args) {\n" + "  int myNum1 = plusMethod(8, 5);\n" + "  double myNum2 = plusMethod(4.3, 6.26);\n" + "}"
            )),

            new ArrayList<String>(Arrays.asList(
                    "We have met once again and this will probably be the last I would see you :(",
                    "Our last meeting will check your mastery on the first stage. ",
                    "There will be a quiz that includes all the topics we have covered from the first stage",
                    "After proving your mastery on the topic and passing the quiz",
                    "You will be able to proceed to the next stage, which is the School!"
            )),

            new ArrayList<String>(Arrays.asList(
                    //classes
                    "Our first topic is Classes", "Everything in Java is associated with classes and objects, along with its attributes and methods.", "For example: in real life, a car is an object. The car has attributes, such as weight and color, and methods, such as drive and brake.",
                    "A Class is like an object constructor, or a \"blueprint\" for creating objects.", "To create a class, use the keyword class:" + "\n\nExample" + "\npublic class Main {\n" + "  int x = 5;\n" + "}" + "\n\nRemember that a class should always start with an uppercase first letter, and that the name of the java file should match the class name.",
                    // Objects
                    "Our last topic is Objects", "In Java, an object is created from a class. We have already created the class named Main, so now we can use this to create objects", "To create an object of Main, specify the class name, followed by the object name, and use the keyword new:" + "\n\nFor example:" +
                            "\npublic class Main {\n" + "  int x = 5;\n" + "\n" + "  public static void main(String[] args) {\n" + "    Main myObj = new Main();\n" + "    System.out.println(myObj.x);\n" + "  }\n" + "}",

                    "You can create multiple objects of one class:" + "\nExample" + "\n\npublic static void main(String[] args) {\n" + "    Main myObj1 = new Main();  // Object 1\n" + "    Main myObj2 = new Main();  // Object 2\n" + "    System.out.println(myObj1.x);\n" + "    System.out.println(myObj2.x);\n" + "  }"
            ))


    )
    );

    // This variable will show in code riddle minigame everytime the user got a correct or wrong answer
    private ArrayList<ArrayList<String>> codeRiddleFeedback = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            // Right answer for JediGrandpa
                            "Great job Jedisaur!", "You deserve my cookie!", "You are definitely my Grandson!",
                            "You're as smart as me :)", "You can do this!!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Wrong answer for JediGrandpa
                            "Please don't stay up playing all night..", "I taught you a while ago...",
                            "Read the question carefully..", "Be careful my Grandson"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Right answer for JediProf
                            "You actually listened during the lecture", "You really studied last night huh",
                            "Keep up the good work!", "How are you so good at this??", "Have you studied this before?"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Wrong answer for JediProf
                            "Your answer is wrong......", "Read the question carefully",
                            "Please be mindful of the question given", "Please Review the module"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Right answer for JediManager
                            "Great Job!", "You deserve a raise!!", "I hired you for this!",
                            "I knew you can do it!", "Great Work"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Wrong answer for JediManager
                            "Do your job properly!!", "Don't mess it up",
                            "Don't make me fire you..", "Do it right!"
                    ))


            )
    );

    // This variable will be shown after the minigame
    private ArrayList<ArrayList<String>> resultFeedback = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            // After minigame if passed
                            "Good job!", "Wow, that's impressive work.", "You are a fast learner.", "I'm so proud of your effort.", "How did you do this so fast?", "Terrific job!",
                            "Wow, you are so skilled!", "This is insanely good.", "Fantastic!", "Your level of expertise is impressive.", "This proves you are ready for more here.",
                            "Now this is good work.","Your progress is truly inspiring."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // After minigame if low score
                            "You should review xD", "Please review.."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Stage Complete
                            "Congratulations you've completed the stage in less than 5 minutes","Good job you've completed the stage in less than 10 minutes", "Nice you've completed the stage in less than 15 minutes", "You've completed the stage you're doing this right",
                            "You've passed the 5 minute mark.","You've passed the 10 minute mark.","You've passed the 15 minute mark."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Stage failed
                            "You should pay attention to JediGrandpa's teaches and try again", "Recall all your mistakes and try again.", "Visit and talk to JediGrandpa to recall the lesson and try again ",
                            "Try again to learn from your mistakes"
                    ))
            )
    );

//
//    private ArrayList<ArrayList<String>> behaviorFeedback = new ArrayList<>(
//            Arrays.asList());

    //"public static void main is always the start of each java program"

    // This variable will show in every 30 secs when the AI detects the behavior of the user is not engaged


    // Code: Order will have its own hint

    // Mystery Code and Fill in the Block has the same hints
    private ArrayList<ArrayList<String>> twoHintsContainer = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<String>(Arrays.asList(
                            // Minigame ID 1
                            "Remember that this is java programming",
                            "Try to remember the basic structure on how to create a java program",
                            "Make sure to check the syntax of the code",
                            "Make sure that there's always a closing statement",
                            "Don't be confuse on how to print an output in java"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Minigame ID 2
                            "Try to remember the basic structure on how to create a java program",
                            "Make sure to check the syntax of the code",
                            "Don't forget to check the closing statement",
                            "Remember that the output of Integer and String values are different",
                            "Plus sign can also be in the output of Integer"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Minigame ID 3
                            "Basic java program structure..",
                            "Make sure to check the syntax of the code",
                            "Don't forget to check the closing statement",
                            "Remember that the output is an Integer",
                            "Integer also has order of operation when being output"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Minigame ID 4
                            "Basic java program structure..",
                            "Make sure to check the syntax of the code",
                            "Don't forget to check the closing statement",
                            "Remember that the output is an Integer",
                            "Integer also has order of operation when being output"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Looping Statements
                            "In using a while loop always check if the condition in properly set.", "Don't forget to increase or decrease the variable in the condition", "" +
                                    "Don't forget there are 3 statements in a for loop", "Check if statement if it uses the correct comparison operator in a for loop",
                            "check variable name and array name if it is correct in using the for-each loop"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Arrays
                            "Did you properly declare an array", "Did you use the proper brackets in declaring an array", "Did you use the array length properly",
                            "0 is the first index in an array when accessing it", "When using for loop in arrays you will use the length property of an array",
                            "Did you properly put brackets in declaring an multidimensional array","In Multidimensional array you will need to specify two indexes when calling or accessing the elements"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Methods
                            "Did you properly declare your own method", "Did you properly call a method", "Did you use the pre-defined methods properly"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Parameters
                            "Did you properly set the parameters in a method", "Every parameters you set must be separated by a comma", "You can use primitive data types instead of void keyword if you want a return value in a method",
                            // Parameters Overloading
                            "You can't override the method when it is final or static", "Constructors in a method cannot be overridden",
                            "Use the proper variable type in when calling a method you override "
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Stage 3
                            // Classes
                            "Don't forget the keyword when creating a class", "THe name of the java file should match the class name", "Use proper syntax when creating a class"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Objects
                            "Did you specify the class name before the object name", "there should be a new keyword when creating an object", "Did you properly create an object"
                    ))
            )
    );


    private boolean statementEnd;
    float myFloatNum = 5.99f;
    private String npcName;
    private int stage;
    private String topic;

    public Dialogue(){
        statementEnd = false;
    }

    public String reader(int nextStatement){

        System.out.println(getNpcName());
        if(nextStatement == introductionDialogue.get(getStage() - 1).size()){
            System.out.println("true");
            setStatementEnd(true);
        }else{
            setStatementEnd(false);
            System.out.println("dumaan ako dito");
            return introductionDialogue.get(getStage() - 1).get(nextStatement);
        }

        return "";
    }

    public String resultFeedback(int index){
        Random random = new Random();
        int limit = resultFeedback.get(index).size() - 1;
        int number = random.nextInt(limit);

        return resultFeedback.get(index).get(number);
    }

    public String codeRiddleFeedback(int index){
        Random random = new Random();
        int limit = codeRiddleFeedback.get(index).size() - 1;
        int number = random.nextInt(limit);

        return codeRiddleFeedback.get(index).get(number);
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public boolean isStatementEnd() {
        return statementEnd;
    }

    public void setStatementEnd(boolean statementEnd) {
        this.statementEnd = statementEnd;
    }

    public String getNpcName() {
        return npcName;
    }
}
