package edu.cascadia.mobas.photopoints.ui.photopoints;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.repo.PointItemRepository;
import edu.cascadia.mobas.photopoints.repo.PlantRepository;
import edu.cascadia.mobas.photopoints.ui.details.DetailsFragment;
import edu.cascadia.mobas.photopoints.ui.details.DetailsViewModel;

public class PhotoPointsFragment extends Fragment {

    PointItemRepository photoPointsRepo = new PointItemRepository(getContext());
    PlantRepository plantRepo = new PlantRepository();

    private DetailsViewModel model;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //ViewModel set
        model = new ViewModelProvider(this).get(DetailsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_photopoints, container, false);

        PhotoPointsAdapter adapter = new PhotoPointsAdapter(getContext(),plantRepo,getFragmentManager());

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.cardview_photopoints);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(),2));

        return root;
    }

    FragmentManager fragmentManager = new FragmentManager() {
        @NonNull
        @Override
        public FragmentTransaction beginTransaction() {
            return null;
        }

        @Override
        public boolean executePendingTransactions() {
            return false;
        }

        @Nullable
        @Override
        public Fragment findFragmentById(int id) {
            return null;
        }

        @Nullable
        @Override
        public Fragment findFragmentByTag(@Nullable String tag) {
            return null;
        }

        @Override
        public void popBackStack() {

        }

        @Override
        public boolean popBackStackImmediate() {
            return false;
        }

        @Override
        public void popBackStack(@Nullable String name, int flags) {

        }

        @Override
        public boolean popBackStackImmediate(@Nullable String name, int flags) {
            return false;
        }

        @Override
        public void popBackStack(int id, int flags) {

        }

        @Override
        public boolean popBackStackImmediate(int id, int flags) {
            return false;
        }

        @Override
        public int getBackStackEntryCount() {
            return 0;
        }

        @NonNull
        @Override
        public BackStackEntry getBackStackEntryAt(int index) {
            return null;
        }

        @Override
        public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

        }

        @Override
        public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {

        }

        @Override
        public void putFragment(@NonNull Bundle bundle, @NonNull String key, @NonNull Fragment fragment) {

        }

        @Nullable
        @Override
        public Fragment getFragment(@NonNull Bundle bundle, @NonNull String key) {
            return null;
        }

        @NonNull
        @Override
        public List<Fragment> getFragments() {
            return null;
        }

        @Nullable
        @Override
        public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment f) {
            return null;
        }

        @Override
        public boolean isDestroyed() {
            return false;
        }

        @Override
        public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb, boolean recursive) {

        }

        @Override
        public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb) {

        }

        @Nullable
        @Override
        public Fragment getPrimaryNavigationFragment() {
            return null;
        }

        @Override
        public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {

        }

        @Override
        public boolean isStateSaved() {
            return false;
        }
    };

    private Context context;

    public void onClick(View v){
        fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
    }


}
