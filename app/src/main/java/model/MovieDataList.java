package model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/11/2015.
 */
public class MovieDataList  {
    private List<Result> mResultsArrayList = new ArrayList<Result>();
    static  MovieDataList sMovieDataList;
    Context mContext;
    MovieDataList(Context context){
        mContext= context;
    }
    public static MovieDataList get(Context context){
        if(sMovieDataList==null){
            sMovieDataList = new MovieDataList(context);
        }
        return  sMovieDataList;
    }
    public List<Result>  getResultsArrayList(){
        return mResultsArrayList;
    }
    public  void  setResultsArrayList(List<Result> resultsList){
        for (Result Result:resultsList)
            mResultsArrayList.add(Result);
    }
    public void setSingleResult(Result result){
        mResultsArrayList.add(result);
    }
    public Result getSingleResultByPosition(int pos){
        return mResultsArrayList.get(pos);
    }
    public void  clearList(){
        mResultsArrayList.clear();
    }
}
