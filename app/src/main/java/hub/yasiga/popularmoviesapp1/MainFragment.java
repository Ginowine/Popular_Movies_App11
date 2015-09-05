package hub.yasiga.popularmoviesapp1;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.MoviePOJO;
import model.Result;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SORT_BY = "popularity.desc";
    private static final String API_KEY = "232c7933fb923517762fbaba80f80ba9";

    // TODO: Rename and change types of parameters
    private SharedPreferences mSharedPreferences;
    private String mParam2;
    GridView gridView;



    public ProgressBar mProgressBar;

    private List<Result> result = new ArrayList<Result>();

    private final String MOVIES_KEY="result";

    public Bundle bundle;

    private boolean mIsDownloadInProgress = false;

    private String QUERY_TYPE;

    //private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private PopularMoviesStreamAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainFragment() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MOVIES_KEY, (ArrayList<Result>) result);
        Log.e("MainFragment","State saved and bundle is: "+outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = savedInstanceState;

        mAdapter = new PopularMoviesStreamAdapter(getActivity());


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_grid, container, false);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrderKey = getResources().getString(R.string.pref_sort_order_key);
        QUERY_TYPE = mSharedPreferences.getString(sortOrderKey,"");
        Log.i("MainActvivity","Sorty type: "+QUERY_TYPE);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        gridView = (GridView) view.findViewById(R.id.grid);

        gridView.setOnItemClickListener(this);

        downloadData();

        return view;
    }

    //Checking for Connection so we can inflater the Layout and Handling the force Stop caused by no Internet Connection
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInterface = connectivityManager.getActiveNetworkInfo();
        return networkInterface != null;
    }

    public void downloadData(){

        if (!mIsDownloadInProgress){

            mIsDownloadInProgress = true;

            this.mProgressBar.setVisibility(View.VISIBLE);



            MovieClient.getPopularMoviesClient().getStreams(QUERY_TYPE, API_KEY, new Callback<MoviePOJO>() {
                @Override
                public void success(MoviePOJO moviePOJO, Response response) {
                    consumeApiData(moviePOJO.getResults());
                    result = moviePOJO.getResults();

                    Log.i("MainFragment", "result gotten from POJO: "+result);

                }

                @Override
                public void failure(RetrofitError error) {
                    //consumeApiData(null);

                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    Log.e("ErrorTag", error.toString());

                }
            });
        }
    }

    public void consumeApiData(List<Result> movies) {
        if (movies != null) {
            mAdapter = new PopularMoviesStreamAdapter(getActivity());

            //Log.i("MAinActivity","Movie response: "+movies.size()+" and titile is: "+moviePOJO.getResults().get(0).getTitle()+" And poster is: "+moviePOJO.getResults().get(0).getPoster_path());


            mAdapter.setData(movies);

            gridView.setAdapter(mAdapter);

            // Done loading; remove loading indicator
            mProgressBar.setVisibility(View.GONE);

            // Keep track of what page to download next
            //mState.nextPage++;
        }

        mIsDownloadInProgress = false;
    }
public static final String  INTENT_KEY = "ITEM_ID";

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Result result = (Result)mAdapter.getItem(position);
        //Reviews reviews = (Reviews)mAdapter.getItem(position);
        Long itemId = (Long)mAdapter.getItemId(position);

        Integer idValue = itemId.intValue();
;
        Intent intent = new Intent(getActivity(), MovieDetails.class);


        intent.putExtra(INTENT_KEY,idValue);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (bundle != null){
            //ArrayList<Result> result_Saved = savedInstanceState.getParcelableArrayList(MOVIES_KEY);
            result  = bundle.getParcelableArrayList(MOVIES_KEY);
            consumeApiData(result);

        }
        else {

            this.downloadData();
            Log.i("MainFragment","Downloading data from cloud");

        }


    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
