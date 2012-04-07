package controllers;

import models.CubeQuestion;
import play.mvc.With;

@With(Secure.class)
@CRUD.For(CubeQuestion.class)
public class AdminCubeQuestions extends CRUD {
}
