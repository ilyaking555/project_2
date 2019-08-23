package sample;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.animation.Shake;


public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField login_field;
    @FXML
    private Button TwoButton;

    @FXML
    private Button onrButton;

    @FXML
            void initialize() {
                TwoButton.setOnAction(event -> {
            String loginText=login_field.getText();// удалеяет пробелы
            String loginPassword=password_field.getText().trim();// удалеяет пробелы

            if (!loginText.equals("")&& !loginPassword.equals("")); // если логин не пустой и пароль не пустой
                    loginUser(loginText,loginPassword);
                    if (!loginText.equals("")&& !loginPassword.equals(""));
                    else
                        System.out.println("Login and password are empty"); // иначе вывести что строки пустые
                    });


        onrButton.setOnAction(event -> {
            openNewScene("/sample/SignUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword){
        DatabaseHandler dbHandler=new DatabaseHandler();
        User user= new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);//взять
        int counter=0;
        try {
        while(result.next()){
            counter++;
        }
        }catch (SQLException e) {
        e.printStackTrace();
        }
        if (counter >= 1) {
            openNewScene("/sample/app.fxml");
        }else {
            Shake userLoginAnimation= new Shake(login_field);
            Shake userPassAnimation= new Shake(password_field);

            userLoginAnimation.playAnimation();//проиграть данную анимацию
            userPassAnimation.playAnimation();
        }
     }



     public void openNewScene(String window){
         onrButton.getScene().getWindow().hide();//берем окно и прячим окно
         FXMLLoader loader=new FXMLLoader();//выделяем память для окна
         loader.setLocation(getClass().getResource(window));
         try{
             loader.load();//загрузить окно
         } catch (IOException e){
             e.printStackTrace();
         }
         Parent root= loader.getRoot();
         Stage stage= new Stage();
         stage.setScene(new Scene(root));//передадим новый класс
         stage.showAndWait();//показать
     }
}

