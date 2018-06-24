package Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String moviePoster;
    private String movieName;
    private String movieReleaseDate;
    private double movieRatings;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie(Parcel in){
            this.moviePoster = in.readString();
            this.movieRatings = in.readDouble();
            this.movieReleaseDate = in.readString();
            this.movieName = in.readString();


    }
    public Movie(String movieName,String moviePoster,String movieReleaseDate,double movieRatings){
        this.movieName = movieName;
        this.moviePoster = moviePoster;
        this.movieReleaseDate = movieReleaseDate;
        this.movieRatings = movieRatings;
    }

    public String getMoviePoster() {
        return moviePoster;
    }



    public String getMovieName() {
        return movieName;
    }



    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }


    public double getMovieRatings() {
        return movieRatings;
    }

    @Override
    public String toString() {
        return " Movie name :"+this.movieName+" Movie Poster :"+this.moviePoster+" Movie Release Date "+this.movieReleaseDate+" Ratings "+this.movieRatings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.movieName);
                    dest.writeString(this.moviePoster);
                    dest.writeString(this.movieReleaseDate);
                    dest.writeDouble(this.movieRatings);

    }
}
