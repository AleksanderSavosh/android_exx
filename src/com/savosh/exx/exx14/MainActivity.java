package com.savosh.exx.exx14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.savosh.exx.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exx14_main_activity);
    }

    public void click(View v){
        Intent intent = new Intent(this, TabsActivity.class);
        switch (v.getId()){
            case R.id.exx14_btn_fixed_tabs:
                intent.putExtra(TabsActivity.EXTRA_MODE, TabsActivity.MODE_FIXED_TABS);
                break;
            case R.id.exx14_btn_scrollable_tabs:
                intent.putExtra(TabsActivity.EXTRA_MODE, TabsActivity.MODE_SCROLLABLE_TABS);
                break;
            case R.id.exx14_btn_tabs_with_icon_and_text:
                intent.putExtra(TabsActivity.EXTRA_MODE, TabsActivity.MODE_TABS_WITH_ICON_AND_TEXT);
                break;
            case R.id.exx14_btn_tabs_with_only_icon:
                intent.putExtra(TabsActivity.EXTRA_MODE, TabsActivity.MODE_TABS_WITH_ONLY_ICON);
                break;
            case R.id.exx14_btn_custom_tabs:
                intent.putExtra(TabsActivity.EXTRA_MODE, TabsActivity.MODE_CUSTOM_TABS);
                break;
        }
        startActivity(intent);
    }

}
