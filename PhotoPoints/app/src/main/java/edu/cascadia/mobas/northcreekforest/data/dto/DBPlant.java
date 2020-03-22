package edu.cascadia.mobas.northcreekforest.data.dto;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.cascadia.mobas.northcreekforest.data.converters.StringListTypeConverter;

@Entity(tableName = "Plants")
public class DBPlant {

    @PrimaryKey
    @ColumnInfo(name="plant_id")
    @NonNull
    private int id;

    @ColumnInfo(name="species")
    private String species;


    @TypeConverters(StringListTypeConverter.class)
    @ColumnInfo(name="common_names")
    private List<String> commonNames;


    @ColumnInfo(name="description")
    private String description;


    //Empty constructor for Room
    @Ignore
    private DBPlant(){ }

    // Standard constructor
    public DBPlant(
            int id,
            String species,
            List<String> commonNames,
            String description
    ) {
        this.id = id;
        this.species = species;
        this.commonNames = commonNames;
        this.description = description;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) { this.id = id;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public List<String> getCommonNames() { return this.commonNames;
    }

    public void setCommonNames(List<String> commonNames) { this.commonNames = commonNames; }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //Used the very first time to populate the database.
    public static DBPlant[] populateData(){
        return new DBPlant[]{
                new DBPlant(1,"Salix Lasiandra", new ArrayList<>(Arrays.asList("Pacific Willow", "Yellow Willow", "Waxy Willow")), "The Straits Salish and the Halq'emeylem peeled the bark of [native willow species] in May or…"),
                new DBPlant(2,"Lonicera Involucrata", new ArrayList<>(Arrays.asList("Black Twin Berry", "Bush Honeysuckle", "Twinberry", "Bearberry Honeysuckle")), "The shiny, black, bitter, twinned berries ae not considered edible by most people. They were…"),
                new DBPlant(3,"Pseudotsuga Menziesii", new ArrayList<>(Arrays.asList("Douglas Fir")), "Douglas Fir wood and bark was thought by most of the coastal groups to be an excellent fuel…"),
                new DBPlant(4, "Philadelphus Lewisii", new ArrayList<>(Arrays.asList("Mock-Orange")), "The wood is strong and hard; it never cracks or warps when properly prepared. It is most…"),
                new DBPlant(5,"Thuja Plicata", new ArrayList<>(Arrays.asList("Western Redcedar")), "Redcedar has been called 'the cornerstone of northwest coast Indian culture' and the large-scale…"),
                new DBPlant(6,"Rosa Pisocarpa", new ArrayList<>(Arrays.asList("Clustered Wild Rose")), "Branches of all species of wild rose - along with skunk cabbage leaves, fern fronds, pine needles…"),
                new DBPlant(7,"Symphoricarpos Albus", new ArrayList<>(Arrays.asList("Snowberry")), "The white, waxy-looking berries are considered poisonous by aboriginal peoples. They are…"),
                new DBPlant(8,"Mahonia Nervosa", new ArrayList<>(Arrays.asList("Low Oregon Grape")), "The tart, purple berries of both Oregon-grapes were eaten, but generally not in quantity…")
        };
    }


}
