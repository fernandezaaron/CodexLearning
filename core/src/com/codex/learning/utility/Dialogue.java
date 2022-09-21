package com.codex.learning.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dialogue {

    private ArrayList<ArrayList<String>> introductionDialogue = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList("Hi Jedisaur, I suppose you must be ready now!", "I have a task for you to do", "But first lets study first on the topic",
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
            new ArrayList<>(Arrays.asList(
                    //variables
                    "Welcome on your 2nd day,", "I have a task for you to do", "But first lets study on the topic",
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
            new ArrayList<>(Arrays.asList(
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
            new ArrayList<>(Arrays.asList(
                    "We have met once again and this will probably be the last I would see you :(",
                    "Our last meeting will check your mastery on the first stage. ",
                    "There will be a quiz that includes all the topics we have covered from the first stage",
                    "After proving your mastery on the topic and passing the quiz",
                    "You will be able to proceed to the next stage, which is the School!"
            )),
            new ArrayList<>(Arrays.asList(
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

            new ArrayList<>(Arrays.asList(
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

            new ArrayList<>(Arrays.asList(
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

            new ArrayList<>(Arrays.asList(
                    // Methods
                    "Our fourth topic is Methods", "A method is a block of code which only runs when it is called." + "\nYou can pass data, known as parameters, into a method.", "Methods are used to perform certain actions, and they are also known as functions.",
                    "A method must be declared within a class. It is defined with the name of the method, followed by parentheses (). Java provides some pre-defined methods, such as System.out.println(), but you can also create your own methods to perform certain actions:",
                    "Create a method inside Main:" + "\n\n Example:" + "\npublic class Main {\n" + "  static void myMethod() {\n" + "    // code to be executed\n" + "  }\n" + "}",
                    "To call a method in Java, write the method's name followed by two parentheses () and a semicolon;", "For example" + "\n\npublic class Main {\n" + "  static void myMethod() {\n" + "    System.out.println(\"I just got executed!\");\n" + "  }\n" + "\n" + "  public static void main(String[] args) {\n" +
                    "    myMethod();\n" + "  }\n" + "}"
            )),

            new ArrayList<>(Arrays.asList(
                    // Parameters
                    "Our fifth topic is Parameters","Information can be passed to methods as parameter. Parameters act as variables inside the method.","Parameters are specified after the method name, inside the parentheses. You can add as many parameters as you want, just separate them with a comma.",
                    "For example" + "\n\npublic class Main {\n" + "  static void myMethod(String fname) {\n" + "    System.out.println(fname + \" Refsnes\");\n" + "  }\n" + "\n" + "  public static void main(String[] args) {\n" + "    myMethod(\"Liam\");\n" + "  }\n" + "}",
                    "You can have as many parameters as you like:", "For example" + "\n\npublic class Main {\n" + "  static void myMethod(String fname, int age) {\n" + "    System.out.println(fname + \" is \" + age);\n" + "  }\n" + "\n" + "  public static void main(String[] args) {\n" + "    myMethod(\"Liam\", 5);\n" + "  }\n" + "}",
                    "It is common to use if...else statements inside methods:", "For example" + "\n\npublic class Main {\n" + "\n" + "  static void checkAge(int age) {\n" + "\n" + "    if (age < 18) {\n" + "      System.out.println(\"Access denied - You are not old enough!\");\n" + "\n" +
                            "    } else {\n" + "      System.out.println(\"Access granted - You are old enough!\");\n" + "    }\n" + "\n" + "  }\n" + "\n" + "  public static void main(String[] args) {\n" + "    checkAge(20);\n" + "  }\n" + "}",
                    // Parameters Overloading

                    "Our Sixth topic is Parameters overloading", "multiple methods can have the same name with different parameters:" + "\n\nFor example:" + "\nint myMethod(int x)\n" + "float myMethod(float x)\n" + "double myMethod(double x, double y)",
                    "Instead of defining two methods that should do the same thing, it is better to overload one.", "For example" + "\n\nstatic int plusMethod(int x, int y) {\n" + "  return x + y;\n" + "}\n" + "\n" +
                            "static double plusMethod(double x, double y) {\n" + "  return x + y;\n" + "}\n" + "\n" +
                            "public static void main(String[] args) {\n" + "  int myNum1 = plusMethod(8, 5);\n" + "  double myNum2 = plusMethod(4.3, 6.26);\n" + "}"
            )),

            new ArrayList<>(Arrays.asList(
                    "We have met once again and this will probably be the last I would see you :(",
                    "Our last meeting will check your mastery on the first stage. ",
                    "There will be a quiz that includes all the topics we have covered from the first stage",
                    "After proving your mastery on the topic and passing the quiz",
                    "You will be able to proceed to the next stage, which is the School!"
            )),

            new ArrayList<>(Arrays.asList(
                    //classes
                    "Our first topic is Classes", "Everything in Java is associated with classes and objects, along with its attributes and methods.", "For example: in real life, a car is an object. The car has attributes, such as weight and color, and methods, such as drive and brake.",
                    "A Class is like an object constructor, or a \"blueprint\" for creating objects.", "To create a class, use the keyword class:" + "\n\nExample" + "\npublic class Main {\n" + "  int x = 5;\n" + "}" + "\n\nRemember that a class should always start with an uppercase first letter, and that the name of the java file should match the class name."

            )),

            new ArrayList<>(Arrays.asList(
                    // Objects
                    "Our last topic is Objects", "In Java, an object is created from a class. We have already created the class named Main, so now we can use this to create objects", "To create an object of Main, specify the class name, followed by the object name, and use the keyword new:" + "\n\nFor example:" +
                            "\npublic class Main {\n" + "  int x = 5;\n" + "\n" + "  public static void main(String[] args) {\n" + "    Main myObj = new Main();\n" + "    System.out.println(myObj.x);\n" + "  }\n" + "}",

                    "You can create multiple objects of one class:" + "\nExample" + "\n\npublic static void main(String[] args) {\n" + "    Main myObj1 = new Main();  // Object 1\n" + "    Main myObj2 = new Main();  // Object 2\n" + "    System.out.println(myObj1.x);\n" + "    System.out.println(myObj2.x);\n" + "  }"
            ))


    )
    );
    private ArrayList<ArrayList<String>> noToPlayRoomDialogue = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                        //For JediGrandpa
                        "Grandson! You can't go there yet. Make me proud first by finishing \n" +
                        "the questions in the computer. That way, I can let you go in."
                    )),
                    new ArrayList<>(Arrays.asList(
                        //For JediProf
                        "Jedisaur! You can't go there yet. Finish the tasks in the \n" +
                        "computer first. That way, I can let you go in and give another task."
                    )),
                    new ArrayList<>(Arrays.asList(
                        //For JediManager
                        "Mr.Jedisaur! You can't go there yet. Finish the questions in the \n" +
                        "computer. That way, I can let you go in and give another work."
                    ))
            ));


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
    private ArrayList<ArrayList<String>> codeOrderHints = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            // Syntax
                            "Arrange the block according to the output needed",
                            "Don't forget the output needed",
                            "Try to remember the basic structure on how to create a java program",
                            "Always check the brackets when arranging the code blocks",
                            "Remember that public static void main is where you output the code"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Comments
                            "Arrange the block according to the output needed",
                            "Don't forget the output needed",
                            "Remember the difference between the two comments",
                            "Remember that public static void main is where you output the code",
                            "Comments can be placed everywhere in the code, try to recall"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Variables
                            "Arrange the block according to the output needed",
                            "Don't forget the output needed",
                            "Remember the difference between the two comments",
                            "Remember that public static void main is where you output the code",
                            "Comments can be placed everywhere in the code, try to recall"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Data Types
                            "Arrange the block according to the output needed",
                            "Don't forget the output needed",
                            "Don't be confused to all the Data Types in Java",
                            "Data Types are just classification of a variable",
                            "Declaration of the Data Types are always above the print"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Type Casting
                            "Arrange the block according to the output needed",
                            "Don't forget the output needed",
                            "There are different ways on how to Type Cast a variable",
                            "Not all variables can be Type Cast",
                            "Type Casting is always at the right side of a variable"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Operators
                            "Arrange the block according to the output needed",
                            "Don't forget the output needed",
                            "Remember the order of operations",
                            "Try to solve the numbers one at the time",
                            "Don't be confused by the increments"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Conditional Statement
                            "Arrange the block according to the output needed",
                            "Don't forget the output needed",
                            "Remember the Conditional Statement Syntax",
                            "Try to check if the statement makes sense",
                            "Else statement or Default are always at the bottom"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Loops
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Don't forget the syntax of the loop",
                            "Double check if there is a declared variable to iterate",
                            "Declared variables are always within or above the loop"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Arrays
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Arrays are different to initialized from normal variables",
                            "Indexes of an array can be access through multiple ways",
                            "Indexes of an array starts with 0"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Methods
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Don't be confuse between the PSVM and methods",
                            "Methods doesn't have main",
                            "Methods can return a value and can output a value"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Parameters
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Parameters are inside the parenthesis of a method",
                            "Parameters are similar to the PSVM's String args",
                            "Methods can have 2 or more parameters"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Parameters Overloading
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Methods are can have the same name but different parameter",
                            "Parameters are similar to the PSVM's String args",
                            "Methods can have 2 or more parameters"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Classes
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Variables, or Methods declared in classes are above PSVM",
                            "PSVM is always under a class",
                            "It is necessary to create an object first before calling its function"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Objects
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Variables, or Methods declared in classes are above PSVM",
                            "PSVM is always under a class",
                            "It is necessary to create an object first before calling its function"
                    ))


            )
    );


    // Mystery Code and Fill in the Block has the same hints
    private ArrayList<ArrayList<String>> twoHintsContainer = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            // Syntax
                            "Remember that this is java programming",
                            "Try to remember the basic structure on how to create a java program",
                            "Make sure to check the syntax of the code",
                            "Make sure that there's always a closing statement",
                            "Don't be confuse on how to print an output in java"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Comments
                            "Try to remember the output of the program and where to place the comments",
                            "Try to remember on how to create a comment in java program",
                            "Make sure to check the syntax of the code",
                            "Don't forget to check the closing statement",
                            "Remember that there are two kinds of comments in java"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Variables
                            "Try to remember the output of the program",
                            "Make sure to double check the variables",
                            "Don't forget to check the closing statement",
                            "Output of the program depends on the variable",
                            "Place the variable as the output if necessary"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Data Types
                            "Try to remember the output of the program",
                            "Make sure to check data declared in the variables",
                            "Don't forget to check the closing statement",
                            "Output of the program depends on the variable",
                            "Place the variable as the output if necessary"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Type Casting
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "There are multiple ways to type cast a variable",
                            "Output of the program depends on the variable",
                            "Place the variable as the output if necessary"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Operators
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Try to solve it one by one",
                            "Just do a simple math and you'll make it!",
                            "Place the variable as the output if necessary"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Conditional Statement
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Remember the syntax in conditional Statement",
                            "Don't be confuse between if and use-case statement",
                            "Don't forget that conditional statement has brackets too"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Loops
                            "Try to remember the output of the program",
                            "Iterations must be always at the end of the loop",
                            "Remember that each loops have different syntax",
                            "Loops always initialize an iterator first",
                            "When the iterator is in the output, it will show its value"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Arrays
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Pre-determined arrays have brackets",
                            "Indexes of an array always starts at 0",
                            "There are multiple ways to output an array"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Methods
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Methods are similar to PSVM",
                            "Methods can have return value",
                            "Methods always have brackets"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Parameters
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Parameters are inside the parenthesis of the method's name",
                            "Methods can have 2 or more parameters",
                            "The variable in the parameters are used inside the methods"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Parameters Overloading
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Always check the output needed in the PSVM",
                            "Methods can have similar name but different parameters",
                            "As long as it doesn't have the same parameter, it's called overloading"

                    )),

                    new ArrayList<>(Arrays.asList(
                            // Classes
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Variables, or Methods are outside the PSVM",
                            "It is necessary to create an object to call the class",
                            "Don't forget the parenthesis when creating the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Objects
                            "Try to remember the output of the program",
                            "Don't forget to check the closing statement",
                            "Variables, or Methods are outside the PSVM",
                            "It is necessary to create an object to call the class",
                            "Don't forget the parenthesis when creating the class"
                    ))
            )
    );

    private ArrayList<ArrayList<String>> minigameIntroDiaulogue = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            // Syntax
                            "Hello there Grandson! Welcome to the playroom! This is where \n" +
                            "your skills in programming will be tested.", "For today's topic, \n" +
                            "it will be syntax. Where you will be tested if you really get \n" +
                            "Java.", "This is one of the fundamentals that you need to learn in \n" +
                            "order to proceed!", "Syntax are needed in order to run the program \n" +
                            "But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Comments
                            "Hello there Grandson! Welcome to the playroom! This is where \n" +
                            "your skills in programming will be tested.", "For today's topic, \n" +
                            "it will be comments. Where you will be tested if you really get \n" +
                            "Java.", "This is one of the fundamentals that you need to learn in \n" +
                            "order to proceed!", "Comments are useful in order not to forget the \n" +
                            "use of its function. But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Variables
                            "Hello there Grandson! Welcome to the playroom! This is where \n" +
                            "your skills in programming will be tested.", "For today's topic, \n" +
                            "it will be variable. Where you will be tested if you really get \n" +
                            "Java.", "This is one of the fundamentals that you need to learn in \n" +
                            "order to proceed!", "Variables are useful in order to store data and use\n" +
                            "it to output. But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Data Types
                            "Hello there Grandson! Welcome to the playroom! This is where \n" +
                            "your skills in programming will be tested.", "For today's topic, \n" +
                            "it will be data types. Where you will be tested if you really get \n" +
                            "Java.", "This is one of the fundamentals that you need to learn in \n" +
                            "order to proceed!", "Data types are useful in order to know what type of data\n" +
                            "the program needs. But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Type Casting
                            "Hello there Grandson! Welcome to the playroom! This is where \n" +
                            "your skills in programming will be tested.", "For today's topic, \n" +
                            "it will be type casting. Where you will be tested if you really get \n" +
                            "Java.", "This is one of the fundamentals that you need to learn in \n" +
                            "order to proceed!", "Type casting are useful in order to know what type of data\n" +
                            "the is needed. There are some data that are needed to be converted in order to \n" +
                            "solve or edit the data. But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Operators
                            "Hello there Grandson! Welcome to the playroom! This is where \n" +
                            "your skills in programming will be tested.", "For today's topic, \n" +
                            "it will be operators. Where you will be tested if you really get \n" +
                            "Java.", "This is one of the fundamentals that you need to learn in \n" +
                            "order to proceed!", "Operators are useful in order to solve the output \n" +
                            "requirement. But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Conditional Statement
                            "Hello Jedisaur! Welcome to the playroom! This is where your skills \n" +
                            "in programming will be tested.", "For today's topic, it will be \n" +
                            "conditional statement. Where you will be tested if you really get \n" +
                            "Java.", "This is one of the fundamentals that you need to learn in \n" +
                            "order to proceed!", "Conditional Statement are useful in order to get the \n" +
                            "output requirement by placing a condition to satisfy the data. \n" +
                            "But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Loops
                            "Hello Jedisaur! Welcome to the playroom! This is where your \n" +
                            "skills in programming will be tested.", "For today's topic, it \n" +
                            "will be loops. Where you will be tested if you really get Java.", "\n" +
                            "This is one of the fundamentals that you need to learn in \n" +
                            "order to proceed!", "loops are useful in order to get the \n" +
                            "output requirement by not repeating the output one by one. \n" +
                            "But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Arrays
                            "Hello Jedisaur! Welcome to the playroom! This is where your skills \n" +
                            "in programming will be tested.", "For today's topic, it will be arrays. \n" +
                            "Where you will be tested if you really get Java.", "This is one of \n" +
                            "the fundamentals that you need to learn in order to proceed! \n" +
                            "arrays are useful in order to store multiple data instead of \n" +
                            "creating multiple variables that will consume large memory.", "\n" +
                            "But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Methods
                            "Hello Jedisaur! Welcome to the playroom! This is where \n" +
                            "your skills in programming will be tested.", "For today's topic, \n" +
                            "it will be methods. Where you will be tested if you really \n" +
                            "get Java.", "This is one of the fundamentals that you need to learn \n" +
                            "in order to proceed!", "methods are useful in order to solve \n" +
                            "or show output requirements in the program. \n" +
                            "But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Parameters
                            "Hello Jedisaur! Welcome to the playroom! This is where \n" +
                            "your skills in programming will be tested.", "For today's topic, \n" +
                            "it will be parameters. Where you will be tested if you really get \n" +
                            "Java.", "This is one of the fundamentals that you need to learn in \n" +
                            "order to proceed!", "parameters are useful in order to pass \n" +
                            "the data to show output requirements in the program.\n" +
                            "But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Parameters Overloading
                            "Hello Jedisaur! Welcome to the playroom! This is where your \n" +
                            "skills in programming will be tested.", "For today's topic, it \n" +
                            "will be parameter overloading. Where you will be tested if you \n" +
                            "really get Java.", "This is one of the fundamentals that you need to \n" +
                            "learn in order to proceed!", "parameter overloading are useful if there \n" +
                            "are similar method name to prevent confusion and it is necessary \n" +
                            "to name it. data to show output requirements in the program. \n"
                            ,"But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Classes
                            "Hello Mr.Jedisaur! Welcome to the playroom! This is where \n" +
                            "your skills in programming will be tested.", "For today's topic, \n" +
                            "it will be classes. Where you will be tested if you really get \n" +
                            "Java.", "This is one of the fundamentals that you need to learn in \n" +
                            "order to proceed! classes are useful to prevent confusion on what that \n" +
                            "code will do. It can be called in the main function to show its output \n",
                            "But first, let me show you how to play all the minigames."
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Objects
                            "Hello Mr.Jedisaur! Welcome to the playroom! This is where your skills \n" +
                            "in programming will be tested.", " For today's topic, it will be objects. \n" +
                            "Where you will be tested if you really get Java.", "This is one of the \n" +
                            "fundamentals that you need to learn in order to proceed!", "objects are useful \n" +
                            "to create if you want to have your own customized \"or better object \n" +
                            "than default. It can be called in the main function to show its output \n",
                            "But first, let me show you how to play all the minigames."
                    ))
    ));

    private ArrayList<ArrayList<String>> objectiveDialogue = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 1
                            "Topic: Syntax \n" +
                            "Print \"Hello World!\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 2
                            "Topic: Syntax \n" +
                            "Print \"7\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 3
                            "Topic: Syntax \n" +
                            "Print \"Hello World! 123\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 4
                            "Topic: Syntax \n" +
                            "Print \"Hello World 7\" \n" +
                            "Print \"Hello World 9\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 5
                            "Topic: Syntax \n" +
                            "Print \"Hello World 123\" \n" +
                            "Print \"Hello World 321\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 6
                            "Topic: Syntax \n" +
                            "Print \"Hello World 2+5\" \n" +
                            "Print \"Hello World 3+6\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 7
                            "Topic: Syntax \n" +
                            "Print \"Hello World\" \n" +
                            "Print \"JediLand Home\" \n" +
                            "Print \"JediGrandpa\" \n" +
                            "Print \"Jedisaur\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 8
                            "Topic: Syntax \n" +
                            "Print \"Hello World\" \n" +
                            "Print \"JediLand\" \n" +
                            "Print \"JediGrandpa\" \n" +
                            "Print \"Jedisaur123\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 9
                            "Topic: Syntax \n" +
                            "Print \"123Hello World123\" \n" +
                            "Print \"321Hello World321\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 10
                            "Topic: Comments \n" +
                            "Comment \"This is a single-line comment\" \n" +
                            "Print \"Hello World!\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 11
                            "Topic: Comments \n" +
                            "Comment \"This is a multi-line comment\" \n" +
                            "Print \"Hello World!\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 12
                            "Topic: Comments \n" +
                            "Two separate single-line comment \n" +
                            "Comment \"// = single-line comment\" \n" +
                            "Comment \"/* */ = multi-line comment\" \n" +
                            "Print \"Hello World!\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 13
                            "Topic: Comments \n" +
                            "Multi-line Comment \"// = single-line comment\" \n" +
                            "Single-line Comment \"/* */ = multi-line comment\" \n" +
                            "Print \"Hello World!\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 14
                            "Topic: Comments \n" +
                            "Multi-line Comment: \n" +
                            "\"The program outputs Hello World! \n" +
                            "It is a String output \n" +
                            "multi-line comment was used.\" \n" +
                            "Print \"Hello World!\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 15
                            "Topic: Comments \n" +
                            "Single-line Comment: \n" +
                            "\"The program outputs Hello World!\" \n" +
                            "\"It is a String output\" \n" +
                            "\"single-line comment was used.\" \n" +
                            "Print \"Hello World!\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 16
                            "Topic: Variables \n" +
                            "Data Type: String \n" +
                            "Variable name: name \n" +
                            "Initialized value: Jedisaur \n" +
                            "Print \"My name is Jedisaur\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 17
                            "Topic: Variables \n" +
                            "Data Type: String \n" +
                            "Variable name: name \n" +
                            "Initialized value: JediGrandpa \n" +
                            "Print \"My name is JediGrandpa\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 18
                            "Topic: Variables \n" +
                            "Data Type: int \n" +
                            "Variable name: year \n" +
                            "Initialized value: 1 \n" +
                            "Print \"I will now be a 1st year student soon.\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 19
                            "Topic: Variables \n" +
                            "Data Type: String, int \n" +
                            "Variable name: name, age \n" +
                            "Initialized value: Jedisaur, 8 \n" +
                            "Print \"My name is Jedisaur\" \n" +
                            "Print \"I am 8 years old\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 20
                            "Topic: Variables \n" +
                            "Data Type: String\n" +
                            "Variable name: myName, yourName\n" +
                            "Initialized value: Jedisaur, JediGrandpa\n" +
                            "Print \"My name is Jedisaur\" \n" +
                            "Print \"My GrandPa's name is JediGrandpa\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 21
                            "Topic: Variables \n" +
                            "Data Type: int \n" +
                            "Variable name: year, age \n" +
                            "Initialized value: 1, 8 \n" +
                            "Print \"I will now be a 1st year soon.\" \n" +
                            "Print \"I am 8 years old\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 22
                            "Topic: Variables \n" +
                            "Data Type: String, int \n" +
                            "Variable name: name, year, age \n" +
                            "Initialized value: Jedisaur, 1, 8 \n" +
                            "Print \"My name is Jedisaur\" \n" +
                            "Print \"I will now be a 1st year soon.\" \n" +
                            "Print \"I am 8 years old\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 23
                            "Topic: Variables \n" +
                            "Data Type: String \n" +
                            "Variable name: myName, yourName \n" +
                            "Initialized value: Jedisaur, JediGrandpa \n" +
                            "Print \"My name is Jedisaur\" \n" +
                            "Print \"My GrandPa's name is JediGrandpa\" \n" +
                            "Print \"I am the only Grandson\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 24
                            "Topic: Variables \n" +
                            "Data Type: String, int \n" +
                            "Variable name: name, year \n" +
                            "Initialized value: Jedisaur, 1 \n" +
                            "Print \"My name is Jedisaur\" \n" +
                            "Print \"I will now be a 1st year soon.\" \n" +
                            "Print \"I am a dinosaur\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 25
                            "Topic: Data Types \n" +
                            "Data Type: String, int, float \n" +
                            "Variable name: name, age, height \n" +
                            "Initialized value: Jedisaur, 8, 5.9f \n" +
                            "Print \"Jedisaur is 8 years\" \n" +
                            "Print \"A height of 5.9ft\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 26
                            "Topic: Data Types \n" +
                            "Data Type: int, char, String \n" +
                            "Variable name: number, grade, subject \n" +
                            "Initialized value: 3, A, Math \n" +
                            "Print \"I got 3 A\" \n" +
                            "Print \"in Math\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 27
                            "Topic: Data Types \n" +
                            "Data Type: boolean, char, String \n" +
                            "Variable name: bool, grade, subject \n" +
                            "Initialized value: true, A, Math \n" +
                            "Print \"true\" \n" +
                            "Print \"A\" \n" +
                            "Print \"Math\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 28
                            "Topic: Data Types \n" +
                            "Data Type: boolean, int, float \n" +
                            "Variable name: bool, number, height \n" +
                            "Initialized value: false, 3, 5.9f \n" +
                            "Print \"false\" \n" +
                            "Print \"3\" \n" +
                            "Print \"5.9\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 29
                            "Topic: Data Types \n" +
                            "Data Type: boolean, String, int, float \n" +
                            "Variable name: bool, name, age, height \n" +
                            "Initialized value: true, Jedisaur, 8, 5.9f \n" +
                            "Print \"This is true\" \n" +
                            "Print \"Jedisaur is 8 years\" \n" +
                            "Print \"A height of 5.9ft\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 30
                            "Topic: Data Types \n" +
                            "Data Type: boolean, String, int, float \n" +
                            "Variable name: bool, name, age, height \n" +
                            "Initialized value: false, Jedisaur, 8, 5.9f \n" +
                            "Print \"This is false\" \n" +
                            "Print \"Jedisaur is 8 years\" \n" +
                            "Print \"A height of 5.9ft\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 31
                            "Topic: Type Casting \n" +
                            "Data Type: float, int \n" +
                            "Variable name: num, convert \n" +
                            "Initialized value: 5.9f, (int) num \n" +
                            "Print \"5.9\" \n" +
                            "Print \"5\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 32
                            "Topic: Type Casting \n" +
                            "Data Type: int, String \n" +
                            "Variable name: num, convert \n" +
                            "Initialized value: 3, String.valueOf(num) \n" +
                            "Print \"3\" \n" +
                            "Print \"3\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 33
                            "Topic: Type Casting \n" +
                            "Data Type: float, int \n" +
                            "Variable name: num, convert \n" +
                            "Initialized value: 5.9f, (int) num \n" +
                            "Print \"My height is 5.9ft\" \n" +
                            "Print \"I have 5 subjects\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 34
                            "Topic: Type Casting \n" +
                            "Data Type: int, String \n" +
                            "Variable name: num, num2, convert \n" +
                            "Initialized value: 3, 3, String.valueOf(num) \n" +
                            "Print \"3 == 3\" \n" +
                            "Print \"3 != 3\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 35
                            "Topic: Type Casting \n" +
                            "Data Type: float, int \n" +
                            "Variable name: num, num2, convert, num3 \n" +
                            "Initialized value: 5.9f, 6.7f, (int) num, 4 \n" +
                            "Print \"5.9 is similar to 6.7\" \n" +
                            "Print \"5.9 is different to 5\" \n" +
                            "Print \"5 is different to 4\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 36
                            "Topic: Type Casting \n" +
                            "Data Type: boolean, String \n" +
                            "Variable name: bool, bool2, convert \n" +
                            "Initialized value: true, false, String.valueOf(bool) \n" +
                            "Print \"true is similar to false\" \n" +
                            "Print \"true != false\" \n" +
                            "Print \"true is different to true\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 37
                            "Topic: Operators \n" +
                            "Print \"2\" \n" +
                            "Print \"10\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 38
                            "Topic: Operators \n" +
                            "Print \"2\" \n" +
                            "Print \"8\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 39
                            "Topic: Operators \n" +
                            "Print \"1\" \n" +
                            "Print \"2\" \n" +
                            "Print \"3\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 40
                            "Topic: Operators \n" +
                            "Print \"5\" \n" +
                            "Print \"4\" \n" +
                            "Print \"3\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 41
                            "Topic: Operators \n" +
                            "Print \"2\" \n" +
                            "Print \"4\" \n" +
                            "Print \"4\" \n" +
                            "Print \"3\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 42
                            "Topic: Operators \n" +
                            "Print \"7\" \n" +
                            "Print \"4\" \n" +
                            "Print \"5\" \n" +
                            "Print \"4\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 43
                            "Topic: Conditional Statement \n" +
                            "Data Type: boolean \n" +
                            "Variable name: bool \n" +
                            "Initialized value: true \n" +
                            "If bool is true: \n" +
                            "Print \"True\" \n" +
                            "Else: \n" +
                            "Print \"False\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 44
                            "Topic: Conditional Statement \n" +
                            "Data Type: boolean \n" +
                            "Variable name: bool \n" +
                            "Initialized value: false \n" +
                            "If bool is true: \n" +
                            "Print \"True\" \n" +
                            "Else: \n" +
                            "Print \"False\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 45
                            "Topic: Conditional Statement \n" +
                            "Data Type: int \n" +
                            "Variable name: num1, num2 \n" +
                            "Initialized value: 5, 3 \n" +
                            "If num1 is greater than num2: \n" +
                            "Print \"A is greater\" \n" +
                            "Else: \n" +
                            "Print \"B is lower\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 46
                            "Topic: Conditional Statement \n" +
                            "Data Type: int \n" +
                            "Variable name: num1, num2 \n" +
                            "Initialized value: 5, 3 \n" +
                            "If num1 is less than num2: \n" +
                            "Print \"A is greater\" \n" +
                            "Else: \n" +
                            "Print \"B is lower\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 47
                            "Topic: Conditional Statement \n" +
                            "Data Type: int \n" +
                            "Variable name: num1, num2, num3 \n" +
                            "Initialized value: 5, 3, 1 \n" +
                            "If num3 is greater than num1: \n" +
                            "Print \"A is greater\" \n" +
                            "Else if num3 is greater than num2: \n" +
                            "Print \"B is greater\" \n" +
                            "Else: \n" +
                            "Print \"C is lower\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 48
                            "Topic: Conditional Statement \n" +
                            "Data Type: int \n" +
                            "Variable name: num1, num2, num3 \n" +
                            "Initialized value: 5, 3, 1 \n" +
                            "If num3 is greater than num1: \n" +
                            "Print \"A is greater\" \n" +
                            "Else if num3 is less than num2: \n" +
                            "Print \"B is greater\" \n" +
                            "Else: \n" +
                            "Print \"C is lower\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 49
                            "Topic: Conditional Statement \n" +
                            "Data Type: char \n" +
                            "Variable name: letter \n" +
                            "Initialized value: A \n" +
                            "Case A: \n" +
                            "Print \"A Grade\" \n" +
                            "Default: \n" +
                            "Print \"No Grade\" "
                    )),

                     new ArrayList<>(Arrays.asList(
                             // Minigame ID 50
                             "Topic: Conditional Statement \n" +
                             "Data Type: char \n" +
                             "Variable name: letter \n" +
                             "Initialized value: B \n" +
                             "Case A: \n" +
                             "Print \"A Grade\" \n" +
                             "Default: \n" +
                             "Print \"No Grade\" "
                     )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 51
                            "Topic: Conditional Statement \n" +
                            "Data Type: String \n" +
                            "Variable name: direction \n" +
                            "Initialized value: North \n" +
                            "Case North: \n" +
                            "Print \"Up\" \n" +
                            "Case South: \n" +
                            "Print \"Down\" \n" +
                            "Default: \n" +
                            "Print \"No Direction\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 52
                            "Topic: Conditional Statement \n" +
                            "Data Type: String \n" +
                            "Variable name: direction \n" +
                            "Initialized value: South \n" +
                            "Case North: \n" +
                            "Print \"Up\" \n" +
                            "Case South: \n" +
                            "Print \"Down\" \n" +
                            "Default: \n" +
                            "Print \"No Direction\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 53
                            "Topic: Conditional Statement \n" +
                            "Data Type: char \n" +
                            "Variable name: letter \n" +
                            "Initialized value: A \n" +
                            "Case A: \n" +
                            "Print \"A Grade\" \n" +
                            "Case B: \n" +
                            "Print \"B Grade\" \n" +
                            "Default: \n" +
                            "Print \"No Grade\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 54
                            "Topic: Conditional Statement \n" +
                            "Data Type: char \n" +
                            "Variable name: letter \n" +
                            "Initialized value: C \n" +
                            "Case A: \n" +
                            "Print \"A Grade\" \n" +
                            "Case B: \n" +
                            "Print \"B Grade\" \n" +
                            "Default: \n" +
                            "Print \"No Grade\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 55
                            "Topic: Loops \n" +
                            "Data Type: int \n" +
                            "Variable name: i \n" +
                            "Initialized value: 0 \n" +
                            "Final iteration: 5 \n" +
                            "For loop: \n" +
                            "Print \"Hello World\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 56
                            "Topic: Loops \n" +
                            "Data Type: int \n" +
                            "Variable name: i \n" +
                            "Initialized value: 0 \n" +
                            "Final iteration: 5 \n" +
                            "While loop: \n" +
                            "Print \"Hello World\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 57
                            "Topic: Loops \n" +
                            "Data Type: int \n" +
                            "Variable name: i \n" +
                            "Initialized value: 0 \n" +
                            "Final iteration: 5 \n" +
                            "Do-While loop: \n" +
                            "Print \"Hello World\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 58
                            "Topic: Loops \n" +
                            "Data Type: int \n" +
                            "Variable name: i \n" +
                            "Initialized value: 0 \n" +
                            "Final iteration: 5 \n" +
                            "For loop: \n" +
                            "Print the value of \"i\" \n" +
                            "Print \"Hello World\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 59
                            "Topic: Loops \n" +
                            "Data Type: int \n" +
                            "Variable name: i \n" +
                            "Initialized value: 0 \n" +
                            "Final iteration: 5 \n" +
                            "While loop: \n" +
                            "Print the value of \"i\" \n" +
                            "Print \"Hello World\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 60
                            "Topic: Loops \n" +
                            "Data Type: int \n" +
                            "Variable name: i \n" +
                            "Initialized value: 0 \n" +
                            "Final iteration: 5 \n" +
                            "Do-While loop: \n" +
                            "Print the value of \"i\" \n" +
                            "Print \"Hello World\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 61
                            "Topic: Loops \n" +
                            "Data Type: int \n" +
                            "Variable name: i \n" +
                            "Initialized value: 0 \n" +
                            "Final iteration: 5 \n" +
                            "For loop: Break if i is 2\n" +
                            "Print the value of \"i\" \n" +
                            "Print \"Hello World\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 62
                            "Topic: Loops \n" +
                            "Data Type: int \n" +
                            "Variable name: i \n" +
                            "Initialized value: 0 \n" +
                            "Final iteration: 5 \n" +
                            "While loop: Break if i is 2\n" +
                            "Print the value of \"i\" \n" +
                            "Print \"Hello World\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 63
                            "Topic: Loops \n" +
                            "Data Type: int \n" +
                            "Variable name: i \n" +
                            "Initialized value: 0 \n" +
                            "Final iteration: 5 \n" +
                            "Do-While loop: Break if i is 2\n" +
                            "Print the value of \"i\" \n" +
                            "Print \"Hello World\" "
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 64
                            "Topic: Arrays \n" +
                            "Data Type: String[] \n" +
                            "Variable name: array \n" +
                            "Initialized value of array: Jedisaur, is, going, to, Jedischool \n" +
                            "Print the value of \"array\" manually"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 65
                            "Topic: Arrays \n" +
                            "Data Type: String[] \n" +
                            "Variable name: array \n" +
                            "Initialized value of array: Jedisaur, is, in, JediLand \n" +
                            "Print the value of \"array\" manually"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 66
                            "Topic: Arrays \n" +
                            "Data Type: String[] \n" +
                            "Variable name: array \n" +
                            "Initialized value of array: Jedisaur, is, in, JediLand \n" +
                            "For loop: \n" +
                            "Print the value of \"array\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 67
                            "Topic: Arrays \n" +
                            "Data Type: String[] \n" +
                            "Variable name: array \n" +
                            "Initialized value of array: Jedisaur, is, going, to, JediSchool \n" +
                            "For loop: \n" +
                            "Print the value of \"array\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 68
                            "Topic: Arrays \n" +
                            "Data Type: String[], int \n" +
                            "Variable name: array, counter \n" +
                            "Initialized value of array: Jedisaur, is, in, JediLand \n" +
                            "Initialized value of counter: 0 \n" +
                            "While loop: \n" +
                            "Print the value of \"array\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 69
                            "Topic: Arrays \n" +
                            "Data Type: String[], int \n" +
                            "Variable name: array, counter \n" +
                            "Initialized value of array: Jedisaur, is, going, to, JediLand \n" +
                            "Initialized value of counter: 0 \n" +
                            "While loop: \n" +
                            "Print the value of \"array\""
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 70
                            "Topic: Methods \n" +
                            "Method Type: void \n" +
                            "Method name: greet \n" +
                            "Method output: Hello! \n" +
                            "Print \"Hello!\" by using the greet method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 71
                            "Topic: Methods \n" +
                            "Method Type: void \n" +
                            "Method name: greet \n" +
                            "Method output: Hi! \n" +
                            "Print \"Hi!\" by using the greet method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 72
                            "Topic: Methods \n" +
                            "Method Type: String \n" +
                            "Method name: greet \n" +
                            "Method output: Hello \n" +
                            "Print \"Hello Jedisaur\" by using the greet method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 73
                            "Topic: Methods \n" +
                            "Method Type: String \n" +
                            "Method name: greet \n" +
                            "Method output: Hi \n" +
                            "Print \"Hi Jedisaur\" by using the greet method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 74
                            "Topic: Methods \n" +
                            "Method Type: String \n" +
                            "Method name: greet, name \n" +
                            "Method output: Hello, Jedisaur \n" +
                            "Print \"Hello Jedisaur\" by using the greet method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 75
                            "Topic: Methods \n" +
                            "Method Type: String \n" +
                            "Method name: greet, name \n" +
                            "Method output: Hi Jedisaur \n" +
                            "Print \"Hi Jedisaur\" by using the greet method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 76
                            "Topic: Parameters \n" +
                            "Method Type: void \n" +
                            "Method name: greet \n" +
                            "Method parameter Data Type: String \n" +
                            "Method parameter name: name \n" +
                            "Method output: Hi Jedisaur \n" +
                            "Print \"Hi Jedisaur\" by using the greet method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 77
                            "Topic: Parameter Overloading\n" +
                            "Method Type: void \n" +
                            "Method name: greet \n" +
                            "1st Method parameter: String name \n" +
                            "1st Method output: Hello + name \n" +
                            "2nd Method parameter: String greet, String name \n" +
                            "2nd Method output: greet + name \n" +
                            "Print \"Hi Jedisaur\" by using the 2nd greet method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 78
                            "Topic: Parameters \n" +
                            "Method Type: int \n" +
                            "Method name: solve \n" +
                            "Method parameter Data Type: int \n" +
                            "Method parameter name: num \n" +
                            "Method output: num + 5 \n" +
                            "Print \"10\" by using the solve method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 79
                            "Topic: Parameter Overloading\n" +
                            "Method Type: int \n" +
                            "Method name: solve \n" +
                            "1st Method parameter: int num \n" +
                            "1st Method output: num + 10 \n" +
                            "2nd Method parameter: int num, int num2 \n" +
                            "2nd Method output: num + num2 \n" +
                            "Print \"10\" by using the solve method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 80
                            "Topic: Parameters \n" +
                            "Method Type: boolean \n" +
                            "Method name: opposite \n" +
                            "Method parameter Data Type: boolean \n" +
                            "Method parameter name: bool \n" +
                            "Method output: opposite of bool \n" +
                            "Print \"false\" by using the opposite method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 81
                            "Topic: Parameter Overloading\n" +
                            "Method Type: boolean \n" +
                            "Method name: opposite \n" +
                            "1st Method parameter: boolean bool \n" +
                            "1st Method output: opposite of bool \n" +
                            "2nd Method parameter: int num \n" +
                            "2nd Method output: negative number of num \n" +
                            "Print \"-1\" by using the opposite method"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 82
                            "Topic: Classes \n" +
                            "Class name: number \n" +
                            "Class variable's data type: Integer \n" +
                            "Class variable's name: num \n" +
                            "Class variable's value: 8 \n" +
                            "Print \"8\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 83
                            "Topic: Classes \n" +
                            "Class name: name \n" +
                            "Class variable's data type: String \n" +
                            "Class variable's name: name \n" +
                            "Class variable's value: Jedisaur \n" +
                            "Print \"Jedisaur\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 84
                            "Topic: Classes \n" +
                            "Class name: number \n" +
                            "Class variable's data type: Integer \n" +
                            "Class variable's name: num \n" +
                            "Class variable's value: 8 \n" +
                            "Print \"I am now 8 years old\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 85
                            "Topic: Classes \n" +
                            "Class name: name \n" +
                            "Class variable's data type: String \n" +
                            "Class variable's name: name \n" +
                            "Class variable's value: Jedisaur \n" +
                            "Print \"My name is Jedisaur\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 86
                            "Topic: Classes \n" +
                            "Class name: number \n" +
                            "Class method's name: show \n" +
                            "Class method's parameter: int val \n" +
                            "Print \"I am now 8 years old\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 87
                            "Topic: Classes \n" +
                            "Class name: name \n" +
                            "Class method's name: greet \n" +
                            "Class method's parameter: String val \n" +
                            "Print \"My name is Jedisaur\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 88
                            "Topic: Objects \n" +
                            "Class name: number, main \n" +
                            "Class variable's data type: Integer \n" +
                            "Class variable's name: num \n" +
                            "Class variable's value: 8 \n" +
                            "Print \"8\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 89
                            "Topic: Objects \n" +
                            "Class name: name, main \n" +
                            "Class variable's data type: String \n" +
                            "Class variable's name: name \n" +
                            "Class variable's value: Jedisaur \n" +
                            "Print \"Jedisaur\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 90
                            "Topic: Objects \n" +
                            "Class name: number, main \n" +
                            "Class variable's data type: Integer \n" +
                            "Class variable's name: num \n" +
                            "Class variable's value: 8 \n" +
                            "Print \"I am now 8 years old\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 91
                            "Topic: Objects \n" +
                            "Class name: name, main \n" +
                            "Class variable's data type: String \n" +
                            "Class variable's name: name \n" +
                            "Class variable's value: Jedisaur \n" +
                            "Print \"My name is Jedisaur\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 92
                            "Topic: Objects \n" +
                            "Class name: number, main \n" +
                            "Class method's name: show \n" +
                            "Class method's parameter: int val \n" +
                            "Print \"I am now 8 years old\" by using the class"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 93
                            "Topic: Objects \n" +
                            "Class name: name, main \n" +
                            "Class method's name: greet \n" +
                            "Class method's parameter: String val \n" +
                            "Print \"My name is Jedisaur\" by using the class"
                    ))
    ));

    private ArrayList<ArrayList<String>> questionDialogue = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 1
                            "Now, let's proceed... I want you to output a \"Hello World!\" \n " +
                            "in the program by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is syntax! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 2
                            "Now, let's proceed... I want you to output \"7\" \n " +
                            "in the program by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is syntax! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 3
                            "Now, let's proceed... I want you to output \"Hello World! 123\" \n " +
                            "in the program by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is syntax! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 4
                            "Now, let's proceed... I want you to output \"Hello World 7\" and \"Hello World 9\"\n " +
                            "in the program by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is syntax! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 5
                            "Now, let's proceed... I want you to output \"Hello World 123\" and \"Hello World 321\"\n " +
                            "in the program by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is syntax! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 6
                            "Now, let's proceed... I want you to output \"Hello World 2+5\" and \"Hello World 3+6\"\n " +
                            "in the program by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is syntax! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 7
                            "Now, let's proceed... I want you to output \"Hello World\", \n" +
                            "\"JediLand Home\", \"JediGrandpa\", and \"Jedisaur\" \n" +
                            "Take note that the topic that I'm teaching you is syntax! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 8
                            "Now, let's proceed... I want you to output \"Hello World\", \n" +
                            "\"JediLand\", \"JediGrandpa\", and \"Jedisaur123\" \n" +
                            "in the program by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is syntax! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 9
                            "Now, let's proceed... I want you to output \"123Hello World123\" and \"321Hello World321\"\n " +
                            "in the program by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is syntax! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 10
                            "Now, let's proceed... I want you to put a comment \"This is a single-line comment\" \n " +
                            "above that outputs \"Hello World!\" by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about comments! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 11
                            "Now, let's proceed... I want you to put a comment \"This is a multi-line comment\" \n " +
                            "above that outputs \"Hello World!\" by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about comments! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 12
                            "Now, let's proceed... I want you to put a comment \"// = single-line comment\"\n " +
                            "and \"/* */ = multi-line comment\" in a two separate single-line comment \n" +
                            "above that outputs \"Hello World!\" by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about comments! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 13
                            "Now, let's proceed... I want you to put a comment \"// = single-line comment\" \n" +
                            "in a multi-line comment, and \"/* */ = multi-line comment\" in a single-line comment \n" +
                            "above that outputs \"Hello World!\" by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about comments! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 14
                            "Now, let's proceed... I want you to put a multi-line comment saying \n" +
                            "\"The program outputs Hello World! \n" +
                            "It is a String output \n" +
                            "multi-line comment was used.\" \n" +
                            "above that outputs \"Hello World!\" by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about comments! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 15
                            "Now, let's proceed... I want you to put a single-line comment saying \n" +
                            "\"The program outputs Hello World! \n" +
                            "It is a String output \n" +
                            "single-line comment was used.\" \n" +
                            "above that outputs \"Hello World!\" by fixing the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about comments! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 16
                            "Now, let's proceed... I want you to output \"My name is Jedisaur.\" \n" +
                            "and the Jedisaur will be a String variable named name. \n" +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about variables! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 17
                            "Now, let's proceed... I want you to output \"My Grandpa's name is JediGrandpa\" \n" +
                            "and the JediGrandpa will be a String variable named name. \n" +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about variables! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 18
                            "Now, let's proceed... I want you to output \"I will now be a 1st year student soon.\" \n" +
                            "and the 1 will be an Integer variable named year. \n" +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about variables! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 19
                            "Now, let's proceed... I want you to output \"My name is Jedisaur.\" and \"I am 8 years old\" \n" +
                            "The Jedisaur will be a String variable named name, and the 8 will be an \n" +
                            "Integer variable named age. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about variables! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 20
                            "Now, let's proceed... I want you to output \"My name is Jedisaur.\" \n" +
                            "and \"My GrandPa's name is JediGrandpa\" \n" +
                            "The Jedisaur will be a String variable named myName, and the JediGrandpa will be a \n" +
                            "String variable named yourName. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about variables! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 21
                            "Now, let's proceed... I want you to output \"I will now be a 1st year student soon.\" \n" +
                            "and \"I am 8 years old\" The 1 will be an Integer variable named year, \n" +
                            "and the 8 will be an Integer variable named age. \n" +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about variables! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 22
                            "Now, let's proceed... I want you to output \"My name is Jedisaur.\", \n" +
                            "\"I will now be a 1st year student soon.\", and \"I am 8 years old\" \n" +
                            "The Jedisaur will be a String variable named name, 1 will be an Integer variable named year. \n" +
                            "And the 8 will be an Integer variable named age. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about variables! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 23
                            "Now, let's proceed... I want you to output \"My name is Jedisaur.\", \n" +
                            "\"My GrandPa's name is JediGrandpa.\", and \"I am the only Grandson.\". \n" +
                            "The Jedisaur will be a String variable named myName, and the JediGrandpa will be a \n" +
                            "String variable named yourName. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about variables! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 24
                            "Now, let's proceed... I want you to output \"My name is Jedisaur.\" \n" +
                            "\"I will now be a 1st year student soon.\", and \"I am a dinosaur.\". \n" +
                            "The Jedisaur will be a String variable named name, and 1 will be an \n" +
                            "Integer variable named year. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about variables! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 25
                            "Now, let's proceed... I want you to output \"Jedisaur is 8 years old\", and \n" +
                            "\"A height of 5.9ft.\". The Jedisaur will be a String variable named name, \n" +
                            "and 8 will be an Integer variable named age, and 5.9 will be a Float variable \n" +
                            "named height. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Data Types! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 26
                            "Now, let's proceed... I want you to output \"I got 3A\", and \n" +
                            "\"in Math.\". The 3 will be an Integer variable named number, \n" +
                            "A will be a Character variable named grade, and Math will be a String variable \n" +
                            "named subject. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Data Types! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 27
                            "Now, let's proceed... I want you to output \"true\", \n" +
                            "\"A\", and \"Math\". The true will be a boolean variable named bool, \n" +
                            "A will be a Character variable named grade, and Math will be a String variable \n" +
                            "named subject. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Data Types! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 28
                            "Now, let's proceed... I want you to output \"false\", \n" +
                            "\"3\", and \"5.9\". The false will be a boolean variable named bool, \n" +
                            "A will be a Character variable named grade, and 5.9 will be a Float variable \n" +
                            "named height. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Data Types! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 29
                            "Now, let's proceed... I want you to output \"This is true\", \n" +
                            "\"Jedisaur is 8 years old\", and \"A height of 5.9ft.\". \n" +
                            "The Jedisaur will be a String variable named name, \n" +
                            "and 8 will be an Integer variable named age, and 5.9 will be a Float variable \n" +
                            "named height. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Data Types! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 30
                            "Now, let's proceed... I want you to output \"This is false\", \n" +
                            "\"Jedisaur is 8 years old\", and \"A height of 5.9ft.\". \n" +
                            "The Jedisaur will be a String variable named name, \n" +
                            "and 8 will be an Integer variable named age, and 5.9 will be a Float variable \n" +
                            "named height. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Data Types! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 31
                            "Now, let's proceed... I want you to output \"5.9\", \n" +
                            "and \"5.\". The 5.9 will be a float variable named num, \n" +
                            "and 5 will be a converted value from a Float variable, it will be named convert \n" +
                            "as an Integer variable. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Type Casting! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 32
                            "Now, let's proceed... I want you to output \"3\", \n" +
                            "and \"3.\". The 3 will be an Integer variable named num, \n" +
                            "and 3 will be a converted value from an Integer variable, it will be named convert \n" +
                            "as a String variable. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Type Casting! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 33
                            "Now, let's proceed... I want you to output \"My height is 5.9ft\", \n" +
                            "and \"I have 5 subjects\". The 5.9 will be a float variable named num, \n" +
                            "and 5 will be a converted value from a Float variable, it will be named convert \n" +
                            "as an Integer variable. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Type Casting! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 34
                            "Now, let's proceed... I want you to output \"3 = 3\", \n" +
                            "and \"3 != 3\". The 3 will be an Integer variable named num, \n" +
                            "the other 3 will be an Integer variable named num2, \n" +
                            "and 3 will be a converted value from an Integer variable, it will be named convert \n" +
                            "as a String variable. Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Type Casting! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 35
                            "Now, let's proceed... I want you to output \"5.9 is similar to 6.7\", \n" +
                            "\"5.9 is different to 5\", and \"5 is similar to 4\". \n" +
                            "The 5.9 will be a Float variable named num, \n" +
                            "the 6.7 will be a Float variable named num2, \n" +
                            "the 5 will be a converted value from a Float variable, it will be named convert \n" +
                            "as an Integer variable, and 4 will be an Integer variable named num3. \n" +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Type Casting! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 36
                            "Now, let's proceed... I want you to output \"true is similar to false\", \n" +
                            "\"true != false\", and \"true is different to true\". \n" +
                            "The true will be a Boolean variable named bool, \n" +
                            "the false will be a Boolean variable named bool2, \n" +
                            "and the true will be a converted value from a Boolean variable, \n" +
                            "it will be named convert as a String variable. \n" +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Type Casting! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 37
                            "Now, let's proceed... I want you to output \"2\", \n" +
                            "and \"10\". There will be no variable this time and \n" +
                            "think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Operators! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 38
                            "Now, let's proceed... I want you to output \"2\", \n" +
                            "and \"8\". There will be no variable this time and \n" +
                            "think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Operators! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 39
                            "Now, let's proceed... I want you to output \"1\", \"2\", \n" +
                            "and \"3\". There will be a variable called num with a value of 5 \n" +
                            "and think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Operators! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 40
                            "Now, let's proceed... I want you to output \"5\", \"4\", \n" +
                            "and \"3\". There will be a variable called num with a value of 5 \n" +
                            "and think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Operators! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 41
                            "Now, let's proceed... I want you to output \"2\", \"4\", \"4\", \n" +
                            "and \"3\". There will be a variable called num with a value of 3 \n" +
                            "and think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Operators! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 42
                            "Now, let's proceed... I want you to output \"7\", \"4\", \"5\", \n" +
                            "and \"4\". There will be a variable called num with a value of 3 \n" +
                            "and think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Operators! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 43
                            "Now, let's proceed... I want you to output \"True\". \n" +
                            "There will be a variable called bool with a value of true. \n" +
                            "The else condition will be a \"False\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 44
                            "Now, let's proceed... I want you to output \"False\". \n" +
                            "There will be a variable called bool with a value of false. \n" +
                            "The else condition will be a \"False\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 45
                            "Now, let's proceed... I want you to output \"A is greater\". \n" +
                            "There will be a variable called num1 with a value of 5, \n" +
                            "and 3 will be called num2. The else condition will be \"B is lower\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 46
                            "Now, let's proceed... I want you to output \"B is lower\". \n" +
                            "There will be a variable called num1 with a value of 5, \n" +
                            "and 3 will be called num2. The else condition will be \"B is lower\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 47
                            "Now, let's proceed... I want you to output \"C is lower\". \n" +
                            "There will be a variable called num1 with a value of 5, \n" +
                            "num2 with a value of 3, and num3 with a value of 1. \n" +
                            "The if condition will be \"A is greater\", \n" +
                            "and else if condition will be \"B is greater\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 48
                            "Now, let's proceed... I want you to output \"B is greater\". \n" +
                            "There will be a variable called num1 with a value of 5, \n" +
                            "num2 with a value of 3, and num3 with a value of 1. \n" +
                            "The if condition will be \"A is greater\", \n" +
                            "and else condition will be \"C is lower\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 49
                            "Now, let's proceed... I want you to output \"A Grade\". \n" +
                            "There will be a variable called letter with a value of A, \n" +
                            "it will be a switch-case condition. \n" +
                            "The default is \"No Grade\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 50
                            "Now, let's proceed... I want you to output \"No Grade\". \n" +
                            "There will be a variable called letter with a value of B, \n" +
                            "it will be a switch-case condition. \n" +
                            "The only case statement is \"A Grade\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 51
                            "Now, let's proceed... I want you to output \"Up\". \n" +
                            "There will be a variable called direction with a value of North, \n" +
                            "it will be a switch-case condition. \n" +
                            "The case statements are \"Up\", and \"Down\". \n" +
                            "The default statement is \"No Direction\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 52
                            "Now, let's proceed... I want you to output \"South\". \n" +
                            "There will be a variable called direction with a value of A, \n" +
                            "it will be a switch-case condition. \n" +
                            "The case statements are \"Up\", and \"Down\". \n" +
                            "The default statement is \"No Direction\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 53
                            "Now, let's proceed... I want you to output \"A Grade\". \n" +
                            "There will be a variable called letter with a value of A, \n" +
                            "it will be a switch-case condition. \n" +
                            "The case statements are \"A Grade\", and \"B Grade\". \n" +
                            "The default statement is \"No Grade\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 54
                            "Now, let's proceed... I want you to output \"No Grade\". \n" +
                            "There will be a variable called letter with a value of C, \n" +
                            "it will be a switch-case condition. \n" +
                            "The only case statements are \"A Grade\", and \"B Grade\". \n" +
                            "The default statement is \"No Grade\". \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Conditional Statement! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 55
                            "Now, let's proceed... I want you to output \"Hello World\" 5 times. \n" +
                            "There will be no initialized variable since it will be a For Loop. \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 56
                            "Now, let's proceed... I want you to output \"Hello World\" 5 times. \n" +
                            "There will be a variable called i and its value is 0. It will be a While Loop. \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 57
                            "Now, let's proceed... I want you to output \"Hello World\" 5 times. \n" +
                            "There will be a variable called i and its value is 0. It will be a Do-While Loop. \n" +
                            "Think of a way on how to solve this problem. \n " +
                            "Fix the structure of the given code. \n " +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 58
                            "Now, let's proceed... I want you to output the value of \"i\", and \n" +
                            "\" Hello World\" 5 times. There will be no initialized variable since \n" +
                            "it will be a For Loop. Think of a way on how to solve this problem. \n" +
                            "Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 59
                            "Now, let's proceed... I want you to output the value of \"i\" \n"+
                            "\"Hello World\" 5 times. There will be a variable called i and its value is 0.\n" +
                            "It will be a While Loop. Think of a way on how to solve this problem. \n" +
                            "Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 60
                            "Now, let's proceed... I want you to output the value of \"i\" \n" +
                            "\"Hello World\" 5 times. There will be a variable called i and its value is 0. \n" +
                            "It will be a Do-While Loop. Think of a way on how to solve this problem. \n" +
                            "Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 61
                            "Now, let's proceed... I want you to output the value of \"i\", and \n" +
                            "\" Hello World\" 3 times. There will be no initialized variable since \n" +
                            "it will be a For Loop. There will be a conditional statement for the loop to stop. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 62
                            "Now, let's proceed... I want you to output the value of \"i\" \n"+
                            "\"Hello World\" 3 times. There will be a variable called i and its value is 0.\n" +
                            "It will be a While Loop. There will be a conditional statement for the loop to stop. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 63
                            "Now, let's proceed... I want you to output the value of \"i\" \n" +
                            "\"Hello World\" 3 times. There will be a variable called i and its value is 0. \n" +
                            "It will be a Do-While Loop. There will be a conditional statement for the loop to stop. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 64
                            "Now, let's proceed... I want you to output the elements in the array by \n" +
                            "using a for loop. There will be a variable called array and its value is. \n" +
                            "\"Jedisaur\", \"is\", \"in\", and \"Jediland\". \n" +
                            "The only way to output is to print it. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 65
                            "Now, let's proceed... I want you to output the elements in the array by \n" +
                            "using a for loop. There will be a variable called array and its value is. \n" +
                            "\"Jedisaur\", \"is\", \"going\", \"to\", and \"Jediland\". \n" +
                            "The only way to output is to print it. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Loops! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 66
                            "Now, let's proceed... I want you to output the elements in the array by \n" +
                            "using a for loop. There will be a variable called array and its value is. \n" +
                            "\"Jedisaur\", \"is\", \"in\", and \"Jediland\". \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Arrays! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 67
                            "Now, let's proceed... I want you to output the elements in the array by \n" +
                            "using a for loop. There will be a variable called array and its value is. \n" +
                            "\"Jedisaur\", \"is\", \"going\", \"to\", and \"Jediland\". \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Arrays! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 68
                            "Now, let's proceed... I want you to output the elements in the array by \n" +
                            "using a while loop. There will be a variable called array and its value is. \n" +
                            "\"Jedisaur\", \"is\", \"in\", and \"Jediland\". A counter variable will be set to 0. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Arrays! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 69
                            "Now, let's proceed... I want you to output the elements in the array by \n" +
                            "using a for loop. There will be a variable called array and its value is. \n" +
                            "\"Jedisaur\", \"is\", \"going\", \"to\", and \"Jediland\". \n" +
                            "A counter variable will be set to 0. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Arrays! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 70
                            "Now, let's proceed... I want you to output \"Hello!\" by using a method called greet.\n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Methods! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 71
                            "Now, let's proceed... I want you to output \"Hi!\" by using a method called greet.\n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Methods! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 72
                            "Now, let's proceed... I want you to output \"Hello Jedisaur\" \n" +
                            "by using a method called greet.The greet method will only return the word \"Hello\". \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Methods! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 73
                            "Now, let's proceed... I want you to output \"Hi Jedisaur\" \n" +
                            "by using a method called greet.The greet method will only return the word \"Hi\". \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Methods! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 74
                            "Now, let's proceed... I want you to output \"Hello Jedisaur\" \n" +
                            "by using a method called greet, and name. \n" +
                            "The greet method will only return the word \"Hello\". \n" +
                            "While the name method will only return the word \"Jedisaur\". \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Methods! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 75
                            "Now, let's proceed... I want you to output \"Hi Jedisaur\" \n" +
                            "by using a method called greet, and name. \n" +
                            "The greet method will only return the word \"Hi\".\n" +
                            "While the name method will only return the word \"Jedisaur\". \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Methods! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 76
                            "Now, let's proceed... I want you to output \"Hi Jedisaur\" \n" +
                            "by using a method called greet with a parameter of name. \n" +
                            "The greet method will show the word \"Hi\" and the name Jedisaur.\n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Parameters! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 77
                            "Now, let's proceed... I want you to output \"Hi Jedisaur\" \n" +
                            "by using a method called greet with a parameter of greet, and name. \n" +
                            "The greet method will show the greet \"Hi\" and the name \"Jedisaur\".\n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Parameter Overloading! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 78
                            "Now, let's proceed... I want you to output \"10\" \n" +
                            "by using a method called solve with a parameter of num. \n" +
                            "The solve method will return the value of num plus 5. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Parameters! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 79
                            "Now, let's proceed... I want you to output \"10\" \n" +
                            "by using a method called solve with a parameter of num and num2. \n" +
                            "The solve method will return the value of num plus num2. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Parameter Overloading! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 80
                            "Now, let's proceed... I want you to output \"false\" \n" +
                            "by using a method called opposite with a parameter of bool. \n" +
                            "The opposite method will return the opposite value of bool. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Parameters! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 81
                            "Now, let's proceed... I want you to output \"-1\" \n" +
                            "by using a method called opposite with a parameter of num. \n" +
                            "The opposite method will return the opposite value of num. \n" +
                            "Think of a way on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Parameters! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 82
                            "Now, let's proceed... I want you to output \"8\" by using the \n" +
                            "variable in a class called num with a value of 8. Call the \n" +
                            "class called number inside the main method. Think of a way on how \n" +
                            "to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Classes! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 83
                            "Now, let's proceed... I want you to output \"Jedisaur\" \n" +
                            "by using the variable in a class called name1 with a value of Jedisaur. \n" +
                            "Call the class called name inside the main method. Think of a way \n" +
                            "on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Classes! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 84
                            "Now, let's proceed... I want you to output \"I am now 8 years old\" \n" +
                            "by using the variable in a class called num1 with a value of \n" +
                            "8. Call the class called number inside the main method. Think of a way \n" +
                            "on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Classes! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 85
                            "Now, let's proceed... I want you to output \"My name is Jedisaur\" \n" +
                            "by using the variable in a class called name1 with a value of Jedisaur. \n" +
                            "Call the class called name inside the main method. Think of a way \n" +
                            "on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Classes! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 86
                            "Now, let's proceed... I want you to output \"I am now 8 years old\" \n" +
                            "by using the method in a class called show with a parameter of val.\n" +
                            "Call the class called number inside the main method. Think of a way \n" +
                            "on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Classes! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 87
                            "Now, let's proceed... I want you to output \"My name is Jedisaur\" \n" +
                            "by using the method in a class called greet with a parameter of val. \n" +
                            "Call the class called name inside the main method. Think of a way \n" +
                            "on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Classes! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 88
                            "Now, let's proceed... I want you to output \"8\" \n" +
                            "by using the object in a class called number with a value of num.\n" +
                            "Call the class called number inside the main method. Think of a way \n" +
                            "on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Objects! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 89
                            "Now, let's proceed... I want you to output \"Jedisaur\" \n" +
                            "by using the object in a class called name with a value of name. \n" +
                            "Call the class called name inside the main method. Think of a way \n" +
                            "on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Objects! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 90
                            "Now, let's proceed... I want you to output \"I am now 8 years old\" \n" +
                            "by using the object in a class called number with a value of num.\n" +
                            "Call the class called number inside the main method. Think of a way \n" +
                            "on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Objects! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 91
                            "Now, let's proceed... I want you to output \"My name is Jedisaur\" \n" +
                            "by using the object in a class called name with a value of name. \n" +
                            "Call the class called name inside the main method. Think of a way \n" +
                            "on how to solve this problem. Fix the structure of the given code. \n" +
                            "Take note that the topic that I'm teaching you is about Objects! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 92
                            "Now, let's proceed... I want you to output \"I am now 8 years old\" \n" +
                            "by using the object in a class called number with a method named show \n" +
                            "with a parameter of int val. Call the class called number inside the main \n" +
                            "method. Think of a way on how to solve this problem. Fix the structure of \n" +
                            "the given code. Take note that the topic that I'm teaching you is about Objects! \n" +
                            "Make sure to not mess this up. Good luck!"
                    )),

                    new ArrayList<>(Arrays.asList(
                            // Minigame ID 93
                            "Now, let's proceed... I want you to output \"My name is Jedisaur\" \n" +
                            "by using the object in a class called name with a method named greet \n" +
                            "with a parameter of String name. Call the class called name inside the main \n" +
                            "method. Think of a way on how to solve this problem. Fix the structure of \n" +
                            "the given code. Take note that the topic that I'm teaching you is about Objects! \n" +
                            "Make sure to not mess this up. Good luck!"
                    ))
            )
    );

    private ArrayList<ArrayList<String>> newPlayerIntro = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                       "Hello Jedisaur! Welcome on your first day of coding!",
                       "Before getting started, I will first introduce myself",
                       "I am JediGrandpa, obviously your grandfather",
                            "I am here to guide you all the way to success and learn coding in a more efficient and fun way!",
                            "First, I will show you how to play this game", "For the first step, you will need to talk to me again after this tutorial.",
                            "To do that, PRESS E when you are in contact with me.\n Then I will teach you the topic of the day!",
                            "After I discuss the topic for today, You are required to go to the computer.\nThat computer will serve as the ticket to the next area!",
                            "The computer is a Question and Answer portion, You must choose the correct answers from the bottom table in order to proceed!",
                            "To access the computer you must PRESS E and after answering all the questions, You may PRESS F to close the window",
                            "Rest assured after answering all the questions you will be able to proceed to the next area.",
                            "The next area called PLAYROOM is on the right side of this house, Inside the playroom there will be a RANDOM MINIGAME that will solely focus on your practical skills.",
                            "I will teach you how to play inside the PLAYROOM when you get there, don't worry.", "Now, its about time and I suppose you should know how to play now.",
                            "I will let you be and goodluck on your journey Jedisaur!"
                    ))
            )
    );

    private ArrayList<ArrayList<String>> askIfFinished = new ArrayList<>(
            Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            "Are you finished son?"
                    )),

                    new ArrayList<>(Arrays.asList(
                            "Uy tama!!!"
                    )),
                    new ArrayList<>(Arrays.asList(
                            "BOBO KA MALE!!!"
                    ))
            )
    );

    private boolean statementEnd;
    float myFloatNum = 5.99f;
    private String npcName;
    private int stage;
    private String topic;
    private ArrayList<ArrayList<String>> arrayLists;

    public Dialogue(){
        statementEnd = false;
    }

    public String reader(int nextStatement, String dialogueSet, int index){

        switch (dialogueSet){
            case "introduction": return statementMover(nextStatement, introductionDialogue, getStage() - 1);
            case "question": return statementMover(nextStatement, questionDialogue, 0);
            case "codeorderhints": return statementMover(nextStatement, codeOrderHints, getStage() - 1);
            case "hints": return statementMover(nextStatement, twoHintsContainer, getStage() - 1);
            case "finishCheck": return statementMover(nextStatement, askIfFinished, index);
            case "minigameintrodialogue": return statementMover(nextStatement, minigameIntroDiaulogue, index - 1);
            case "done" : return statementMover(nextStatement, askIfFinished, 0);
            case "noplayroom": return statementMover(nextStatement, noToPlayRoomDialogue, index - 1);
            case "newPlayer": return statementMover(nextStatement, newPlayerIntro, 0);


        }
        return "";
    }

    public String statementMover(int nextStatement, ArrayList<ArrayList<String>> arrayLists, int index){
        setArrayLists(arrayLists);
        if(nextStatement == arrayLists.get(index).size()){
            System.out.println(nextStatement + " + " + arrayLists.get(index).size());
            setStatementEnd(true);
        }else{
            setStatementEnd(false);
            return arrayLists.get(index).get(nextStatement);
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

    public int getTopic(String topic){
        switch (topic){
            case "Syntax": return 1;
            case "Comments": return 2;
            case "Variables": return 3;
            case "Data Types": return 4;
            case "Type Casting": return 5;
            case "Operators": return 6;
            case "Conditional": return 7;
            case "Loops": return 8;
            case "Arrays": return 9;
            case "Methods": return 10;
            case "Parameters": return 11;
            case "Parameter Overloading": return 12;
            case "Classes": return 13;
            case "Objects": return 14;
        }
        return 0;
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

    public String getObjectiveDialogue(int QID) {
        return objectiveDialogue.get(QID).get(0);
    }

    public ArrayList<ArrayList<String>> getArrayLists() {
        return arrayLists;
    }

    public void setArrayLists(ArrayList<ArrayList<String>> arrayLists) {
        this.arrayLists = arrayLists;
    }
}
