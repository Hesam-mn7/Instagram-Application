package com.alroid.instagramhesam.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alroid.instagramhesam.Adapter.ProfilePostAdapter;
import com.alroid.instagramhesam.Di.Component.DaggerBottomSheetDialogComponent;
import com.alroid.instagramhesam.Di.Component.DaggerProfilePostAdapterComponent;
import com.alroid.instagramhesam.Di.Module.BottomSheetDialogModule;
import com.alroid.instagramhesam.Di.Module.ProfilePostAdapterModule;
import com.alroid.instagramhesam.Model.Room.Entity.Post;
import com.alroid.instagramhesam.Model.Room.Entity.User;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Activity.EditProfileActivity;
import com.alroid.instagramhesam.ViewModel.PostViewModel;
import com.alroid.instagramhesam.ViewModel.UserViewModel;
import com.alroid.instagramhesam.databinding.FragmnetProfileBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class ProfileFragment extends Fragment {
    FragmnetProfileBinding binding;
    UserViewModel userViewModel;
    PostViewModel postViewModel;

    @Inject
    ProfilePostAdapter profilePostAdapter;

    private AppCompatDelegate mDelegate;


    public interface CallbackFragmentProfile
    {
        void onclickProfileToDetail(int id , String usernamePost, String imgProfilePost
                ,String imgPost , String numberLike , String caption , String numberComments , String timePost , String cheekMyPost );
    }
    CallbackFragmentProfile listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof CallbackFragmentProfile) {
            listener = (CallbackFragmentProfile) context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmnetProfileBinding.inflate(inflater);

        postViewModel = ViewModelProviders.of(getActivity()).get(PostViewModel.class);
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

        setMyPost();

        onClickItems();

        setup_viewpager();
        setUserInformation();
        setupNavigationView();
        setupToolbar();

        return binding.getRoot();
    }

    private void setMyPost() {
        try {
        //profilePostAdapter = new ProfilePostAdapter(postViewModel, getActivity() , listener);
            DaggerProfilePostAdapterComponent.builder()
                    .profilePostAdapterModule(new ProfilePostAdapterModule(postViewModel, getActivity() , listener))
                    .build().injectProfilePost(this);

        binding.recycleMyPost.setLayoutManager(new GridLayoutManager(getActivity(),3));
        binding.recycleMyPost.setAdapter(profilePostAdapter);


            postViewModel.selectMyPost("1").observe(getActivity(), new Observer<List<Post>>() {
                @Override
                public void onChanged(List<Post> posts) {
                    profilePostAdapter.setList(posts);
                    String numberPost = String.valueOf(posts.size());
                    binding.tvNumberPost.setText(numberPost);

                    if (posts.size()==0){
                        binding.reletive2.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        catch (Exception e){
            binding.reletive2.setVisibility(View.VISIBLE);

        }


    }

    private void setup_viewpager() {

        binding.linearGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.recycleMyPost.setVisibility(View.VISIBLE);
                binding.reletive2.setVisibility(View.INVISIBLE);

                binding.imgGridDisSelect.setVisibility(View.INVISIBLE);
                binding.viewDisSelect.setVisibility(View.INVISIBLE);

                binding.imgGridSelect.setVisibility(View.VISIBLE);
                binding.viewSelect.setVisibility(View.VISIBLE);

                binding.imgTagSelect.setVisibility(View.INVISIBLE);
                binding.viewAccount.setVisibility(View.INVISIBLE);

                binding.imgTagUnSelect.setVisibility(View.VISIBLE);
                binding.viewUnAccount.setVisibility(View.VISIBLE);
            }
        });

        binding.linearAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.recycleMyPost.setVisibility(View.INVISIBLE);
                binding.reletive2.setVisibility(View.VISIBLE);

                binding.imgGridDisSelect.setVisibility(View.VISIBLE);
                binding.viewDisSelect.setVisibility(View.VISIBLE);

                binding.imgGridSelect.setVisibility(View.INVISIBLE);
                binding.viewSelect.setVisibility(View.INVISIBLE);

                binding.imgTagSelect.setVisibility(View.VISIBLE);
                binding.viewAccount.setVisibility(View.VISIBLE);

                binding.imgTagUnSelect.setVisibility(View.INVISIBLE);
                binding.viewUnAccount.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void onClickItems() {
        binding.btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        binding.createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_botton_sheet_create, null);

                //final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                BottomSheetDialog dialog  = DaggerBottomSheetDialogComponent.builder()
                        .bottomSheetDialogModule(new BottomSheetDialogModule(getActivity()))
                        .build().getBottomSheetDialog();

                dialog.setContentView(view);
                dialog.show();

                TextView tvPostt = view.findViewById(R.id.tvPostt);
                tvPostt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        Snackbar.make(binding.navViewProfile, "Post", Snackbar.LENGTH_SHORT).show();
                    }
                });

                TextView tvStoryy = view.findViewById(R.id.tvStoryy);
                tvStoryy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        Snackbar.make(binding.navViewProfile, "Story", Snackbar.LENGTH_SHORT).show();
                    }
                });

                TextView tvStoryHighlight = view.findViewById(R.id.tvStoryHighlight);
                tvStoryHighlight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        Snackbar.make(binding.navViewProfile, "Story Highlight", Snackbar.LENGTH_SHORT).show();
                    }
                });

                TextView tvIGTV = view.findViewById(R.id.tvIGTV);
                tvIGTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        Snackbar.make(binding.navViewProfile, "IGTV Videos", Snackbar.LENGTH_SHORT).show();
                    }
                });

                TextView tvGuide = view.findViewById(R.id.tvGuide);
                tvGuide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        Snackbar.make(binding.navViewProfile, "Guide", Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });

        binding.linearfollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(binding.navViewProfile, "219 Followers", Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.linearfollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(binding.navViewProfile, "371 Following", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void setUserInformation() {
        userViewModel.select().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User item : users){
                    String userName = item.getUserName();
                    String name = item.getName();
                    String profilePic = item.getProfilePic();
                    String bio = item.getBio();

                    binding.tvUsername.setText(userName);
                    binding.tvName.setText(name);
                    binding.tvBio.setText(bio);

                    Picasso.get()
                            .load(profilePic)
                            .placeholder(R.drawable.nostory)
                            .error(R.drawable.nostory)
                            .into(binding.profilePicUser);

                    binding.imgDown.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_botton_sheet_addacconunt, null);

                            //final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                            BottomSheetDialog dialog  = DaggerBottomSheetDialogComponent.builder()
                                    .bottomSheetDialogModule(new BottomSheetDialogModule(getActivity()))
                                    .build().getBottomSheetDialog();

                            dialog.setContentView(view);
                            dialog.show();

                            TextView usernamee = view.findViewById(R.id.tvUsernamee);
                            usernamee.setText(userName);

                            ImageView picUsename = view.findViewById(R.id.picUsename);

                            Picasso.get()
                                    .load(profilePic)
                                    .placeholder(R.drawable.nostory)
                                    .error(R.drawable.nostory)
                                    .into(picUsename);

                            ConstraintLayout linearMyProfile = view.findViewById(R.id.linearMyProfile);
                            linearMyProfile.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.cancel();
                                    Snackbar.make(binding.navViewProfile, userName, Snackbar.LENGTH_SHORT).show();
                                }
                            });

                            ConstraintLayout constAdd = view.findViewById(R.id.constAdd);
                            constAdd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.cancel();
                                    Snackbar.make(binding.navViewProfile, "Add Account", Snackbar.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void setupNavigationView() {
        binding.navViewProfile.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_settings:
                        Snackbar.make(binding.navViewProfile, "Settings", Snackbar.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_archive:
                        Snackbar.make(binding.navViewProfile, "Archive", Snackbar.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_your_activity:
                        Snackbar.make(binding.navViewProfile, "Your Activity", Snackbar.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_qr_code:
                        Snackbar.make(binding.navViewProfile, "QR Code", Snackbar.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_saved:
                        Snackbar.make(binding.navViewProfile, "Saved", Snackbar.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_close_friends:
                        Snackbar.make(binding.navViewProfile, "Close Freinds", Snackbar.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_covid19:
                        Snackbar.make(binding.navViewProfile, "COVID-19 Information", Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private void setupToolbar() {
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(getActivity(), R.color.design_default_color_primary_variant));

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(),
                binding.drawerLayoutProfileActivity, binding.toolbar, 0, 0);
        binding.drawerLayoutProfileActivity.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }


}
