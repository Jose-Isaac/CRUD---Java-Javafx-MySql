package br.com.gestorCA.view;

import br.com.gestorCA.model.entities.MinuteProperty;
import br.com.gestorCA.model.entities.PartnerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AllMinuteController {
    @FXML
    private TableView<MinuteProperty> minuteTableView;
    @FXML
    private TableColumn<MinuteProperty, String> nameColumn;
    @FXML
    private TableColumn<MinuteProperty, String> dateColumn;

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        // Inicializa a tabela de sócios com três colunas
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().secretarialNameProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    }

    public void setTableItems(ObservableList<MinuteProperty> all) {
        minuteTableView.setItems(all);
    }
}
