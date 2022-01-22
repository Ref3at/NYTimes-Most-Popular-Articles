package com.refaat.nytimesmostviewedarticles.network

// import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.refaat.nytimesmostviewedarticles.network.models.MostViewedArticlesResponse
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
// import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


private const val apiKey: String = "?api-key=tHV0hK9ffLL8jBlNm5XnJgVcHmiVVcPi"
private const val BASE_URL = "https://api.nytimes.com/"

enum class PeriodApiFilter(val value: Int) { LAST_DAY(1), LAST_WEEK(7), LAST_MONTH(30) }


private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val client = OkHttpClient.Builder()
    .addInterceptor(logging).readTimeout(1000, TimeUnit.SECONDS)
    .build();

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface NYTApiService {
    /**
     * Returns a Coroutine [List] of [ArticIetem] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("/svc/mostpopular/v2/viewed/{period}.json$apiKey")
    suspend fun getArticles(@Path("period") period: Int): MostViewedArticlesResponse
//    suspend fun getArticles(@Path("period") period: Int): MostViewedArticlesResponse
}


/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object NYTApi {
    val retrofitService: NYTApiService by lazy { retrofit.create(NYTApiService::class.java) }
}
