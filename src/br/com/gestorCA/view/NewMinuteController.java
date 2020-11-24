package br.com.gestorCA.view;

import br.com.gestorCA.MainApp;
import br.com.gestorCA.model.dao.DaoFactory;
import br.com.gestorCA.model.entities.Minute;
import br.com.gestorCA.model.entities.Partner;
import br.com.gestorCA.util.CreateAlert;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class NewMinuteController {
    @FXML
    private TextField secretarialName;
    @FXML
    private TextArea minute;
    @FXML
    private DatePicker date;
    @FXML
    private VBox vboxMain;
    @FXML
    private ScrollPane scroolPane;
    @FXML
    private AnchorPane scroolAnchorPane;

    private Partner partner;
    private MainApp mainApp;
    private boolean isAltered;
    private boolean isEmpty;

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        vboxMain.heightProperty().addListener((observableValue, number, t1) -> {
            scroolPane.setPrefHeight(t1.doubleValue());
            scroolAnchorPane.setPrefHeight(t1.doubleValue());
        });
        scroolPane.widthProperty().addListener((observableValue, number, t1) -> {
            scroolAnchorPane.setPrefWidth(t1.doubleValue() - 3);
        });

        minute.textProperty().addListener((obsvalue, oldvalue, newvalue) -> {
            if(newvalue.isEmpty()) {
                isEmpty = true;
            }
            isAltered = true;
        });

        date.setValue(LocalDate.now());
    }

    public boolean isAltered() {
        return isAltered;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;

        secretarialName.setText(partner.getName());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleCancel() {
        if (!isAltered) {
            mainApp.getHomeController().getSecretarialMenuController().handleNewMinute();
        } else {
            CreateAlert.createAlertConformation(
                    "Informações Alteradas",
                    "Você possui algumas informações que ainda não foram salvas!",
                    "Gostaria de Salvar?",
                    "dialog-pane"
            ).showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        saveInfo();
                    });

            isAltered = false;
            mainApp.getHomeController().getSecretarialMenuController().handleNewMinute();
        }
    }

    @FXML
    private void handleSave() {
        saveInfo();
        mainApp.getHomeController().getSecretarialMenuController().handleNewMinute();
    }

    private void saveInfo() {
        if (isAltered) {
            if (!isEmpty) {
                Minute minute = new Minute();
                minute.setPartnerId(partner.getId());
                minute.setAssociationId(partner.getAssociation().getId());
                minute.setText(this.minute.getText());
                minute.setDate(LocalDate.now());

                DaoFactory.createMinuteDao().insert(minute);

                CreateAlert.createAlertInformation(
                        "Sucesso",
                        "Informações salvas com sucesso!",
                        "dialog-pane")
                        .showAndWait();
            }
        }
    }
}
