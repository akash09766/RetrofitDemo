package developer.skylight.com.retrofitdemo.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import developer.skylight.com.retrofitdemo.R;
import developer.skylight.com.retrofitdemo.constant.RConstant;

import static android.R.attr.data;

/**
 * Created by Akash Wangalwar on 17-01-2017.
 */
public class DetailViewActivity extends AppCompatActivity {

    private ImageView mPhoto;
    private TextView mName;
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;
    private Animation fadeInAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_activity_layout);

        setIdsToViews();
        getIntentValues();
    }

    private void setIdsToViews() {

        mPhoto = (ImageView) findViewById(R.id.profile_image_id);
        mName = (TextView) findViewById(R.id.profile_name_id);

        mImageLoader = ImageLoader.getInstance();
        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

/*        options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();*/
    }


    private void getIntentValues() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString(RConstant.NAME);
            String url = bundle.getString(RConstant.IMG);
            mName.startAnimation(fadeInAnimation);
            mName.setText(name);
            if (url != null && !url.isEmpty()) {
                mImageLoader.displayImage(url, mPhoto);
            } else {
                mPhoto.setImageResource(R.mipmap.ic_launcher);
            }
        }
    }
}
