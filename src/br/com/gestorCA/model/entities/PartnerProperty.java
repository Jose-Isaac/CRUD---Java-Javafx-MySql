package br.com.gestorCA.model.entities;

import br.com.gestorCA.model.dao.DaoFactory;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.List;

public class PartnerProperty {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private ObjectProperty<File> image = new SimpleObjectProperty<>();

    public static ObservableList<PartnerProperty> getAllPartnerProperty(int id) {
        List<Partner> all = DaoFactory.createPartnerdao().findByAssociation(id);

        ObservableList<PartnerProperty> allPartner = FXCollections.observableArrayList();
        for (Partner partner : all) {
            PartnerProperty partnerProperty = new PartnerProperty();

            partnerProperty.setName(partner.getName());
            partnerProperty.setPhone(partner.getNumberPhone());

            allPartner.add(partnerProperty);
        }

        return allPartner;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public File getImage() {
        return image.get();
    }

    public ObjectProperty<File> imageProperty() {
        return image;
    }

    public void setImage(File image) {
        this.image.set(image);
    }
}
