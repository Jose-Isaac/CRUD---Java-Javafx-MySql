package br.com.gestorCA.model.entities;

import java.time.LocalDate;

public class Minute {
    private Integer id;
    private Integer PartnerId;
    private Integer AssociationId;
    private String text;
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(Integer partnerId) {
        PartnerId = partnerId;
    }

    public Integer getAssociationId() {
        return AssociationId;
    }

    public void setAssociationId(Integer associationId) {
        AssociationId = associationId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
