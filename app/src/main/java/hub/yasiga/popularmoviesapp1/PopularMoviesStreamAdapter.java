package hub.yasiga.popularmoviesapp1;

/**
 * Created by user on 7/14/2015.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import model.Result;
import utils.Constants;

/**
 * Created by user on 7/7/2015.
 */
public class PopularMoviesStreamAdapter extends BaseAdapter {

    private Context mContext;

    private List<Result> mResults = new ArrayList<Result>();

    public PopularMoviesStreamAdapter(Context context){

        this.mContext = context.getApplicationContext();
    }

    public void setData(List<Result> result){
        this.mResults = result;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public Object getItem(int position) {
        return mResults.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = convertView;

        Holder holder;

        if (view == null){

            view = inflater.inflate(R.layout.grid_movies, parent, false);

            holder = new Holder(view);

            holder.mMoviePoster = (ImageView) view.findViewById(R.id.mMoviePoster);

            view.setTag(holder);
        }
        else {

            holder = (Holder) view.getTag();
        }

        Result result = mResults.get(position);

        String urlPath = Constants.IMAGE_URL_BASE_PATH+mResults.get(position).getPoster_path();
        Log.i("Popular Movies Adapter","url path of image is: "+urlPath);
        Picasso.with(mContext).load(urlPath).into(holder.mMoviePoster);


        return view;

    }
    private static final class Holder {

        public ImageView mMoviePoster;



        public Holder(View view){
            mMoviePoster = (ImageView) view.findViewById(R.id.mMoviePoster);
        }

    }
}


