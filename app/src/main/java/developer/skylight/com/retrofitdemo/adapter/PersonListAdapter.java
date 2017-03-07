package developer.skylight.com.retrofitdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import developer.skylight.com.retrofitdemo.R;
import developer.skylight.com.retrofitdemo.interfaces.OnItemClick;
import developer.skylight.com.retrofitdemo.model.PersonDetails;

/**
 * Created by Akash Wangalwar on 17-01-2017.
 */
public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {

    private Context context;
    private ArrayList<PersonDetails> list;

    private final LayoutInflater inflator;
    private final ImageLoader mImageLoader;
    private final DisplayImageOptions options;
    private OnItemClick onitemClick;

    public PersonListAdapter(Context context, ArrayList<PersonDetails> list,
                             OnItemClick onitemClick) {

        this.context = context;
        this.list = list;
        this.onitemClick = onitemClick;
        inflator = LayoutInflater.from(context);

        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.list_row_item, parent, false);

        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {

        final PersonDetails item = list.get(position);

        holder.mName.setText(item.getPeople_name());
        if (item.getPeople_image() != null && !item.getPeople_image().isEmpty()) {
            mImageLoader.displayImage(item.getPeople_image(), holder.mPhoto, options);
        } else {
            holder.mPhoto.setImageResource(R.mipmap.ic_launcher);
        }
        holder.mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onitemClick.onClick(item,(ImageView)view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        private ImageView mPhoto;
        private TextView mName;

        public PersonViewHolder(View itemView) {
            super(itemView);

            mPhoto = (ImageView) itemView.findViewById(R.id.profile_image_id);
            mName = (TextView) itemView.findViewById(R.id.profile_name_id);
        }
    }
}
