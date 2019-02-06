package rmi.validate;

import javafx.scene.control.TextField;

public class DoubleTextField extends TextField {
    public DoubleTextField(){
        this.setPromptText("Enter string");

    }


    public void replaceText(int i, int il, String s){
        if(s.matches("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?") || s.isEmpty()){
            super.replaceText(i,il,s);
        }

    }

    public void replaceSelection(String s ){
        super.replaceSelection(s);
    }
}
