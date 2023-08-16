import helpers.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestBase implements ApplicationManager {

    public interface ApplicationManager {

    }

    @BeforeSuite
    public void setUp() {
        init();
    }

    @AfterSuite
    public void tearDown() {
        wd.quit();
    }
}
