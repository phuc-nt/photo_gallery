package gmoz.com.photogallery.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import gmoz.com.photogallery.R;

/**
 * Created by PhucNT on 16/August/31.
 */
public class FullScreenImageAdapter extends PagerAdapter {
    private Activity context;
    private List<String> photoUrls;
    private LayoutInflater inflater;

    // constructor
    public FullScreenImageAdapter(Activity activity,
                                  List<String> photoUrls) {
        this.context = activity;
        this.photoUrls = photoUrls;
    }

    @Override
    public int getCount() {
        return this.photoUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;
        Button btnClose;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.full_image_display);
        btnClose = (Button) viewLayout.findViewById(R.id.full_image_close);

        Picasso.with(context).load(photoUrls.get(position)).into(imgDisplay);

        // close button click event
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });

        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
