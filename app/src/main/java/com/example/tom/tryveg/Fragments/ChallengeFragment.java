package com.example.tom.tryveg.Fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.tom.tryveg.Globals;
import com.example.tom.tryveg.R;
import com.example.tom.tryveg.friends.FriendsController;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * Created by tom on 05-Aug-16.
 */
public class ChallengeFragment extends Fragment {

    public ChallengeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.material_design_profile_screen_xml_ui_design, container, false);
        ImageButton imgProfile = (ImageButton)v.findViewById(R.id.user_profile_photo);
        ImageView imgBackground = (ImageView)v.findViewById(R.id.header_cover_image);
        TextView txtName = (TextView)v.findViewById(R.id.user_profile_name);
        TextView txtVeganFrom = (TextView)v.findViewById(R.id.user_profile_short_bio);
        ImageView shareButton = (ImageView)v.findViewById(R.id.share_button);
        ArcProgress progress = (ArcProgress)v.findViewById(R.id.arc_progress);

        imgProfile.setImageDrawable(getActivity().getResources().getDrawable(Globals.currentUser.getPetDrawable()));
        imgBackground.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.abcd));
        txtName.setText(Globals.currentUser.Name);
        txtVeganFrom.setText("Vegetarian since: " + Globals.currentUser.getStartVeganString());
        int progressValue = Globals.currentUser.getDaysVeg() * 100 / 365;
        progress.setProgress(progressValue);


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));

//                int resId = Globals.currentUser.getPetDrawable();
//                Resources resources = getActivity().getResources();
//                Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
//                        resources.getResourcePackageName(resId) + '/' +
//                        resources.getResourceTypeName(resId) + '/' +
//                        resources.getResourceEntryName(resId));
//
////                Uri imageUri = Uri.parse("android.resource://" + getActivity().getPackageName()
////                        + "/drawable/" + "rhyno.png");
//                Intent shareIntent = new Intent();
//                shareIntent.setAction(Intent.ACTION_SEND);
//                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
//                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                shareIntent.setType("image/*");
//                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                startActivity(Intent.createChooser(shareIntent, "send"));
            }
        });

        ShareButton fbShareButton = (ShareButton) v.findViewById(R.id.share_on_fb_button);

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle("Join me")
                .setQuote("I am " + progressValue + "% on way")
                .setContentDescription("Challenge your friends..")
                .setShareHashtag(new ShareHashtag.Builder().setHashtag("#VeChallenge").build())
                .setContentUrl(Uri.parse("https://s8.postimg.org/6i3q2n91h/ic_app_logo.png"))
                .build();

        fbShareButton.setShareContent(content);


        return v;
    }

    public static ChallengeFragment newInstance(String text) {

        ChallengeFragment f = new ChallengeFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    public Bitmap getbitpam(String path){
        Bitmap imgthumBitmap=null;
        try
        {

            final int THUMBNAIL_SIZE = 64;

            FileInputStream fis = new FileInputStream(path);
            imgthumBitmap = BitmapFactory.decodeStream(fis);

            imgthumBitmap = Bitmap.createScaledBitmap(imgthumBitmap,
                    THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);

            ByteArrayOutputStream bytearroutstream = new ByteArrayOutputStream();
            imgthumBitmap.compress(Bitmap.CompressFormat.JPEG, 100,bytearroutstream);


        }
        catch(Exception ex) {

        }
        return imgthumBitmap;
    }
}