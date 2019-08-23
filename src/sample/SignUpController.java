package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox SignUpCheckBoxFeMail;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField SigmUpCountry;

    @FXML
    private PasswordField login_field;

    @FXML
    private TextField SignUpLastName;

    @FXML
    private TextField SignUPName;

    @FXML
    private CheckBox SignUpCheckBoxfeMail;

    @FXML
    private Button SingUpButton;


    @FXML
    void initialize() {
            SingUpButton.setOnAction(event -> {
                signUpNewUser(); //создадим метод
            });

    }
    private void signUpNewUser() {
        DatabaseHandler dbHandler= new DatabaseHandler();

        String firstName=SignUPName.getText();
        String lastName=SignUpLastName.getText();
        String userName=login_field.getText();
        String password =password_field.getText();
        String  location=SigmUpCountry.getText();
        String gender= "";
        if(SignUpCheckBoxFeMail.isSelected())
            gender="Мужской";
        else
            gender= "Женский";
          // будем создавть новый обьект в классе user
        User user = new User(firstName,lastName,userName,password,location,gender);
        dbHandler.signUpUser(user);//передаем вещи как

    }
}
