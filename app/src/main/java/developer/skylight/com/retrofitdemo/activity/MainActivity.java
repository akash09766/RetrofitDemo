package developer.skylight.com.retrofitdemo.activity;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;

import developer.skylight.com.retrofitdemo.R;
import developer.skylight.com.retrofitdemo.fragments.MainFragment;

import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            getWindow().setSharedElementReenterTransition(TransitionInflater.from(this)
                    .inflateTransition(R.transition.shared_element_transition));
        }

        navigateToFragment(MainFragment.newInstance());
    }


    public void navigateToFragment(Fragment fragment) {

        try {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();

            transaction.replace(R.id.main_container, fragment, fragment.getClass()
                    .getSimpleName());
            transaction.commit();
        } catch (Exception e) {

            Log.e(TAG, "navigateToFragment: navigateToFragment :: ", e);
        }
    }
}
