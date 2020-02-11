package edu.cascadia.mobas.photopoints.ui.photopoints;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.List;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.GeoCoordinate;
import edu.cascadia.mobas.photopoints.model.ItemType;
import edu.cascadia.mobas.photopoints.model.PointItem;
import edu.cascadia.mobas.photopoints.model.Plant;
import edu.cascadia.mobas.photopoints.repo.PhotoPointsRepository;
import edu.cascadia.mobas.photopoints.repo.PlantRepository;
import edu.cascadia.mobas.photopoints.ui.details.DetailsFragment;


public class PhotoPointsAdapter extends RecyclerView.Adapter<PhotoPointsAdapter.PhotoPointsViewHolder>{

    private ArrayList<PointItem> mPhotoPointRepo;
    FragmentManager fragmentManager;

    private PlantRepository mPlantRepo;
    private Context mContext;
    private FragmentManager mFragmentManager;



    // constructor
    public PhotoPointsAdapter(Context context, PlantRepository plantRepo, FragmentManager fragmentManager){
        mContext = context;
        mFragmentManager = fragmentManager;
        mPhotoPointRepo = new ArrayList<PhotoPoint>();
        mPlantRepo = plantRepo;
        mPhotoPointRepo.add(new PointItem(1, ItemType.Plant, new GeoCoordinate(47.776013, -122.192043), "https://www.plantsmap.com/organizations/24477/plants/28097", false));
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
            //Click listener for recycler view items
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View itemView){
                mFragmentManager.beginTransaction()
                        .add(R.id.nav_host_fragment, new DetailsFragment())
                        .commit();




                }
            });
        }
    }

    @NonNull
    @Override
    public PhotoPointsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_photopoint, parent, false);
        return new PhotoPointsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoPointsViewHolder viewHolder, int position) {

        PointItem photoPoint = mPhotoPointRepo.get(position);
        Plant plant = mPlantRepo.getById(photoPoint.getItemID());
        viewHolder.image_photopoint_displayphoto.setImageResource(R.drawable.default_plant_photo_small);
        viewHolder.text_photopoint_displaytext.setText(plant.getCommonName());
        viewHolder.text_photopoint_subtext.setText(plant.getSpecies());
    }

    @Override
    public int getItemCount() {
        return mPhotoPointRepo.size();
    }
}
