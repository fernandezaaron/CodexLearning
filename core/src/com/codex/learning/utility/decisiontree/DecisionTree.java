package com.codex.learning.utility.decisiontree;

import java.util.ArrayList;


public class DecisionTree {

    private ArrayList<String> headers;
    private Double impurity;
    private Double infoGain;
    private Node tree;

    public DecisionTree(){
        this.headers = Behavior.headers;
    }

    public void createTree(){
        Question question = new Question(Behavior.headers, 1, "HIGH");
        ArrayList<ArrayList<ArrayList<String>>> trueFalseList = partition(Behavior.trainingData, question);
        ArrayList<ArrayList<String>> trueList = trueFalseList.get(0);
        ArrayList<ArrayList<String>> falseList = trueFalseList.get(1);

        impurity = gini(Behavior.trainingData);
        infoGain = infoGain(trueList, falseList, impurity);
        tree = buildTree(Behavior.trainingData);
    }

    /**
     * Finds the unique values for a column in a dataset.
     *
     * @param rows The dataset
     * @param col column of the dataset
     * @return ArrayList of unique values
     */
    public ArrayList<String> uniqueValues(ArrayList<ArrayList<String>> rows, int col){
        ArrayList<String> unique = new ArrayList<String>();
        for (ArrayList<String> row: rows){
            String value = row.get(col-1);
            if (!unique.contains(value)){
                unique.add(value);
            }
        }
        return unique;
    }

    /**
     * Counts the number of each label in a dataset.
     *
     * @param rows The dataset
     * @return ArrayList of label and label count
     */
    public ArrayList<String> countClass(ArrayList<ArrayList<String>> rows){
        ArrayList<String> uniqueLabels = new ArrayList<String>();
        ArrayList<Integer> amount = new ArrayList<Integer>();
        for (ArrayList<String> row: rows){
            String value = row.get(row.size()-1);
            if (!uniqueLabels.contains(value)){
                uniqueLabels.add(value);
                amount.add(1);
            }
            else{
                int place = uniqueLabels.indexOf(value);
                amount.set(place, amount.get(place) + 1);
            }
        }
        ArrayList<String> objects = new ArrayList<String>();
        int i = 0;
        for (String label: uniqueLabels){
            String labelNum = label + ": " + amount.get(i);
            objects.add(labelNum);
            i++;
        }
        return objects;
    }

    /**
     * This method partitions a dataset.
     * For each row in the dataset check it against the question and add true rows into the trueList and
     * false rows into the falseList.
     *
     * @param rows dataset being partitioned
     * @param q question being asked
     * @return trueFalseList This list contains a true list at index 0 and false list at index 1.
     */
    public ArrayList<ArrayList<ArrayList<String>>> partition(ArrayList<ArrayList<String>> rows, Question q){
        ArrayList<ArrayList<ArrayList<String>>> trueFalseList = new ArrayList<>();
        ArrayList<ArrayList<String>> trueList = new ArrayList<>();
        ArrayList<ArrayList<String>> falseList = new ArrayList<>();
        for (ArrayList<String> row: rows){
            if (q.match(row)){
                trueList.add(row);
            }
            else{
                falseList.add(row);
            }

        }

        trueFalseList.add(trueList);
        trueFalseList.add(falseList);
        return trueFalseList;
    }

    /**
     * This method calculates the gini impurity of a given dataset.
     * There are multiple ways to calculate gini impurity but this is the way that I chose.
     *
     * @param rows The dataset
     * @return gini impurity of the dataset
     */
    public Double gini(ArrayList<ArrayList<String>> rows){
        ArrayList<String> labels = this.countClass(rows);
        Double impurity = 1.0;
        for(String label: labels){
            Double labelProbability = Double.parseDouble(label.substring(label.length()-1, label.length()))/rows.size();
            impurity -= Math.pow(labelProbability, 2);
        }
        return impurity;
    }

    /**
     * This method calculates the information gain.
     * Information gain is the uncertainty of a certain node minus the weighted impurity of the child nodes.
     *
     * @param left Left dataset
     * @param right Right dataset
     * @param currentUncertainty uncertainty of current node
     * @return information gain
     */
    public Double infoGain(ArrayList<ArrayList<String>> left, ArrayList<ArrayList<String>> right, Double currentUncertainty){
        Double p = (double) (left.size() / (left.size() + right.size()));
        return currentUncertainty - p * gini(left) - (1 - p) * gini(right);
    }

    /**
     * This method finds the best question to split the data using gini impurity and information gain.
     *
     * @param rows The dataset.
     * @return QuestionGainPair This is the question and info gain for that question.
     */
    public QuestionGainPair findBestSplit(ArrayList<ArrayList<String>> rows){
        Double bestGain = 0.0;
        Question bestQuestion = null;
        QuestionGainPair qgp = new QuestionGainPair();
        Double currentUncertainty = gini(rows);
        int numOfFeatures = rows.get(0).size() - 1;

        for (int i = 1; i <= numOfFeatures; i++){
            ArrayList<String> values = uniqueValues(rows, i);

            for (String val: values){
                Question q = new Question(headers, i-1, val);

                ArrayList<ArrayList<ArrayList<String>>> trueFalseList = partition(rows, q);
                ArrayList<ArrayList<String>> trueList = trueFalseList.get(0);
                ArrayList<ArrayList<String>> falseList = trueFalseList.get(1);
                if (trueList.size() != 0 && falseList.size() != 0){
                    Double gain = infoGain(trueList, falseList, currentUncertainty);

                    if (gain > bestGain){
                        bestGain = gain;
                        bestQuestion = q;
                        qgp = new QuestionGainPair(bestQuestion, bestGain);
                    }
                }
            }
        }

        return qgp;
    }

    public class Node{
        public Node() {}
    }

    /**
     * This class represents a leaf node in the decision tree
     * @author 756887
     *
     */
    public class Leaf extends DecisionNode{

        private ArrayList<String> predictions;

        public Leaf(ArrayList<ArrayList<String>> rows){
            super();
            predictions = countClass(rows);
        }

        public ArrayList<String> getPredictions() {
            return predictions;
        }

        public void setPredictions(ArrayList<String> predictions) {
            this.predictions = predictions;
        }
    }

    /**
     * This class represents a decision node in the decision tree
     * @author 756887
     *
     */
    public class DecisionNode extends Node{

        private DecisionNode trueBranch;
        private DecisionNode falseBranch;
        private Question question;

        public DecisionNode(DecisionNode trueBranch, DecisionNode falseBranch, Question question){
            super();
            this.trueBranch = trueBranch;
            this.falseBranch = falseBranch;
            this.question = question;
        }

        public DecisionNode() {}

        public DecisionNode getTrueBranch() {
            return trueBranch;
        }

        public DecisionNode getFalseBranch() {
            return falseBranch;
        }

        public Question getQuestion() {
            return question;
        }

    }

    /**
     * This method recursively builds the decision tree and trains the classifier.
     *
     * @param rows The training dataset
     * @return The tree created by this method
     */
    public Node buildTree(ArrayList<ArrayList<String>> rows){
        QuestionGainPair qgp = findBestSplit(rows);

        if (qgp.getGain() == 0){
            return new Leaf(rows);
        }

        ArrayList<ArrayList<ArrayList<String>>> trueFalseList = partition(rows, qgp.getQuestion());
        ArrayList<ArrayList<String>> trueList = trueFalseList.get(0);
        ArrayList<ArrayList<String>> falseList = trueFalseList.get(1);

        DecisionNode trueBranch = (DecisionNode) buildTree(trueList);
        DecisionNode falseBranch = (DecisionNode) buildTree(falseList);

        return new DecisionNode(trueBranch, falseBranch, qgp.getQuestion());
    }

    /**
     * This method prints out the decision tree.
     *
     * @param node The decision tree to print
     * @param spacing The spacing placed in front of each branch
     */
    public void printTree(Node node, String spacing){
        if (spacing == null){
            spacing = "";
        }
        if (node instanceof Leaf){
            System.out.println(spacing + "Predict: " + percentLeaf((Leaf) node));
            return;
        }

        System.out.println(spacing + ((DecisionNode) node).getQuestion());

        System.out.println(spacing + "--> True:");
        printTree(((DecisionNode) node).getTrueBranch(), spacing + " ");

        System.out.println(spacing + "--> False:");
        printTree(((DecisionNode) node).getFalseBranch(), spacing + " ");

    }

    /**
     * This method takes in an unknown row of data and tries to classify it.
     *
     * @param row The unclassified row of data
     * @param node The decision tree
     * @return This is the prediction
     */
    public ArrayList<String> classify(ArrayList<String> row, Node node){
        if (node instanceof Leaf){
            return percentLeaf((Leaf) node);
        }

        if (((DecisionNode) node).getQuestion().match(row)){
            return classify(row, ((DecisionNode) node).getTrueBranch());
        }
        else{
            return classify(row, ((DecisionNode) node).getFalseBranch());
        }
    }

    /**
     * This is a nicer way of printing out the leaf nodes.
     *
     * @param node Leaf node to be printed
     * @return The predictions with percent
     */
    public ArrayList<String> percentLeaf(Leaf node){
        ArrayList<String> predictions = node.getPredictions();
        Double total = 0.0;
        ArrayList<String> printPredictions = new ArrayList<String>();
        for (String prediction: predictions){
            int pos = prediction.indexOf(":");
            Double count = Double.parseDouble(prediction.substring(pos+1));
            total = total + count;
        }

        for (String prediction: predictions){
            int pos = prediction.indexOf(":");
            Double count = Double.parseDouble(prediction.substring(pos+1));
            String label = prediction.substring(0, pos);
            long percent = Math.round((count / total) * 100);
//            printPredictions.add(label + percent + "%");
            printPredictions.add(label);
        }
        return printPredictions;
    }

    public Double getImpurity() {
        return impurity;
    }

    public void setImpurity(Double impurity) {
        this.impurity = impurity;
    }

    public Double getInfoGain() {
        return infoGain;
    }

    public void setInfoGain(Double infoGain) {
        this.infoGain = infoGain;
    }

    public Node getTree() {
        return tree;
    }

    public void setTree(Node tree) {
        this.tree = tree;
    }
}

