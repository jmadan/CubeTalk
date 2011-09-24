import models.*;
import models.Article;
import models.Category;
import org.junit.*;
import java.util.*;
import play.test.*;

public class ArticleTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }

    @Test
    public void createPost() {
        // Create a new user and save it
        User bob = new User("Jasdeep","Madan","JD","jasdeepm@gmail.com","password").save();

        //Create a new Category and save it
        Category category = new Category("Tech", "Some Description").save();

        // Create a new post
        new Article("My first post", "Hello world", bob, category, true).save();

        // Test that the post has been created
        assertEquals(1, Article.count());

        // Retrieve all posts created by Bob
        List<Article> bobPosts = Article.find("byAuthor", bob).fetch();

        // Tests
        assertEquals(1, bobPosts.size());
        Article firstPost = bobPosts.get(0);
        assertNotNull(firstPost);
        assertEquals(bob, firstPost.author);
        assertEquals("My first post", firstPost.title);
        assertEquals("Hello world", firstPost.content);
        assertNotNull(firstPost.submit_date);
    }

}