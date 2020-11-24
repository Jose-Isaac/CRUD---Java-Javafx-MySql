package br.com.gestorCA.view;

import br.com.gestorCA.model.entities.Partner;
import br.com.gestorCA.model.entities.PartnerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class AllPartnerController {
    @FXML
    private TableView<PartnerProperty> partnerTableView;
//    @FXML
//    private TableColumn<Per, ImageView> avatarColumn;
    @FXML
    private TableColumn<PartnerProperty, String> nameColumn;
    @FXML
    private TableColumn<PartnerProperty, String> phoneColumn;

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        // Inicializa a tabela de sócios com três colunas
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
    }

    public void setTableItems(ObservableList<PartnerProperty> all) {
        partnerTableView.setItems(all);
    }
}
