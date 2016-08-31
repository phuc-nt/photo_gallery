package gmoz.com.photogallery.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import gmoz.com.photogallery.Activity.FullScreenViewActivity;
import gmoz.com.photogallery.R;

/**
 * Created by PhucNT on 16/August/31.
 */
public class ListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> names;
    private final List<String> profileUrls;
    private final List<String> photoUrls;
    private final List<String> categories;

    public ListAdapter(Activity context, List<String> names, List<String> profileUrls, List<String> photoUrls, List<String> categories) {
        super(context, R.layout.list_image, names);
        this.context = context;
        this.names = names;
        this.profileUrls = profileUrls;
        this.photoUrls = photoUrls;
        this.categories = categories;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_image, null, true);

        TextView userName = (TextView) rowView.findViewById(R.id.txt_name);
        TextView category = (TextView) rowView.findViewById(R.id.txt_category);
        ImageView userProfile = (ImageView) rowView.findViewById(R.id.img_user);
        final ImageView photo = (ImageView) rowView.findViewById(R.id.img_photo);

        userName.setText(names.get(position));
        category.setText(categories.get(position));
        Picasso.with(context).load(profileUrls.get(position)).into(userProfile);
        Picasso.with(context).load(photoUrls.get(position)).into(photo);

        final boolean[] zoomOut = {false};

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, FullScreenViewActivity.class);
                i.putExtra("position", position);
                context.startActivity(i);
            }
        });

        return rowView;
    }
}
