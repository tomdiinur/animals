package com.example.tom.tryveg;

import android.content.Intent;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dev.sacot41.scviewpager.DotsView;
import com.dev.sacot41.scviewpager.SCPositionAnimation;
import com.dev.sacot41.scviewpager.SCViewAnimation;
import com.dev.sacot41.scviewpager.SCViewAnimationUtil;
import com.dev.sacot41.scviewpager.SCViewPager;
import com.dev.sacot41.scviewpager.SCViewPagerAdapter;

import tyrantgit.explosionfield.ExplosionField;

public class WelcomeActivity extends AppCompatActivity {
    private ExplosionField mExplosionField;
    private static final int NUM_PAGES = 4;

    private SCViewPager mViewPager;
    private SCViewPagerAdapter mPageAdapter;
    private DotsView mDotsView;
    private boolean bFirstTime = true;
    Intent main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.welcome_layout);

        mExplosionField = ExplosionField.attach2Window(this);
        main = new Intent(this, MainActivity.class);

        if (!bFirstTime) {
            startActivity(main);
        }

        mViewPager = (SCViewPager) findViewById(R.id.viewpager_main_activity);
        mDotsView = (DotsView) findViewById(R.id.dotsview_main);
        mDotsView.setDotRessource(R.drawable.dot_selected, R.drawable.dot_unselected);
        mDotsView.setNumberOfPage(NUM_PAGES);

        mPageAdapter = new SCViewPagerAdapter(getSupportFragmentManager());
        mPageAdapter.setNumberOfPage(NUM_PAGES);
        mPageAdapter.setFragmentBackgroundColor(R.color.theme_100);
        mViewPager.setAdapter(mPageAdapter);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mDotsView.selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        final Point size = SCViewAnimationUtil.getDisplaySize(this);

        View welcome = findViewById(R.id.welcome);
        SCViewAnimation welcomeAnimation = new SCViewAnimation(welcome);
        welcomeAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 0, -size.y / 2));
        mViewPager.addAnimation(welcomeAnimation);

        View ant = findViewById(R.id.ant);
        SCViewAnimation antAnimation = new SCViewAnimation(ant);
        antAnimation.addPageAnimation(new SCPositionAnimation(this, 0, -size.x, 0));
        mViewPager.addAnimation(antAnimation);

        View appLogo = findViewById(R.id.app_logo);
        SCViewAnimationUtil.prepareViewToGetSize(appLogo);
        SCViewAnimation appLogoAnimation = new SCViewAnimation(appLogo);
        appLogoAnimation.addPageAnimation(new SCPositionAnimation(this.getApplicationContext(), 0, 0, -(size.y - appLogo.getHeight() - 160)));
        appLogoAnimation.addPageAnimation(new SCPositionAnimation(this.getApplicationContext(), 1, -size.x, 0));
        mViewPager.addAnimation(appLogoAnimation);

        View daysNumber = findViewById(R.id.days_number);
        SCViewAnimation daysNumberAnimation = new SCViewAnimation(daysNumber);
        daysNumberAnimation.startToPosition((int) (size.x * 1.5), null);
        daysNumberAnimation.addPageAnimation(new SCPositionAnimation(this, 0, -(int) (size.x * 1.5), 0));
        daysNumberAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -(int) (size.x * 1.5), 0));
        mViewPager.addAnimation(daysNumberAnimation);

        View daysVegan = findViewById(R.id.days_vegan);
        SCViewAnimation daysVeganAnimation = new SCViewAnimation(daysVegan);
        daysVeganAnimation.startToPosition(null, -size.y);
        daysVeganAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 0, size.y));
        daysVeganAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 0, size.y));
        mViewPager.addAnimation(daysVeganAnimation);

        View catImage = findViewById(R.id.cat);
        SCViewAnimation catAnimation = new SCViewAnimation(catImage);
        catAnimation.startToPosition(null, -size.y);
        catAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 0, size.y));
        catAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 0, size.y));
        mViewPager.addAnimation(catAnimation);

        catImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View dogImage = findViewById(R.id.dog);
        SCViewAnimation dogAnimation = new SCViewAnimation(dogImage);
        dogAnimation.startToPosition(null, -size.y);
        dogAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 750, size.y));
        dogAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 500, 100));
        mViewPager.addAnimation(dogAnimation);

        dogImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View cowImage = findViewById(R.id.cow);
        SCViewAnimation cowAnimation = new SCViewAnimation(cowImage);
        cowAnimation.startToPosition(null, -size.y);
        cowAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        cowAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        cowAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -900, -520));
        mViewPager.addAnimation(cowAnimation);

        cowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View crabImage = findViewById(R.id.crab);
        SCViewAnimation crabAnimation = new SCViewAnimation(crabImage);
        crabAnimation.startToPosition(null, -size.y);
        crabAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        crabAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        crabAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -300, -200));
        mViewPager.addAnimation(crabAnimation);

        crabImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View elephantImage = findViewById(R.id.elephant);
        SCViewAnimation elephantAnimation = new SCViewAnimation(elephantImage);
        elephantAnimation.startToPosition(null, -size.y);
        elephantAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        elephantAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        elephantAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -500, -200));
        mViewPager.addAnimation(elephantAnimation);

        elephantImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View leopardImage = findViewById(R.id.leopard);
        SCViewAnimation leopardAnimation = new SCViewAnimation(leopardImage);
        leopardAnimation.startToPosition(null, -size.y);
        leopardAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        leopardAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        leopardAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -700, -200));
        mViewPager.addAnimation(leopardAnimation);

        leopardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View dolphinImage = findViewById(R.id.dolphin);
        SCViewAnimation dolphinAnimation = new SCViewAnimation(dolphinImage);
        dolphinAnimation.startToPosition(null, -size.y);
        dolphinAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        dolphinAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        dolphinAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -900, -200));
        mViewPager.addAnimation(dolphinAnimation);

        dolphinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View duckImage = findViewById(R.id.duck);
        SCViewAnimation duckAnimation = new SCViewAnimation(duckImage);
        duckAnimation.startToPosition(null, -size.y);
        duckAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        duckAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        duckAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -1100, -200));
        mViewPager.addAnimation(duckAnimation);

        duckImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View beaverImage = findViewById(R.id.beaver);
        SCViewAnimation beaverAnimation = new SCViewAnimation(beaverImage);
        beaverAnimation.startToPosition(null, -size.y);
        beaverAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        beaverAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        beaverAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -300, 200));
        mViewPager.addAnimation(beaverAnimation);

        beaverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View whaleImage = findViewById(R.id.whale);
        SCViewAnimation whaleAnimation = new SCViewAnimation(whaleImage);
        whaleAnimation.startToPosition(null, -size.y);
        whaleAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        whaleAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        whaleAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -500, 200));
        mViewPager.addAnimation(whaleAnimation);

        whaleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View deerImage = findViewById(R.id.deer);
        SCViewAnimation deerAnimation = new SCViewAnimation(deerImage);
        deerAnimation.startToPosition(null, -size.y);
        deerAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        deerAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        deerAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -700, 200));
        mViewPager.addAnimation(deerAnimation);

        deerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View gorillaImage = findViewById(R.id.gorilla);
        SCViewAnimation gorillaAnimation = new SCViewAnimation(gorillaImage);
        gorillaAnimation.startToPosition(null, -size.y);
        gorillaAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        gorillaAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        gorillaAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -900, 200));
        mViewPager.addAnimation(gorillaAnimation);

        gorillaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });


        View rhynoImage = findViewById(R.id.rhyno);
        SCViewAnimation rhynoAnimation = new SCViewAnimation(rhynoImage);
        rhynoAnimation.startToPosition(null, -size.y);
        rhynoAnimation.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 200));
        rhynoAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 100, size.y));
        rhynoAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -1100, 170));
        mViewPager.addAnimation(rhynoAnimation);

        rhynoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View chickenImage = findViewById(R.id.chicken);
        SCViewAnimation chickenAnimation = new SCViewAnimation(chickenImage);
        chickenAnimation.startToPosition(null, -size.y);
        chickenAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 0, 250));
        chickenAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 50, size.y - 50));
        chickenAnimation.addPageAnimation(new SCPositionAnimation(this, 2, 700, size.y));
        mViewPager.addAnimation(chickenAnimation);

        chickenImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View sheepImage = findViewById(R.id.sheep);
        SCViewAnimation sheepAnimation = new SCViewAnimation(sheepImage);
        sheepAnimation.startToPosition(null, -size.y);
        sheepAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -400, size.y - 350));
        mViewPager.addAnimation(sheepAnimation);

        sheepImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });



        View kangarooImage = findViewById(R.id.kangaroo);
        SCViewAnimation kangarooAnimation = new SCViewAnimation(kangarooImage);
        kangarooAnimation.startToPosition(null, -size.y);
        kangarooAnimation.addPageAnimation(new SCPositionAnimation(this, 2, 0, size.y - 350));
        mViewPager.addAnimation(kangarooAnimation);

        kangarooImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View turtleImage = findViewById(R.id.turtle);
        SCViewAnimation turtleAnimation = new SCViewAnimation(turtleImage);
        turtleAnimation.startToPosition(null, -size.y);
        turtleAnimation.addPageAnimation(new SCPositionAnimation(this, 2, 200, size.y - 350));
        mViewPager.addAnimation(turtleAnimation);

        turtleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View aliggatorImage = findViewById(R.id.aliggator);
        SCViewAnimation aliggatorAnimation = new SCViewAnimation(aliggatorImage);
        aliggatorAnimation.startToPosition(null, -size.y);
        aliggatorAnimation.addPageAnimation(new SCPositionAnimation(this, 2, 400, size.y - 350));
        mViewPager.addAnimation(aliggatorAnimation);

        aliggatorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });

        View yourGoal = findViewById(R.id.your_goal);
        SCViewAnimation yourGoalAnimation = new SCViewAnimation(yourGoal);
        yourGoalAnimation.startToPosition(size.x, null);
        yourGoalAnimation.addPageAnimation(new SCPositionAnimation(this, 0, -size.x, 0));
        yourGoalAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        mViewPager.addAnimation(yourGoalAnimation);

        View soundsHard = findViewById(R.id.sounds_hard);
        SCViewAnimation soundsHardAnimation = new SCViewAnimation(soundsHard);
        soundsHardAnimation.startToPosition(size.x, null);
        soundsHardAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        soundsHardAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -size.x, 0));
        mViewPager.addAnimation(soundsHardAnimation);

        View notAlone = findViewById(R.id.not_alone);
        SCViewAnimation notAloneAnimation = new SCViewAnimation(notAlone);
        notAloneAnimation.startToPosition((size.x * 2), null);
        notAloneAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x * 2, 0));
        notAloneAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -size.x * 2, 0));
        mViewPager.addAnimation(notAloneAnimation);

        View friendsImage = findViewById(R.id.friends_list);
        SCViewAnimation friendsAnimation = new SCViewAnimation(friendsImage);
        friendsAnimation.startToPosition((size.x * 2), null);
        friendsAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x * 2, 0));
        friendsAnimation.addPageAnimation(new SCPositionAnimation(this, 2, 0, -size.y));
        mViewPager.addAnimation(friendsAnimation);

        View thereIsMore = findViewById(R.id.there_is_more);
        SCViewAnimation thereIsMoreAnimation = new SCViewAnimation(thereIsMore);
        thereIsMoreAnimation.startToPosition(size.x, null);
        thereIsMoreAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        thereIsMoreAnimation.addPageAnimation(new SCPositionAnimation(this, 2, 0, size.y));
        mViewPager.addAnimation(thereIsMoreAnimation);

        View challengeYourFriends = findViewById(R.id.challenge);
        SCViewAnimation challengeYourFriendsAnimation = new SCViewAnimation(challengeYourFriends);
        challengeYourFriendsAnimation.startToPosition(null, -size.y);
        challengeYourFriendsAnimation.addPageAnimation(new SCPositionAnimation(this, 2, 20, size.y));
        challengeYourFriendsAnimation.addPageAnimation(new SCPositionAnimation(this, 3, -size.x, 0));
        mViewPager.addAnimation(challengeYourFriendsAnimation);

        View collectAnimals = findViewById(R.id.collect_animals);
        SCViewAnimation collectAnimalsAnimation = new SCViewAnimation(collectAnimals);
        collectAnimalsAnimation.startToPosition(size.x * 2, null);
        collectAnimalsAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -size.x * 2, 100));
        collectAnimalsAnimation.addPageAnimation(new SCPositionAnimation(this, 3, -size.x, 100));
        mViewPager.addAnimation(collectAnimalsAnimation);

        View dailyNotification = findViewById(R.id.daily_notification);
        SCViewAnimation dailyNotificationAnimation = new SCViewAnimation(dailyNotification);
        dailyNotificationAnimation.startToPosition(-size.x, null);
        dailyNotificationAnimation.addPageAnimation(new SCPositionAnimation(this, 2, size.x, 180));
        dailyNotificationAnimation.addPageAnimation(new SCPositionAnimation(this, 3, -size.x, 2000));
        mViewPager.addAnimation(dailyNotificationAnimation);

        final View getStarted = findViewById(R.id.get_started);
        SCViewAnimation getStartedAnimation = new SCViewAnimation(getStarted);
        getStartedAnimation.startToPosition((int) (size.x * 1.5), null);
        getStartedAnimation.addPageAnimation(new SCPositionAnimation(this, 2, -(int) (size.x * 1.5) - 80, 200));
        getStartedAnimation.addPageAnimation(new SCPositionAnimation(this, 3, -size.x - 100, 0));
        mViewPager.addAnimation(getStartedAnimation);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mExplosionField.explode(getStarted);
                startActivity(main);
            }
        });

    }
}
