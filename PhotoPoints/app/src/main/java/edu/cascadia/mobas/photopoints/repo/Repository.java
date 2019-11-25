package edu.cascadia.mobas.photopoints.repo;

import android.content.Context;
import java.util.List;

public interface Repository<T>{
    List<T> getAll();
    int count();
    List<T> getAll(Context context);
}
