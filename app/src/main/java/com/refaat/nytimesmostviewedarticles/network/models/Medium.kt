package com.refaat.nytimesmostviewedarticles.network.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.refaat.nytimesmostviewedarticles.network.models.MediaMetadatum

class Medium {
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("subtype")
    @Expose
    var subtype: String? = null

    @SerializedName("caption")
    @Expose
    var caption: String? = null

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    @SerializedName("approved_for_syndication")
    @Expose
    var approvedForSyndication: Int? = null

    @SerializedName("media-metadata")
    @Expose
    var mediaMetadata: List<MediaMetadatum>? = null
}