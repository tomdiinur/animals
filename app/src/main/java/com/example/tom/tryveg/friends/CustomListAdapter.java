package com.example.tom.tryveg.friends;

/**
 * Created by tom on 30-Jul-16.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.tom.tryveg.Globals;
import com.example.tom.tryveg.Notification.NotificationService;
import com.example.tom.tryveg.R;
import com.example.tom.tryveg.classes.FacebookFriend;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.share.Sharer;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import bolts.AppLinks;
import tyrantgit.explosionfield.ExplosionField;


public class CustomListAdapter extends BaseAdapter {
    private ExplosionField mExplosionField;
    private Activity activity;
    private LayoutInflater inflater;
    private List<FacebookFriend> friendsItems;
    ImageLoader imageLoader = FriendsController.getInstance().getImageLoader();
    private Context context;

    private String m_Text = "";

    public CustomListAdapter(Activity activity, List<FacebookFriend> friends, Context context) {
        this.activity = activity;
        this.friendsItems = friends;
        this.context = context;
    }

    @Override
    public int getCount() {
        return friendsItems.size();
    }

    @Override
    public Object getItem(int location) {
        return friendsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = FriendsController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);

        NetworkImageView thumbNailBack = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail_back);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView imgPet = (ImageView) convertView.findViewById(R.id.imgPet);
        TextView daysVeg = (TextView) convertView.findViewById(R.id.daysVeg);
        TextView dateVeg = (TextView) convertView.findViewById(R.id.dateVeg);
//        final Button btnFollowOrInvite = (Button) convertView.findViewById(R.id.btnFollowOrInvite);

        // getting movie data for the row
        final FacebookFriend currFriend = friendsItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(currFriend.ThumbnailUrl, imageLoader);
        thumbNailBack.setImageUrl(currFriend.ThumbnailUrl, imageLoader);

        ImageView imgLike = (ImageView)convertView.findViewById(R.id.like);
        ImageView imgMessage = (ImageView)convertView.findViewById(R.id.message);
        ImageView imgFollow = (ImageView)convertView.findViewById(R.id.follow);

        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You liked " + currFriend.Name,
                            Toast.LENGTH_SHORT).show();

                NotificationService notificationService = new NotificationService(context);
                notificationService.sendNotification(currFriend.Name.split(" ")[0] + " Liked your progress","", currFriend.ThumbnailUrl);
            }
        });

        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Message to " + currFriend.Name.split(" ")[0]);
                builder.setIcon(context.getResources().getDrawable(R.drawable.ic_message));

                // Set up the input
                final EditText input = new EditText(context);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                input.setText("Good Job!");

                // Set up the buttons
                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();

                        NotificationService notificationService = new NotificationService(context);
                        notificationService.sendNotification(currFriend.Name.split(" ")[0] + " sent you:", m_Text, currFriend.ThumbnailUrl);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        imgFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If move from not follow to follow
                if ((v.getTag() == null) || (v.getTag().toString().equals("not following"))) {

                    NotificationService notificationService = new NotificationService(context);
                    notificationService.sendNotification(currFriend.Name.split(" ")[0] + " is follwing you", "Keep on going!", currFriend.ThumbnailUrl);

                    v.setTag("following");
                    ((ImageView)v).setImageResource(R.drawable.ic_eye_close);

                    Toast.makeText(context, "You are following " + currFriend.Name,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    v.setTag("not following");
                    ((ImageView)v).setImageResource(R.drawable.ic_eye_open);
                }
            }
        });

        // title
        name.setText(currFriend.Name);

        final String currName = currFriend.Name;

        if (currFriend.bIsVegeterian) {

            imgPet.setImageDrawable(context.getResources().getDrawable(currFriend.User.getPetDrawable()));

            daysVeg.setText("Days vegan : " + String.valueOf(currFriend.User.getDaysVeg()));

            dateVeg.setText("Since: " + currFriend.User.getStartVeganString());
        }
        else {
            imgPet.setImageResource(0);

            daysVeg.setText("Still carnist");

            dateVeg.setText("");
        }

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                View viewBack = null;
                View viewFront = null;

                // If we move to front
                if ((v.getTag() == null) || (v.getTag().toString().equals("back"))) {
                    viewBack = v.findViewById(R.id.back);
                    viewFront = v.findViewById(R.id.front);
                    v.setTag("front");
                }
                // If we move the back
                else {
                    viewBack = v.findViewById(R.id.front);
                    viewFront = v.findViewById(R.id.back);
                    v.setTag("back");
                }

                flip(viewFront,viewBack,500);

                return true;
            }
        });

        Button button= (Button) convertView.findViewById(R.id.btn_week_challenge);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallbackManager callbackManager;
                ShareDialog shareDialog;
                FacebookSdk.sdkInitialize(activity);
                callbackManager = CallbackManager.Factory.create();
                shareDialog = new ShareDialog(activity);
                // this part is optional
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }

                });
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Hello Facebook")
                            .setContentDescription(
                                    "The 'Hello Facebook' sample  showcases simple Facebook integration")
                            .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                            .build();

                    shareDialog.show(linkContent);
                }

            }
        });

        return convertView;
    }

    public void flip(final View front, final View back, final int duration)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            AnimatorSet set = new AnimatorSet();
            set.playSequentially(
                    ObjectAnimator.ofFloat(front, "rotationY", 90).setDuration(duration / 2),
                    ObjectAnimator.ofInt(front, "visibility", View.GONE).setDuration(0),
                    ObjectAnimator.ofFloat(back, "rotationY", -90).setDuration(0),
                    ObjectAnimator.ofInt(back, "visibility", View.VISIBLE).setDuration(0),
                    ObjectAnimator.ofFloat(back, "rotationY", 0).setDuration(duration / 2));
            set.start();
        } else
        {
            front.animate().rotationY(90).setDuration(duration / 2)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            front.setVisibility(View.GONE);
                            back.setRotationY(-90);
                            back.setVisibility(View.VISIBLE);
                            back.animate().rotationY(0).setDuration(duration / 2).setListener(null);
                        }
                    });
        }
    }
}


