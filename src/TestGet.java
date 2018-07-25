
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;


public class TestGet {

    @Test
    public void loginTest() throws Exception {
        HttpURLConnection conn = null;

            conn = Utils.login("ChgDilyana", "Password.1");
            Assert.assertEquals(conn.getResponseCode(), 200);

    }


}

