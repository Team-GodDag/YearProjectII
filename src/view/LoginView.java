package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.Optional;

public class LoginView {

 String user = "Admin";
    String pw = "123";
    String checkUser;
    String checkPw;

    public GridPane createView(){

        GridPane root = new GridPane();

        root.setPadding(new Insets(100,100,320,100));
        root.setVgap(8);
        root.setHgap(10);


        //Name label
        Label nameLabel = new Label("Brugernavn: ");
        GridPane.setConstraints(nameLabel,1,0);
        nameLabel.setId("loginLabel");

        //Name input
        TextField nameInput = new TextField();
        nameInput.setPromptText("Indtast Brugernavn");
        GridPane.setConstraints(nameInput,1,1);

        //Password Label
        Label passwordLabel = new Label("Password: ");
        GridPane.setConstraints(passwordLabel,1,2);
        passwordLabel.setId("loginLabel");

        //Password Input
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Indtast Password");
        GridPane.setConstraints(passwordInput,1,3);

        Button loginButton = new Button("Login");
        loginButton.setMaxWidth(100);
        GridPane.setConstraints(loginButton,1,5);

        //Login message
        final Label loginMessage = new Label();
        GridPane.setConstraints(loginMessage,1,4);

        root.getChildren().addAll(
                nameLabel,
                nameInput,
                passwordLabel,
                passwordInput,
                loginMessage,
                loginButton);


        root.setAlignment(Pos.CENTER);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                checkUser = nameInput.getText().toString();
                checkPw = passwordInput.getText().toString();
                if(checkUser.equals(user) && checkPw.equals(pw)){
                    UIManager.instance().switchCenter(new CustomerView().createView());
                    loginMessage.setText("Congratulations!");
                    loginMessage.setTextFill(Color.GREEN);
                }
                else{
                    loginMessage.setText("Forkert Brugernavn eller Password.");
                    loginMessage.setTextFill(Color.RED);
                }
                nameInput.setText("");
                passwordInput.setText("");
            }
        });



        return root;
    }

}
