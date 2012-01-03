package controllers;

import models.Category;
import play.mvc.With;

@With(Secure.class)
@CRUD.For(Category.class)
public class AdminCategories extends CRUD {
}
