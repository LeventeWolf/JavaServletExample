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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        KisallatDAOImpl kisallatDAO = new KisallatDAOImpl(getDBPath());
        List<Kisallat> kisallatok = kisallatDAO.kisallatListazas();

        // Init list with animalType
        request.setAttribute("fajtak", Helper.getFajtak(kisallatDAO.kisallatListazas()));

        // Filter submitted for animalType
        String fajta = request.getParameter("selectedFajta");

        if (fajta == null || "".equals(fajta)) {
            request.setAttribute("pets", kisallatok);
            return;
        }

        kisallatok = kisallatok.stream()
                .filter(kisallat -> fajta.equals(kisallat.getFajta()))
                .collect(Collectors.toList());

        request.setAttribute("pets", kisallatok);
    }

    public String getDBPath() {
        ServletContext sc = getServletContext();

        return sc.getInitParameter("driver");
    }
}
