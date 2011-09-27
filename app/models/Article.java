package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Article extends Model {

    @Required
    public String title;

    @Required
    public Boolean approved = true;

    public int article_view;

    @Required
    @Lob
    public String content;

    @Required
    @ManyToOne
    public User author;

    @Required
    @ManyToOne
    public Category category;

    @OneToMany(mappedBy="article", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    public List<Comment> comments;

    public Date submit_date;

    public Article(String title, String content, User author, Category category, Boolean approved){
        this.title = title;
        this.content = sanitizeContent(content);
        this.author = author;
        this.category = category;
        this.submit_date = new Date();
        this.approved = approved;
        this.comments = new ArrayList<Comment>();
        this.article_view = 0;
    }

    private String sanitizeContent(String content) {
        if(content != null){
            content.replace("[\']", "&rsquo;");
            content.replace("'","&rsquo;");

        }
        return content;
    }

    public Article addComment(Long articleId, User author, String content) {
        Article article = Article.findById(articleId);
        Comment newComment = new Comment(article, content, author.userAlias, author.userEmail).save();
        this.comments.add(newComment);
        this.save();
        return this;
    }

}
