package com.alroid.instagramhesam.View.Activity;

import android.os.Bundle;

import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Fragment.DetailFragment;
import com.alroid.instagramhesam.View.Fragment.ExploreFragment;
import com.alroid.instagramhesam.View.Fragment.ProfileFragment;
import com.alroid.instagramhesam.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends BaseActivity implements ExploreFragment.CallbackFragmentExplore , ProfileFragment.CallbackFragmentProfile {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_explore, R.id.navigation_newpost, R.id.navigation_igtv, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

    @Override
    public void onclickProfileToDetail(int id, String usernamePost, String imgProfilePost,
                                       String imgPost, String numberLike, String caption,
                                       String numberComments, String timePost, String cheekMyPost) {

        DetailFragment fragment = DetailFragment.newInstanceDetailProfile(id,usernamePost
                ,imgProfilePost,imgPost,numberLike,caption, numberComments , timePost , cheekMyPost);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentDetail, fragment, "fragmentDetailProfile");
        transaction.commit();

    }


    @Override
    public void onclickExploreToDetail(int id, String usernamePost, String imgProfilePost,
                                       String imgPost, String numberLike, String caption,
                                       String numberComments, String timePost, String cheekMyPost) {

        DetailFragment fragment = DetailFragment.newInstanceDetailProfile(id,usernamePost
                ,imgProfilePost,imgPost,numberLike,caption, numberComments , timePost , cheekMyPost);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentDetail, fragment, "fragmentDetailProfile");
        transaction.commit();


    }
}