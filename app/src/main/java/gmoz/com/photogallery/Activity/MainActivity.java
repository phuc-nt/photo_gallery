package gmoz.com.photogallery.Activity;

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

import gmoz.com.photogallery.Adapter.ListAdapter;
import gmoz.com.photogallery.R;
import gmoz.com.photogallery.Service.MainService;
import gmoz.com.photogallery.Utils.Cache;
import gmoz.com.photogallery.Utils.Utils;

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
        List<String> categories = new ArrayList<>();
        List<String> fullImageUrls = new ArrayList<>();

        //watingDialog.dismiss(); // stop "waiting dialog"

        if (response != null) { // Internet is OK --> can access to server
            try {
                JSONObject result = new JSONObject(response);
                JSONArray photos = result.getJSONArray("results");
                Log.i("JSONArray Photos", photos.toString());
                Cache.photos = photos;
                StringBuilder tmp;

                for (int i = 0; i < photos.length(); i++) {
                    tmp = new StringBuilder();
                    JSONObject json = photos.getJSONObject(i);

                    String name = json.getJSONObject("user").getString("name");
                    names.add(name);

                    JSONArray listCategories = json.getJSONArray("categories");
                    for (int j = 0; j < listCategories.length(); j++) {
                        if (j == (listCategories.length() - 1)) {
                            tmp.append(listCategories.getJSONObject(j).getString("title"));
                        } else {
                            tmp.append(listCategories.getJSONObject(j).getString("title") + ", ");
                        }
                    }

                    categories.add(tmp.toString());

                    String profileUrl = json.getJSONObject("user").getJSONObject("profile_image").getString("small");
                    profileUrls.add(profileUrl);

                    String photoUrl = json.getJSONObject("urls").getString("small");
                    photoUrls.add(photoUrl);

                    String fullImageUrl = json.getJSONObject("urls").getString("full");
                    fullImageUrls.add(fullImageUrl);
                }

                adapter = new ListAdapter(this, names, profileUrls, photoUrls, categories);
                listImageView.setAdapter(adapter);

                Cache.fullImageUrls = fullImageUrls;
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
