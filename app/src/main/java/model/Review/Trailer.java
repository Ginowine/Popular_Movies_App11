package model.Review;

/**
 * Created by user on 8/20/2015.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trailer {

    private List<Object> quicktime = new ArrayList<Object>();
    private List<Youtube> youtube = new ArrayList<Youtube>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The quicktime
     */
    public List<Object> getQuicktime() {
        return quicktime;
    }

    /**
     *
     * @param quicktime
     * The quicktime
     */
    public void setQuicktime(List<Object> quicktime) {
        this.quicktime = quicktime;
    }

    /**
     *
     * @return
     * The youtube
     */
    public List<Youtube> getYoutube() {
        return youtube;
    }

    /**
     *
     * @param youtube
     * The youtube
     */
    public void setYoutube(List<Youtube> youtube) {
        this.youtube = youtube;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
