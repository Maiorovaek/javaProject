package sample.validate;


import javafx.scene.control.TextField;

public class NameTextField extends TextField {
    public NameTextField(){
        this.setPromptText("Enter string");

    }


    public void replaceText(int i, int il, String s){
        if(s.matches("[a-zA-Z]")|| s.isEmpty()){
            super.replaceText(i,il,s);
        }

    }

    public void replaceSelection(String s ){
        super.replaceSelection(s);
    }
}
