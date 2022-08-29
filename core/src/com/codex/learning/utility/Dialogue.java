package com.codex.learning.utility;

import java.util.ArrayList;
import java.util.Arrays;

public class Dialogue {

    private ArrayList<String> dialogueContainer = new ArrayList<>(
            Arrays.asList(new String[]{"Hi I am your lolo, JediGrandpa", "I have a task for you to do", "But first lets study first on the topic", "tite hehe xD"}));
    private boolean statementEnd;

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
