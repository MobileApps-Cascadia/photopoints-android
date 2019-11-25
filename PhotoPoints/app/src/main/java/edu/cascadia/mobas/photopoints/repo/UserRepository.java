package edu.cascadia.mobas.photopoints.repo;

import android.content.Context;
import edu.cascadia.mobas.photopoints.data.PhotoPointsDatabase;
import edu.cascadia.mobas.photopoints.data.dto.DBUser;
import edu.cascadia.mobas.photopoints.model.User;

public class UserRepository {

    public User getCurrentUser(Context context) {
        return map(PhotoPointsDatabase.getAppDatabase(context).userDao().getCurrentUser());
    }

    private User map(DBUser dbUser){
        return new User(dbUser.getFirstName(), dbUser.getLastName(), dbUser.getDateOfBirth(), dbUser.getEmailAddress());
    }
}
