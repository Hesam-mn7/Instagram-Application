package com.alroid.instagramhesam.View.Fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alroid.instagramhesam.Di.Component.DaggerBottomSheetDialogComponent;
import com.alroid.instagramhesam.Di.Module.BottomSheetDialogModule;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Activity.EditPostActivity;
import com.alroid.instagramhesam.View.Activity.MainActivity;
import com.alroid.instagramhesam.ViewModel.PostViewModel;
import com.alroid.instagramhesam.databinding.FragmentDetailBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;

    PostViewModel postViewModel;

    public static DetailFragment newInstanceDetailProfile(int id , String usernamePost, String imgProfilePost
            , String imgPost , String numberLike , String caption , String numberComments , String timePost , String cheekMyPost ) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("usernamePost", usernamePost);
        bundle.putString("imgProfilePost", imgProfilePost);
        bundle.putString("imgPost", imgPost);
        bundle.putString("numberLike", numberLike);
        bundle.putString("caption", caption);
        bundle.putString("numberComments", numberComments);
        bundle.putString("cheekMyPost", cheekMyPost);
        bundle.putString("timePost", timePost);
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater);
        setPost();
        onClikItems();

        return binding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setPost() {

        if (getArguments() != null && getArguments().containsKey("usernamePost") && getArguments().containsKey("imgProfilePost")
        && getArguments().containsKey("imgPost") && getArguments().containsKey("numberLike") && getArguments().containsKey("caption")
                && getArguments().containsKey("numberComments") && getArguments().containsKey("timePost") && getArguments().containsKey("cheekMyPost")
                && getArguments().containsKey("id")) {

            String usernamePost = getArguments().getString("usernamePost");
            String imgProfilePost = getArguments().getString("imgProfilePost");
            String imgPost = getArguments().getString("imgPost");
            String numberLike = getArguments().getString("numberLike");
            String caption = getArguments().getString("caption");
            String numberComments = getArguments().getString("numberComments");
            String timePost = getArguments().getString("timePost");
            String cheekMyPost = getArguments().getString("cheekMyPost");
            int id = getArguments().getInt("id");

            binding.tvUsernamePost.setText(usernamePost);
            binding.tvUsernamePost2.setText(usernamePost);
            binding.tvNumberLike.setText(numberLike);
            binding.tvNumberComments.setText(numberComments);
            binding.tvTime.setText(timePost);
            binding.tvCaption.setText(caption);

            Uri uriImgProfilePost = Uri.parse(imgProfilePost);
            Picasso.get()
                    .load(uriImgProfilePost)
                    .placeholder(R.drawable.nostory)
                    .error(R.drawable.nostory)
                    .into(binding.imgProfilePost);

            Uri uriimgPost = Uri.parse(imgPost);
            Picasso.get()
                    .load(uriimgPost)
                    .placeholder(R.drawable.nostory)
                    .error(R.drawable.nostory)
                    .into(binding.imgPost);

            if (cheekMyPost.equals("1")){
                binding.tvFollow.setVisibility(View.INVISIBLE);
            }

            binding.imgmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cheekMyPost.equals("0")){
                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_botton_sheet_more_post, null);

                        //final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                        BottomSheetDialog dialog = DaggerBottomSheetDialogComponent.builder()
                                .bottomSheetDialogModule(new BottomSheetDialogModule(getActivity()))
                                .build().getBottomSheetDialog();

                        dialog.setContentView(view);
                        dialog.show();

                        TextView tvReport = view.findViewById(R.id.tvReport);
                        tvReport.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Report", Toast.LENGTH_SHORT).show();
                            }
                        });
                        TextView tvnotif = view.findViewById(R.id.tvnotif);
                        tvnotif.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Turn on post Notification", Toast.LENGTH_SHORT).show();
                            }
                        });
                        TextView tvcopy = view.findViewById(R.id.tvcopy);
                        tvcopy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Copy Link", Toast.LENGTH_SHORT).show();
                            }
                        });
                        TextView tvShare = view.findViewById(R.id.tvShare);
                        tvShare.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, "Uername : " + binding.tvUsernamePost.getText().toString() + "\n" + "Caption : " + binding.tvCaption.getText().toString());
                                sendIntent.setType("text/plain");
                                startActivity(sendIntent);
                                dialog.cancel();

                            }
                        });
                        TextView tvUnfollow = view.findViewById(R.id.tvUnfollow);
                        tvUnfollow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Unfollow", Toast.LENGTH_SHORT).show();
                            }
                        });
                        TextView tvMute = view.findViewById(R.id.tvMute);
                        tvMute.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Mute", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else if (cheekMyPost.equals("1")){
                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_botton_sheet_more_mypost, null);

                        //final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                        BottomSheetDialog dialog = DaggerBottomSheetDialogComponent.builder()
                                .bottomSheetDialogModule(new BottomSheetDialogModule(getActivity()))
                                .build().getBottomSheetDialog();

                        dialog.setContentView(view);
                        dialog.show();

                        TextView tvPostToOtherApp = view.findViewById(R.id.tvPostToOtherApp);
                        tvPostToOtherApp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, "Uername : " + usernamePost + "\n" + "Caption : " + caption);
                                sendIntent.setType("text/plain");
                                startActivity(sendIntent);
                                dialog.cancel();
                            }
                        });

                        TextView tvcopy = view.findViewById(R.id.tvcopy);
                        tvcopy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Copy Link", Toast.LENGTH_SHORT).show();
                            }
                        });

                        TextView tvShare = view.findViewById(R.id.tvShare);
                        tvShare.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, "Uername : " + usernamePost + "\n" + "Caption : " + caption);
                                sendIntent.setType("text/plain");
                                startActivity(sendIntent);
                                dialog.cancel();

                            }
                        });

                        TextView tvArchive = view.findViewById(R.id.tvArchive);
                        tvArchive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Archive", Toast.LENGTH_SHORT).show();
                            }
                        });

                        TextView tvDelete = view.findViewById(R.id.tvDelete);
                        tvDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                final AlertDialog alertDialog;

                                View alertDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.allert_dialog_delete, null);

                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setView(alertDialogView);
                                builder.setCancelable(false);

                                alertDialog = builder.create();
                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.trans)));
                                alertDialog.show();

                                TextView tvDelete=alertDialogView.findViewById(R.id.tvDelete);
                                tvDelete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        postViewModel = ViewModelProviders.of(getActivity()).get(PostViewModel.class);

                                        postViewModel.deleteById(id)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new CompletableObserver() {
                                                    @Override
                                                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                                    }

                                                    @Override
                                                    public void onComplete() {
                                                        alertDialog.dismiss();
                                                        startActivity(new Intent(getActivity(), MainActivity.class));


                                                    }

                                                    @Override
                                                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                                    }
                                                });
                                    }


                                });
                                TextView tvdontDelete =alertDialogView.findViewById(R.id.tvdontDelete);

                                tvdontDelete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        alertDialog.dismiss();
                                    }
                                });
                            }

                        });

                        TextView tvEdit = view.findViewById(R.id.tvEdit);
                        tvEdit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                Intent intent = new Intent(getActivity(), EditPostActivity.class);
                                intent.putExtra("usernamePost" , usernamePost);
                                intent.putExtra("imgPost" , imgPost);
                                intent.putExtra("timePost" , timePost);
                                intent.putExtra("caption" , caption);
                                intent.putExtra("imgProfilePost" , imgProfilePost);
                                intent.putExtra("id",id);
                                startActivity(intent);

                            }
                        });

                        TextView tvHideLikeCount = view.findViewById(R.id.tvHideLikeCount);
                        tvHideLikeCount.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Hide Like Count", Toast.LENGTH_SHORT).show();
                            }
                        });

                        TextView tvTurnOffCommenting = view.findViewById(R.id.tvTurnOffCommenting);
                        tvTurnOffCommenting.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Turn Off Commenting", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            });

        }

        }

    private void onClikItems() {
        binding.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(v, "Opening Comments", Snackbar.LENGTH_SHORT);
                snackBar.setAnchorView(binding.chat);
                snackBar.show();
            }
        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Uername : " + binding.tvUsernamePost.getText().toString() + "\n" + "Caption : " + binding.tvCaption.getText().toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        binding.tvUsernamePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(v, "Opening " + binding.tvUsernamePost.getText().toString(), Snackbar.LENGTH_SHORT);
                snackBar.setAnchorView(binding.chat);
                snackBar.show();
            }
        });
        binding.tvUsernamePost2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(v, "Opening " + binding.tvUsernamePost.getText().toString(), Snackbar.LENGTH_SHORT);
                snackBar.setAnchorView(binding.chat);
                snackBar.show();
            }
        });
        binding.imgProfilePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(v, "Opening " + binding.tvUsernamePost.getText().toString(), Snackbar.LENGTH_SHORT);
                snackBar.setAnchorView(binding.chat);
                snackBar.show();
            }
        });
        binding.imglike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imglike.setVisibility(View.INVISIBLE);
                binding.imgdislike.setVisibility(View.VISIBLE);

            }
        });
        binding.imgdislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imglike.setVisibility(View.VISIBLE);
                binding.imgdislike.setVisibility(View.INVISIBLE);

            }
        });
        binding.imgPost.setOnClickListener(new View.OnClickListener() {
            volatile int i = 0;

            @Override
            public void onClick(View v) {
                i++;
                Handler handler = new Handler();

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        if (i == 1) {
                            //single click logic

                            i = 0; //set to zero
                        }
                    }
                };

                if (i == 1) {
                    handler.postDelayed(r, 300);
                } else if (i == 2) {
                    handler.removeCallbacks(r);
                    i = 0;
                    binding.imglike.setVisibility(View.VISIBLE);
                    binding.imgdislike.setVisibility(View.INVISIBLE);
                    Snackbar snackBar = Snackbar.make(v, "Liked", Snackbar.LENGTH_SHORT);
                    snackBar.setAnchorView(binding.imglike);
                    snackBar.show();
                }


            }
        });
        binding.imgdissaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgsaved.setVisibility(View.VISIBLE);
                binding.imgdissaved.setVisibility(View.INVISIBLE);
                Snackbar snackBar = Snackbar.make(v, "Saved", 500);
                snackBar.setAnchorView(binding.imgsaved);
                snackBar.show();
            }
        });
        binding.imgsaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgsaved.setVisibility(View.INVISIBLE);
                binding.imgdissaved.setVisibility(View.VISIBLE);

            }
        });
        binding.consts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.tvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(v, "Follow", 500);
                snackBar.setAnchorView(binding.tvFollow);
                snackBar.show();
            }
        });
    }
}
