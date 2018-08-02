package br.com.flaviofilipe.comunidadeorganica;

public class PostsModel {
    private String title;
    private String description;
    private String date;
    private String link;

    public PostsModel(){}

    public PostsModel(String title, String description, String date, String link) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.link = link;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
