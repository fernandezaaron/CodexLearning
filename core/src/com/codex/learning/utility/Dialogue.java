package com.codex.learning.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dialogue {




    private ArrayList<ArrayList<String>> introductionDialogue = new ArrayList<>(Arrays.asList(
            new ArrayList<String>(Arrays.asList("Welcome to Jediland, Jedisaur.", "Hi I am, JediGrandpa", "From today, you'll be my Padawan, and I'll teach you the ways of a true Jedi.",
                    "Instead of teaching you how to use the force, I'll teach you the fundamentals of Java.", "These teachings have been passed down by many generations, and they were developed by: James Gosling in May 1995", "I have a task for you to do young one", "But first lets study first on the topic",
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
                    // "Welcome to School, Jedisaur", "Hi I am, JediTeacher", "This teaching is harder than what JediGrandpa teaches you", "I have a task for you to do young one", "But first lets study first on the topic",
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
                    "You've made JediGrandpa proud by reaching this stage",
                    "Our last meeting will check your mastery on the second stage. ",
                    "There will be a quiz that includes all the topics we have covered from the second stage",
                    "After proving your mastery on the topic and passing the quiz",
                    "You will be able to proceed to the next stage, which is the Company!"
            )),

            new ArrayList<String>(Arrays.asList(
                    //classes
                    // "Welcome to the Company, Jedisaur", "Hi I am, JediManager", "This lesson is harder than the previous one you encountered.", "I have a task for you to do young one", "But first lets study first on the topic",
                    "Our first topic is Classes", "Everything in Java is associated with classes and objects, along with its attributes and methods.", "For example: in real life, a car is an object. The car has attributes, such as weight and color, and methods, such as drive and brake.",
                    "A Class is like an object constructor, or a \"blueprint\" for creating objects.", "To create a class, use the keyword class:" + "\n\nExample" + "\npublic class Main {\n" + "  int x = 5;\n" + "}" + "\n\nRemember that a class should always start with an uppercase first letter, and that the name of the java file should match the class name.",
                    // Objects
                    "Our last topic is Objects", "In Java, an object is created from a class. We have already created the class named Main, so now we can use this to create objects", "To create an object of Main, specify the class name, followed by the object name, and use the keyword new:" + "\n\nFor example:" +
                            "\npublic class Main {\n" + "  int x = 5;\n" + "\n" + "  public static void main(String[] args) {\n" + "    Main myObj = new Main();\n" + "    System.out.println(myObj.x);\n" + "  }\n" + "}",

                    "You can create multiple objects of one class:" + "\nExample" + "\n\npublic static void main(String[] args) {\n" + "    Main myObj1 = new Main();  // Object 1\n" + "    Main myObj2 = new Main();  // Object 2\n" + "    System.out.println(myObj1.x);\n" + "    System.out.println(myObj2.x);\n" + "  }"
            )),

            new ArrayList<String>(Arrays.asList(
                    "We have met once again and this will probably be the last I would see you :(",
                    "You've made JediGrandpa and JediTeacher proud by reaching this stage",
                    "Our last meeting will check your mastery on the third stage.",
                    "There will be a quiz that includes all the topics we have covered from the third stage",
                    "After proving your mastery on the topic and passing the quiz",
                    "There will be a exam that will include all the topics we have covered from all the stages",
                    "After proving your mastery on all the topics and passing the exam",
                    "You're no longer a Padawan because you've become a full-fledged Jedi Master",
                    "You've made all of us proud, young one",
                    "Pass on what you have learned."


            ))


    )
    );

    private ArrayList<ArrayList<String>> resultFeedback = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            // After minigame if passed
                            "Good job", "Wow, that's impressive work.", "You are a fast learner.", "I'm so proud of your effort.", "How did you do this so fast?", "Terrific job!",
                            "Wow, you are so skilled!", "This is insanely good.", "Fantastic!", "Your level of expertise is impressive.", "This proves you are ready for more here.",
                            "Now this is good work.","Your progress is truly inspiring.","Outstanding work!", "You are a fast learner."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // After minigame if low score
                            "You should review xD", "Please review", "Try to focus on the topic you got a low score", "Did you really understand the lesson?", "Maybe you just answer the question without understanding it",
                            "Pls don't shotgun the questions", "Please try again", ""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Stage Complete
                            "Congratulations you've completed the stage in less than 5 minutes","Good job you've completed the stage in less than 10 minutes", "Nice you've completed the stage in less than 15 minutes", "You've completed the stage you're doing this right",
                            "You've passed the 5 minute mark.","You've passed the 10 minute mark.","You've passed the 15 minute mark.", "Congrats on finishing the stage"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Stage failed
                            "You should pay attention to JediGrandpa's teaches and try again", "Recall all your mistakes and try again.",
                            "Visit and talk to JediGrandpa to recall the lesson and try again ", "Maybe you didn't understand the topic",
                            "Try again to learn from your mistakes", "Maybe that's why you failed. You didn't learn from your mistakes"
                    ))
            )
    );


   private ArrayList<ArrayList<String>> behaviorFeedback = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            // Player Engaged
                            "Keep doing what you are doing","Keep up the good work",
                            "Wow you already mastered this topic", "Do you find the question to be Exciting?",
                            "Does the question challenge you?", "You certainly are an expert on this topic", "How come you answer faster than others?",
                            "Wow you're more interested in this topic than others", "You've put in a lot of effort to answer the question",
                            "I can tell you have been paying attention and answering the questions. Nicely done!" //10
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Player Neutral
                            "Sounds like you've got it under control", "Trust your ability to answer the question", "Keep answering you can do it!","Just take one step at a time in answering",
                            "You can still do better than the last time", "You've come prepared in this topic", "", "", "", "" // 6
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Player Bored
                            "Does the question not challenge you?",  "Is the question too long for you?", "Is the question too simple for you?",
                            "Do the questions not stimulate your brain?", "Maybe you don't find the topic interesting", "You can listen to your favorite music while playing",
                            "Maybe try another type of mini-game if it becomes interesting to you", "", "", "" // 7
                            // "Do you find the topic to be somewhat boring?", "Is the question easy for you, and that's why you are bored?",
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Player Confused
                            "Take a deep breathe and recall the lesson", "Read the topic carefully until you understand it",
                            "Read the question carefully and try to understand it", "Don't give up", "Is the question too difficult for you?",
                            "Maybe you just need a different perspective to answer it", "Go back to the topic that you're having difficulty with",
                            "Maybe you need to use a hint to help you overcome this question", "If you can address the problem, you can answer it!",
                            "Trust your gut if you don't know the answer" // 10
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Player Frustrated
                            "Calm down you can solve it", "Clear your mind and look at the question again", "Take a few minutes break",
                            "Relax and review the lesson","Don't be mad and use a hint so that you can move on from this question" ,
                            "Maybe you just need to take a break", "Play calming music when you are having a bad time answering the question",
                            "Maybe take a break and do some stretches before going back to the question and answering it",
                            "Maybe think outside of the box when you are answering this question", "Consider the outcomes of all possible answers to the questions." // 10
                    ))
            )
   );


    private ArrayList<ArrayList<String>> hintsContainer = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<String>(Arrays.asList(
                            // Stage 1
                            // Syntax
                            "Did you check the end of every code statement?", "Did you check the class name if it's uppercase or lowercase?",
                            "Multi-line comments must start and ends with?", "You should put the single-line comment after the code"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Variables
                            "The value of a String should be surrounded by?", "Unlike String, the value of char should be surrounded by?", "In float it is just like a double variable but it has an f at the end of the number",
                            "In boolean the value should not be surrounded by single or double quotes", "in int the values should not have a decimal point",
                            "The variable name should not contain whitespaces", "you can't overwrite a variable if it has a final keyword in it",
                            // Data Types
                            "int data type can only have whole numbers", "String is a primitive data type", "unlike String, char is a non-primitive data type", "boolean is the only one that has a bit size", "There are only 8 primitive types", "The highest size of the primitive data type is 8 bytes",
                            // Type Casting
                            "You can't convert double to int automatically", "Use widening casting to convert small size to larger size", "Use narrowing casting to convert larger size to smaller size",
                            "In narrowing casting you must place the variable type in parentheses in front of the value"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Operators
                            "Use Arithmetic Operators to perform math operations", "Use proper assignment operations to assign value to a variable",
                            "You can use Comparison operations to compare two values"
                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Stage 2
                            // Conditional Statements
                            "Check the condition in the if-statement if the condition is true", "Check the condition in the else-if statement if it is true",
                            "When using short hand check if you use a the proper ternary operator","Don't forget the expression when using switch case","Don't forget to use break after every switch statements"
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

                /*    new ArrayList<String>(Arrays.asList(
                            // Minigames
                            // Stage 1 - easy
                            // Minigame_ID - 1
                            "It is a simple code that will print out the firstname and lastname",
                            // Minigame_ID - 2
                            "The output should be 50",
                            // Minigame_ID - 3
                            "The first output should be 43, then 1, followed by 19 and the last one is 13",
                            // Minigame_ID - 4
                            "The output will form a big JAVA word when printed out",
                            // Minigame_ID - 5
                            "The system will ask you to input two numbers and the output is usually false unless you put the two numbers as 0.5 ",
                            // Minigame_ID - 6
                            "The first output should be 10 and the next output should be 10.0"

                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Stage 2 - easy
                            // Minigame_ID - 7
                            "It will ask to input two numbers and if the two numbers are equal the output should be \"Numbers are equal\" else it is not equal",
                            // Minigame_ID - 8
                            "It is a simple loop that will print all the odd numbers from 1 to 100",
                            // Minigame_ID - 9
                            "It is a simple loop that will print \"java is fun\" five times",
                            // Minigame_ID - 10
                            "This is a loop that will print out the value of sum = 1, 3, 10 until it reaches 500500",
                            // Minigame_ID - 11
                            "This is just a simple method, when the method is called it will pass along the first names of liam, jenny, anja inside the method and it will print out their full names",
                            // Minigame_ID - 12
                            "This is a simple method that will overwrite the value of x and it will print out 8"

                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Stage 3 - easy
                            // Minigame_ID - 13
                            "When the object is called it will print the value of 5",
                            // Minigame_ID - 14
                            "When the object is called it will print the value of 25",
                            // Minigame_ID - 15
                            "When you call the class you will need to add 2 values of the parameters to print the students name and id",
                            // Minigame_ID - 16
                            "When you call this class it print the value of count",
                            // Minigame_ID - 17
                            "This is a sample of a class called Dog wherein it is called it will print out the following: \"Dogs!\" and \"Creating\" a dog ",
                            // Minigame_ID - 18
                            "This will print out the factorial of 5"

                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Stage 1 - easy
                            // CodeRiddle
                            // AnswerID - 1
                            "Inside of the parenthesis there should be a [] at the end",
                            // AnswerID - 2
                            "Only the first letter is capital then the rest is lowercase",
                            // AnswerID - 3
                            "It has the word semi in it",
                            // AnswerID - 4
                            "It has the word colon in it",
                            // AnswerID - 5
                            "It has the word System in it",
                            // AnswerID - 6
                            "This * should be in side the comment",
                            // AnswerID - 7
                            "It has the word beef",
                            // AnswerID - 8
                            "It should not be enclosed by \"\" or ''",
                            // AnswerID - 9
                            "There should be an f after the number",
                            // AnswerID - 10
                            "It is short form for floating",
                            // AnswerID - 11
                            "It is short form for character",
                            // AnswerID - 12
                            "It is not an integer because it has decimal point and it should not be enclosed by \"\" or ''",
                            // AnswerID - 13
                            "The variable type should come first and the value should be enclosed by \"\" ",
                            // AnswerID - 14
                            "It should start with 0 and it ends with ---35",
                            // AnswerID - 15
                            "There are only 2 data types that can hold digits and integer can't hold decimals",
                            // AnswerID - 16
                            "Char and boolean doesn't need to be enclosed in a double quotes",
                            // AnswerID - 17
                            "Only boolean has 2 values",
                            // AnswerID - 18
                            "String can hold more than one characters",
                            // AnswerID - 19
                            "String can also hold numbers and characters but it must me enclosed with double quotes",
                            // AnswerID - 20
                            "Integer can hold whole numbers",
                            // AnswerID - 21
                            "Integers can hold numbers that has decimal on it",
                            // AnswerID - 22
                            "Protected is used as an access modifier, volatile is for threads and abstract is for classes and methods ",
                            // AnswerID - 23
                            "Only int to long can be converted automatically",
                            // AnswerID - 24
                            "If you want to automatically convert it should be from smaller to larger type size",
                            // AnswerID - 25
                            "If any operand is double the result of expression is double",
                            // AnswerID - 26
                            "It is just like converting float to int",
                            // AnswerID - 27
                            "You can convert the data type if you manually place the type in parentheses in front of the value",
                            // AnswerID - 28
                            "Int can hold byte or literal numbers",
                            // AnswerID - 29
                            "If you see this ++, it will automatically add 1",
                            // AnswerID - 30
                            "Only ==, !=, >, <, >=, <=, can be used to compare two values ",
                            // AnswerID - 31
                            "Only division and multiplication has the same precedence",
                            // AnswerID - 32
                            "If ++ is called increment then -- is called?",
                            // AnswerID - 33
                            "you cant use x as the multiplication operator",
                            // AnswerID - 34
                            "You need to use + to combine a message to another message or variable to be displayed together"

                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Stage 2 - easy
                            // CodeRiddle
                            // AnswerID - 35
                            "Only && can be used to replace the nested if",
                            // AnswerID - 36
                            "Default is used if some code to run if there is no case match",
                            // AnswerID - 37
                            "Relational operators is used for conditions",
                            // AnswerID - 38
                            "Remember if, else, if-else, if-else-if are used in conditional statements",
                            // AnswerID - 39
                            "The fall through will happen if we don't have a break statement in a switch",
                            // AnswerID - 40
                            "Logical errors run but it produce wrong output",
                            // AnswerID - 41
                            "A is not equal a so that means it is not true",
                            // AnswerID - 42
                            "You don't need to put and else in every if statements",
                            // AnswerID - 43
                            "If the statement is true it will run in the if-else statement",
                            // AnswerID - 44
                            "Never stops or in other words infinite",
                            // AnswerID - 45
                            "You can always stop a loop if you put a break in it",
                            // AnswerID - 46
                            "The correct syntax for the while loop is: while(condition)",
                            // AnswerID - 47
                            "In the switch statement if it matches the values of expression it will execute that part of the code",
                            // AnswerID - 48
                            "This will print 10 times because the value of count is 0 in the beginning",
                            // AnswerID - 49
                            "This will print 10 times because the value of count is 0 in the beginning before it prints Welcome to java",
                            // AnswerID - 50
                            "This will print 3 and while printing it will keep subtracting it until it reaches 0",
                            // AnswerID - 51
                            "This will print 10 times because the value of count is 1 in the beginning and it will stop if the value of count is 10",
                            // AnswerID - 52
                            "Remembre the sysntax: variable variablename [] = {values}",
                            // AnswerID - 53
                            "It is an object that can hold group of values",
                            // AnswerID - 54
                            "Array can hold group of values that has the same type",
                            // AnswerID - 55
                            "Remember array starts ay index 0",
                            // AnswerID - 56
                            "Always start counting in 0 in array",
                            // AnswerID - 57
                            "It should be 4 because you start at 0",
                            // AnswerID - 58
                            "Methods is a collection of statements ",
                            // AnswerID - 59
                            "It should be public double",
                            // AnswerID - 60
                            "If you want to restrict the access you need to add private",
                            // AnswerID - 61
                            "To get the highest value you use Math.max()",
                            // AnswerID - 62
                            "Method can control the program",
                            // AnswerID - 63
                            "You must use return keyword to return a value",
                            // AnswerID - 64
                            "Parameteers are varaibles passed into a method",
                            // AnswerID - 65
                            "Check the values of parameters that was used in int = w, then check the parameters of the method carefully before solving",
                            // AnswerID - 66
                            "It will print the values of a, b, and c",
                            // AnswerID - 67
                            "You must first solve the value inside the parenthesis then the outside",
                            // AnswerID - 68
                            "It will be compilation error because c is not declared in the method of sum"

                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Stage 3 - easy
                            // CodeRiddle
                            // AnswerID - 69
                            "You can't import if you don't use the import keyword",
                            // AnswerID - 70
                            "The class always defines an object",
                            // AnswerID - 71
                            "Objects is always an instance of a class",
                            // AnswerID - 72
                            "You can't declare a class if you don't use a class keyword",
                            // AnswerID - 73
                            "Class variables are shared in every instances in a class",
                            // AnswerID - 74
                            "Boolean default value is false or 0 or null",
                            // AnswerID - 75
                            "Default constructor are always provided and it is a non-arg",
                            // AnswerID - 76
                            "Objecs can represent an entity at the real world",
                            // AnswerID - 77
                            "Remember the syntax: class name object name = new class name",
                            // AnswerID - 78
                            "Object always represent and entity",
                            // AnswerID - 79
                            "When you invoke an object you will also get its reference of the object",
                            // AnswerID - 80
                            "You will need a constructor to  call or create an object",
                            // AnswerID - 81
                            "Remember that when you call an object it's reference is also passed",
                            // AnswerID - 82
                            "The value dates is null because it has not been set, remember to use [] when accessing or declaring a new array"

                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Stage 1 - medium
                            // CodeRiddle
                            // AnswerID - 83
                            "Method, subclasses, and reference are not reserved words",
                            // AnswerID - 84
                            "Use the comparison operator called equal to",
                            // AnswerID - 85
                            "Comment is always optional in describing a code",
                            // AnswerID - 86
                            "Boolean variables has only two value and it is 1 byte per value, if you convert it to bit: 1 byte = 8 bit",
                            // AnswerID - 87
                            "All variables default value is 0",
                            // AnswerID - 88
                            "Class can consist of local, class or instance variable",
                            // AnswerID - 89
                            "Double can only hold whole numbers and decimal numbers only",
                            // AnswerID - 90
                            "If you use relational operator it's return value is either true or false",
                            // AnswerID - 91
                            "String value can be declared null or it can be declared many letters enclosed in a double quote",
                            // AnswerID - 92
                            "String, float, double, interface, class are examples of a keyword",
                            // AnswerID - 93
                            "Short can store from -32--- to -----",
                            // AnswerID - 94
                            "Only double can be returned in transcendental",
                            // AnswerID - 95
                            "Unicode defines fully international character set ",
                            // AnswerID - 96
                            "Boolean has only two values and these are??",
                            // AnswerID - 97
                            "Carefully check the values assigned in the variables",
                            // AnswerID - 98
                            "The expression should only have the same data type  ",
                            // AnswerID - 99
                            "It should be a whole number when you multiply the convert it to double",
                            // AnswerID - 100
                            "Convert it into a whole number before you multiply",
                            // AnswerID - 101
                            "You can't type cast boolean to numbers",
                            // AnswerID - 102
                            "Binary operator has 2 operands",
                            // AnswerID - 103
                            "Only [] and () are the highest",
                            // AnswerID - 104
                            "If the expression has ++ in it then it will always add 1 to it",
                            // AnswerID - 105
                            "If you use this % operator it will only return the division remainder",
                            // AnswerID - 106
                            "This will not compile because of the symbol !"

                    )),

                    new ArrayList<String>(Arrays.asList(
                            // Stage 2 - medium
                            // CodeRiddle
                            // AnswerID - 107
                            "Using System.exit will automatically exit the program",
                            // AnswerID - 108
                            "Carefully solve the problem",
                            // AnswerID - 109
                            "Carefully check the comparison operator that was used",
                            // AnswerID - 110
                            "Carefully place the values in the variables and check the conditions in the if else-if",
                            // AnswerID - 111
                            "When you use a loop it will run until it has encountered a false statement",
                            // AnswerID - 112
                            "Carefully check the condition in the while loop",
                            // AnswerID - 113
                            "Note that the value of count is 0 and carefully check the condition",
                            // AnswerID - 114
                            "Carefully check the code and it's condition",
                            // AnswerID - 115
                            "Carefully note that there are 2 increment used in the loop",
                            // AnswerID - 116
                            "Carefully follow the syntax: type [] variablename = {values}",
                            // AnswerID - 117
                            "Remember in arrays it's index starts at 0",
                            // AnswerID - 118
                            "Null will be the value of an array you declared without assigning a value",
                            // AnswerID - 119
                            "Length will show how many elements in an array",
                            // AnswerID - 120
                            "The answer is in the question",
                            // AnswerID - 121
                            "It is related to throw",
                            // AnswerID - 122
                            "Instance method need an object before you can call it",
                            // AnswerID - 123
                            "You need to capitalize the U and C",
                            // AnswerID - 124
                            "Remember that method can make a program readable, avoid repeating a code and can break a program into smaller parts",
                            // AnswerID - 125
                            "When you use a float it will automatically have a decimal point if it is a whole number",
                            // AnswerID - 126
                            "Carefully understand the code and place the value of x inside the parentheses and solve",
                            // AnswerID - 127
                            "We use overriding if we have the same name of a method in sub class and super class",
                            // AnswerID - 128
                            "Carefully check the conditions in every method",
                            // AnswerID - 129
                            "Carefully replace the variable with it's value and check the conditions within the method",
                            // AnswerID - 130
                            "Carefully solve the problem and note that it is declared as double so it will automatically have a decimal point at the end"

                    ))
*/

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
