package models;

import javax.persistence.*;

@Entity
@Table(schema = "app_schema",  name="quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quote_id")
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "key_word")
    private String keyWord;


    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

}
