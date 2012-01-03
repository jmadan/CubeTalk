package controllers;

import models.IndustryType;
import play.mvc.With;

@With(Secure.class)
@CRUD.For(IndustryType.class)
public class AdminIndustryTypes extends CRUD {
}
