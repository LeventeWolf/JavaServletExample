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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        KisallatDAOImpl kisallatDAO = new KisallatDAOImpl(getDBPath());

        try {
            var id = Integer.parseInt(request.getParameter("id"));
            Kisallat kisallat = kisallatDAO.kisallatKereses(id);
            request.setAttribute("pet", kisallat);
            request.setAttribute("fajtak", Helper.getFajtak(kisallatDAO.kisallatListazas()));
        } catch (Exception e) {
            System.err.println("Error occured!");
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        var kisallatDAO = new KisallatDAOImpl(getDBPath());
        var id = Integer.parseInt(req.getParameter("id"));
        var kisallat = kisallatDAO.kisallatKereses(id);

        kisallat.setNev(req.getParameter("nev"));
        kisallat.setFajta(req.getParameter("selectedFajta"));
        kisallat.setKor(Integer.parseInt(req.getParameter("kor")));

        kisallatDAO.kisallatModositasa(kisallat);

        resp.sendRedirect("index.jsp");
    }


    public String getDBPath() {
        ServletContext sc = getServletContext();

        return sc.getInitParameter("driver");
    }


}
