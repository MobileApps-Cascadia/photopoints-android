package edu.cascadia.mobas.photopoints.repo;
import java.util.List;

public interface Repository<T>{
    List<T> getAll();
    int count();
}
