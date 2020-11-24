package br.com.gestorCA.view;

import br.com.gestorCA.MainApp;
import br.com.gestorCA.model.entities.Partner;
import br.com.gestorCA.util.CreateAlert;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class PartnerMenuController {
    @FXML
    private Button btnHomeMenu;
    @FXML
    private Button btnExitMenu;
    @FXML
    private Button btnAllPartner;
    @FXML
    private Button btnAllMinute;

    private InfoPartnerController infoPartnerController;
    private MainApp mainApp;
    private Partner partner;
    private Stage primaryStage;

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
        if (infoPartnerController.getEditInfoPartnerController() != null) {

            // se alguma informação foi altera
            if (infoPartnerController.getEditInfoPartnerController().isAltered()) {
                infoPartnerController.getEditInfoPartnerController().saveInfo();
            }
        }

        mainApp.getHomeController().showInfoPartner();
        infoPartnerController.setPartner(partner);
        infoPartnerController.setMainApp(mainApp);
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

    @FXML
    private void handleExit() {
        setStyleBtnMenuSelected(btnExitMenu);
        //se a tela de edição foi aberta
        if (infoPartnerController.getEditInfoPartnerController() != null) {

            if (infoPartnerController.getEditInfoPartnerController().isAltered()) {
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
        btnAllPartner.getStyleClass().remove("btn-menu-selected");
        btnAllMinute.getStyleClass().remove("btn-menu-selected");
        btnExitMenu.getStyleClass().remove("btn-menu-selected");

        btnMenu.getStyleClass().add("btn-menu-selected");    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }
}
