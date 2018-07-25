import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

public class PaymentTest {

    @Test
    public void makePayment() throws Exception {
        String accessToken = Utils.getAccessToken("ChgGabriela", "Password.1");
        String ID = Utils.getID(accessToken);


        String test = "{ \"sourceAccountNumber\": \"12345\",\n" +
                "  \"portfolioId\":\"" +ID+ "\",\n" +
                "  \"sourceAccountName\": \"Gab\",\n" +
                "  \"beneficiaryAccountNumber\": \"1234567\",\n" +
                "  \"beneficiaryName\": \"Gab\",\n" +
                "  \"amount\": 5.0,\n" +
                "  \"description\": \"This is the description of the payment\"}";

        JSONObject data = new JSONObject(test);
        HttpURLConnection conn = Utils.POST( "/v1/payments", data, accessToken);
        Assert.assertEquals(conn.getResponseCode(), 200);
    }
}
