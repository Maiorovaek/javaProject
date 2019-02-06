package rmi.validate;


import javafx.scene.control.TextField;

public class NumberTextField extends TextField {

    public NumberTextField(){
        this.setPromptText("Enter only numbers");
    }

    public void replaceText(int i, int il, String s){
        if(s.matches("[0-9]")|| s.isEmpty()){
            super.replaceText(i,il,s);
        }

    }

    public void replaceSelection(String s ){
        super.replaceSelection(s);
    }

}
