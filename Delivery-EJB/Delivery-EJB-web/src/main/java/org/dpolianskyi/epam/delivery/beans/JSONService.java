/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dpolianskyi.epam.delivery.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.dpolianskyi.epam.delivery.controller.dao.real.RequestDAO;

@Path("/json/requests")
public class JSONService {

    @GET
    @Path("/get/record")
    //  @Produces(MediaType.APPLICATION_JSON)
    public String getRecords() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
        String url = "jdbc:sqlserver://localhost:1433;databaseName=DeliveryDB";
        Connection conn = DriverManager.getConnection(url, "Likurg2007", "321679110447924047");

        if (conn == null) {
            System.out.println("Non-connected!");
            System.exit(0);
        }
        Statement stmt = (Statement) conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Request");
        String result = "";

        while (rs.next()) {
            result += rs.getRow() + ". " + rs.getString("REQUEST_CODE") + " status: " + rs.getString("REQUEST_STATUS") + "<br/>";
        }
        return result;
    }

    @GET
    @Path("/get/count")
    public String getCount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        String result = getRecords();
        int count = result.split("<br/>").length;
        return String.valueOf(count);
    }
}
