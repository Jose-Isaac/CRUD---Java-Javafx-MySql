package br.com.gestorCA.model.entities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;

public class Partner extends Person{
    private Address address;
    private Address activityAddress;
    private Association association;
    private LocalDate registrationDate;
    private String numberPhone;
    private Integer id;
    private String landTenure;
    private String landArea;
    private String syndicate;
    private String username;
    private String password;
    private String outhersAssociations;
    private int accessLevel;
    private Partner spouse;
    private String spouseName;
    private String spouseCpf;
    private InputStream image;
    private long imageLength;

    public Partner() {};

    public Partner(Address address, Association association, LocalDate registrationDate,
            String numberPhone, String username, String password, int accessLevel)
    {
        this.address = address;
        this.association = association;
        this.registrationDate = registrationDate;
        this.numberPhone = numberPhone;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public Partner( String name, String fatherName, String motherName, String nationality, String maritalStatus,
            String naturalness, String cpf, String rg, Integer workCardNumber, LocalDate dateBirth,
            Address address, Association association, LocalDate registrationDate, String numberPhone,
            String username, String password, int accessLevel, Character sex, String series, String schooling,
            String profession, String landTenure, String syndicate, Address activityAddress, String landArea)
    {
        super(name, fatherName, motherName, nationality, maritalStatus,
                naturalness, cpf, rg, workCardNumber, dateBirth, sex, series, schooling, profession);
        this.address = address;
        this.association = association;
        this.registrationDate = registrationDate;
        this.numberPhone = numberPhone;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.landTenure = landTenure;
        this.syndicate = syndicate;
        this.activityAddress = activityAddress;
        this.landArea = landArea;
    }

    public Partner(String name, String fatherName, String motherName, String nationality, String maritalStatus,
                   String naturalness, String cpf, String  rg, Integer workCardNumber,
                   Integer quantifyChildren, String voterRegistration, String childrensName,
                   LocalDate dateBirth, Address address, Association association, LocalDate registrationDate, String numberPhone,
                   String username, String password, int accessLevel, Character sex, String series, String schooling,
                   String profession, String landTenure, String syndicate, Address activityAddress, String landArea)

    {
        super(name, fatherName, motherName, nationality, maritalStatus, naturalness, cpf, rg,
                workCardNumber, quantifyChildren, voterRegistration, childrensName, dateBirth, sex, series, schooling,
                profession);
        this.address = address;
        this.association = association;
        this.registrationDate = registrationDate;
        this.numberPhone = numberPhone;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.landTenure = landTenure;
        this.syndicate = syndicate;
        this.activityAddress = activityAddress;
        this.landArea = landArea;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOuthersAssociations() {
        return outhersAssociations;
    }

    public void setOuthersAssociations(String outhersAssociations) {
        this.outhersAssociations = outhersAssociations;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getLandTenure() {
        return landTenure;
    }

    public void setLandTenure(String landTenure) {
        this.landTenure = landTenure;
    }

    public String getSyndicate() {
        return syndicate;
    }

    public void setSyndicate(String syndicate) {
        this.syndicate = syndicate;
    }

    public Address getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(Address activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public Partner getSpouse() {
        return spouse;
    }

    public void setSpouse(Partner spouse) {
        this.spouse = spouse;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpouseCpf() {
        return spouseCpf;
    }

    public void setSpouseCpf(String spouseCpf) {
        this.spouseCpf = spouseCpf;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public long getImageLength() {
        return imageLength;
    }

    public void setImageLength(long imageLength) {
        this.imageLength = imageLength;
    }
}
