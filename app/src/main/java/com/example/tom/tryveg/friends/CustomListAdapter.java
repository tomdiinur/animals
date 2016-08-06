package com.example.tom.tryveg.friends;

/**
 * Created by tom on 30-Jul-16.
 */

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.tom.tryveg.Globals;
import com.example.tom.tryveg.R;
import com.example.tom.tryveg.classes.FacebookFriend;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;

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

    public CustomListAdapter(Activity activity, List<FacebookFriend> movieItems, Context context) {
        this.activity = activity;
        this.friendsItems = movieItems;
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
        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView imgPet = (ImageView) convertView.findViewById(R.id.imgPet);
        TextView daysVeg = (TextView) convertView.findViewById(R.id.daysVeg);
        TextView dateVeg = (TextView) convertView.findViewById(R.id.dateVeg);
        final Button btnFollowOrInvite = (Button) convertView.findViewById(R.id.btnFollowOrInvite);

        // getting movie data for the row
        FacebookFriend currFriend = friendsItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(currFriend.ThumbnailUrl, imageLoader);

        // title
        name.setText(currFriend.Name);

        final String currName = currFriend.Name;

        if (currFriend.bIsVegeterian) {

            imgPet.getLayoutParams().height = 90;
            imgPet.getLayoutParams().width = 90;

            imgPet.setImageDrawable(context.getResources().getDrawable(currFriend.User.getPetDrawable()));

            daysVeg.setText("Days vegetarian : " + String.valueOf(currFriend.User.getDaysVeg()));

            dateVeg.setText("Since: " + currFriend.User.getStartVeganString());

            if (currFriend.User.equals(Globals.currentUser)) {
                btnFollowOrInvite.setVisibility(View.INVISIBLE);

                imgPet.getLayoutParams().height = 160;
                imgPet.getLayoutParams().width = 160;
            }
            else {
                if (currFriend.bIsFollowed) {
                    btnFollowOrInvite.setText("unfollow");
                } else {
                    btnFollowOrInvite.setText("follow friend");
                }

                btnFollowOrInvite.setVisibility(View.VISIBLE);
                btnFollowOrInvite.setTag("Follow");

                btnFollowOrInvite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // If not following
                        if (!Globals.friends.get(position).bIsFollowed) {
                            Toast.makeText(context, "you are following " + currName,
                                    Toast.LENGTH_SHORT).show();
                            btnFollowOrInvite.setText("unfollow");
                            Globals.friends.get(position).bIsFollowed = true;
                        } else {
                            Toast.makeText(context, "Stopped following " + currName,
                                    Toast.LENGTH_SHORT).show();
                            btnFollowOrInvite.setText("follow friend");
                            Globals.friends.get(position).bIsFollowed = false;
                        }
                    }
                });
            }
        }
        else {
            imgPet.setImageResource(0);

            daysVeg.setText("Still carnivore");

            dateVeg.setText("");


            btnFollowOrInvite.setVisibility(View.VISIBLE);
            btnFollowOrInvite.setText("invite friend");
            btnFollowOrInvite.setTag("Invite");

            btnFollowOrInvite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, currName + " invited",
                            Toast.LENGTH_SHORT).show();
                    btnFollowOrInvite.setText("Invited");

                    btnFollowOrInvite.setEnabled(false);


                    String appLinkUrl, previewImageUrl;

                    appLinkUrl = "https://fb.me/1804512149782038";
                    previewImageUrl = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQXLGok0jIYC_FWzLV2FGJd2MBSn52WzlKWdzLM_Z5xNad7vVW3aQ";


                    if (AppInviteDialog.canShow()) {
                        AppInviteContent content = new AppInviteContent.Builder()
                                .setApplinkUrl(appLinkUrl)
                                .setPreviewImageUrl(previewImageUrl)
                                .build();
                        AppInviteDialog.show(activity, content);
                    }

                }
            });
        }

        return convertView;
    }

}
