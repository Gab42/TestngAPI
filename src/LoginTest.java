
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class LoginTest {

    @Test
    public void successfulLogin() throws Exception {
        // Login
        HttpURLConnection conn = Utils.loginConnection("ChgGabriela", "Password.1");

        Assert.assertEquals(conn.getResponseCode(), 200);
    }



}

