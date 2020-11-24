package br.com.gestorCA.view;

import br.com.gestorCA.MainApp;
import br.com.gestorCA.model.entities.Partner;
import br.com.gestorCA.util.CreateAlert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SecretarialMenuController {
    @FXML
    private Button btnHomeMenu;
    @FXML
    private Button btnExitMenu;
    @FXML
    private Button btnNewPartner;
    @FXML
    private Button btnNewMinute;
    @FXML
    private Button btnAllPartner;
    @FXML
    private Button btnAllMinute;

    private EditInfoPartnerController editInfoPartnerController;
    private InfoPartnerController infoPartnerController;
    private MainApp mainApp;
    private Partner partner;
    private Stage primaryStage;
    private VBox boxContent;

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        setStyleBtnMenuSelected(btnHomeMenu);
    }

    @FXML
    public void handleHome() {
        setStyleBtnMenuSelected(btnHomeMenu);
        // se a tela de edição foi aberta
        if (editInfoPartnerController != null) {

            // se alguma informação foi altera
            if (editInfoPartnerController.isAltered()) {
                editInfoPartnerController.saveInfo();
            }
        }

        mainApp.getHomeController().showInfoPartner();
        infoPartnerController.setPartner(partner);
        infoPartnerController.setMainApp(mainApp);
    }

    @FXML
    private void handleExit() {
        setStyleBtnMenuSelected(btnExitMenu);
        //se a tela de edição foi aberta
        if (editInfoPartnerController != null) {

            if (editInfoPartnerController.isAltered()) {
                CreateAlert.createAlertConformation(
                        "Informações não salvas",
                        "Voçe está encerrando sua sessão atual no sistema, " +
                                "existe algumas informações que ainda não foram salvas! ",
                        "Gostaria de salvar e continuar?",
                        "dialog-pane"
                )
                        .showAndWait()
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(response -> {
                            infoPartnerController.getEditInfoPartnerController().saveInfo();
                            primaryStage.close();
                            mainApp.showLoginStage();
                        });
            } else {
                CreateAlert.createAlertConformation(
                        "Encerrar sessão",
                        "Voçe está encerrando sua sessão atual no sistema!",
                        "Gostaria de continuar?",
                        "dialog-pane"
                )
                        .showAndWait()
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(response -> {
                            primaryStage.close();
                            mainApp.showLoginStage();
                        });

                handleHome();
            }
        } else {
            CreateAlert.createAlertConformation(
                    "Encerrar sessão",
                    "Voçe está encerrando sua sessão atual no sistema!",
                    "Gostaria de continuar?",
                    "dialog-pane"
            )
                    .showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        primaryStage.close();
                        mainApp.showLoginStage();
                    });

            handleHome();
        }
    }

    @FXML
    public void handleNewPartner() {
        setStyleBtnMenuSelected(btnNewPartner);

        // Se o cadastro de um novo sócio estive aberto
        if (editInfoPartnerController != null) {
            if (editInfoPartnerController.isAltered()) {
                editInfoPartnerController.saveInfo();
            }
        }

        // Se a edição das informações pessoais estiver aberto
        if (infoPartnerController.getEditInfoPartnerController() != null) {
            if (infoPartnerController.getEditInfoPartnerController().isAltered()) {
                infoPartnerController.getEditInfoPartnerController().saveInfo();
            }
        }

        showEditInfoPartner();
    }

    @FXML
    public void handleNewMinute() {
        setStyleBtnMenuSelected(btnNewMinute);
        showNewMinute();
    }

    @FXML
    private void handleAllPartner() {
        setStyleBtnMenuSelected(btnAllPartner);
        mainApp.getHomeController().showAllPartner();
    }

    @FXML
    private void handleAllMinute() {
        setStyleBtnMenuSelected(btnAllMinute);
        mainApp.getHomeController().showAllMinute();
    }

    private void showNewMinute() {
        try {
            // Carrega o layout da tela de login do arquivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/NewMinute.fxml"));
            AnchorPane newMinute = (AnchorPane) loader.load();

            newMinute.setPrefHeight(boxContent.getPrefHeight());
            boxContent.heightProperty().addListener(
                    (obsValue, value, newValue) -> {
                        newMinute.setPrefHeight(newValue.doubleValue());
                    }
            );

            NewMinuteController controller = loader.getController();
//            editInfoPartnerController = controller;
            controller.setPartner(partner);
            controller.setMainApp(mainApp);
            boxContent.getChildren().clear();
            boxContent.getChildren().add(newMinute);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditInfoPartner() {
        try {
            // Carrega o layout da tela de login do arquivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditInfoPartner.fxml"));
            AnchorPane editInfoPartner = (AnchorPane) loader.load();

            editInfoPartner.setPrefHeight(boxContent.getPrefHeight());
            boxContent.heightProperty().addListener(
                    (obsValue, value, newValue) -> {
                        editInfoPartner.setPrefHeight(newValue.doubleValue());
                    }
            );

            EditInfoPartnerController controller = loader.getController();
            editInfoPartnerController = controller;
            controller.setPartner(new Partner());
            controller.isNewPartner();
            controller.setMainApp(mainApp);

            boxContent.getChildren().clear();
            boxContent.getChildren().add(editInfoPartner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInfoPartnerController(InfoPartnerController controller) {
        this.infoPartnerController = controller;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void setStyleBtnMenuSelected(Button btnMenu) {
        btnHomeMenu.getStyleClass().remove("btn-menu-selected");
        btnNewPartner.getStyleClass().remove("btn-menu-selected");
        btnNewMinute.getStyleClass().remove("btn-menu-selected");
        btnAllPartner.getStyleClass().remove("btn-menu-selected");
        btnAllMinute.getStyleClass().remove("btn-menu-selected");
        btnExitMenu.getStyleClass().remove("btn-menu-selected");

        btnMenu.getStyleClass().add("btn-menu-selected");    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public void setBoxContent(VBox boxContent) {
        this.boxContent = boxContent;
    }

    public EditInfoPartnerController getEditInfoPartnerController() {
        return editInfoPartnerController;
    }
}
