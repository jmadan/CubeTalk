package controllers;

import models.CubeReview;
import play.mvc.With;

@With(Secure.class)
@CRUD.For(CubeReview.class)
public class AdminCubeReviews {
}
