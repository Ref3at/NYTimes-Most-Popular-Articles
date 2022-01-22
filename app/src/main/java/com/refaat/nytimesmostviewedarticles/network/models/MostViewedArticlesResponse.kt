package com.refaat.nytimesmostviewedarticles.network.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class MostViewedArticlesResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    @SerializedName("num_results")
    @Expose
    var numResults: Int? = null

    @SerializedName("results")
    @Expose
    var articleItems: List<ArticleItem>? = null
}