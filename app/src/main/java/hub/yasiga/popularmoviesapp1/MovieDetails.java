package hub.yasiga.popularmoviesapp1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * Created by user on 9/2/2015.
 */
public class MovieDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null){
            int defaultValut = 0;
            Integer movieId = getIntent().getIntExtra(MainFragment.INTENT_KEY, defaultValut);

            Bundle bundle = new Bundle();

            bundle.putInt(MainFragment.INTENT_KEY, movieId);

            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            movieDetailFragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    .add(R.id.container1, movieDetailFragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.action_settings:

                Intent settingIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingIntent);
                break;

            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

}
