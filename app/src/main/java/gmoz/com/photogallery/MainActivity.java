package gmoz.com.photogallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = (TextView) findViewById(R.id.result);

        MainService service = new MainService();
        service.getPhotos(this, this);
    }


    /**
     * After receipt photos from server
     *
     * @param response Server response
     */
    public void onGetPhotosResult(String response) {

        //watingDialog.dismiss(); // stop "waiting dialog"

        if (response != null) { // Internet is OK --> can access to server
            try {
                JSONObject result = new JSONObject(response);
                JSONArray photos = result.getJSONArray("results");
                Log.i("JSONArray Photos", photos.toString());
                Cache.photos = photos;

                for (int i = 0; i < photos.length(); i++) {
                    JSONObject json = photos.getJSONObject(i);
                    String photoUrl = json.getJSONObject("urls").getString("small");
                    resultTv.append('\n' + photoUrl + '\n');
                }
                //resultTv.setText(photos.toString());
                refreshListView();

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
//        adapter = new ShipmentListAdapter(getActivity(), Cache.shipments);
//        listView.setAdapter(adapter);
    }
}
