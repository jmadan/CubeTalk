package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Iterator;
import java.util.List;

@Entity
public class CubeRating extends Model {

    @ManyToOne
    public AnonymousUser anonymousUser;

    @ManyToOne
    public Company company;

    public Integer questionID;
    public Integer rating;

    public CubeRating(AnonymousUser anonymousUser, Company company, Integer questionID, Integer rating){
        this.anonymousUser = anonymousUser;
        this.company = company;
        this.questionID = questionID;
        this.rating = rating;
    }

    public static String getGraphData(List<CubeRating> cubeRatings) {
        Integer verySatisfied=0;
        Integer satisfied=0;
        Integer neutral=0;
        Integer disatisfied=0;
        Integer veryDisatisfied=0;
        String graphData=null;
        Iterator<CubeRating> iterator = cubeRatings.iterator();
        while(iterator.hasNext()){
            CubeRating cubeRating = (CubeRating)iterator.next();
            switch (cubeRating.rating.intValue()){
                case 1:
                    veryDisatisfied += cubeRating.rating;
                    break;
                case 2:
                    disatisfied += cubeRating.rating;
                    break;
                case 3:
                    neutral += cubeRating.rating;
                    break;
                case 4:
                    satisfied += cubeRating.rating;
                    break;
                case 5:
                    verySatisfied += cubeRating.rating;
                    break;
            }
        }
        graphData = veryDisatisfied.toString()+","+disatisfied.toString()+","+neutral.toString()+","+satisfied.toString()+","+verySatisfied.toString();
        return graphData;
    }
}
