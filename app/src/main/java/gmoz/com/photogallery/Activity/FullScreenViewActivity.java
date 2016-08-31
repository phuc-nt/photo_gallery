package gmoz.com.photogallery.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import gmoz.com.photogallery.Adapter.FullScreenImageAdapter;
import gmoz.com.photogallery.R;
import gmoz.com.photogallery.Utils.Cache;

public class FullScreenViewActivity extends Activity {

    private FullScreenImageAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_view);

        viewPager = (ViewPager) findViewById(R.id.fullscreen_pager);


        Intent i = getIntent();
        int position = i.getIntExtra("position", 0);

        adapter = new FullScreenImageAdapter(FullScreenViewActivity.this,
                Cache.fullImageUrls);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);
    }
}
