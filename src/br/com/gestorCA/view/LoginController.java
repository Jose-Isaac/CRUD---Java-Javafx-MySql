package br.com.gestorCA.view;

import br.com.gestorCA.MainApp;
import br.com.gestorCA.model.dao.DaoFactory;
import br.com.gestorCA.model.entities.Partner;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField fieldUserName;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private Label infoLabel;

    private MainApp mainApp;
    private Stage loginStage;
    public LoginController() {
    }

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    }

    @FXML
    private void handleSignIn() throws InterruptedException {
        Partner partner;
        infoLabel.getStyleClass().clear();

        if (!fieldUserName.getText().isEmpty()) {
            partner = DaoFactory.createPartnerdao().findByUsername(fieldUserName.getText().intern());

            if (partner != null) {
                if (!fieldPassword.getText().isEmpty()) {
                    if (partner.getPassword().equals(fieldPassword.getText().intern())) {
                        infoLabel.getStyleClass().add("success");
                        infoLabel.setText("Success");
                        loginStage.close();
                        mainApp.showRootLayout(partner);
                    } else {
                        infoLabel.getStyleClass().add("error");
                        infoLabel.setText("Password invalid");
                    }
                } else {
                    infoLabel.getStyleClass().add("warning");
                    infoLabel.setText("The password field is empty");
                }
            } else {
                infoLabel.getStyleClass().add("error");
                infoLabel.setText("Username not found");
            }
        } else {
            infoLabel.getStyleClass().add("warning");
            infoLabel.setText("The username field is empty");
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }
}
