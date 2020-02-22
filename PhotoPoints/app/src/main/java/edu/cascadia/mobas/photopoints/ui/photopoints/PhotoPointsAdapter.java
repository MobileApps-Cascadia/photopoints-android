package edu.cascadia.mobas.photopoints.ui.photopoints;
import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.telecom.Call;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import edu.cascadia.mobas.photopoints.MainActivity;
import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.Coordinates;
import edu.cascadia.mobas.photopoints.model.ItemType;
import edu.cascadia.mobas.photopoints.model.PointItem;
import edu.cascadia.mobas.photopoints.model.Plant;
import edu.cascadia.mobas.photopoints.repo.PlantRepository;
import edu.cascadia.mobas.photopoints.ui.details.DetailsFragment;
import edu.cascadia.mobas.photopoints.ui.details.DetailsViewModel;


public class PhotoPointsAdapter extends RecyclerView.Adapter<PhotoPointsAdapter.PhotoPointsViewHolder>{

    private ArrayList<PointItem> mPhotoPointRepo;
    FragmentManager fragmentManager;

    private PlantRepository mPlantRepo;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private DetailsViewModel model;

    // constructor
    public PhotoPointsAdapter(Context context, PlantRepository plantRepo, FragmentManager fragmentManager){
        mContext = context;
        mFragmentManager = fragmentManager;
        mPhotoPointRepo = new ArrayList<PointItem>();
        mPlantRepo = plantRepo;
        mPhotoPointRepo.add(new PointItem(1, ItemType.Plant, new Coordinates(47.776013, -122.192043), "https://www.plantsmap.com/organizations/24477/plants/28097", false));
    }

    class PhotoPointsViewHolder extends RecyclerView.ViewHolder{
        ImageView image_photopoint_displayphoto;
        TextView text_photopoint_displaytext;
        TextView text_photopoint_subtext;


public ClassLoader CL;

        public PhotoPointsViewHolder(@NonNull View itemView) {
            super(itemView);
            image_photopoint_displayphoto = (ImageView) itemView.findViewById(R.id.image_photopoint_displayphoto);
            text_photopoint_displaytext = (TextView) itemView.findViewById(R.id.text_photopoint_displaytext);
            text_photopoint_subtext = (TextView) itemView.findViewById(R.id.text_photopoint_subtext);


        }
    }

    @NonNull
    @Override
    public PhotoPointsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_photopoint, parent, false);


        return new PhotoPointsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PhotoPointsViewHolder viewHolder, int position) {

        PointItem photoPoint = mPhotoPointRepo.get(position);
        Plant plant = mPlantRepo.getById(photoPoint.getItemID());
        viewHolder.image_photopoint_displayphoto.setImageResource(R.drawable.default_plant_photo_small);
        viewHolder.text_photopoint_displaytext.setText(plant.getCommonName());
        viewHolder.text_photopoint_subtext.setText(plant.getSpecies());
        final String st1 = plant.getCommonName();
        final String st2 = plant.getSpecies();
        //Click listener for recycler view items
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View itemView){
                Bundle bundle = getText(st1,st2);
                mFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment,DetailsFragment.setInstance(bundle))

                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPhotoPointRepo.size();
    }

public Bundle getText(String one, String two){

        Bundle textBundle = new Bundle();
        textBundle.putString("pos1", one);
        textBundle.putString("pos2", two);
        return textBundle;

}

}
