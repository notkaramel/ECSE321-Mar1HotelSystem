package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.ArrayList;
import java.util.List;
/**
 * Utility Class to avoid code duplication.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public class ServiceUtils {
    static <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
