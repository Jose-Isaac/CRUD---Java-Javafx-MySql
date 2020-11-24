package br.com.gestorCA.view;

import br.com.gestorCA.MainApp;
import br.com.gestorCA.model.dao.DaoFactory;
import br.com.gestorCA.model.entities.Address;
import br.com.gestorCA.model.entities.Association;
import br.com.gestorCA.model.entities.Partner;
import br.com.gestorCA.util.CreateAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditInfoPartnerController {
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
    private boolean isAltered;
    private boolean isError;
    private boolean isNewPartner = false;
    private boolean isEmpty = false;
    private String erroMensage;
    private List<String> fieldEmpty;
    private MainApp mainApp;

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

        idLabel.getStyleClass().add("edit-info-title");
    }

    public EditInfoPartnerController() {
        isAltered = false;
        isError = false;
        fieldEmpty = new ArrayList<>();
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
        insertInfo();
        insertOnChange();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public boolean isAltered() {
        return isAltered;
    }

    private void setErrorClass(TextField textField) {
        textField.getStyleClass().add("error");
    }
    private void removeErrorClass(TextField textField) {
        textField.getStyleClass().remove("error");
        textField.getStyleClass().add("text-field");}

    private void setErrorClass(DatePicker datePicker) {datePicker.getStyleClass().add("error"); }
    private void removeErrorClass(DatePicker datePicker) {
        datePicker.getStyleClass().remove("error");
        datePicker.getStyleClass().add("text-field");}

    private void setWarningClass(TextField textField) {
        textField.getStyleClass().add("warning");
    }
    private void removeWarningClass(TextField textField) {
        textField.getStyleClass().remove("warning");
        textField.getStyleClass().add("text-field");
    }

    private void setWarningClass(DatePicker datePicker) { datePicker.getStyleClass().add("warning");}
    private void removeWarningClass(DatePicker datePicker) {
        datePicker.getStyleClass().remove("warning");
        datePicker.getStyleClass().add("text-field");}

    private void setSuccessClass(TextField textField) {
        textField.getStyleClass().add("success");
    }
    private void removeSuccessClass(TextField textField) {
        textField.getStyleClass().remove("success");
        textField.getStyleClass().add("text-field");}

    private void setSuccessClass(DatePicker datePicker) {datePicker.getStyleClass().add("success"); }
    private void removeSuccessClass(DatePicker datePicker) {
        datePicker.getStyleClass().remove("success");
        datePicker.getStyleClass().add("text-field");}

    private void removeFieldEmpty(String field) {
        for (int index = 0; index < fieldEmpty.size(); index++) {
            if (fieldEmpty.get(index).equals(field)) {
                fieldEmpty.remove(index);
                return;
            }
        }
    }

    private void isAllFieldsNotEmpty() {
        if(associationCnpjFiled.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("CNPJ");
            fieldEmpty.add("CNPJ");
        }

        if(partnerNameField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Nome");
            fieldEmpty.add("Nome");
        }

        if(partnerMotherNameField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Mãe");
            fieldEmpty.add("Mãe");
        }

        if(partnerFatherNameField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Pai");
            fieldEmpty.add("Pai");
        }

        if(partnerDateBirthPicker.getValue() == null) {
            isEmpty = true;
            removeFieldEmpty("Data de Aniversário");
            fieldEmpty.add("Data de Aniversário");
        }

        if(partnerRegidtrationDatePicker.getValue() == null) {
            isEmpty = true;
        }

        if(partnerMaritalStatusField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Estado Civil");
            fieldEmpty.add("Estado Civil");
        }

        if(partnerCpfField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("CPF");
            fieldEmpty.add("CPF");
        }

        if(partnerSexField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Sexo");
            fieldEmpty.add("Sexo");
        }

        if(partnerNationalityField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Naturalidade");
            fieldEmpty.add("Naturalidade");
        }

        if(partnerNaturalnessField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Nacionalidade");
            fieldEmpty.add("Nacionalidade");
        }

        if(partnerSeriesField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Série");
            fieldEmpty.add("Série");
        }

        if(partnerSchoolingField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Escolaridade");
            fieldEmpty.add("Escolaridade");
        }

        if(partnerProfessionField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Profissão");
            fieldEmpty.add("Profissão");
        }

        if(partnerRgField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("RG");
            fieldEmpty.add("RG");
        }

        if(partnerVoterRegistrationField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Título de Eleitor");
            fieldEmpty.add("Título de Eleitor");
        }

        if(partnerCardWorkField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("CTPS");
            fieldEmpty.add("CTPS");
        }

        if(partnerLandTenureField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Posse de Terra");
            fieldEmpty.add("Posse de Terra");
        }

        if(partnerSyndicateField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Sindicato");
            fieldEmpty.add("Sindicato");
        }

        if(partnerLocalityField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Endereço Residencial ] - Localidade");
            fieldEmpty.add("[ Endereço Residencial ] - Localidade");
        }

        if(partnerCityField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Endereço Residencial ] - Município");
            fieldEmpty.add("[ Endereço Residencial ] - Município");
        }

        if(partnerUfField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Endereço Residencial ] - UF");
            fieldEmpty.add("[ Endereço Residencial ] - UF");
        }

        if(partnerCepField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Endereço Residencial ] - CEP");
            fieldEmpty.add("[ Endereço Residencial ] - CEP");
        }

        if(partnerActivityAddressLocalityField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Endereço de Atividade ] - Localidade");
            fieldEmpty.add("[ Endereço de Atividade ] - Localidade");
        }

        if(partnerActivityAddressCityField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Endereço de Atividade ] - Município");
            fieldEmpty.add("[ Endereço de Atividade ] - Município");
        }

        if(partnerActivityAddressUfField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Endereço de Atividade ] - UF");
            fieldEmpty.add("[ Endereço de Atividade ] - UF");
        }

        if(partnerActivityAddressCepField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Endereço de Atividade ] - CEP");
            fieldEmpty.add("[ Endereço de Atividade ] - CEP");
        }

        if(partnerLandAreaField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Endereço de Atividade ] - Área -HA");
            fieldEmpty.add("[ Endereço de Atividade ] - Área -HA");
        }

        if(partnerSpouseNameField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Dados do Cônjuge ] - Nome");
            fieldEmpty.add("[ Dados do Cônjuge ] - Nome");
        }

        if(partnerSpouseCpfField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("[ Dados do Cônjuge ] - CPF");
            fieldEmpty.add("[ Dados do Cônjuge ] - CPF");
        }

        if(outherAssociationField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Outas Associações");
            fieldEmpty.add("Outras Associações");
        }

        if(partnerChildrenNameField.getText() == null || partnerChildrenNameField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Nome dos filhos");
            fieldEmpty.add("Nome dos filhos");
        }

        if(partnerChildrenNumberField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("N° Filhos");
            fieldEmpty.add("N° Filhos");
        }

        if(usernameField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Nome do Usuário");
            fieldEmpty.add("Nome do Usuário");
        }

        if(passwordField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Senha");
            fieldEmpty.add("Senha");
        }

        if(partnerPhoneField.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Celular");
            fieldEmpty.add("Celular");
        }

        if(accessLevel.getText().isEmpty()) {
            isEmpty = true;
            removeFieldEmpty("Nível de Acesso");
            fieldEmpty.add("Nível de Acesso");
        }
    }

    private void insertOnChange() {
        associationNameField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(associationNameField);
            } else {
                removeWarningClass(associationNameField);
            }
            isAltered = true;
        });

        associationCnpjFiled.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(associationCnpjFiled);
                isEmpty = true;
                fieldEmpty.add("CNPJ");
            } else {
                removeWarningClass(associationCnpjFiled);
                partner.setAssociation(DaoFactory.createAssociationDao().findByCnpj(associationCnpjFiled.getText()));

                if (partner.getAssociation() != null) {
                    associationNameField.setText(partner.getAssociation().getName());
                }

                isEmpty = false;
                removeFieldEmpty("CNPJ");
            }
            isAltered = true;
        });

        associationFundationDatePicker.valueProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.toString().isEmpty()) {
                setWarningClass(associationFundationDatePicker);
                isEmpty = true;
            } else {
                removeWarningClass(associationCnpjFiled);
                isEmpty = false;
            }
            isAltered = true;
        });

        associationAddressLocalityField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(associationAddressLocalityField);
                isEmpty = true;
            } else {
                removeWarningClass(associationAddressLocalityField);
                isEmpty = false;
            }
            isAltered = true;
        });

        associationAddressNumberField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(associationAddressNumberField);
                isEmpty = true;
            } else {
                removeWarningClass(associationAddressNumberField);
                try {
                    Integer.parseInt(newValue);
                    removeErrorClass(associationAddressNumberField);
                    isError = false;
                } catch (RuntimeException e) {
                    isError = true;
                    setErrorClass(associationAddressNumberField);
                }
            }
            isAltered = true;
        });

        associationAddressZoneField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(associationAddressZoneField);
            } else {
                removeWarningClass(associationAddressZoneField);
            }
            isAltered = true;
        });

        associationAddressCepField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(associationAddressCepField);
            } else {
                removeWarningClass(associationAddressCepField);
                try {
                    Integer.parseInt(newValue);
                    removeErrorClass(associationAddressCepField);
                    isError = false;
                } catch (RuntimeException e) {
                    setErrorClass(associationAddressCepField);
                    isError = true;
                }
            }
            isAltered = true;
        });

        associationAddressUfField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(associationAddressUfField);
            } else {
                removeWarningClass(associationAddressUfField);
            }
            isAltered = true;
        });

        associationAddressCityField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(associationAddressCityField);
            } else {
                removeWarningClass(associationAddressCityField);
            }
            isAltered = true;
        });

        communitiesField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(communitiesField);
            } else {
                removeWarningClass(communitiesField);
            }
            isAltered = true;
        });

        partnerNameField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerNameField);
                isEmpty = true;
                fieldEmpty.add("Nome");
            } else {
                removeWarningClass(partnerNameField);
                isEmpty = false;
                removeFieldEmpty("Nome");
            }
            isAltered = true;
        });

        partnerMotherNameField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerMotherNameField);
                isEmpty = true;
                fieldEmpty.add("Nome da Mãe");
            } else {
                removeWarningClass(partnerMotherNameField);
                isEmpty = false;
                removeFieldEmpty("Nome da Mãe");
            }
            isAltered = true;
        });

        partnerFatherNameField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerFatherNameField);
                isEmpty = true;
                fieldEmpty.add("Nome do Pai");
            } else {
                removeWarningClass(partnerFatherNameField);
                isEmpty = false;
                removeFieldEmpty("Nome do Pai");
            }
            isAltered = true;
        });

        partnerDateBirthPicker.valueProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.toString().isEmpty()) {
                setWarningClass(partnerDateBirthPicker);
                isEmpty = true;
                fieldEmpty.add("Data de Aniversário");
            } else {
                removeWarningClass(partnerDateBirthPicker);
                isEmpty = false;
                removeFieldEmpty("Data de Aniversário");
            }
            isAltered = true;
        });

        partnerRegidtrationDatePicker.valueProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.toString().isEmpty()) {
                setWarningClass(partnerRegidtrationDatePicker);
            } else {
                removeWarningClass(partnerRegidtrationDatePicker);
            }
            isAltered = true;
        });

        partnerMaritalStatusField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerMaritalStatusField);
                isEmpty = true;
                fieldEmpty.add("Estado Civil");
            } else {
                removeWarningClass(partnerMaritalStatusField);
                isEmpty = false;
                removeFieldEmpty("Estado Civil");
            }
            isAltered = true;
        });

        partnerCpfField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerCpfField);
                isEmpty = true;
                fieldEmpty.add("CPF");
            } else {
                removeWarningClass(partnerCpfField);
                isEmpty = false;
                removeFieldEmpty("CPF");

                if (newValue.length() != 11) {
                    setErrorClass(partnerCpfField);
                } else {
                    if (isNewPartner) {
                        Partner p = DaoFactory.createPartnerdao().findByCpf(newValue);
                        if (p != null) {
                            isError = true;
                            CreateAlert.createAlertError(
                                    "Sócio já cadastrado",
                                    "Já existe um sócio cadastrado no sistema",
                                    "Verifique o campo CPF",
                                    "dialog-pane"
                            ).showAndWait();
                        } else {
                            removeErrorClass(partnerCpfField);
                            isError = false;
                        }
                    } else {
                        Partner p = DaoFactory.createPartnerdao().findByCpf(newValue);
                        if (p != null && !partner.getCpf().equals(p.getCpf())) {
                            setErrorClass(partnerCpfField);
                            CreateAlert.createAlertError(
                                    "Sócio já cadastrado",
                                    "Já existe um sócio cadastrado no sistema",
                                    "Verifique o campo CPF",
                                    "dialog-pane"
                            ).showAndWait();
                        } else {
                            removeErrorClass(partnerCpfField);
                        }
                    }
                }
            }
            isAltered = true;
        });

        partnerSexField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerSexField);
                isEmpty = true;
                fieldEmpty.add("Sexo");
            } else {
                removeWarningClass(partnerSexField);
                isEmpty = false;
                removeFieldEmpty("Sexo");

                if (newValue.toUpperCase().charAt(0) == 'F' || newValue.toUpperCase().charAt(0) == 'M') {
                    removeErrorClass(partnerSexField);
                    isError = false;
                } else {
                    setErrorClass(partnerSexField);
                    isError = true;
                }
            }
            isAltered = true;
        });

        partnerNaturalnessField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerNaturalnessField);
                isEmpty = true;
                fieldEmpty.add("Naturalidade");
            } else {
                removeWarningClass(partnerNaturalnessField);
                isEmpty = false;
                removeFieldEmpty("Naturalidade");
            }
            isAltered = true;
        });

        partnerNationalityField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerNationalityField);
                isEmpty = true;
                fieldEmpty.add("Nacionalidade");
            } else {
                removeWarningClass(partnerNationalityField);
                isEmpty = false;
                removeFieldEmpty("Nacionalidade");
            }
            isAltered = true;
        });

        partnerSeriesField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerSeriesField);
                isEmpty = true;
                fieldEmpty.add("Série");
            } else {
                removeWarningClass(partnerSeriesField);
                isEmpty = false;
                removeFieldEmpty("Série");
            }
            isAltered = true;
        });

        partnerSchoolingField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerSchoolingField);
                isEmpty = true;
                fieldEmpty.add("Escolaridade");
            } else {
                removeWarningClass(partnerSchoolingField);
                isEmpty = false;
                removeFieldEmpty("Escolaridade");
            }
            isAltered = true;
        });

        partnerProfessionField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerProfessionField);
                isEmpty = true;
                fieldEmpty.add("Profissão");
            } else {
                removeWarningClass(partnerProfessionField);
                isEmpty = false;
                removeFieldEmpty("Profissão");
            }
            isAltered = true;
        });

        partnerRgField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerRgField);
                isEmpty = true;
                fieldEmpty.add("RG");
            } else {
                removeWarningClass(partnerRgField);
                isEmpty = false;
                removeFieldEmpty("RG");
            }
            isAltered = true;
        });

        partnerVoterRegistrationField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerVoterRegistrationField);
                isEmpty = true;
                fieldEmpty.add("Título de Eleitor");
            } else {
                removeWarningClass(partnerVoterRegistrationField);
                isEmpty = false;
                removeFieldEmpty("Título de Eleitor");
            }
            isAltered = true;
        });

        partnerCardWorkField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerCardWorkField);
                isEmpty = true;
                fieldEmpty.add("CTPS");
            } else {
                removeWarningClass(partnerCardWorkField);
                isEmpty = false;
                removeFieldEmpty("CTPS");

                try {
                    Integer.parseInt(newValue);
                    removeErrorClass(partnerCardWorkField);
                    isError = false;
                } catch (RuntimeException e) {
                    setErrorClass(partnerCardWorkField);
                    isError = true;
                }
            }
            isAltered = true;
        });

        partnerLandTenureField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerLandTenureField);
                isEmpty = true;
                fieldEmpty.add("Posse de Terra");
            } else {
                removeWarningClass(partnerLandTenureField);
                isEmpty = false;
                removeFieldEmpty("Posse de Terra");
            }
            isAltered = true;
        });

        outherAssociationField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
//                setWarningClass(outherAssociationField);
//                isEmpty = true;
//                fieldEmpty.add("Outras Associações");
            } else {
//                removeWarningClass(outherAssociationField);
//                isEmpty = false;
//                removeFieldEmpty("Outras Associações");
            }
            isAltered = true;
        });

        partnerSyndicateField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerSyndicateField);
//                isEmpty = true;
//                fieldEmpty.add("Sindicato");
            } else {
                removeWarningClass(partnerSyndicateField);
//                isEmpty = false;
//                removeFieldEmpty("Sindicato");
            }
            isAltered = true;
        });

        partnerLocalityField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerLocalityField);
                isEmpty = true;
                fieldEmpty.add("[ Endereço Residencial ] - Localidade");
            } else {
                removeWarningClass(partnerLocalityField);
                isEmpty = false;
                removeFieldEmpty("[ Endereço Residencial ] - Localidade");
            }
            isAltered = true;
        });

        partnerCityField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerCityField);
                isEmpty = true;
                fieldEmpty.add("[ Endereço Residencial ] - Município");
            } else {
                removeWarningClass(partnerCityField);
                isEmpty = false;
                removeFieldEmpty("[ Endereço Residencial ] - Município");
            }
            isAltered = true;
        });

        partnerUfField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerUfField);
                isEmpty = true;
                fieldEmpty.add("[ Endereço Residencial ] - UF");
            } else {
                removeWarningClass(partnerUfField);
                isEmpty = false;
                removeFieldEmpty("[ Endereço Residencial ] - UF");
            }
            isAltered = true;
        });

        partnerCepField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerCepField);
                isEmpty = true;
                fieldEmpty.add("[ Endereço Residencial ] - CEP");
            } else {
                removeWarningClass(partnerCepField);
                isEmpty = false;
                removeFieldEmpty("[ Endereço Residencial ] - CEP");
                try {
                    Integer.parseInt(newValue);
                    removeErrorClass(partnerCepField);
                    isError = false;
                } catch (RuntimeException e) {
                    setErrorClass(partnerCepField);
                    isError = true;
                }
            }
            isAltered = true;
        });

        partnerActivityAddressLocalityField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerActivityAddressLocalityField);
                isEmpty = true;
                fieldEmpty.add("[ Endereço de Atividade ] - Localidade");
            } else {
                removeWarningClass(partnerActivityAddressLocalityField);
                isEmpty = false;
                removeFieldEmpty("[ Endereço de Atividade ] - Localidade");
            }
            isAltered = true;
        });

        partnerActivityAddressCityField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerActivityAddressCityField);
                isEmpty = true;
                fieldEmpty.add("[ Endereço de Atividade ] - Município");
            } else {
                removeWarningClass(partnerActivityAddressCityField);
                isEmpty = false;
                removeFieldEmpty("[ Endereço de Atividade ] - Município");
            }
            isAltered = true;
        });

        partnerActivityAddressUfField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerActivityAddressUfField);
                isEmpty = true;
                fieldEmpty.add("[ Endereço de Atividade ] - UF");
            } else {
                removeWarningClass(partnerActivityAddressUfField);
                isEmpty = false;
                removeFieldEmpty("[ Endereço de Atividade ] - UF");
            }
            isAltered = true;
        });

        partnerActivityAddressCepField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerActivityAddressCepField);
                isEmpty = true;
                fieldEmpty.add("[ Endereço de Atividade ] - CEP");
            } else {
                removeWarningClass(partnerActivityAddressCepField);
                isEmpty = false;
                removeFieldEmpty("[ Endereço de Atividade ] - CEP");

                try {
                    Integer.parseInt(newValue);
                    removeErrorClass(partnerActivityAddressCepField);
                    isError = false;
                } catch (RuntimeException e) {
                    setErrorClass(partnerActivityAddressCepField);
                    isError = true;
                }
            }
            isAltered = true;
        });

        partnerLandAreaField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerLandAreaField);
                isEmpty = true;
                fieldEmpty.add("[ Endereço de Atividade ] - Área -HA");
            } else {
                removeWarningClass(partnerLandAreaField);
                isEmpty = false;
                removeFieldEmpty("[ Endereço de Atividade ] - Área -HA");

                try {
                    Integer.parseInt(partnerLandAreaField.getText());
                    removeErrorClass(partnerLandAreaField);
                    isError = false;
                } catch (RuntimeException e) {
                    setErrorClass(partnerLandAreaField);
                    isError = true;
                }
            }
            isAltered = true;
        });

        partnerSpouseNameField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerSpouseNameField);
                isEmpty = true;
                fieldEmpty.add("[ Dados do Cônjuge ] - Nome");
            } else {
                removeWarningClass(partnerSpouseNameField);
                isEmpty = false;
                removeFieldEmpty("[ Dados do Cônjuge ] - Nome");
            }
            isAltered = true;
        });

        partnerSpouseCpfField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerSpouseCpfField);
                isEmpty = true;
                fieldEmpty.add("[ Dados do Cônjuge ] - CPF");
            } else {
                removeWarningClass(partnerSpouseCpfField);
                isEmpty = false;
                removeFieldEmpty("[ Dados do Cônjuge ] - CPF");

                if (newValue.length() != 11) {
                    setErrorClass(partnerSpouseCpfField);
                } else {
                    removeErrorClass(partnerSpouseCpfField);

                    partner.setSpouse(DaoFactory.createPartnerdao().findByCpf(partnerSpouseCpfField.getText()));
                }
            }
            isAltered = true;
        });

        partnerChildrenNumberField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerChildrenNumberField);
                isEmpty = true;
                fieldEmpty.add("N° Filhos");
            } else {
                removeWarningClass(partnerChildrenNumberField);
                isEmpty = false;
                removeFieldEmpty("N° Filhos");

                try {
                    Integer.parseInt(partnerChildrenNumberField.getText());
                    removeErrorClass(partnerChildrenNumberField);
                    isError = false;
                } catch (RuntimeException e) {
                    setErrorClass(partnerChildrenNumberField);
                    isError = true;
                }
            }
            isAltered = true;
        });

        partnerChildrenNameField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(partnerChildrenNameField);
                isEmpty = true;
                fieldEmpty.add("Nome dos filhos");
            } else {
                removeWarningClass(partnerChildrenNameField);
                isEmpty = false;
                removeFieldEmpty("Nome dos filhod");
            }
            isAltered = true;
        });

        partnerPhoneField.textProperty().addListener((obsValue, value, newValue) ->{
            if (newValue.isEmpty()) {
                setWarningClass(partnerPhoneField);
                isEmpty = true;
                fieldEmpty.add("Celular");
            } else {
                removeWarningClass(partnerPhoneField);
                isEmpty = false;
                removeFieldEmpty("Celular");
            }
            isAltered = true;
        });

        usernameField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(usernameField);
                isEmpty = true;
                fieldEmpty.add("Nome do Usuário");
            } else {
                removeWarningClass(usernameField);
                isEmpty = false;
                removeFieldEmpty("Nome do Usuário");
            }
            isAltered = true;
        });

        passwordField.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(passwordField);
                isEmpty = true;
                fieldEmpty.add("Senha");
            } else {
                removeWarningClass(passwordField);
                isEmpty = false;
                removeFieldEmpty("Senha");
            }
            isAltered = true;
        });

        accessLevel.textProperty().addListener((obsValue, value, newValue) -> {
            if (newValue.isEmpty()) {
                setWarningClass(accessLevel);
                isEmpty = true;
                fieldEmpty.add("Nível de Acesso");
            } else {
                removeWarningClass(accessLevel);
                isEmpty = false;
                removeFieldEmpty("Nível de Acesso");

                try {
                    Integer.parseInt(accessLevel.getText());
                    isError = false;
                    removeErrorClass(accessLevel);
                } catch (RuntimeException e) {
                    isError = true;
                    setErrorClass(accessLevel);
                }
            }
            isAltered = true;
        });
    }

    private void insertInfo() {
        // se não for um novo usuário
        if (partner.getId() != null) {
            idLabel.setText(idLabel.getText() + " " + partner.getId().toString());

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

            partnerMotherNameField.setText(partner.getMotherName());

            partnerFatherNameField.setText(partner.getFatherName());

            LocalDate dateBirth = partner.getDateBirth();
            partnerDateBirthPicker.setValue(LocalDate.of(dateBirth.getYear(), dateBirth.getMonth(), dateBirth.getDayOfMonth()));

            LocalDate registrationDate = partner.getRegistrationDate();
            partnerRegidtrationDatePicker.setValue(
                    LocalDate.of(registrationDate.getYear(), registrationDate.getMonth(), registrationDate.getDayOfMonth())
            );
            datePickerDisableClick(partnerRegidtrationDatePicker);

            partnerMaritalStatusField.setText(partner.getMaritalStatus());

            partnerCpfField.setText(partner.getCpf());

            partnerSexField.setText(partner.getSex() + ""); // + "" o retorno de getSex vira uma String

            partnerNationalityField.setText(partner.getNationality());

            partnerNaturalnessField.setText(partner.getNaturalness());

            partnerSeriesField.setText(partner.getSeries());

            partnerSchoolingField.setText(partner.getSchooling());

            partnerProfessionField.setText(partner.getProfession());

            partnerRgField.setText(partner.getRg());

            partnerVoterRegistrationField.setText(partner.getVoterRegistration());

            partnerCardWorkField.setText(partner.getWorkCardNumber().toString());

            partnerLandTenureField.setText(partner.getLandTenure());

            partnerSyndicateField.setText(partner.getSyndicate());

            partnerLocalityField.setText(partner.getAddress().getLocality());

            partnerCityField.setText(partner.getAddress().getCity());

            partnerUfField.setText(partner.getAddress().getState());

            partnerCepField.setText(partner.getAddress().getCepNumber().toString());

            partnerActivityAddressLocalityField.setText(partner.getActivityAddress().getLocality());

            partnerActivityAddressCityField.setText(partner.getActivityAddress().getCity());

            partnerActivityAddressUfField.setText(partner.getActivityAddress().getState());

            partnerActivityAddressCepField.setText(partner.getActivityAddress().getCepNumber().toString());

            partnerLandAreaField.setText(partner.getLandArea());

            // Caso o Conjugê seja um sócio
            if (partner.getSpouse() != null) {
                partnerSpouseNameField.setText(partner.getSpouse().getName());
                partnerSpouseCpfField.setText(partner.getSpouse().getCpf());
            } else {
                if (partner.getSpouseName() == null) {
                    partnerSpouseNameField.setText("");
                } else {
                    partnerSpouseNameField.setText(partner.getSpouseName());
                }

                if (partner.getSpouseCpf() == null) {
                    partnerSpouseCpfField.setText("");
                } else {
                    partnerSpouseCpfField.setText(partner.getSpouseCpf());
                }
            }

            if (partner.getOuthersAssociations() != null) {
                outherAssociationField.setText(partner.getOuthersAssociations());
            }

            partnerChildrenNumberField.setText(partner.getQuantifyChildren().toString());

            partnerChildrenNameField.setText(partner.getChildrensName());

            usernameField.setText(partner.getUsername());

            passwordField.setText(partner.getPassword());

            partnerPhoneField.setText(partner.getNumberPhone());

            accessLevel.setText(Integer.toString(partner.getAccessLevel()));
        }
    }

    public void saveInfo() {
        if (isAltered) {
            isAllFieldsNotEmpty();
            if (!isError && !isEmpty) {
                /**
                 * Dados da Associação
                 */

                if (isNewPartner) {
                    if (partner.getAssociation() == null) {
                        isError = true;
                        CreateAlert.createAlertInformation(
                                "Não encontrado!",
                                "Associação não encontrada!\nRevise o campo CNPJ!",
                                "dialog-pane").showAndWait();
                        mainApp.getHomeController().getSecretarialMenuController().handleNewPartner();
                    } else {
                        isError = false;
                    }
                } else {
                    partner.getAssociation().setName(associationNameField.getText());
                    partner.getAssociation().setCnpj(associationCnpjFiled.getText());
                    partner.getAssociation().setFundationDate(associationFundationDatePicker.getValue());
                    partner.getAssociation().getAddress().setLocality(associationAddressLocalityField.getText());

                    int addressNumber = Integer.parseInt(associationAddressNumberField.getText());
                    partner.getAssociation().getAddress().setNumber(addressNumber);

                    partner.getAssociation().getAddress().setZone(associationAddressZoneField.getText());

                    int cepNumber = Integer.parseInt(associationAddressCepField.getText());
                    partner.getAssociation().getAddress().setCepNumber(cepNumber);

                    partner.getAssociation().getAddress().setState(associationAddressUfField.getText());
                    partner.getAssociation().getAddress().setCity(associationAddressCityField.getText());
                    partner.getAssociation().setCommunities(communitiesField.getText());
                }


                /**
                 * Dados do sócio
                 */

                partner.setName(partnerNameField.getText());
                partner.setMotherName(partnerMotherNameField.getText());
                partner.setFatherName(partnerFatherNameField.getText());
                partner.setDateBirth(partnerDateBirthPicker.getValue());

                if (isNewPartner) {
                    partner.setRegistrationDate(LocalDate.now());
                } else {
                    partner.setRegistrationDate(partnerRegidtrationDatePicker.getValue());
                }

                partner.setMaritalStatus(partnerMaritalStatusField.getText());
                partner.setCpf(partnerCpfField.getText());

                if (partnerSexField.getText().isEmpty()) {
                    partner.setSex(' ');
                } else {
                    partner.setSex(partnerSexField.getText().toUpperCase().charAt(0));
                }

                partner.setNaturalness(partnerNaturalnessField.getText());
                partner.setNationality(partnerNationalityField.getText());
                partner.setSeries(partnerSeriesField.getText());
                partner.setSchooling(partnerSchoolingField.getText());
                partner.setProfession(partnerProfessionField.getText());
                partner.setRg(partnerRgField.getText());
                partner.setVoterRegistration(partnerVoterRegistrationField.getText());

                int workCardNumber = Integer.parseInt(partnerCardWorkField.getText());
                partner.setWorkCardNumber(workCardNumber);

                partner.setLandTenure(partnerLandTenureField.getText());
                partner.setOuthersAssociations(outherAssociationField.getText());
                partner.setSyndicate(partnerSyndicateField.getText());

                partner.setChildrensName(partnerChildrenNameField.getText());

                int childrenNumber = Integer.parseInt(partnerChildrenNumberField.getText());
                partner.setQuantifyChildren(childrenNumber);

                partner.setUsername(usernameField.getText());
                partner.setPassword(passwordField.getText());
                partner.setAccessLevel(Integer.parseInt(accessLevel.getText()));

                partner.setNumberPhone(partnerPhoneField.getText());

                /**
                 * Endereço Residencial
                 */

                Address address = new Address();
                address.setLocality(partnerLocalityField.getText());
                address.setCity(partnerCityField.getText());
                address.setState(partnerUfField.getText());
                address.setZone("");

                int addressCepNumber = Integer.parseInt(partnerCepField.getText());
                address.setCepNumber(addressCepNumber);

                partner.setAddress(address);

                /**
                 * Endereço de Atividade
                 */

                Address activityAddress = new Address();
                activityAddress.setLocality(partnerActivityAddressLocalityField.getText());
                activityAddress.setCity(partnerActivityAddressCityField.getText());
                activityAddress.setState(partnerActivityAddressUfField.getText());
                activityAddress.setZone("");

                if (partnerActivityAddressCepField.getText().isEmpty()) {
                    activityAddress.setCepNumber(0);
                } else {
                    int activityAddressCepNumber = Integer.parseInt(partnerActivityAddressCepField.getText());
                    activityAddress.setCepNumber(activityAddressCepNumber);
                }

                partner.setActivityAddress(activityAddress);


                partner.setLandArea(partnerLandAreaField.getText());

                /**
                 * Informações do Cônjuge
                 */

                if (partner.getSpouse() == null) {
                    partner.setSpouseName(partnerSpouseNameField.getText());
                    partner.setSpouseCpf(partnerSpouseCpfField.getText());
                }

                if (partner.getImage() == null) {
                    try {
                        partner.setImage(
                            new FileInputStream(
                        "C:\\Users\\josei\\IdeaProjects\\Gestor de Comunidades Agricolas\\src\\br\\com\\" +
                                "gestorCA\\illustrations\\undraw_male_avatar_323b.jpg"
                        ));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                if (isNewPartner) {
                    DaoFactory.createPartnerdao().insert(partner);
                } else {
                    DaoFactory.createPartnerdao().update(partner);
                }

                isAltered = false;

//                mainApp.getHomeController().handleHome();

                CreateAlert.createAlertInformation(
                        "Sucesso",
                        "Informações salvas com sucesso!",
                        "dialog-pane")
                        .showAndWait();
            } else {
                if (isError) {
                    CreateAlert.createAlertError(
                            "Imformações não salvas",
                            "Existe dados inválidos",
                            "Verifique os inputs em vermelho",
                            "dialog-pane"
                    ).showAndWait();
                }

                if (isEmpty) {
                    String fields = "";

                    for (String field : fieldEmpty) {
                        fields += field + "\n";
                    }

                    CreateAlert.createAlertError(
                            "Imformações não salvas",
                            "Os seguintes campos estão fazios:",
                            fields,
                            "dialog-pane"
                    ).showAndWait();
//                    fieldEmpty.clear();
                }
            }
        } else {
            mainApp.getHomeController().handleHome();
        }
    }

    @FXML
    private void handleCancel() {
        if (!isAltered) {
            mainApp.getHomeController().handleHome();
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
            mainApp.getHomeController().handleHome();
        }
    }

    @FXML
    private void handleSave() {
        saveInfo();

        if (!isNewPartner) {
            mainApp.getHomeController().showInfoPartner();
            mainApp.getHomeController().getInfoPartnerController().setPartner(partner);
            mainApp.getHomeController().getInfoPartnerController().setMainApp(mainApp);
        } else {
            if(!isEmpty) {
                isNewPartner = false;
                mainApp.getHomeController().getSecretarialMenuController().handleNewPartner();
            }
        }
    }

    private void datePickerDisableClick(DatePicker datePicker) {
        datePicker.setOnMouseClicked(e -> {
            if (!datePicker.isEditable()) {
                datePicker.hide();
            }
        });
    }

    public void isNewPartner() {
        isNewPartner = true;

        associationCnpjFiled.setEditable(true);
        associationCnpjFiled.getStyleClass().remove("text-field-no-editable");

        accessLevel.setEditable(true);
        accessLevel.getStyleClass().remove("text-field-no-editable");

        datePickerDisableClick(associationFundationDatePicker);
    }
}
