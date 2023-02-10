package com.alroid.instagramhesam.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Activity.BaseActivity;
import com.alroid.instagramhesam.databinding.FragmentIgtvBinding;
import com.alroid.instagramhesam.databinding.FragmentLoginBinding;
import com.google.android.material.snackbar.Snackbar;


public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;

    public interface CallbackFragmentLogin {
        void onclickLogin();
    }

    CallbackFragmentLogin listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof CallbackFragmentLogin) {
            listener = (CallbackFragmentLogin) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater);

        onclickItem();
        onclickbtnLogin();

        return binding.getRoot();

    }

    private void onclickbtnLogin() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (binding.etUserName.getText().toString().isEmpty()) {
                    binding.outlinedTextFieldUsername.setError("Enter username");
                } else if (binding.etPassword.getText().toString().isEmpty()) {
                    binding.outlinedTextFieldPassword.setError("Enter password");
                    binding.outlinedTextFieldUsername.setError(null);
                } else {
                    AlertDialog alertDialog;

                    View alert = LayoutInflater.from(getActivity()).inflate(R.layout.allert_dialog_login, null);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(alert);
                    builder.setCancelable(false);

                    alertDialog = builder.create();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.trans)));
                    alertDialog.show();

                    TextView tvTryAgain = alert.findViewById(R.id.tvTryAgain);
                    tvTryAgain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    TextView tvsign = alert.findViewById(R.id.tvsign);
                    tvsign.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onclickLogin();
                            alertDialog.dismiss();

                        }
                    });
                }

            }
        });
    }

    private void onclickItem() {
        binding.tvEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Select language", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.linearforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Opening Login Help", Snackbar.LENGTH_SHORT).show();

            }
        });
        binding.tvFacebookk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Opening Facebook", Snackbar.LENGTH_SHORT).show();

            }
        });
        binding.linearsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclickLogin();
            }
        });
    }
}
