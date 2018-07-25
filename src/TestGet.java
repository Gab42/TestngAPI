
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class TestGet {

    @Test
    public void loginTest() throws Exception {
        HttpURLConnection  conn = Utils.loginConnection("ChgDilyana", "Password.1");
        Assert.assertEquals(conn.getResponseCode(), 200);
    }

    @Test
    public void userHasMessages() throws Exception {
        HttpURLConnection  conn = Utils.loginConnection("ChgDilyana", "Password.1");
        String accessToken = Utils.getAccessToken(conn);
        conn = Utils.GET(Utils.url+"v1/messages?include_body=false", accessToken );
        Assert.assertEquals(false,conn.getResponseMessage().isEmpty());
    }


}

