package com.alroid.instagramhesam.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.alroid.instagramhesam.Di.Component.DaggerCreateAccountHelperComponent;
import com.alroid.instagramhesam.Exception.Exceptions;
import com.alroid.instagramhesam.Exception.Helper.CreateAccountHelper;
import com.alroid.instagramhesam.databinding.FragmentCreateAccountBinding;



public class CreateAccountFragment extends Fragment {
    FragmentCreateAccountBinding binding;

    public interface CallbackFragmentCreateAccount
    {
        void onclickCreateAccount(String userName ,String password ,String phoneNumber ,String email );
    }
    CallbackFragmentCreateAccount listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof  CallbackFragmentCreateAccount)
        {
            listener = (CallbackFragmentCreateAccount) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateAccountBinding.inflate(inflater);

        binding.btnNextCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String userName = binding.etUserName.getText().toString();
                    String password = binding.etPassword.getText().toString();
                    String phoneNumber = binding.etPhoneNumber.getText().toString();
                    String email = binding.etEmail.getText().toString();

                    //CreateAccountHelper createAccountHelper = new CreateAccountHelper();
                    CreateAccountHelper createAccountHelper = DaggerCreateAccountHelperComponent.create().getCreateAccountHelper();

                    createAccountHelper.registerCreateAccount(userName,password,phoneNumber,email);

                    if(listener!=null)
                    {
                        listener.onclickCreateAccount(userName , password , phoneNumber , email);
                    }
                }catch (Exceptions.UsernameWeakLenghtException ex){
                    binding.outlinedTextFieldUsername.setError(ex.getMessage());
                }
                catch (Exceptions.PasswordWeakLenghtException ex){
                    binding.outlinedTextFieldPassword.setError(ex.getMessage());
                    binding.outlinedTextFieldUsername.setError(null);
                }
                catch (Exceptions.PhoneNumberLenghtException ex){
                    binding.outlinedTextFieldPhoneNumber.setError(ex.getMessage());
                    binding.outlinedTextFieldPassword.setError(null);
                    binding.outlinedTextFieldUsername.setError(null);
                }
                catch (Exceptions.EmailWeakLenghtException ex){
                    binding.outlinedTextFieldEmail.setError(ex.getMessage());
                    binding.outlinedTextFieldPhoneNumber.setError(null);
                    binding.outlinedTextFieldPassword.setError(null);
                    binding.outlinedTextFieldUsername.setError(null);
                }

            }
        });

        return binding.getRoot();
    }


}


