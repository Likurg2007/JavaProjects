/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dpolianskyi.epam.delivery.beans;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "servBean")
@SessionScoped
public class ServBean {

    public String getRecordsInDB() {
        String result = "";
        try {
            URL url = new URL("http://localhost:8080/Delivery-EJB-web/rest/json/requests/get/record");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                result += output;
            }
            conn.disconnect();
        } catch (Exception ex) {
            System.out.println("Service is unavailable");
        } finally {
            return result;
        }
    }

    public String getCountsInDB() {
        String result = "";
        try {
            URL url = new URL("http://localhost:8080/Delivery-EJB-web/rest/json/requests/get/count");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                result += output;
            }
            conn.disconnect();
        } catch (Exception ex) {
            System.out.println("Service is unavailable");
        } finally {
            return result;
        }
    }
}
