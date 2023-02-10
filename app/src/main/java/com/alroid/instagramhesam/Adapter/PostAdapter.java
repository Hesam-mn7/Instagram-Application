package com.alroid.instagramhesam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.alroid.instagramhesam.Di.Component.DaggerBottomSheetDialogComponent;
import com.alroid.instagramhesam.Di.Module.BottomSheetDialogModule;
import com.alroid.instagramhesam.Model.Room.Entity.Post;
import com.alroid.instagramhesam.R;
import com.alroid.instagramhesam.View.Activity.EditPostActivity;
import com.alroid.instagramhesam.ViewModel.PostViewModel;
import com.alroid.instagramhesam.databinding.ItemPostBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private PostViewModel viewModel;
    private List<Post> list;

    Context context;

    public PostAdapter(PostViewModel postViewModel, Context context) {
        this.list = new ArrayList<>();
        this.viewModel = postViewModel;
        this.context = context;

    }

    public void setList(List<Post> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {
        Post post = this.list.get(position);

        int id = post.getId();
        String usernamePost = post.getUsernamePost();
        String imgProfilePost = post.getImgProfilePost();
        String imgPost = post.getImgPost();
        String imgProfileLike = post.getImgProfileLike();
        String usernameLike = post.getUsernameLike();
        String numberLike = post.getNumberLike();
        String caption = post.getCaption();
        String numberComments = post.getNumberComments();
        String timePost = post.getTimePost();
        String cheekLike = post.getCheekLike();
        String cheekSaved = post.getCheekSaved();
        String cheekMyPost = post.getCheekMyPost();

        holder.binding.tvUsernamePost.setText(usernamePost);
        holder.binding.tvUsernamePost2.setText(usernamePost);
        holder.binding.tvNameLike.setText(usernameLike);
        holder.binding.tvNumberLike.setText(numberLike);
        holder.binding.tvCaption.setText(caption);
        holder.binding.tvNumberComments.setText(numberComments);
        holder.binding.tvTime.setText(timePost);

        Uri uriImgProfilePost = Uri.parse(imgProfilePost);
        Picasso.get()
                .load(uriImgProfilePost)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgProfilePost);

        Uri uriImgPost = Uri.parse(imgPost);
        Picasso.get()
                .load(uriImgPost)
                .placeholder(R.drawable.load_insta)
                .error(R.drawable.error_pic)
                .into(holder.binding.imgPost);

        Uri uriImgProfileLike = Uri.parse(imgProfileLike);
        Picasso.get()
                .load(uriImgProfileLike)
                .placeholder(R.drawable.nostory)
                .error(R.drawable.nostory)
                .into(holder.binding.imgProfileLike);

        if (cheekLike.equals("0")) {
            holder.binding.imglike.setVisibility(View.INVISIBLE);
            holder.binding.imgdislike.setVisibility(View.VISIBLE);

        } else if (cheekLike.equals("1")) {
            holder.binding.imglike.setVisibility(View.VISIBLE);
            holder.binding.imgdislike.setVisibility(View.INVISIBLE);
        }

        if (cheekSaved.equals("0")) {
            holder.binding.imgsaved.setVisibility(View.INVISIBLE);
            holder.binding.imgdissaved.setVisibility(View.VISIBLE);

        } else if (cheekSaved.equals("1")) {
            holder.binding.imgsaved.setVisibility(View.VISIBLE);
            holder.binding.imgdissaved.setVisibility(View.INVISIBLE);
        }


        holder.binding.imgmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cheekMyPost.equals("0")) {
                    View view = LayoutInflater.from(context).inflate(R.layout.fragment_botton_sheet_more_post, null);

                    //final BottomSheetDialog dialog = new BottomSheetDialog(context);
                    BottomSheetDialog dialog = DaggerBottomSheetDialogComponent
                            .builder()
                            .bottomSheetDialogModule(new BottomSheetDialogModule(context))
                            .build()
                            .getBottomSheetDialog();

                    dialog.setContentView(view);
                    dialog.show();

                    TextView tvReport = view.findViewById(R.id.tvReport);
                    tvReport.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            Toast.makeText(context, "Report", Toast.LENGTH_SHORT).show();
                        }
                    });
                    TextView tvnotif = view.findViewById(R.id.tvnotif);
                    tvnotif.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            Toast.makeText(context, "Turn on post Notification", Toast.LENGTH_SHORT).show();
                        }
                    });
                    TextView tvcopy = view.findViewById(R.id.tvcopy);
                    tvcopy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            Toast.makeText(context, "Copy Link", Toast.LENGTH_SHORT).show();
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
                            context.startActivity(sendIntent);
                            dialog.cancel();

                        }
                    });
                    TextView tvUnfollow = view.findViewById(R.id.tvUnfollow);
                    tvUnfollow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            Toast.makeText(context, "Unfollow", Toast.LENGTH_SHORT).show();
                        }
                    });
                    TextView tvMute = view.findViewById(R.id.tvMute);
                    tvMute.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            Toast.makeText(context, "Mute", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (cheekMyPost.equals("1")) {
                    View view = LayoutInflater.from(context).inflate(R.layout.fragment_botton_sheet_more_mypost, null);

                    //final BottomSheetDialog dialog = new BottomSheetDialog(context);
                    BottomSheetDialog dialog = DaggerBottomSheetDialogComponent
                            .builder()
                            .bottomSheetDialogModule(new BottomSheetDialogModule(context))
                            .build()
                            .getBottomSheetDialog();

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
                            context.startActivity(sendIntent);
                            dialog.cancel();
                        }
                    });

                    TextView tvcopy = view.findViewById(R.id.tvcopy);
                    tvcopy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            Toast.makeText(context, "Copy Link", Toast.LENGTH_SHORT).show();
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
                            context.startActivity(sendIntent);
                            dialog.cancel();

                        }
                    });

                    TextView tvArchive = view.findViewById(R.id.tvArchive);
                    tvArchive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            Toast.makeText(context, "Archive...", Toast.LENGTH_SHORT).show();
                        }
                    });

                    TextView tvDelete = view.findViewById(R.id.tvDelete);
                    tvDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            final AlertDialog alertDialog;

                            View alertDialogView = LayoutInflater.from(context).inflate(R.layout.allert_dialog_delete, null);

                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setView(alertDialogView);
                            builder.setCancelable(false);

                            alertDialog = builder.create();
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.trans)));
                            alertDialog.show();

                            TextView tvDelete = alertDialogView.findViewById(R.id.tvDelete);
                            tvDelete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    viewModel.deleteById(id)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new CompletableObserver() {
                                                @Override
                                                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                                }

                                                @Override
                                                public void onComplete() {
                                                    alertDialog.dismiss();

                                                }

                                                @Override
                                                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                                }
                                            });
                                }


                            });
                            TextView tvdontDelete = alertDialogView.findViewById(R.id.tvdontDelete);

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
                            Intent intent = new Intent(context, EditPostActivity.class);
                            intent.putExtra("usernamePost", usernamePost);
                            intent.putExtra("imgPost", imgPost);
                            intent.putExtra("timePost", timePost);
                            intent.putExtra("caption", caption);
                            intent.putExtra("imgProfilePost", imgProfilePost);
                            intent.putExtra("id", id);
                            context.startActivity(intent);

                        }
                    });

                    TextView tvHideLikeCount = view.findViewById(R.id.tvHideLikeCount);
                    tvHideLikeCount.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            Toast.makeText(context, "Hide Like Count...", Toast.LENGTH_SHORT).show();
                        }
                    });

                    TextView tvTurnOffCommenting = view.findViewById(R.id.tvTurnOffCommenting);
                    tvTurnOffCommenting.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            Toast.makeText(context, "Turn Off Commenting...", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }

        });

        holder.binding.imgdislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.updateLike(id, "1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                holder.binding.imglike.setVisibility(View.VISIBLE);
                                holder.binding.imgdislike.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }
                        });
            }
        });

        holder.binding.imglike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.updateLike(id, "0")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                holder.binding.imglike.setVisibility(View.INVISIBLE);
                                holder.binding.imgdislike.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }
                        });
            }
        });

        holder.binding.imgPost.setOnClickListener(new View.OnClickListener() {
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

                    viewModel.updateLike(id, "1")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    holder.binding.imglike.setVisibility(View.VISIBLE);
                                    holder.binding.imgdislike.setVisibility(View.INVISIBLE);
                                    Snackbar snackBar = Snackbar.make(v, "Liked", Snackbar.LENGTH_SHORT);
                                    snackBar.setAnchorView(holder.binding.imglike);
                                    snackBar.show();
                                }

                                @Override
                                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                }
                            });

                }
            }

        });

        holder.binding.imgdissaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.updateSaved(id, "1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                holder.binding.imgsaved.setVisibility(View.VISIBLE);
                                holder.binding.imgdissaved.setVisibility(View.INVISIBLE);
                                Snackbar snackBar = Snackbar.make(v, "Saved", 500);
                                snackBar.setAnchorView(holder.binding.imgsaved);
                                snackBar.show();
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }
                        });
            }
        });

        holder.binding.imgsaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.updateSaved(id, "0")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                holder.binding.imgsaved.setVisibility(View.INVISIBLE);
                                holder.binding.imgdissaved.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }
                        });
            }
        });

        holder.binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(v, "Opening Comments", Snackbar.LENGTH_SHORT);
                snackBar.setAnchorView(holder.binding.chat);
                snackBar.show();
            }
        });

        holder.binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Uername : " + usernamePost + "\n" + "Caption : " + caption);
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });
        holder.binding.tvUsernamePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(v, "Opening " + usernamePost, Snackbar.LENGTH_SHORT);
                snackBar.setAnchorView(holder.binding.chat);
                snackBar.show();
            }
        });
        holder.binding.tvUsernamePost2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(v, "Opening " + usernamePost, Snackbar.LENGTH_SHORT);
                snackBar.setAnchorView(holder.binding.chat);
                snackBar.show();
            }
        });
        holder.binding.imgProfilePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(v, "Opening " + usernamePost, Snackbar.LENGTH_SHORT);
                snackBar.setAnchorView(holder.binding.chat);
                snackBar.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        com.alroid.instagramhesam.databinding.ItemPostBinding binding;

        public MyViewHolder(com.alroid.instagramhesam.databinding.ItemPostBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }

    }


}
