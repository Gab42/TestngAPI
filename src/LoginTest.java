
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class LoginTest {
    String user = "Gab";
    String password = "Password";

    @Test
    public void successfulLogin() throws Exception {
        // Login
        HttpURLConnection conn = Login.loginConnection(user, password);
        Assert.assertEquals(conn.getResponseCode(), 200);
    }

    @Test
    public void wrongPassword() throws Exception {
        // Login
        HttpURLConnection conn = Login.loginConnection(user, "WrongPassword");
        Assert.assertEquals(conn.getResponseCode(), 400);
    }

    @Test
    public void nonExistentUser() throws Exception {
        // Login
        HttpURLConnection conn = Login.loginConnection("1234RandomUsername-1", password);
        Assert.assertEquals(conn.getResponseCode(), 400);
    }

}

