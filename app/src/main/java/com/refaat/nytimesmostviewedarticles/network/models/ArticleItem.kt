package com.refaat.nytimesmostviewedarticles.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.refaat.nytimesmostviewedarticles.network.models.Medium
import kotlinx.android.parcel.Parcelize

@Parcelize
class ArticleItem : Parcelable{
    @SerializedName("uri")
    @Expose
    var uri: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("id")
    @Expose
    var id: Long? = null

    @SerializedName("asset_id")
    @Expose
    var assetId: Long? = null

    @SerializedName("source")
    @Expose
    var source: String? = null

    @SerializedName("published_date")
    @Expose
    var publishedDate: String? = null

    @SerializedName("updated")
    @Expose
    var updated: String? = null

    @SerializedName("section")
    @Expose
    var section: String? = null

    @SerializedName("subsection")
    @Expose
    var subsection: String? = null

    @SerializedName("nytdsection")
    @Expose
    var nytdsection: String? = null

    @SerializedName("adx_keywords")
    @Expose
    var adxKeywords: String? = null

    @SerializedName("column")
    @Expose
    var column: Any? = null

    @SerializedName("byline")
    @Expose
    var byline: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("abstract")
    @Expose
    var abstract: String? = null

    @SerializedName("des_facet")
    @Expose
    var desFacet: List<String>? = null

    @SerializedName("org_facet")
    @Expose
    var orgFacet: List<String>? = null

    @SerializedName("per_facet")
    @Expose
    var perFacet: List<String>? = null

    @SerializedName("geo_facet")
    @Expose
    var geoFacet: List<String>? = null

    @SerializedName("media")
    @Expose
    var media: List<Medium>? = null

    @SerializedName("eta_id")
    @Expose
    var etaId: Int? = null
}