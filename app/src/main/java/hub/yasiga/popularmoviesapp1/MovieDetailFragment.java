package hub.yasiga.popularmoviesapp1;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import model.Review.Review;
import model.Review.ReviewJson;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by user on 9/1/2015.
 */
public class MovieDetailFragment extends Fragment implements AdapterView.OnItemClickListener {

    Review review = new Review();
    private int movieId;
    private List<Review> reviews = new ArrayList<Review>();

    private static final String API_KEY = "232c7933fb923517762fbaba80f80ba9 ";
    String YOUTUBE_BASE_URL = "https://www.youtube.com/embed/";

    //List<Review> review = new ArrayList<Review>();

    String LOG_TAG = MovieDetailFragment.class.getSimpleName();

    Bitmap posterBitMap;
    private Uri mUri;
    ViewHolder holder;

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // MovieDetailFragment movie = new MovieDetailFragment();
        //Bundle bundle = new Bundle();

        Bundle bundle = getArguments();
        result = bundle.getParcelable("result");

        //bundle = getActivity().getIntent().getExtras();
        //result = (Result) bundle.getParcelable("result");

        Log.i("MovieDetails", "result is: " + result.getTitle());

        download();
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (null != arguments) {
            movieId = arguments.getInt(MainFragment.INTENT_KEY);
        }

        View view = inflater.inflate(R.layout.fragment_movies_details, container, false);

        download();

        //reviews.get(movieId)

        if (view == null){

            holder= new ViewHolder(view);

        holder.movieOverview.setText(reviews.get(movieId).getMovieOverview());
        holder.movieRelease.setText(reviews.get(movieId).getMovieRelease());
        holder.movieTitle.setText(reviews.get(movieId).getMovieTitle());


        Float rating = Float.parseFloat(reviews.get(movieId).getMovieRating());
        holder.movieRating.setRating(rating);

        holder.movieRatingValue.setText(reviews.get(movieId).getMovieRatingValue());
        holder.movieReviewsAuthor.setText(reviews.get(movieId).getAuthor());
        holder.movieReviewsContent.setText(reviews.get(movieId).getContent());


        Bitmap b =  Utility.loadImageFromStorage(reviews.get(movieId).getDetailPoster() , reviews.get(movieId).getMovieTitle());
        Log.e(LOG_TAG , "bit from local" + b) ;

        String sorted = Utility.getPreferedMovieSorted(getActivity());

        if(b != null) {
            holder.detailPoster.setImageBitmap(b);
        }
        else {
            Glide.with(this)
                    .load(reviews.get(movieId).getDetailPoster())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(50, 50) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            MovieDetailFragment.this.posterBitMap = bitmap;
                            holder.detailPoster.setImageBitmap(bitmap);
                        }
                    });

        }


        view.setTag(holder);


    }
    else {

        holder = (ViewHolder) view.getTag();
    }


    //final String trailer = YOUTUBE_BASE_URL + reviews.get(mov).getSource();


    /*holder.trailerBtn.setOnClickListener(new View.OnClickListener() {
        @Override

        public void onClick(View v) {

            Intent trailerIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(trailer));

            ActivityInfo activityInfo = trailerIntent.resolveActivityInfo(getActivity().getPackageManager(), trailerIntent.getFlags());

            if (activityInfo.exported) {
                startActivity(trailerIntent);
            } else {
                Toast.makeText(getActivity(), "Sorry , No Apps to play Trailer Video on your Device ", Toast.LENGTH_LONG).show();
            }
        }
    });*/
        return view;
    }

    public void download(){

        //final Integer movieId = result.getId();

        MovieClient.getPopularMoviesClient().getMovieId(movieId, API_KEY,"trailers,reviews", new Callback<ReviewJson>() {
            @Override
            public void success(ReviewJson reviewJson, Response response) {
                //LinkedTreeMap treemap = reviewJson.getResults();

                reviews = reviewJson.getResults();
                Log.i("MovieDetailFragment","Movie details from get result "+reviewJson.getResults());

                //ArrayList<LinkedTreeMap> subtree = (ArrayList<LinkedTreeMap>) treemap.get("results");

               /* for (LinkedTreeMap map :subtree){

                    review.setId(map.get("").toString());
                    review.setAuthor(map.get("author").toString());
                    review.setUrl(map.get("url").toString());
                    review.setContent(map.get("content").toString());
                    review.setDetailPoster(map.get("detailPoster").toString());
                    review.setMovieRelease(map.get("movieRelease").toString());
                    review.setMovieTitle(map.get("movieTitle").toString());
                    review.setMovieOverview(map.get("movieOverview").toString());
                    review.setMovieRating(map.get("movieRating").toString());
                    review.setMovieRatingValue(map.get("movieRatingValue").toString());
                    review.setName(map.get("name").toString());
                    review.setSize(map.get("size").toString());
                    review.setSource(map.get("source").toString());
                    review.setType(map.get("type").toString());
                }*/
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    static class ViewHolder {
        ImageView detailPoster;
        TextView movieTitle;
        TextView movieRelease;
        TextView movieOverview;
        RatingBar movieRating;
        TextView movieRatingValue;
        ImageButton trailerBtn;
        TextView movieReviewsContent;
        TextView movieReviewsAuthor;
        //ImageButton favoriteBtn;

        public ViewHolder(View v) {
            detailPoster = (ImageView) v.findViewById(R.id.detailPoster_view);
            movieTitle = (TextView) v.findViewById(R.id.movie_title_view);
            movieRelease = (TextView) v.findViewById(R.id.movie_release_view);
            movieOverview = (TextView) v.findViewById(R.id.movie_overview_view);
            movieRating = (RatingBar) v.findViewById(R.id.movieRatingBar);
            movieRatingValue = (TextView) v.findViewById(R.id.movieRatingValue);
            trailerBtn = (ImageButton) v.findViewById(R.id.play_trailer);
            movieReviewsContent = (TextView) v.findViewById(R.id.movie_reviews_content);
            movieReviewsAuthor = (TextView) v.findViewById(R.id.movie_reviews_author);
            //favoriteBtn = (ImageButton) v.findViewById(R.id.favorite_btn);
        }
    }
}
