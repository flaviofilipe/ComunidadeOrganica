package br.com.flaviofilipe.comunidadeorganica;

public class PostsModel {
    private final String mTitle;
    private final String mDescription;
    private final String mDate;
    private int mAge;

    public PostsModel(String title, String description, String date) {
        mTitle = title;
        mDescription = description;
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }


    public String getDate() {
        return mDate;
    }


}
