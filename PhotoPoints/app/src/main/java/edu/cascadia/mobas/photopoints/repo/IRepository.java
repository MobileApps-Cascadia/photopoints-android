package edu.cascadia.mobas.photopoints.repo;
import java.util.List;

public interface IRepository<T>{
    List<T> getAll();
    Integer count();
}
