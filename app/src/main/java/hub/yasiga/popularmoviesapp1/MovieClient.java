package hub.yasiga.popularmoviesapp1;

/**
 * Created by user on 7/14/2015.
 */

import model.MoviePOJO;
import model.Review.ReviewJson;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by user on 7/7/2015.
 */
public class MovieClient {
    private static PopularMoviesInterface popularMoviesService;
    private static final String TAG = "myApp";

    public static PopularMoviesInterface getPopularMoviesClient(){

        if (popularMoviesService == null){

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.themoviedb.org")
                    .build();

            popularMoviesService = restAdapter.create(PopularMoviesInterface.class);
        }

        return popularMoviesService;
    }

    public interface PopularMoviesInterface{
        //@GET("/stream/list.json")
        //void getStreams(@Query("limit") int limit, @Query("offset") int offset, Callback<List<Result>> callback);

        @GET("/3/discover/movie")
        void getStreams(@Query("sort_by") String sort_Type, @Query("api_key") String keyVal, Callback<MoviePOJO> callback);


        /*@GET("/3/movie/{movieid}")
        public void getMovieId(@Path("movieid") String movieId, @Query("api_key") String apiKey, Callback<Reviews> callback);*/


        @GET("/3/movie/{movieid}")
        public void getMovieId(@Path("movieid")Integer movieId, @Query("api_key")String apiKey,@Query("append_to_response")String appendResponse, Callback<ReviewJson> reviewCallback);

    }
}

