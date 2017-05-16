package com.jnjcomu.edison.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-12
 */

public class Region {
    @SerializedName("region") int regionID;
    @SerializedName("name") String placeName;

    public Region(int regionID, String placeName) {
        this.regionID = regionID;
        this.placeName = placeName;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
