package hu.alkfejl.model.bean;

import lombok.Data;

@Data
public class Kisallat {
    private int id;
    private String nev;
    private String fajta;
    private int kor;

    public Kisallat() {
    }

    public Kisallat(String nev, String fajta, int kor) {
        this.id = 0;
        this.nev = nev;
        this.fajta = fajta;
        this.kor = kor;
    }

    public Kisallat(int id, String nev, String fajta, int kor) {
        this.id = id;
        this.nev = nev;
        this.fajta = fajta;
        this.kor = kor;
    }

    @Override
    public String toString() {
        return "Kisallat{" +
                "id=" + id +
                ", nev='" + nev + '\'' +
                ", fajta='" + fajta + '\'' +
                ", kor=" + kor +
                '}';
    }
}
