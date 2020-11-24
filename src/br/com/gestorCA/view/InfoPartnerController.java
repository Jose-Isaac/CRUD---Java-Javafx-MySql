package br.com.gestorCA.view;

import br.com.gestorCA.MainApp;
import br.com.gestorCA.model.entities.Partner;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;

public class InfoPartnerController {
    @FXML
    private VBox vboxMain;
    @FXML
    private ScrollPane scroolPane;
    @FXML
    private AnchorPane scroolAnchorPane;

    @FXML
    private Label idLabel;
    @FXML
    private TextField associationNameField;
    @FXML
    private DatePicker associationFundationDatePicker;
    @FXML
    private TextField associationCnpjFiled;
    @FXML
    private TextField associationAddressLocalityField;
    @FXML
    private TextField associationAddressNumberField;
    @FXML
    private TextField associationAddressZoneField;
    @FXML
    private TextField associationAddressCityField;
    @FXML
    private TextField associationAddressUfField;
    @FXML
    private TextField associationAddressCepField;
    @FXML
    private TextField communitiesField;
    @FXML
    private TextField outherAssociationField;
    @FXML
    private TextField partnerNameField;
    @FXML
    private TextField partnerMotherNameField;
    @FXML
    private TextField partnerFatherNameField;
    @FXML
    private DatePicker partnerDateBirthPicker;
    @FXML
    private DatePicker partnerRegidtrationDatePicker;
    @FXML
    private TextField partnerMaritalStatusField;
    @FXML
    private TextField partnerCpfField;
    @FXML
    private TextField partnerSexField;
    @FXML
    private TextField partnerNaturalnessField;
    @FXML
    private TextField partnerNationalityField;
    @FXML
    private TextField partnerSeriesField;
    @FXML
    private TextField partnerSchoolingField;
    @FXML
    private TextField partnerProfessionField;
    @FXML
    private TextField partnerRgField;
    @FXML
    private TextField partnerVoterRegistrationField;
    @FXML
    private TextField partnerCardWorkField;
    @FXML
    private TextField partnerLandTenureField;
    @FXML
    private TextField partnerSyndicateField;
    @FXML
    private TextField partnerLocalityField;
    @FXML
    private TextField partnerCityField;
    @FXML
    private TextField partnerUfField;
    @FXML
    private TextField partnerCepField;
    @FXML
    private TextField partnerActivityAddressLocalityField;
    @FXML
    private TextField partnerActivityAddressCityField;
    @FXML
    private TextField partnerActivityAddressUfField;
    @FXML
    private TextField partnerActivityAddressCepField;
    @FXML
    private TextField partnerLandAreaField;
    @FXML
    private TextField partnerSpouseNameField;
    @FXML
    private TextField partnerSpouseCpfField;
    @FXML
    private TextField partnerChildrenNumberField;
    @FXML
    private TextField partnerChildrenNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField partnerPhoneField;
    @FXML
    private TextField accessLevel;

    private Partner partner;
    private VBox boxContent;
    private MainApp mainApp;
    private EditInfoPartnerController editInfoPartnerController;

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        vboxMain.heightProperty().addListener((observableValue, number, t1) -> {
            scroolPane.setPrefHeight(t1.doubleValue());
        });
        scroolPane.widthProperty().addListener((observableValue, number, t1) -> {
            scroolAnchorPane.setPrefWidth(t1.doubleValue() - 3);
        });
    }

    @FXML
    private void handleEditInfo() {
        showEditInfoPartner();
    }

    public void showEditInfoPartner() {
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
            controller.setPartner(partner);
            controller.setMainApp(mainApp);

            boxContent.getChildren().clear();
            boxContent.getChildren().add(editInfoPartner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPartner(Partner partner) {
        this.partner = partner;

        insertInfo();
    }

    public void setBoxContent(VBox boxContent) {
        this.boxContent = boxContent;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void insertInfo() {
        idLabel.setText("FICHA INDIVIDUAL DE SÓCIO N°_" + partner.getId().toString());

        associationNameField.setText(partner.getAssociation().getName());

        LocalDate fundationDate = partner.getAssociation().getFundationDate();
        associationFundationDatePicker.setValue(
                LocalDate.of(fundationDate.getYear(), fundationDate.getMonth(), fundationDate.getDayOfMonth())
        );
        datePickerDisableClick(associationFundationDatePicker);

        associationCnpjFiled.setText(partner.getAssociation().getCnpj());

        associationAddressLocalityField.setText(partner.getAssociation().getAddress().getLocality());

        associationAddressNumberField.setText(partner.getAssociation().getAddress().getNumber().toString());

        associationAddressZoneField.setText(partner.getAssociation().getAddress().getZone());

        associationAddressCepField.setText(partner.getAssociation().getAddress().getCepNumber().toString());

        associationAddressCityField.setText(partner.getAssociation().getAddress().getCity());

        associationAddressUfField.setText(partner.getAssociation().getAddress().getState());

        communitiesField.setText(partner.getAssociation().getCommunities());

        partnerNameField.setText(partner.getName());
        partnerNameField.setEditable(false);

        partnerMotherNameField.setText(partner.getMotherName());
        partnerMotherNameField.setEditable(false);

        partnerFatherNameField.setText(partner.getFatherName());
        partnerFatherNameField.setEditable(false);

        LocalDate dateBirth = partner.getDateBirth();
        partnerDateBirthPicker.setValue(LocalDate.of(dateBirth.getYear(), dateBirth.getMonth(), dateBirth.getDayOfMonth()));
        partnerDateBirthPicker.setEditable(false);

        datePickerDisableClick(partnerDateBirthPicker);

        LocalDate registrationDate = partner.getRegistrationDate();
        partnerRegidtrationDatePicker.setValue(
                LocalDate.of(registrationDate.getYear(), registrationDate.getMonth(), registrationDate.getDayOfMonth())
        );
        partnerRegidtrationDatePicker.setEditable(false);

        datePickerDisableClick(partnerRegidtrationDatePicker);

        partnerMaritalStatusField.setText(partner.getMaritalStatus());
        partnerMaritalStatusField.setEditable(false);

        partnerCpfField.setText(partner.getCpf());
        partnerCpfField.setEditable(false);

        partnerSexField.setText(partner.getSex() + ""); // + "" o retorno de getSex vira uma String
        partnerSexField.setEditable(false);

        partnerNationalityField.setText(partner.getNationality());
        partnerNationalityField.setEditable(false);

        partnerNaturalnessField.setText(partner.getNaturalness());
        partnerNaturalnessField.setEditable(false);

        partnerSeriesField.setText(partner.getSeries());
        partnerSeriesField.setEditable(false);

        partnerSchoolingField.setText(partner.getSchooling());
        partnerSchoolingField.setEditable(false);

        partnerProfessionField.setText(partner.getProfession());
        partnerProfessionField.setEditable(false);

        partnerRgField.setText(partner.getRg());
        partnerRgField.setEditable(false);

        partnerVoterRegistrationField.setText(partner.getVoterRegistration());
        partnerVoterRegistrationField.setEditable(false);

        partnerCardWorkField.setText(partner.getWorkCardNumber().toString());
        partnerCardWorkField.setEditable(false);

        partnerLandTenureField.setText(partner.getLandTenure());
        partnerLandTenureField.setEditable(false);

        partnerSyndicateField.setText(partner.getSyndicate());
        partnerSyndicateField.setEditable(false);

        partnerLocalityField.setText(partner.getAddress().getLocality());
        partnerLocalityField.setEditable(false);

        partnerCityField.setText(partner.getAddress().getCity());
        partnerCityField.setEditable(false);

        partnerUfField.setText(partner.getAddress().getState());
        partnerUfField.setEditable(false);

        partnerCepField.setText(partner.getAddress().getCepNumber().toString());
        partnerCepField.setEditable(false);

        partnerActivityAddressLocalityField.setText(partner.getActivityAddress().getLocality());
        partnerActivityAddressLocalityField.setEditable(false);

        partnerActivityAddressCityField.setText(partner.getActivityAddress().getCity());
        partnerActivityAddressCityField.setEditable(false);

        partnerActivityAddressUfField.setText(partner.getActivityAddress().getState());
        partnerActivityAddressUfField.setEditable(false);

        partnerActivityAddressCepField.setText(partner.getActivityAddress().getCepNumber().toString());
        partnerActivityAddressCepField.setEditable(false);

        partnerLandAreaField.setText(partner.getLandArea());
        partnerLandAreaField.setEditable(false);

        // Caso o Conjugê seja um sócio
        if (partner.getSpouse() != null) {
            partnerSpouseNameField.setText(partner.getSpouse().getName());
            partnerSpouseCpfField.setText(partner.getSpouse().getCpf());
        } else {
            partnerSpouseNameField.setText(partner.getSpouseName());
            partnerSpouseCpfField.setText(partner.getSpouseCpf());
        }

        partnerSpouseNameField.setEditable(false);
        partnerSpouseCpfField.setEditable(false);

        if (partner.getOuthersAssociations() != null) {
            outherAssociationField.setText(partner.getOuthersAssociations());
        }

        outherAssociationField.setEditable(false);

        partnerChildrenNumberField.setText(partner.getQuantifyChildren().toString());

        partnerChildrenNameField.setText(partner.getChildrensName());

        usernameField.setText(partner.getUsername());

        passwordField.setText(partner.getPassword());

        accessLevel.setText(Integer.toString(partner.getAccessLevel()));

        partnerPhoneField.setText(partner.getNumberPhone());
    }

    private void datePickerDisableClick(DatePicker datePicker) {
        datePicker.setOnMouseClicked(e -> {
            if (!datePicker.isEditable()) {
                datePicker.hide();
            }
        });
    }

    public EditInfoPartnerController getEditInfoPartnerController() {
        return editInfoPartnerController;
    }
}
