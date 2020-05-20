package models;

import java.util.List;

public class QuoteDTO {

    private String quote;
    private String author;
    private Integer likes;
    private List<String> tags = null;
    private Integer pk;
    private Object image;
    private String language;

    public QuoteDTO() {
    }

    public QuoteDTO(String quote, String author, Integer likes, List<String> tags, Integer pk, Object image, String language) {
        this.quote = quote;
        this.author = author;
        this.likes = likes;
        this.tags = tags;
        this.pk = pk;
        this.image = image;
        this.language = language;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}
