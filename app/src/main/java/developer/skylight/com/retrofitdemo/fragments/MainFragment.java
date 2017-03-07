package developer.skylight.com.retrofitdemo.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import developer.skylight.com.retrofitdemo.R;
import developer.skylight.com.retrofitdemo.activity.DetailViewActivity;
import developer.skylight.com.retrofitdemo.adapter.PersonListAdapter;
import developer.skylight.com.retrofitdemo.constant.RConstant;
import developer.skylight.com.retrofitdemo.interfaces.OnItemClick;
import developer.skylight.com.retrofitdemo.model.Person;
import developer.skylight.com.retrofitdemo.model.PersonDetails;
import developer.skylight.com.retrofitdemo.network.NetworkManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.id.list;
import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

/**
 * Created by Akash Wangalwar on 17-01-2017.
 */
public class MainFragment extends Fragment implements OnItemClick {

    private static final String BASE_URL = "http://bitcodeindia.com/";
    private RecyclerView mPersonLv;
    private ProgressDialog mProressDialog;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment_layout, container, false);

        setIdsViews(view);
        setListenersToViews();

        getProgressDialog();
        getAdapterData();

        return view;
    }

    private void getProgressDialog() {
        mProressDialog = new ProgressDialog(getActivity());
        mProressDialog.setMessage("Loading....");
    }

    private void setIdsViews(View view) {
        mPersonLv = (RecyclerView) view.findViewById(R.id.person_lv_id);
    }

    private void setListenersToViews() {
        mPersonLv.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
    }

    private void getAdapterData() {
        mPersonLv.setVisibility(View.INVISIBLE);
        mProressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkManager networkManager = retrofit.create(NetworkManager.class);

        Call<Person> call = networkManager.getPersonsList();

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {

                int code = response.code();
                Log.d(TAG, "onResponse: code : " + code);
                Person person = (Person) response.body();
                Log.d(TAG, "onResponse: size : " + person.getResult().size());

                for (PersonDetails battle : person.getResult()) {

                    Log.d(TAG, "onResponse: " + battle.getPeople_name());

                }
                setDataToAdpater(person);

            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                mProressDialog.hide();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setDataToAdpater(Person person) {
        mPersonLv.setAdapter(new PersonListAdapter(getActivity(),person.getResult(),this));
        mProressDialog.hide();
        mPersonLv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(PersonDetails personDetails, ImageView v) {

        Intent intent = new Intent(getActivity(), DetailViewActivity.class);
        intent.putExtra(RConstant.NAME, personDetails.getPeople_name());
        intent.putExtra(RConstant.IMG, personDetails.getPeople_image());


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(),v,
                    v.getTransitionName());
            startActivity(intent,activityOptionsCompat.toBundle());

        }else{
            startActivity(intent);
        }
    }
}
