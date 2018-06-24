package HttpCallUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private Context context;

    private RequestQueue requestQueue;
    private static VolleySingleton httpRequestInstance;
    private ImageLoader imageLoader;


    public VolleySingleton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache(){

                    private final LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                            cache.put(url, bitmap);
                    }
                }
                );
    }


    public static synchronized VolleySingleton getInstance(Context context){
        if(httpRequestInstance == null){
            httpRequestInstance = new VolleySingleton(context);
        }
        return httpRequestInstance;
    }


    public RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

    public ImageLoader getImageLoader(){
        return this.imageLoader;
    }
}
