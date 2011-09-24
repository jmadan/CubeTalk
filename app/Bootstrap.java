import models.Article;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job{

    public void doJob() {
        // Check if the database is empty
        if(Article.count() == 0) {
//            Fixtures.loadModels("initial-data.yml");
            System.out.println("U Got No Data!");
        }
    }


}
