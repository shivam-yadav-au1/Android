package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shivam.movies.R;

import java.util.ArrayList;

import Model.Movie;

import static android.content.ContentValues.TAG;


public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MyViewHolder> {

    ArrayList<Movie> moviesList;
    Context context;
    LayoutInflater inflater;
    View view;
    MyViewHolder holder;
    ImageLoader imageLoader;
    String baseUrl = " http://image.tmdb.org/t/p/w185";


    public MoviesListAdapter(ArrayList<Movie> moviesList,Context context){

        this.moviesList = moviesList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        view = inflater.inflate(R.layout.item_row,parent,false);

        holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        Movie movie = moviesList.get(position);
        holder.movieName.setText(movie.getMovieName());
        holder.movieRating.setText(String.valueOf(movie.getMovieRatings()));

        holder.movieReleaseDate.setText(movie.getMovieReleaseDate());

     /*   imageLoader.get(movie.getMoviePoster(),ImageLoader.getImageListener(
            holder.networkImageView,//Server Image
                R.mipmap.ic_launcher,// Default Image
                android.R.drawable.ic_dialog_alert // Error image if requested image dose not found on server.
                ))

        );*/


     String url=baseUrl+movie.getMoviePoster();
        Log.e(TAG, "onBindViewHolder: "+url );
        Glide.with(context).load(url.trim())
                
                .thumbnail(0.5f)
                .into(holder.moviePoster)

        ;
        ;

      //  holder.networkImageView.setImageUrl(baseUrl+movie.getMoviePoster(),imageLoader);

    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: size of list in adapter  "+moviesList.size() );
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView movieName,movieReleaseDate,movieRating;

        NetworkImageView networkImageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            moviePoster = (ImageView)itemView.findViewById(R.id.movieThumbnail);
            movieName = (TextView)itemView.findViewById(R.id.movieName);
            movieReleaseDate = (TextView)itemView.findViewById(R.id.releaseDate);
            movieRating = (TextView)itemView.findViewById(R.id.rating);
        }
    }
}
