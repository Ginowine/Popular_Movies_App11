package model.Review;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 8/27/2015.
 */
public class Review implements Parcelable{

    private String id;
    private String author;
    private String content;
    private String url;
    private String detailPoster;
    private String movieRelease;
    private String movieTitle;
    private String movieOverview;
    private String movieRating;
    private String movieRatingValue;
    private String name;
    private String size;
    private String source;
    private String type;

    public static final Creator<Review> CREATOR = new Creator<Review>(){

        @Override
        public Review createFromParcel(Parcel parcel) {
            return new Review(parcel);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public Review(String id, String author, String title, String content, String url, String poster, String releaseDate, String overview, String user_rating, String user_rating_value, String name, String size, String source, String type){
        this.id = id;
        this.author = author;
        this.detailPoster = poster;
        this.movieRating = user_rating;
        this.movieOverview = overview;
        this.movieRelease = releaseDate;
        this.size = size;
        this.source = source;
        this.name = name;
        this.type = type;
        this.movieRatingValue = user_rating_value;
        this.movieTitle = title;
        this.content = content;
        this.url = url;
    }

    public Review(){

    }

    protected Review(Parcel parcel){

        id = parcel.readString();
        author = parcel.readString();
        detailPoster = parcel.readString();
        movieRating = parcel.readString();
        movieOverview = parcel.readString();
        movieRelease = parcel.readString();
        size = parcel.readString();
        source = parcel.readString();
        name = parcel.readString();
        type = parcel.readString();
        movieRatingValue = parcel.readString();
        movieTitle = parcel.readString();
        url = parcel.readString();
        content = parcel.readString();
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getDetailPoster() {
        return detailPoster;
    }

    public void setDetailPoster(String detailPoster) {
        this.detailPoster = detailPoster;
    }

    public String getMovieRelease() {
        return movieRelease;
    }

    public void setMovieRelease(String movieRelease) {
        this.movieRelease = movieRelease;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieRatingValue() {
        return movieRatingValue;
    }

    public void setMovieRatingValue(String movieRatingValue) {
        this.movieRatingValue = movieRatingValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(author);
        parcel.writeString(detailPoster);
        parcel.writeString(movieRating);
        parcel.writeString(movieOverview);
        parcel.writeString(movieRelease);
        parcel.writeString(size);
        parcel.writeString(source);
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeString(movieRatingValue);
        parcel.writeString(movieTitle);
        parcel.writeString(content);
        parcel.writeString(url);
    }
}
