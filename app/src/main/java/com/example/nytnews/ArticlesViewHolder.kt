package com.example.nytnews

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticlesViewHolder (val view: View, val listener: OnItemClickListener): RecyclerView.ViewHolder(view){

    fun render(noticia:Article){
        view.findViewById<TextView>(R.id.tvTitulo).text = noticia.title
        view.findViewById<TextView>(R.id.tvFecha).text = noticia.section

        view.findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.cnn)
        itemView.setOnClickListener{
            listener.onItemClick(noticia)
        }

    }
}