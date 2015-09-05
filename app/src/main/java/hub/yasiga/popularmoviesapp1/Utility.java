package hub.yasiga.popularmoviesapp1;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by user on 8/24/2015.
 */
public class Utility {

    public static String getPreferedMovieSorted(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String sort_order = preferences.getString(context.getString(R.string.pref_sort_order_key),
                context.getString(R.string.pref_sort_order_default));


        return sort_order;

    }

    //http://stackoverflow.com/questions/17674634/saving-images-to-internal-memory-in-android
    public static String saveToInternalSorage(Bitmap bitmapImage, String posterName ,  Context c) {
        ContextWrapper cw = new ContextWrapper(c);

        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        String imgName = posterName + ".png";

        File mypath = new File(directory, imgName);

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }


    public static Bitmap loadImageFromStorage(String path, String ImageName) {
        String name = ImageName + ".png";
        Bitmap b = null;
        try {
            File f = new File(path, name);
            b = BitmapFactory.decodeStream(new FileInputStream(f));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return b;

    }
}
