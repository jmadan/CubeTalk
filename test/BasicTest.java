import models.*;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

import java.util.List;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }

    @Test
    public void createAndRetrieveUser(){
        new User("Jasdeep","Madan","JD","jasdeepm@gmail.com","password").save();

        User jd = User.find("byUserEmail","jasdeepm@gmail.com").first();

        assertNotNull(jd);
        assertEquals("Jasdeep", jd.firstName);
    }

    @Test
    public void tryConnectAsUser(){
        new User("Ramnik","Kaur","RK","ramneek25@gmail.com","password").save();

        assertNotNull(User.connect("ramneek25@gmail.com","password"));
        assertNull(User.connect("ramneek251@gmail.com", "password"));
        assertNull(User.connect("ramneek25@gmail.com","badpassword"));
    }

    @Test
    public void createAndRetrieveCategory() {
        new Category("Tech", "some description").save();

        Category category = Category.find("byCategory", "Tech").first();
        assertNotNull(category);
        assertEquals("Tech", category.category);
    }

    @Test
    public void createAndRetrieveCompany(){
        IndustryType industryType = new IndustryType("IT","Information Technology").save();

        new Company("TW", "www.thoughtworks.com", "decent company", industryType, true).save();

        Company company = Company.find("byOrgName", "TW").first();

        assertNotNull(company);
        assertEquals("TW", company.orgName);
    }

    @Test
    public void createCompany(){
        IndustryType industryType = new IndustryType("IT","Information Technology").save();

        Company tw = new Company("TW", "www.thoughtworks.com", "decent company", industryType, true).save();

        new CompanyAddress("some Address","India","HQ", tw).save();

        Company testCompany = Company.find("byOrgName","TW").first();

        List<CompanyAddress> twAddress = CompanyAddress.find("byCompany", testCompany).fetch();

        assertEquals(1, twAddress.size());
    }

    @Test
    public void companyRating(){
        IndustryType industryType = new IndustryType("IT","Information Technology").save();

        Company tw = new Company("TW", "www.thoughtworks.com", "decent company", industryType, true).save();
        User jd = new User("Jasdeep","Madan","JD","jasdeepm@gmail.com","password").save();

//        CubeRating companyRating = new CubeRating(tw, jd, "Equality & Fairness", 3, true).save();

//        assertNotNull(companyRating);
//        assertEquals(3, companyRating.rating);
//        assertEquals(1,tw.getCompanyRatings(tw.id).size());
    }

}
