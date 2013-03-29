package org.dpolianskyi.epam.delivery.test.servlet.check;

import org.dpolianskyi.epam.delivery.model.Curpro_Request;
import org.dpolianskyi.epam.delivery.model.Model;
import org.dpolianskyi.epam.delivery.model.CurProduct;
import org.dpolianskyi.epam.delivery.model.Producer;
import org.dpolianskyi.epam.delivery.model.Category;
import org.dpolianskyi.epam.delivery.model.Request;
import org.dpolianskyi.epam.delivery.controller.dao.real.CurProductDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.ModelDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.Curpro_RequestDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.ProducerDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.RequestDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Likurg
 */
@WebServlet(name = "Test", urlPatterns = {"/Test"})
public class Test extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("start");

            Category cat = new Category();
            CategoryDAO catdao = new CategoryDAO();

            CurProduct cp = new CurProduct();
            CurProductDAO cpdao = new CurProductDAO();

            Model m = new Model();
            ModelDAO mdao = new ModelDAO();

            Curpro_Request cr = new Curpro_Request();
            Curpro_RequestDAO crdao = new Curpro_RequestDAO();

            Producer p = new Producer();
            ProducerDAO pdao = new ProducerDAO();

            Request r = new Request();
            RequestDAO rdao = new RequestDAO();



            //     pl = pldao.findById(3L);
            //     pl.setName("ABCDABASDASD");
            //     pldao.merge(pl);
            //pldao.remove(pl);
            //out.println(pldao.findById(2L));
            //out.println(pldao.findById(2L));

            // r = rdao.findById(1L);
            // r.setCredentials("Asdasdasdasdasda");
            // rdao.merge(r);
            // r = rdao.findById(1L);
            // rdao.remove(r);
            // out.println(pldao.findById(1L));
            // pl = pldao.findById(1L);
            //out.println(pl);
            // pldao.remove(pl);
            //out.println(rdao.findAll());
            //out.println(r);
            //rdao.remove(r);
//            out.println(rdao.findAll());
            //           out.println(rdao.findAll());
            //           out.println(pldao.findAll());
            //pldao.persist(pl);
//              out.println("<br>" + pldao.findEntities(0, 6, 2) + "</br>");   //    1st -   0: range   1: all     2nd - counts of records    3rd -   from what element
//            pl = pldao.findById(9L);
//            pldao.remove(pl);
//            pldao.merge(pl);
            //out.println(pldao.findEntities(true, 4, 1));

//            cat = catdao.findById(3L);
//            cat.setName("BlaBla");
//            catdao.merge(cat);



//            out.println("ALL -------------------------------------------------------->");
//
//            out.println(catdao.findAll());
//            out.println("<br></br>");
//            out.println(cpdao.findAll());
//            out.println("<br></br>");
//            out.println(crdao.findAll());
//            out.println("<br></br>");
//            out.println(mdao.findAll());
//            out.println("<br></br>");
//            out.println(pdao.findAll());
//            out.println("<br></br>");
//            out.println(pldao.findAll());
//            out.println("<br></br>");
//            out.println(pldao.findAll());
//            out.println("<br></br>");
//
//            out.println("SpecFind for Category -------------------------------------------------------->");
//
//            out.println(catdao.findByName("BlaBla"));
//            out.println("<br></br>");
//
//            out.println("SpecFind for CurProduct -------------------------------------------------------->");
//
//            out.println(cpdao.findByStatus(true));
//            out.println("<br></br>");
//
//            out.println("SpecFind for Curpro_Request -------------------------------------------------------->");
//
//            out.println(crdao.findByQuantity(10));
//            out.println("<br></br>");
//
//            out.println("SpecFind for Model -------------------------------------------------------->");
//
//            out.println(mdao.findByName("UTEL"));
//            out.println("<br></br>");
//
//            out.println("SpecFind for Producer -------------------------------------------------------->");
//
//            out.println(pdao.findByName("Nokia"));
//            out.println("<br></br>");
//
//            out.println("SpecFind for ProductList -------------------------------------------------------->");
//
//            out.println(pldao.findByCategoryName("BlaBla"));
//            out.println("<br></br>");
//            out.println(pldao.findByModelName("UTEL"));
//            out.println("<br></br>");
//            out.println(pldao.findByProducerName("Altec Lansing"));
//            out.println("<br></br>");
//            out.println(pldao.findByName("asddsa"));
//            out.println("<br></br>");
//            out.println(pldao.findByYear(1992));
//            out.println("<br></br>");
//
//            out.println("SpecFind for Request -------------------------------------------------------->");
//
//            out.println(rdao.findByCode("555"));
//            out.println("<br></br>");
//            out.println(rdao.findByCredentials("GG"));
//            out.println("<br></br>");
//            out.println(rdao.findByStatus(true));
//            out.println("<br></br>");
            out.println("end");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
