package com.alroid.instagramhesam.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.alroid.instagramhesam.Const;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Fragment.CreateAccountFragment;
import com.alroid.instagramhesam.View.Fragment.LoginFragment;
import com.alroid.instagramhesam.View.Fragment.RegisterFragment;
import com.alroid.instagramhesam.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends BaseActivity implements CreateAccountFragment.CallbackFragmentCreateAccount , LoginFragment.CallbackFragmentLogin {
    ActivityCreateAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onCheckBeforRegister();
        setCreateAccountFragment();

    }

    private void setCreateAccountFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentLogin,new LoginFragment(),"fragment1");
        transaction.commit();
    }

    private void onCheckBeforRegister() {
        String result = getSharedPreference(Const.SHARED_PERF_KEY_REGISTER);
        if (result != null){
            Intent intent = new Intent(CreateAccountActivity.this , MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onclickCreateAccount(String userName, String password, String phoneNumber, String email) {
        RegisterFragment fragment = RegisterFragment.newInstanceCreateAccount(userName,password,phoneNumber,email);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentRegister,fragment,"fragment1");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public String getSharedPreference(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(Const.SHARED_PREF_NAME, MODE_PRIVATE);
        if (!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getString(key, null);
    }


    @Override
    public void onclickLogin() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentCreateAccount, new CreateAccountFragment(), "fragment0");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}