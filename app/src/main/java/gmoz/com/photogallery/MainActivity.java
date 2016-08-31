package gmoz.com.photogallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listImageView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listImageView = (ListView) findViewById(R.id.image_list);

        MainService service = new MainService();
        service.getPhotos(this, this);
    }


    /**
     * After receipt photos from server
     *
     * @param response Server response
     */
    public void onGetPhotosResult(String response) {
        List<String> names = new ArrayList<>();
        List<String> profileUrls = new ArrayList<>();
        List<String> photoUrls = new ArrayList<>();

        //watingDialog.dismiss(); // stop "waiting dialog"

        if (response != null) { // Internet is OK --> can access to server
            try {
                JSONObject result = new JSONObject(response);
                JSONArray photos = result.getJSONArray("results");
                Log.i("JSONArray Photos", photos.toString());
                Cache.photos = photos;

                for (int i = 0; i < photos.length(); i++) {
                    JSONObject json = photos.getJSONObject(i);
                    String name = json.getJSONObject("user").getString("name");
                    names.add(name);
                    String profileUrl = json.getJSONObject("user").getJSONObject("profile_image").getString("small");
                    profileUrls.add(profileUrl);
                    String photoUrl = json.getJSONObject("urls").getString("thumb");
                    photoUrls.add(photoUrl);

                    adapter = new ListAdapter(this, names, profileUrls, photoUrls);
                    listImageView.setAdapter(adapter);
                }
                //refreshListView();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else { // Internet is NOT OK --> cannot access to server
            Toast toast = Toast.makeText(this, "Internet have problem, please try again", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
    }

    public void refreshListView() {
//        adapter = new ListAdapter(this, Cache.names, Cache.avatarUrls, photoUrls);
//        listImageView.setAdapter(adapter);
    }
}
