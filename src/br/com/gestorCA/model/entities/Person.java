package br.com.gestorCA.model.entities;

import java.time.LocalDate;
import java.util.Date;

public class Person {
    private String name;
    private String fatherName;
    private String motherName;
    private String nationality;
    private String maritalStatus;
    private String naturalness;
    private String cpf;
    private String rg;
    private Integer workCardNumber;
    private Integer quantifyChildren;
    private String voterRegistration;
    private String childrensName;
    private LocalDate dateBirth;
    private Character sex;
    private String series;
    private String schooling;
    private String profession;

    public Person() {}

    public Person(
            String name, String fatherName, String motherName, String nationality, String maritalStatus,
            String naturalness, String cpf, String rg, Integer workCardNumber, LocalDate dateBirth, Character sex,
            String series, String schooling, String profession)
    {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.naturalness = naturalness;
        this.cpf = cpf;
        this.rg = rg;
        this.workCardNumber = workCardNumber;
        this.dateBirth = dateBirth;
        this.sex = sex;
        this.series = series;
        this.schooling = schooling;
        this.profession = profession;
    }

    public Person(
            String name, String fatherName, String motherName, String nationality, String maritalStatus,
            String naturalness, String cpf, String rg, Integer workCardNumber, Integer quantifyChildren,
            String voterRegistration, String childrensName, LocalDate dateBirth, Character sex, String series,
            String schooling, String profession)
    {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.naturalness = naturalness;
        this.cpf = cpf;
        this.rg = rg;
        this.workCardNumber = workCardNumber;
        this.quantifyChildren = quantifyChildren;
        this.voterRegistration = voterRegistration;
        this.childrensName = childrensName;
        this.dateBirth = dateBirth;
        this.sex = sex;
        this.series = series;
        this.schooling = schooling;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNaturalness() {
        return naturalness;
    }

    public void setNaturalness(String naturalness) {
        this.naturalness = naturalness;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Integer getWorkCardNumber() {
        return workCardNumber;
    }

    public void setWorkCardNumber(Integer workCardNumber) {
        this.workCardNumber = workCardNumber;
    }

    public Integer getQuantifyChildren() {
        return quantifyChildren;
    }

    public void setQuantifyChildren(Integer quantifyChildren) {
        this.quantifyChildren = quantifyChildren;
    }

    public String getVoterRegistration() {
        return voterRegistration;
    }

    public void setVoterRegistration(String voterRegistration) {
        this.voterRegistration = voterRegistration;
    }

    public String getChildrensName() {

        return childrensName;
    }

    public void setChildrensName(String childrenName) {
        this.childrensName = childrenName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSchooling() {
        return schooling;
    }

    public void setSchooling(String schooling) {
        this.schooling = schooling;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
