package br.com.gestorCA.model.entities;

import br.com.gestorCA.model.dao.DaoFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class MinuteProperty {
    private StringProperty secretarialName = new SimpleStringProperty();
    private StringProperty date = new SimpleStringProperty();

    public static ObservableList<MinuteProperty> getAllMinuteProperty(int id) {
        List<Minute> all = DaoFactory.createMinuteDao().findByAssociation(id);

        ObservableList<MinuteProperty> allMinute = FXCollections.observableArrayList();
        for (Minute minute : all) {
            MinuteProperty minuteProperty = new MinuteProperty();

            Partner partner = DaoFactory.createPartnerdao().findById(minute.getPartnerId());

            minuteProperty.setSecretarialName(partner.getName());

            minuteProperty.setDate(minute.getDate().toString());

            allMinute.add(minuteProperty);
        }

        return allMinute;
    }

    public String getSecretarialName() {
        return secretarialName.get();
    }

    public StringProperty secretarialNameProperty() {
        return secretarialName;
    }

    public void setSecretarialName(String secretarialName) {
        this.secretarialName.set(secretarialName);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
