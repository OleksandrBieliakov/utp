package ass1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArticleTest {

    private static final String TITLE = "Interesting article";
    private static final String TEXT = "Some text.txt, more text.txt, and more.";

    private Article article;

    @Before
    public void before() {
        article = new Article(TITLE, TEXT);
        Assert.assertEquals(TITLE, article.getTitle());
        Assert.assertEquals(TEXT, article.getText());
    }

    @Test
    public void aggregate() {
        Assert.assertEquals(TITLE + "\n" + TEXT, article.aggregate(null));
        Assert.assertEquals(TEXT + "\n" + TITLE + "\n" + TEXT, article.aggregate(TEXT));
    }

    @Test
    public void deepClone() {
        Article clone = article.deepClone();
        Assert.assertEquals(article.getTitle(), clone.getTitle());
        Assert.assertEquals(article.getText(), clone.getText());
        Assert.assertNotSame(article, clone);
    }
}