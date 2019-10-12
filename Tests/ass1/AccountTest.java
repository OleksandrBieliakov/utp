package ass1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    private static final Person OWNER = new Person(20);
    private static final Double SUM = 999.99;

    private Account account;

    @Before
    public void before() {
        account = new Account(OWNER, SUM);
        Assert.assertEquals(OWNER, account.getOwner());
        Assert.assertEquals(SUM, account.getSum(), 0.01);
    }

    @Test
    public void aggregate() {
        Assert.assertEquals(SUM, account.aggregate(null), 0.01);
        Assert.assertEquals(SUM + 100, account.aggregate(100.), 0.01);
    }

    @Test
    public void deepClone() {
        Account clone = account.deepClone();
        Assert.assertEquals(account.getOwner(), clone.getOwner());
        Assert.assertEquals(account.getSum(), clone.getSum(), 0.01);
        Assert.assertNotSame(account, clone);
    }
}