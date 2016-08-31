package gmoz.com.photogallery;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * Created by PhucNT on 16/August/31.
 */
public class ListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> names;
    private final List<String> profileUrls;
    private final List<String> photoUrls;

    public ListAdapter(Activity context, List<String> names, List<String> profileUrls, List<String> photoUrls) {
        super(context, R.layout.list_image, names);
        this.context = context;
        this.names = names;
        this.profileUrls = profileUrls;
        this.photoUrls = photoUrls;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_image, null, true);

        TextView userName = (TextView) rowView.findViewById(R.id.txt_name);
        ImageView userProfile = (ImageView) rowView.findViewById(R.id.img_user);
        ImageView photo = (ImageView) rowView.findViewById(R.id.img_photo);

        userName.setText(names.get(position));
        Picasso.with(context).load(profileUrls.get(position)).into(userProfile);
        Picasso.with(context).load(photoUrls.get(position)).into(photo);
        return rowView;
    }
}
