
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;


public class TestGet {

    @Test
    public void loginTest() {
        HttpURLConnection conn = null;
        try {
            conn = Utils.login("ChgDilyana", "Password.1");
            Assert.assertEquals(conn.getResponseCode(), 200);
        } catch (IOException e) {
            System.out.println("loginTest IOException");
        } catch (Exception e){
            System.out.println("loginTest Exception");
        }
    }

    @Test
    public void getAccounts() {
        try {
            HttpURLConnection conn = Utils.login("Gab", "Password.1");
        } catch (IOException e) {
            System.out.println("getAccounts IOException");
        } catch (Exception e){
            System.out.println("getAccounts Exception");
        }


    }
}

