package br.com.gestorCA.model.entities;

import br.com.gestorCA.model.dao.DaoFactory;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Association {
    private int id;
    private String cnpj;
    private String communities;
    private String name;
    private Address address;
    private LocalDate fundationDate;

    public Association() {};

    public Association(String cnpj, String name, Address address, LocalDate fundationDate, String communities) {
        this.cnpj = cnpj;
        this.name = name;
        this.address = address;
        this.fundationDate = fundationDate;
        this.communities = communities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getFundationDate() {
        return fundationDate;
    }

    public void setFundationDate(LocalDate fundationDate) {
        this.fundationDate = fundationDate;
    }

    public String getCommunities() {
        return communities;
    }

    public void setCommunities(String communities) {
        this.communities = communities;
    }
}
