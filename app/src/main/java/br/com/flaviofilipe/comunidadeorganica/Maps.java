package br.com.flaviofilipe.comunidadeorganica;

public class Maps {

    private String name;
    private String description;
    private String endereco;
    private String tel;
    private String email;
    private Double latitude;
    private Double longitude;

    public Maps() {
    }

    public Maps(String name, String description, String endereco, String tel, String email, Double latitude, Double longitude) {
        this.name = name;
        this.description = description;
        this.endereco = endereco;
        this.tel = tel;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
