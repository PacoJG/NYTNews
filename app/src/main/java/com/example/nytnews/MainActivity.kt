package com.example.nytnews

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytnews.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnItemClickListener{

    private lateinit var adapter: ArticlesAdapter
    private lateinit var binding: ActivityMainBinding
    private val news = mutableListOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getArticles()
    }

    private fun getArticles()
    {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<NYTResponse> = getRetrofit().create(APIService::class.java).getMostPopular("7.json?api-key=gP9Af4KJsMk1kFyykhbJCvSTD1bL0xQV")
            val nYTResponse: NYTResponse? =  call.body()
            runOnUiThread {
                if(call.isSuccessful)
                {
                    val articles: List<Article> = nYTResponse?.results ?: emptyList()
                    if(!articles.isEmpty())
                    {
                        news.clear()
                        news.addAll(articles)
                        adapter.notifyDataSetChanged()
                        binding.browser1.loadUrl(articles[0].url)
                    }
                }
            }
        }

    }

    private fun initRecyclerView(){

        adapter = ArticlesAdapter(news,this)

        binding.barraNoticias.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        binding.barraNoticias.adapter = adapter

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/emailed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    override fun onItemClick(noticia: Article) {
        binding.browser1.loadUrl(noticia.url)
    }
}