package logic;

import javafx.scene.control.TextField;

public class DownPaymentValidation {    //bliver ikke brugt - der ligger noget lokalt i stedet
    TextField textField;

    public void validation(TextField carPrice, TextField downPayment){
        textField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!textField.getText().matches("[1-5]\\.[0-9]|6\\.0")){
                    //when it not matches the pattern (1.0 - 6.0)
                    //set the textField empty
                    textField.setText("");
                }
            }

        });
    }
}
