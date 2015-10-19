package com.savosh.exx.exx14;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.savosh.exx.R;
import com.savosh.exx.exx14.fragment.AsymmetricLayoutFragment;
import com.savosh.exx.exx14.fragment.TextFragment;

import java.util.ArrayList;
import java.util.List;

public class TabsActivity extends AppCompatActivity {

    public static final String EXTRA_MODE = "com.savosh.exx.exx14.TabsActivity.extra_mode";

    public static final int MODE_FIXED_TABS = 1;
    public static final int MODE_SCROLLABLE_TABS = 2;
    public static final int MODE_TABS_WITH_ICON_AND_TEXT = 3;
    public static final int MODE_TABS_WITH_ONLY_ICON = 4;
    public static final int MODE_CUSTOM_TABS = 5;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mode = getIntent().getIntExtra(EXTRA_MODE, 1);

        if(mode == MODE_FIXED_TABS || mode == MODE_TABS_WITH_ICON_AND_TEXT || mode == MODE_TABS_WITH_ONLY_ICON) {
            setContentView(R.layout.exx14_fixed_tabs_activity);
        } else if(mode == MODE_SCROLLABLE_TABS){
            setContentView(R.layout.exx14_scrollable_tabs_activity);
        } else if(mode == MODE_CUSTOM_TABS){
            setContentView(R.layout.exx14_custom_tabs_activity);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if(mode == MODE_TABS_WITH_ICON_AND_TEXT || mode == MODE_TABS_WITH_ONLY_ICON){
            setupTabIcons();
        }
        if(mode == MODE_CUSTOM_TABS){
            setupCustomTabViews();
        }
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(android.R.drawable.ic_dialog_info);
        tabLayout.getTabAt(1).setIcon(android.R.drawable.ic_dialog_alert);
        tabLayout.getTabAt(2).setIcon(android.R.drawable.ic_dialog_dialer);
        tabLayout.getTabAt(2).setIcon(android.R.drawable.ic_dialog_map);
    }

    private void setupCustomTabViews(){
        tabLayout.getTabAt(0).setCustomView(createCustomView(android.R.drawable.ic_dialog_info, "ONE"));
        tabLayout.getTabAt(1).setCustomView(createCustomView(android.R.drawable.ic_dialog_alert, "TWO"));
        tabLayout.getTabAt(2).setCustomView(createCustomView(android.R.drawable.ic_dialog_dialer, "THREE"));
        tabLayout.getTabAt(2).setCustomView(createCustomView(android.R.drawable.ic_dialog_map, "FOUR"));
    }

    private View createCustomView(int icId, String text){
        TextView tab = (TextView) LayoutInflater.from(this).inflate(R.layout.exx14_custom_tab, null);
        tab.setText(text);
        tab.setCompoundDrawablesWithIntrinsicBounds(0, icId, 0, 0);
        return tab;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(TextFragment.create("One"), "ONE");
        adapter.addFragment(TextFragment.create("Two"), "TWO");
        adapter.addFragment(TextFragment.create("Three"), "THREE");
        adapter.addFragment(new AsymmetricLayoutFragment(), "FOUR");
        if(mode == MODE_SCROLLABLE_TABS) {
            adapter.addFragment(TextFragment.create("Four"), "FOUR");
            adapter.addFragment(TextFragment.create("Five"), "FIVE");
            adapter.addFragment(TextFragment.create("Six"), "SIX");
            adapter.addFragment(TextFragment.create("Seven"), "SEVEN");
            adapter.addFragment(TextFragment.create("Eight"), "EIGHT");
            adapter.addFragment(TextFragment.create("Nine"), "NINE");
            adapter.addFragment(TextFragment.create("Ten"), "TEN");
            adapter.addFragment(TextFragment.create("Eleven"), "ELEVEN");
        }
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(mode == MODE_TABS_WITH_ONLY_ICON){
                return null;
            }
            return mFragmentTitleList.get(position);
        }
    }

}
