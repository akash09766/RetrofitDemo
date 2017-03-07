package developer.skylight.com.retrofitdemo.interfaces;

import android.widget.ImageView;

import developer.skylight.com.retrofitdemo.model.PersonDetails;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Akash Wangalwar on 17-01-2017.
 */
public interface OnItemClick {
    void onClick(PersonDetails personDetails, ImageView view);
}
