package com.example.tom.tryveg.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tom.tryveg.Globals;
import com.example.tom.tryveg.R;
import com.example.tom.tryveg.classes.Pet;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tom on 05-Aug-16.
 */
public class PersonalZoneFragment extends Fragment {

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        callbackManager = CallbackManager.Factory.create();

        View v = inflater.inflate(R.layout.material_design_profile_screen_xml_ui_design, container, false);
        final ImageButton imgUserPet = (ImageButton)v.findViewById(R.id.user_profile_photo);
        ImageView imgUser = (ImageView)v.findViewById(R.id.header_cover_image);
        TextView txtName = (TextView)v.findViewById(R.id.user_profile_name);
        final TextView txtVeganFrom = (TextView)v.findViewById(R.id.user_profile_short_bio);
        final TextView txtDaysToNext = (TextView)v.findViewById(R.id.days_to_next);
        TextView resetVechallenge = (TextView)v.findViewById(R.id.reset_vechallenge);
        ImageView shareButton = (ImageView)v.findViewById(R.id.share_button);
        final ArcProgress progress = (ArcProgress)v.findViewById(R.id.arc_progress);

        imgUserPet.setImageDrawable(getActivity().getResources().getDrawable(Globals.currentUser.getPetDrawable()));
        imgUser.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.abcd));
        txtName.setText(Globals.currentUser.Name);
        txtVeganFrom.setText("Vegetarian since: " + Globals.currentUser.getStartVeganString());
        final int progressValue = Globals.currentUser.getDaysVeg() * 100 / 365;
        progress.setProgress(progressValue);

        Pet nextAnimal = Globals.currentUser.getNextPet();

        int nextAnimalDays = nextAnimal.getDays() - Globals.currentUser.getDaysVeg();
        txtDaysToNext.setText("Days to next animal: " + String.valueOf(nextAnimalDays));

        // Read your drawable from somewhere
        Drawable dr = getResources().getDrawable(nextAnimal.getDrawable());
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));

        txtDaysToNext.setCompoundDrawablesWithIntrinsicBounds(null, null,
                d, null);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                sendIntent.setType("text/plain");
//                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));

                ArrayList<Uri> imageUris = new ArrayList<Uri>();
                imageUris.add(Uri.parse("http://i4.cdnds.net/13/25/618x450/fruit-and-veg.jpg")); // Add your image URIs here

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "Share images to.."));
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

        resetVechallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.currentUser.StartVeganTime = new Date();
                progress.setProgress(0);

                Pet nextAnimal = Globals.currentUser.getNextPet();

                int nextAnimalDays = nextAnimal.getDays() - Globals.currentUser.getDaysVeg();
                txtDaysToNext.setText("Days to next animal: " + String.valueOf(nextAnimalDays));

                // Read your drawable from somewhere
                Drawable dr = getResources().getDrawable(nextAnimal.getDrawable());
                Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
                Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));

                txtDaysToNext.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        d, null);

                txtVeganFrom.setText("Vegetarian since: " + Globals.currentUser.getStartVeganString());
                imgUserPet.setImageDrawable(getActivity().getResources().getDrawable(Globals.currentUser.getPetDrawable()));
            }
        });

//        loginButton = (LoginButton) v.findViewById(R.id.login_button);
//        loginButton.setReadPermissions("email");
        // If using in a fragment
//        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                int x = 3;
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//                int x = 3;
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//                int x = 3;
//            }
//        });

        return v;
    }

    public static PersonalZoneFragment newInstance(String text) {

        PersonalZoneFragment f = new PersonalZoneFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
