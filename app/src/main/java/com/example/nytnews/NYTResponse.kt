package com.example.nytnews

class NYTResponse(
    var status:String,
    var copyright:String,
    var num_results:Int,
    var results:List<Article>
)