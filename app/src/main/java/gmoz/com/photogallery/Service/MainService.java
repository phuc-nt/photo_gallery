package gmoz.com.photogallery.Service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import gmoz.com.photogallery.Activity.MainActivity;
import gmoz.com.photogallery.Utils.Constants;
import gmoz.com.photogallery.Utils.Utils;

/**
 * Created by PhucNT on 16/August/31.
 */
public class MainService {
    private RequestQueue requestqueue;
    private final String APIUrl = Constants.APIUrl;

    public MainService() {
    }

    public String getPhotos(Context context, final MainActivity parent) {

        String result = null;

        String url = APIUrl;

        Log.i("(MainService) get photos url", url);

        requestqueue = Volley.newRequestQueue(context);

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        String r = "";
                        try {
                            r = URLDecoder.decode(URLEncoder.encode(response, "iso8859-1"), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        parent.onGetPhotosResult(r);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        parent.onGetPhotosResult(null);
                    }
                }
        );

        requestqueue.add(postRequest);
        return result;
    }
}
