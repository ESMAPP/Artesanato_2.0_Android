package pt.cm_vila_do_conde.artesanato_2.utils;

import java.util.ArrayList;
import java.util.Random;

import pt.cm_vila_do_conde.artesanato_2.model.Artisan;

public class SortingHelper {
    public Artisan randomFeaturedArtisan(ArrayList<Artisan> artisans){
        int max = artisans.size();
        int position = new Random().nextInt(max);
        return artisans.get(position);
    }
}
