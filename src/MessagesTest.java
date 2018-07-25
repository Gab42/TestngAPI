import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class MessagesTest{

        @Test
        public void userHasMessages() throws Exception {
            // Login and get access token
            String accessToken = Utils.getAccessToken("ChgDilyana", "Password.1");
            // GET request to messages endpoint
            HttpURLConnection conn = Utils.GET(Utils.url+"v1/messages?include_body=false", accessToken );
            Assert.assertEquals(false,conn.getResponseMessage().isEmpty());
        }
}
