package edu.cascadia.mobas.northcreekforest.repo;
import java.util.List;

public interface IRepository<T>{
    List<T> getAll();
    Integer count();
}
