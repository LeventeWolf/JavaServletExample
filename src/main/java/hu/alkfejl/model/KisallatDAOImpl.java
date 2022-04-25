package hu.alkfejl.model;

import hu.alkfejl.model.KisallatDAO;
import hu.alkfejl.model.bean.Kisallat;

import java.beans.PropertyEditorSupport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KisallatDAOImpl implements KisallatDAO {

    private static String CONN = "jdbc:sqlite:person.db";
    private static final String ADD_KISALLAT = "INSERT INTO kisallat (nev, fajta, kor) VALUES (?,?,?)";
    private static final String UPDATE_KISALLAT = "UPDATE kisallat SET nev=?, fajta = ?, kor = ? WHERE id=?";
    private static final String SELECT_ALL_KISALLAT = "SELECT * FROM kisallat";
    private static final String FIND_BY_ID_KISALLAT = "SELECT * FROM kisallat WHERE id = ?";
    private static final String SELECT_KISALLAT = "SELECT * FROM kisallat WHERE fajta = ?";

    public KisallatDAOImpl(String connStr) {
        CONN = connStr;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }


    @Override
    public Kisallat kisallatHozzaadas(Kisallat k) {
        try (Connection c = DriverManager.getConnection(CONN);
             PreparedStatement pst = k.getId() <= 0 ? c.prepareStatement(ADD_KISALLAT, Statement.RETURN_GENERATED_KEYS)
                     : c.prepareStatement(UPDATE_KISALLAT)
        ) {
            pst.setString(1, k.getNev());
            pst.setString(2, k.getFajta());
            pst.setInt(3, k.getKor());
            if (0 < k.getId()) {
                pst.setInt(4, k.getId());
            }

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }

            if(k.getId() <= 0){ // INSERT
                ResultSet genKeys = pst.getGeneratedKeys();
                if(genKeys.next()){
                    k.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return k;
    }

    @Override
    public List<Kisallat> kisallatListazas() {
        List<Kisallat> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(CONN);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_KISALLAT)
        ) {

            while(rs.next()) {
                Kisallat k = new Kisallat();
                k.setId(rs.getInt("id"));
                k.setNev(rs.getString("nev"));
                k.setFajta(rs.getString("fajta"));
                k.setKor(rs.getInt("kor"));

                result.add(k);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Kisallat> kisallatListazas(String fajta) {
        List<Kisallat> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(CONN);
            PreparedStatement stmt = c.prepareStatement(SELECT_KISALLAT);
        ) {
            stmt.setString(1, fajta);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Kisallat k = new Kisallat();
                    k.setId(rs.getInt("id"));
                    k.setNev(rs.getString("nev"));
                    k.setFajta(rs.getString("fajta"));
                    k.setKor(rs.getInt("kor"));

                    result.add(k);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public Kisallat kisallatKereses(int id) {
        try(Connection c = DriverManager.getConnection(CONN);
            PreparedStatement stmt = c.prepareStatement(FIND_BY_ID_KISALLAT);
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Kisallat k = new Kisallat();
                    k.setId(rs.getInt("id"));
                    k.setNev(rs.getString("nev"));
                    k.setFajta(rs.getString("fajta"));
                    k.setKor(rs.getInt("kor"));

                    return k;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;

    }


    @Override
    public Kisallat kisallatModositasa(Kisallat k) {
        try (Connection c = DriverManager.getConnection(CONN);
             PreparedStatement pst = c.prepareStatement(UPDATE_KISALLAT)
        ) {
            pst.setString(1, k.getNev());
            pst.setString(2, k.getFajta());
            pst.setInt(3, k.getKor());
            pst.setInt(4, k.getId());

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }

            if (k.getId() <= 0) { // INSERT
                ResultSet genKeys = pst.getGeneratedKeys();
                if (genKeys.next()) {
                    k.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return k;
    }
}
