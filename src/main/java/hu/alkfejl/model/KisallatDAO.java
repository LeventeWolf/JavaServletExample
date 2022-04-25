package hu.alkfejl.model;

import hu.alkfejl.model.bean.Kisallat;

import java.util.List;

public interface KisallatDAO {

    Kisallat kisallatHozzaadas(Kisallat k);
    List<Kisallat> kisallatListazas();
    List<Kisallat> kisallatListazas(String fajta);
    Kisallat kisallatKereses(int id);
    Kisallat kisallatModositasa(Kisallat k);

}
