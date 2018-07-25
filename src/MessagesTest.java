import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class MessagesTest{

        @Test
        public void userHasMessages() throws Exception {
            // Login and get access token
            HttpURLConnection conn = Utils.loginConnection("ChgDilyana", "Password.1");
            String accessToken = Utils.getAccessToken("ChgDilyana", "Password.1");
            conn = Utils.GET(Utils.url+"v1/messages?include_body=false", accessToken );
            Assert.assertEquals(false,conn.getResponseMessage().isEmpty());
        }
}
