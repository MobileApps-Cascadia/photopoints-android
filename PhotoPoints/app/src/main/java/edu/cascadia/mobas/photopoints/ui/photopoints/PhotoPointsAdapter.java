package edu.cascadia.mobas.photopoints.ui.photopoints;
import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.cascadia.mobas.photopoints.R;
import edu.cascadia.mobas.photopoints.model.GeoCoordinate;
import edu.cascadia.mobas.photopoints.model.ItemType;
import edu.cascadia.mobas.photopoints.model.PointItem;
import edu.cascadia.mobas.photopoints.model.Plant;
import edu.cascadia.mobas.photopoints.repo.PlantRepository;


public class PhotoPointsAdapter extends RecyclerView.Adapter<PhotoPointsAdapter.PhotoPointsViewHolder>{

    private ArrayList<PointItem> mPhotoPointRepo;
    private PlantRepository mPlantRepo;

    // constructor
    public PhotoPointsAdapter(PlantRepository plantRepo){
        mPhotoPointRepo = new ArrayList<PointItem>();
        mPlantRepo = plantRepo;
        mPhotoPointRepo.add(new PointItem(1, ItemType.Plant, new GeoCoordinate(47.776013, -122.192043), "https://www.plantsmap.com/organizations/24477/plants/28097", false));
    }

    class PhotoPointsViewHolder extends RecyclerView.ViewHolder{
        ImageView image_photopoint_displayphoto;
        TextView text_photopoint_displaytext;
        TextView text_photopoint_subtext;

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
