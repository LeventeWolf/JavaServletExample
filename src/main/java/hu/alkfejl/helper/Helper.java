package hu.alkfejl.helper;

import hu.alkfejl.model.bean.Kisallat;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Helper {
    public static Set<String> getFajtak(List<Kisallat> kisallatok) {
        Set<String> result = kisallatok.stream()
                .map(Kisallat::getFajta)
                .collect(Collectors.toSet());

        result.add("");

        return result;
    }
}
