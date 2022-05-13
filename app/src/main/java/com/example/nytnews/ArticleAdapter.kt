package com.example.nytnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ArticlesAdapter (val noticias:List<Article>,val listener: OnItemClickListener) : RecyclerView.Adapter<ArticlesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ArticlesViewHolder(layoutInflater.inflate(R.layout.item_noticia,parent,false),listener)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.render(noticias[position])
    }

    override fun getItemCount(): Int {
        return noticias.size
    }

}