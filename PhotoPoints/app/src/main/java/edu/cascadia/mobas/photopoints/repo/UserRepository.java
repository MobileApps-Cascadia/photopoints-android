package edu.cascadia.mobas.photopoints.repo;

import android.content.Context;
import edu.cascadia.mobas.photopoints.data.PhotoPointsDatabase;
import edu.cascadia.mobas.photopoints.data.dto.DBUser;
import edu.cascadia.mobas.photopoints.model.User;

public class UserRepository {

    private Context mContext;

    public UserRepository(Context context){
        mContext = context;
    }

    public User getCurrentUser() {
        return map(PhotoPointsDatabase.getAppDatabase(mContext).userDao().getCurrentUser());
    }

    private User map(DBUser dbUser){
        if (dbUser == null){
            return null;
        }

        return new User(dbUser.getUserID(), dbUser.getFirstName(), dbUser.getLastName(), dbUser.getDateOfBirth(), dbUser.getEmailAddress());
    }
}
