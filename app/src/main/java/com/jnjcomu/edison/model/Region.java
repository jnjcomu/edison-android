package com.jnjcomu.edison.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-05-12
 */

public class Region {
    @SerializedName("region") int regionID;
    @SerializedName("name") int placeName;

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public int getPlaceName() {
        return placeName;
    }

    public void setPlaceName(int placeName) {
        this.placeName = placeName;
    }
}
