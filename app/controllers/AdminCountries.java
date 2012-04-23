package controllers;

import models.Article;
import models.Country;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
@CRUD.For(Country.class)
public class AdminCountries extends CRUD {

}
