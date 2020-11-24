package br.com.gestorCA;

import br.com.gestorCA.model.entities.Partner;
import br.com.gestorCA.view.HomeController;
import br.com.gestorCA.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private Stage loginStage;
    private HomeController homeController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        showLoginStage();
    }

    public void showLoginStage() {
        try {
            // Carrega o layout da tela de login do arquivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
            BorderPane login = (BorderPane) loader.load();

            // Mosta a Scene contendo o layout do login
            Scene scene = new Scene(login);
            loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setResizable(false);
            loginStage.initModality(Modality.WINDOW_MODAL);
            loginStage.setScene(scene);

            LoginController controller = loader.getController();
            controller.setLoginStage(loginStage);
            controller.setMainApp(this);

            loginStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRootLayout(Partner partner) {
        try {
            // Carrega o layout da tela de login do arquivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Home.fxml"));
            BorderPane home = (BorderPane) loader.load();

            Scene scene = new Scene(home);
            primaryStage.setScene(scene);

            HomeController controller = loader.getController();
            homeController = controller;
            controller.setMainApp(this);
            controller.setPrimaryStage(primaryStage);
            controller.setPartner(partner);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HomeController getHomeController() {
        return homeController;
    }
}
