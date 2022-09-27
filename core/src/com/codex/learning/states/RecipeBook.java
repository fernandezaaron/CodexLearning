package com.codex.learning.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.codex.learning.utility.Constants;
import com.codex.learning.utility.Manager;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipeBook extends State{

    private Table backgroundTable, bookTable, leftPage, rightPage, textTableLeft;
    private ImageButton leftButton, rightButton, pauseButton;
    private Label label;
    private int index, j;

    private ArrayList<ArrayList<String>> content = new ArrayList<>(Arrays.asList(
            new ArrayList<String>(Arrays.asList(
                    //syntax
                    "Syntax",
                    "The syntax of Java refers to the set of rules defining how a Java program is written and interpreted.",
                    "The syntax is mostly derived from C and C++. Unlike in C++, in Java there are no global functions or variables" ,
                    "But there are data members which are also regarded as global variables.",
                    "\npublic class Main {\n" + "  public static void main(String[] args) {\n" + "    System.out.println(\"Hello World\");\n" + "  }\n" + "}"

                    )),
            new ArrayList<>(Arrays.asList(
                    //comments
                    "Comments",
                    "Comments can be used to explain Java code, and to make it more readable.", "It can also be used to prevent execution when testing alternative code.",
                    "Single-line comments start with two forward slashes (//).", "Any text between // and the end of the line is ignored by Java (will not be executed).",
                    "// This is a comment \n /* This is a Multi-Line \n Comment */ \n // /*This is a single line comment*/ \n /* //this is a \n multi-line comment */"

                    )),
            new ArrayList<String>(Arrays.asList(
                    //variables
                    "Variables",
                    "Variables are containers for storing data values.", "In Java, there are different types of variables",
                    "String - stores text, such as \"Hello\".\nString values are surrounded by double quotes", "An example of declaring a string value: " + "\nString name =\"Hello\";",
                    "int - stores integers (whole numbers), without decimals, such as 123 or -123", "An example of declaring an integer value:" + "\nint num = 123;",
                    "double - stores point numbers, with decimals, such as 19.99 or -19.99", "An example of declaring a double value:" + "\ndouble num = 19.99;",
                    "char - stores single characters, such as 'a' or 'B'. Char values are surrounded by single quotes", "An example of declaring a char value:" + "\nchar letter = 'a';",
                    "boolean - stores values with two states: true or false", "An example of declaring a boolean value:" + "\nboolean bool = true;"
            )),
            new ArrayList<>(Arrays.asList(
                    //data types
                    "Data Types",
                    "Data types are divided into two groups: Primitive and Non-Primitve Data Types",
                    "Primitive data types - includes byte, short, int, long, float, double, boolean and char" + "\n" +
                    "A primitive data type specifies the size and type of variable values, and it has no additional methods.",
                    "There are eight primitive data types in Java which are: byte, float, short, double, int, boolean, long, and char",
                    "byte - 1 byte - min/max value: -128 to 127 \nfloat - 4 bytes \nshort - 2 bytes \ndouble - 8 bytes", "int - 4 bytes\nboolean - 1 bit\nlong - 8 bytes\nchar - 2 bytes",
                    "Non-primitive data types - such as String, Arrays and Classes", "Non-primitive data types are called reference types because they refer to objects." + "\n" +
                    "They are created by the programmer and is not defined by Java (except for String). \n It can be used to call methods to perform certain operations, while primitive types cannot. \n Lastly non-primitive types have all the same size."

            )), new ArrayList<String>(Arrays.asList(
                    //type casting
                    "Type Casting",
                    "Type casting is when you assign a value of one primitive data type to another type. \n In Java, there are two types of casting:",
                    "Widening Casting (automatically) - converting a smaller type to a larger type size\n" +
                            "byte -> short -> char -> int -> long -> float -> double", "For example:" + "\n int myInt = 9;\n" + "double myDouble = myInt;",
                    "Narrowing Casting (manually) - converting a larger type to a smaller size type\n" +
                            "double -> float -> long -> int -> char -> short -> byte", "For example:" + "double myDouble = 9.78d;\n" + "int myInt = (int) myDouble"

            )),
            new ArrayList<>(Arrays.asList(
                    //operators
                    "Operators",
                    "Operators are used to perform operations on variables and values.",
                    "Java divides the operators into the following groups: \nArithmetic operators \nAssignment operators \nComparison operators \nLogical operators \nBitwise operators",
                    "Arithmetic operators are used to perform common mathematical operations like: \n+ Addition - Adds together two values " + "\n- Subtraction - Subtracts one value from another"
                    + "\n* Multiplication - Multiplies two values" +
                            "\n/ Division - Divides one value by another" +
                    "% Modulus - Returns the division remainder" + "\n++ Increment - Increases the value of a variable by 1" +
                            "\n-- Decrement - Decreases the value of a variable by 1",
                    "Assignment operators are used to assign values to variables. Your usual operator is literally '=' but it may also vary from: '+=', '-=', '*=', '/=', and many more",
                    "The following are examples of assignment operators: \n" +
                            "x = 5\n" +
                            "x += 3\n" +
                            "x -= 7\n" +
                            "x *= 5\n" +
                            "x /= 3\n"
            )), new ArrayList<String>(Arrays.asList(
                    //conditionals
                    "Conditionals",
                    "Java has the following conditional statements:" + "\n\nif\nelse\nelse if\nswitch",
                    "Conditional if to specify a block of code to be executed, if a specified condition is true\n" +
                            "int x = 20;\nint y = 18;\nif (x > y) {\nSystem.out.println(\"x is greater than y\");\n}",
                    "Conditional else to specify a block of code to be executed, if the same condition is false\n " +
                            "int time = 20;\nif (time < 18) {\nSystem.out.println(\"Good day.\");\n} else {\nSystem.out.println(\"Good evening.\");\n}",
                    "Conditional else if to specify a new condition to test, if the first condition is false" +
                    "\nint time = 22;\n" + "\nif (time < 10) {\n" + "  System.out.println(\"Good morning.\");\n" + "} else if (time < 20) {\n" + "  System.out.println(\"Good day.\");\n" + "} else {\n" + "  System.out.println(\"Good evening.\");\n" + "}",
                    "Use switch to specify many alternative blocks of code to be executed" +
                            "\nThe switch expression is evaluated once." +
                            "\nThe value of the expression is compared with the values of each case."+
                    "\nIf there is a match, the associated block of code is executed." + "\nThe break and default keywords are optional"

                    )),
            new ArrayList<>(Arrays.asList(
                    //loops
                    "Loops",
                    "Loops can execute a block of code as long as a specified condition is reached."
                    +"\nLoops are handy because they save time, reduce errors, and they make code more readable." +
                    "\nThe while loop loops through a block of code as long as a specified condition is true" +
                            "\nint i = 0;\n" + "while (i < 5) {\n" + "  System.out.println(i);\n" + "  i++;\n" + "}",
                    "The do/while loop is a variant of the while loop. This loop will execute the code block once, before checking if the condition is true, then it will repeat the loop as long as the condition is true." +
                            "\nint i = 0;\n" + "do {\n" + "  System.out.println(i);\n" + "  i++;\n" + "}\n" + "while (i < 5);",
                    "for (statement 1; statement 2; statement 3) {" + "}" + "\nStatement 1 is executed (one time) before the execution of the code block.\n" + "Statement 2 defines the condition for executing the code block.\n" + "Statement 3 is executed (every time) after the code block has been executed.",
                    "\nfor (int i = 0; i < 5; i++) {\n" + "  System.out.println(i);\n" + "}",
                    "for-each\" loop, which is used exclusively to loop through elements in an array:" +
                            "\nString[] cars = {\"Volvo\", \"BMW\", \"Ford\", \"Mazda\"};\n" + "for (String i : cars) {\n" + "  System.out.println(i);\n" + "}"

            )),
            new ArrayList<String>(Arrays.asList(
                    //arrays
                    "Arrays",
                    "Arrays are used to store multiple values in a single variable, instead of declaring separate variables for each value."+
                            "\nTo declare an array, define the variable type with square brackets:" + "\n\nFor example:" + "\nString[] elements;"+
                    "\nTo insert values to it, we can use an array literal - place the values in a comma-separated list, inside curly braces:" +
                            "\nString[] elements = {\"Air\", \"Water\", \"Earth\", \"Fire\"};"
            )),
            new ArrayList<>(Arrays.asList(
                    //methods
                    "Methods",
                    "A method is a block of code which only runs when it is called." +
                    "\nYou can pass data, known as parameters, into a method." +
                    "\nMethods are used to perform certain actions, and they are also known as functions." +
                    "\npublic class Main {\n" + "  static void myMethod() {\n" + "    // code to be executed\n" + "  }\n" + "}"
            )), new ArrayList<String>(Arrays.asList(
                    //parameters
                    "Parameters",
                    "Parameters act as variables inside the method."+
                            "\nParameters are specified after the method name, inside the parentheses. You can add as many parameters as you want, just separate them with a comma.",
                    "public class Main {\n" + "  static void myMethod(String fname) {\n" + "    System.out.println(fname + \" Refsnes\");\n" + "  }\n" + "\n" + "  public static void main(String[] args) {\n" + "    myMethod(\"Liam\");\n" + "  }\n" + "}"

            )),
            new ArrayList<>(Arrays.asList(
                    //parameter overloading
                    "Parameter Overloading",
                    "multiple methods can have the same name with different parameters\n"+
                            "int myMethod(int x)\n" + "float myMethod(float x)\n" + "double myMethod(double x, double y)"
            )),
            new ArrayList<String>(Arrays.asList(
                    //classes
                    "Classes",
                    "Everything in Java is associated with classes and objects, along with its attributes and methods.\n" +
                            "For example: in real life, a car is an object. The car has attributes, such as weight and color, and methods, such as drive and brake.",
                    "A Class is like an object constructor, or a \"blueprint\" for creating objects.", "To create a class, use the keyword class:\n" +
                            "\npublic class Main {\n" + "  int x = 5;\n" + "}",
                    "Remember that a class should always start with an uppercase first letter, and that the name of the java file should match the class name."
            )),
            new ArrayList<String>(Arrays.asList(
                    //objects
                    "Objects",
                    "In Java, an object is created from a class. We have already created the class named Main, so now we can use this to create objects"
                    +"\nTo create an object of Main, specify the class name, followed by the object name, and use the keyword new",
                    "\npublic class Main {\n" + "  int x = 5;\n" + "\n" + "  public static void main(String[] args) {\n" + "    Main myObj = new Main();\n" + "    System.out.println(myObj.x);\n" + "  }\n" + "}",
                    "You can create multiple objects of one class:" + "\nExample" + "\n\npublic static void main(String[] args) {\n" + "    Main myObj1 = new Main();  // Object 1\n" + "    Main myObj2 = new Main();  // Object 2\n" + "    System.out.println(myObj1.x);\n" + "    System.out.println(myObj2.x);\n" + "  }"
            ))
    ));

    public RecipeBook(Manager manager){
        super(manager);
        backgroundTable = new Table(manager.getSkin());

        bookTable = new Table(manager.getSkin());
        bookTable.setBackground("jedigrandparecipe");

        leftPage = new Table(manager.getSkin());
        leftPage.setBackground("leftPage");

        rightPage = new Table(manager.getSkin());
        rightPage.setBackground("rightPage");

        leftButton = new ImageButton(manager.getSkin(), "LeftArrow");
        rightButton = new ImageButton(manager.getSkin(), "RightArrow");
        pauseButton = new ImageButton(manager.getSkin(), "PauseButton");

        textTableLeft = new Table(manager.getSkin());

        label = new Label("", manager.getSkin());
        label.setFontScale((manager.getStage().getWidth()/Constants.PPM)*0.024f);
        label.getStyle().font.getData().setLineHeight(35);
        label.setAlignment(Align.left);
        label.setWrap(true);

        index = 0;
        j=0;
    }

    @Override
    public void update(float delta) {
        manager.getStage().act();
    }

    @Override
    public void render(SpriteBatch sprite) {
        System.out.println(index + " " + j);

        if(!backgroundTable.hasChildren()){
            backgroundTable.defaults().size(manager.getStage().getWidth(),manager.getStage().getHeight());
            backgroundTable.setPosition(0, 0);

            bookTable.add(pauseButton).top().left().row();
            bookTable.add(leftButton).padRight(15f);

            textTableLeft.add(label).align(Align.left).growX().padLeft(5f);
            leftPage.add(textTableLeft).width(600);

            bookTable.add(leftPage).size(650, 700).padTop(5);
            bookTable.add(rightPage).size(650,700).padTop(5);
            bookTable.add(rightButton).padLeft(15f);

            leftButton.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    if(j <= 0){
                        j = 0;
                        if(index <= 0){
                            index = 0;
                        }
                    }else{
                        j--;
                    }
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button){

                }
            });

            rightButton.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    if(j >= content.get(index).size()-1){
                        index++;
                        j = 0;
                        if(index >= content.size()){
                            index = 0;
                        }
                    }else{
                        j++;
                    }
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button){

                }
            });

            pauseButton.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                    if(j >= content.get(index).size()-1){
                        index++;
                        j = 0;
                        if(index >= content.size()){
                            index = 0;
                        }
                    }else{
                        j++;
                    }
                    return true;
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                    manager.set(new MenuState(manager));
                    manager.getStage().clear();
                }
            });


            backgroundTable.add(bookTable);
            backgroundTable.pack();
        }
        label.setText(content.get(index).get(j));

        manager.getStage().addActor(backgroundTable);
        manager.getStage().draw();
    }

    @Override
    public void dispose() {

    }
}
