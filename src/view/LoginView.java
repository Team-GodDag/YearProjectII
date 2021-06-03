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
import javafx.scene.text.Text;

public class LoginView {
    //Lavet af Lars
    TopMenuView topMenu;

    String user = "Admin";
    String pw = "password";
    String checkUser;
    String checkPw;

    public GridPane createView(){
        UIManager.instance().switchTop(null);

        GridPane root = new GridPane();

        root.setPadding(new Insets(100,100,320,100));
        root.setVgap(8);
        root.setHgap(10);

        //Welcome text
        Text welcomeTxt = new Text("Login");
        welcomeTxt.setStyle("-fx-font: normal bold 20px 'serif' ");
        GridPane.setConstraints(welcomeTxt,1,0);

        //Name label
        Label nameLabel = new Label("Brugernavn: ");
        GridPane.setConstraints(nameLabel,1,4);
        nameLabel.setId("loginLabel");

        //Name input
        TextField nameInput = new TextField();
        nameInput.setPromptText("Indtast Brugernavn");
        GridPane.setConstraints(nameInput,1,5);

        //Password Label
        Label passwordLabel = new Label("Password: ");
        GridPane.setConstraints(passwordLabel,1,6);
        passwordLabel.setId("loginLabel");

        //Password Input
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Indtast Password");
        GridPane.setConstraints(passwordInput,1,7);

        //Login message
        final Label loginMessage = new Label();
        GridPane.setConstraints(loginMessage,1,8);

        //Login Button

        Button loginButton = new Button("Login");
        loginButton.setMaxWidth(100);
        GridPane.setConstraints(loginButton,1,9);

        root.getChildren().addAll(
                welcomeTxt,
                nameLabel,
                nameInput,
                passwordLabel,
                passwordInput,
                loginMessage,
                loginButton);

        root.setAlignment(Pos.CENTER);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                checkUser = nameInput.getText();
                checkPw = passwordInput.getText();
                if(checkUser.equals(user) && checkPw.equals(pw)){
                    UIManager.instance().switchTop(new TopMenuView().createTopMenu());
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
