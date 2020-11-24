package br.com.gestorCA.view;

import br.com.gestorCA.MainApp;
import br.com.gestorCA.model.dao.DaoFactory;
import br.com.gestorCA.model.entities.MinuteProperty;
import br.com.gestorCA.model.entities.Partner;
import br.com.gestorCA.model.entities.PartnerProperty;
import br.com.gestorCA.util.CreateAlert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController {
    @FXML
    private Label usernameLabel;
    @FXML
    private VBox vBoxMain;
    @FXML
    private VBox boxContent;
    @FXML
    private VBox vboxMenu;
    @FXML
    private Button btnAvatar;
    @FXML
    private ImageView avatar;

    private Partner partner;
    private MainApp mainApp;
    private Stage primaryStage;
    private InfoPartnerController infoPartnerController;
    private PartnerMenuController partnerMenuController;
    private SecretarialMenuController secretarialMenuController;

    private Desktop desktop = Desktop.getDesktop();

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        vBoxMain.heightProperty().addListener((obsValue, value, newValue) -> {
            boxContent.setPrefHeight(newValue.doubleValue());
        });
    }

    public HomeController() {

    }

    public void showAllPartner() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AllPartner.fxml"));
            AnchorPane allPartner = (AnchorPane) loader.load();

            AllPartnerController controller = loader.getController();
            controller.setTableItems(PartnerProperty.getAllPartnerProperty(partner.getAssociation().getId()));

            allPartner.setPrefHeight(boxContent.getPrefHeight());
            boxContent.heightProperty().addListener(
                    (obsValue, value, newValue) -> {allPartner.setPrefHeight(newValue.doubleValue());}
            );

            boxContent.getChildren().clear();
            boxContent.getChildren().add(allPartner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllMinute() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AllMinute.fxml"));
            AnchorPane allMinute = (AnchorPane) loader.load();

            AllMinuteController controller = loader.getController();
            controller.setTableItems(MinuteProperty.getAllMinuteProperty(partner.getAssociation().getId()));

            allMinute.setPrefHeight(boxContent.getPrefHeight());
            boxContent.heightProperty().addListener(
                    (obsValue, value, newValue) -> {allMinute.setPrefHeight(newValue.doubleValue());}
            );

            boxContent.getChildren().clear();
            boxContent.getChildren().add(allMinute);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleHome() {
        if (partnerMenuController != null) {
            partnerMenuController.handleHome();
        }

        if (secretarialMenuController != null) {
            secretarialMenuController.handleHome();
        }
    }

    public void setPartner(Partner partner) throws IOException {
        this.partner = partner;

        usernameLabel.setText(partner.getName());
//        avatar.setImage(new Image(partner.getImage()));
        switchMenu();
    }

    private void switchMenu() throws IOException {
        if (partner.getAccessLevel() <= 3) {
            showInfoPartner();
            setPartnerMenu();
        }

        // Secretário
        if (partner.getAccessLevel() >= 4) {
            showInfoPartner();
            setSecretarialMenu();
        }
    }

    private void setPartnerMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PartnerMenu.fxml"));
        VBox partnerMenu = (VBox) loader.load();

        PartnerMenuController controller = loader.getController();
        partnerMenuController = controller;
        controller.setInfoPartnerController(infoPartnerController);
        controller.setPrimaryStage(primaryStage);
        controller.setMainApp(mainApp);
        controller.setPartner(partner);

        vboxMenu.getChildren().clear();
        vboxMenu.getChildren().add(partnerMenu);
    }

    private void setSecretarialMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/SecretarialMenu.fxml"));
        VBox partnerMenu = (VBox) loader.load();

        SecretarialMenuController controller = loader.getController();
        secretarialMenuController = controller;
        controller.setInfoPartnerController(infoPartnerController);
        controller.setPrimaryStage(primaryStage);
        controller.setMainApp(mainApp);
        controller.setPartner(partner);
        controller.setBoxContent(boxContent);

        vboxMenu.getChildren().clear();
        vboxMenu.getChildren().add(partnerMenu);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showInfoPartner() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/InfoPartner.fxml"));
            AnchorPane infoPartner = (AnchorPane) loader.load();

            infoPartnerController = loader.getController();
            infoPartnerController.setBoxContent(boxContent);
            infoPartnerController.setMainApp(mainApp);
            infoPartnerController.setPartner(partner);

            infoPartner.setPrefHeight(boxContent.getPrefHeight());
            boxContent.heightProperty().addListener(
                    (obsValue, value, newValue) -> {infoPartner.setPrefHeight(newValue.doubleValue());}
            );

            boxContent.getChildren().clear();
            boxContent.getChildren().add(infoPartner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SecretarialMenuController getSecretarialMenuController() {
        return secretarialMenuController;
    }

    public InfoPartnerController getInfoPartnerController() {
        return infoPartnerController;
    }

    @FXML
    private void handleAvatar() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            FileInputStream inputStream = new FileInputStream(file);
            partner.setImage(inputStream);
            avatar.setImage(new Image(inputStream));
            partner.setImageLength(file.length());
            DaoFactory.createPartnerdao().update(partner);
        }
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
