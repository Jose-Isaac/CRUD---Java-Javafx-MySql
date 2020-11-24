package br.com.gestorCA.model.entities;

public class Address {
    private String city;
    private String state;
    private String zone;
    private String locality;
    private String complement;
    private Integer cepNumber;
    private Integer number;

    public Address() {};

    public Address(String city, String state, String zone, String locality, Integer cepNumber, Integer number)
    {
        this.city = city;
        this.state = state;
        this.zone = zone;
        this.locality = locality;
        this.cepNumber = cepNumber;
        this.number = number;
    }

    public Address(String city, String state, String zone, String locality, String complement, Integer cepNumber,
                   Integer number)
    {
        this.city = city;
        this.state = state;
        this.zone = zone;
        this.locality = locality;
        this.complement = complement;
        this.cepNumber = cepNumber;
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Integer getCepNumber() {
        return cepNumber;
    }

    public void setCepNumber(Integer cepNumber) {
        this.cepNumber = cepNumber;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
