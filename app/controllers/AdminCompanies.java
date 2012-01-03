package controllers;

import models.Company;
import play.mvc.With;

@With(Secure.class)
@CRUD.For(Company.class)
public class AdminCompanies extends CRUD {
}
