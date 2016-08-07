package com.example.tom.tryveg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;
import com.github.lzyzsd.circleprogress.ArcProgress;

/**
 * Created by tom on 05-Aug-16.
 */
public class PersonalZoneFragment extends Fragment {

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        callbackManager = CallbackManager.Factory.create();


        View v = inflater.inflate(R.layout.persnal_zone_layout, container, false);
        ArcProgress progress = (ArcProgress)v.findViewById(R.id.arc_progress);

        int progressValue = Globals.currentUser.getDaysVeg() * 100 / 365;
        progress.setProgress(progressValue);

        loginButton = (LoginButton) v.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                int x = 3;
            }

            @Override
            public void onCancel() {
                // App code
                int x = 3;
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                int x = 3;
            }
        });

//        Uri imageUri = Uri.parse("android.resource://com.example.tom.tryveg/drawable/cat");

        ShareButton fbShareButton = (ShareButton) v.findViewById(R.id.share_on_fb_button);

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle("Join me")
                .setQuote("I am " + progressValue + "% on way")
                .setContentDescription("Challenge your friends..")
                .setShareHashtag(new ShareHashtag.Builder().setHashtag("#VeChallenge").build())
                .setContentUrl(Uri.parse("https://s8.postimg.org/6i3q2n91h/ic_app_logo.png"))
                .build();

        fbShareButton.setShareContent(content);

//        LikeView likeView = (LikeView) v.findViewById(R.id.like_view);
//        likeView.setObjectIdAndType(
//                "https://www.facebook.com/groups/156499668116950/",
//                LikeView.ObjectType.PAGE);

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
