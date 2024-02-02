package edu.northeastern.nomscape.network

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipesApiClient {

    private const val BASE_URL = "https://tasty.p.rapidapi.com/recipes/"
    private const val RECIPE_DETAIL_URL = BASE_URL + "get-more-info?id="

    private fun getRetrofitClient(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            requestBuilder.addHeader(
                "X-RapidAPI-Key",
                "7b2e3f920dmshbf974c793627a42p125168jsna4b43c280c4d"
            )
            requestBuilder.addHeader("X-RapidAPI-Host", "tasty.p.rapidapi.com")
            chain.proceed(requestBuilder.build())
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    fun getRecipesClient(): RecipesApiInterface {
        return getRetrofitClient().create(RecipesApiInterface::class.java)
    }
}
