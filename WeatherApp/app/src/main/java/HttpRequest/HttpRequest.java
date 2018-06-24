package HttpRequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class HttpRequest{

    private Context context;

    private RequestQueue requestQueue;
    private static HttpRequest httpRequestInstance;


    public HttpRequest(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }


    public static synchronized HttpRequest getInstance(Context context){
        if(httpRequestInstance == null){
            httpRequestInstance = new HttpRequest(context);
        }
        return httpRequestInstance;
    }


    public RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request <T> request){
        getRequestQueue().add(request);
    }



    /*public  StringRequest makeHttpRequest(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                ApiCall.url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        HttpResult result = new HttpResult(response);
                        Log.e(TAG, "onResponse: "+response );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        return stringRequest;*/
    }


