package com.savosh.exx.eleven;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.savosh.exx.R;
import com.savosh.exx.eleven.model.ImageInfo;

import java.util.Hashtable;
import java.util.Map;

public class ScreenSlidePagerActivity extends FragmentActivity {

    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eleven_screen_slide_pager_activity);

        viewPager = (ViewPager) findViewById(R.id.eleven_activity_screen_slide_view_pager);
        adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(ImageInfo.currentItemPosition + ImageInfo.CACHE.size() * 100);

    }

    @Override
    public void onBackPressed() {
        Log.i(getClass().getName(),"position = " + viewPager.getCurrentItem());
        ImageInfo.currentItemPosition = viewPager.getCurrentItem() % ImageInfo.CACHE.size();
        Log.i(getClass().getName(),"calculated = " + ImageInfo.currentItemPosition);
        super.onBackPressed();
    }



    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        Map<Integer, Fragment> map = new Hashtable<Integer, Fragment>();

        @Override
        public Fragment getItem(int position) {
            Log.i(getClass().getName(),"position = " + position);
            position = position % ImageInfo.CACHE.size();

            Fragment fragment = null;

            if(!map.containsKey(position)) {
                ScreenSlidePageFragment screenSlidePageFragment = new ScreenSlidePageFragment();
                screenSlidePageFragment.setPosition(position);
                map.put(position, screenSlidePageFragment);
                fragment = screenSlidePageFragment;
            } else {
                fragment = map.get(position);
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
    }


}
