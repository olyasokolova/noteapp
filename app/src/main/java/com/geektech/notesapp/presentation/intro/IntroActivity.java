package com.geektech.notesapp.presentation.intro;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.badoualy.stepperindicator.StepperIndicator;
import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.presentation.main.MainActivity;

public class IntroActivity extends AppCompatActivity
    implements View.OnClickListener {

    private static String PREF_FIRST_LAUNCH = "first_launch";

    private IntroPagerAdapter mIntroAdapter;
    private ViewPager mViewPager;
    private StepperIndicator mStepper;

    private TextView mNextBtn;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, IntroActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstLaunch()) {
            setContentView(R.layout.activity_intro);

            firstLaunch();

            init();
        } else {
            MainActivity.start(this);
            finish();
        }
    }

    //region Init

    private void init() {
        initViewPager();

        mNextBtn = findViewById(R.id.intro_next_btn);
        mNextBtn.setOnClickListener(this);
    }

    private void initViewPager() {
        mIntroAdapter = new IntroPagerAdapter(getSupportFragmentManager());

        mStepper = findViewById(R.id.intro_stepper);
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mIntroAdapter);
        mViewPager.setOffscreenPageLimit(3);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                onPageChanged(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        mStepper.setViewPager(mViewPager, mIntroAdapter.getCount());
    }

    //endregion

    //region Shared Preferences

    private boolean isFirstLaunch() {
        return App.sharedStorage.get(PREF_FIRST_LAUNCH, true);
    }

    private void firstLaunch() {
        App.sharedStorage.set(PREF_FIRST_LAUNCH, false);
    }

    //endregion

    private void onPageChanged(int position) {
        String btnText = "Next";
        if (position == 2) {
            btnText = "Finish";
        }
        mNextBtn.setText(btnText);
    }

    private void onNextClick() {
        if (mViewPager.getCurrentItem() == mIntroAdapter.getCount() - 1) {
            MainActivity.start(this);
            finish();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.intro_next_btn:
                onNextClick();
                break;
        }
    }

    public class IntroPagerAdapter extends FragmentPagerAdapter {
        private final int PAGES_COUNT = 3;

        public IntroPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //TODO: Return IntroFragment instance with target image url and title string id
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0: fragment = new IntroFirstFragment();
                    break;
                case 1: fragment = new IntroSecondFragment();
                    break;
                case 2: fragment = new IntroThirdFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return PAGES_COUNT;
        }
    }

    public static class IntroFragment extends Fragment {

        public IntroFragment() {
        }

        public static IntroFragment newInstance(
                String imageUrl,
                @StringRes int titleRes
        ) {
            IntroFragment fragment = new IntroFragment();

            //TODO: Put all values into arguments

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_intro_second, container, false);

            initView(view);

            return view;
        }

        //TODO: Initialize all data from arguments
        private void initView(View rootView) {
        }
    }
}
