package controllers;

import models.Article;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
@CRUD.For(Article.class)
public class AdminArticles extends CRUD {

}
