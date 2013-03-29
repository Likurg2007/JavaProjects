package org.dpolianskyi.epam.delivery.beans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;

@WebServlet(name = "Log4jBean", urlPatterns = {"/Log4jBean"})
public class Log4jBean extends HttpServlet {

    @Override
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j-init-file");
        if (file != null) {
            PropertyConfigurator.configure(prefix + file);
        }
        getServletContext().log("logging to: " + prefix + file);
    }
}
