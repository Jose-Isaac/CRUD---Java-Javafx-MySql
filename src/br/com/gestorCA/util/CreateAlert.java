package br.com.gestorCA.util;

import br.com.gestorCA.MainApp;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public abstract class CreateAlert {
    public static Alert createAlertConformation(String title, String header, String content, String cssClass) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(String.valueOf(MainApp.class.getResource("styles/alert.css")));
        dialogPane.getStylesheets().add("dialog-pane");

        return alert;
    }

    public static Alert createAlertError(String title, String header, String content, String cssClass) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(String.valueOf(MainApp.class.getResource("styles/alert.css")));
        dialogPane.getStylesheets().add(cssClass);

        return alert;
    }

    public static Alert createAlertInformation(String title, String content, String cssClass) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(" ");
        alert.setContentText(content);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(String.valueOf(MainApp.class.getResource("styles/alert.css")));
        dialogPane.getStylesheets().add(cssClass);

        return alert;
    }
}
