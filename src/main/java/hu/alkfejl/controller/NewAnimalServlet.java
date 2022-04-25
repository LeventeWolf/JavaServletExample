package hu.alkfejl.controller;

import hu.alkfejl.helper.Helper;
import hu.alkfejl.model.KisallatDAOImpl;
import hu.alkfejl.model.bean.Kisallat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidParameterException;

@WebServlet("/NewAnimalServlet")
public class NewAnimalServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        KisallatDAOImpl kisallatDAO = new KisallatDAOImpl(getDBPath());

        request.setAttribute("fajtak", Helper.getFajtak(kisallatDAO.kisallatListazas()));


        try {
            var nev = request.getParameter("nev");
            var fajta = request.getParameter("selectedFajta");
            var kor = Integer.parseInt(request.getParameter("kor"));

            if ("".equals(nev) || "".equals(fajta)) {
                throw new InvalidParameterException("Invalid fields");
            }

            kisallatDAO.kisallatHozzaadas(new Kisallat(nev, fajta, kor));
            System.out.println("Új állat sikeresen hozáadva!");

        } catch (Exception e) {
            System.err.println("Error occured!");
            System.err.println(e.getMessage());
        }

    }

    public String getDBPath() {
        ServletContext sc = getServletContext();

        return sc.getInitParameter("driver");
    }
}
