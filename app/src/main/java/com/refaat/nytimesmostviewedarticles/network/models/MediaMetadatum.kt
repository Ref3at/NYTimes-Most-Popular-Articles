package com.refaat.nytimesmostviewedarticles.network.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class MediaMetadatum {
    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("format")
    @Expose
    var format: String? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null
}