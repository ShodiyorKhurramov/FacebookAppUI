package com.example.facebookappclone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookappclone.R
import com.example.facebookappclone.adapter.FeedAdapter
import com.example.facebookappclone.model.Feed
import com.example.facebookappclone.model.Post
import com.example.facebookappclone.model.Story

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView

    companion object {
        const val profile1 ="https://picsum.photos/500/300?random=12"

        const val profile2="https://picsum.photos/500/300?random=4"
        const val profile3 ="https://picsum.photos/500/300?random=5"

        const val profile4="https://picsum.photos/500/300?random=6"


        const val photo1 =
            "https://picsum.photos/500/300?random=3"
        const val photo2 =
            "https://picsum.photos/500/300?random=33"
        const val photo3 =
            "https://picsum.photos/500/300?random=42"
        const val photo4 =
            "https://picsum.photos/500/300?random=33"
        const val photo5 =
            "https://picsum.photos/500/300?random=123"

        const val video1 =
            "https://vod-progressive.akamaized.net/exp=1645096363~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F3400%2F21%2F542004485%2F2570352312.mp4~hmac=6726a3a94819327cf3134630bce9f0858506aca5403440abad56d5f1cb4f0573/vimeo-prod-skyfire-std-us/01/3400/21/542004485/2570352312.mp4?filename=pexels-mart-production-7667423.mp4"
        const val video2 =
            "https://vod-progressive.akamaized.net/exp=1645097242~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2251%2F16%2F411256287%2F1766006419.mp4~hmac=859496a5d3b09cd410a0e08890353daddfb0586c0ff02b790d3951d1a783d464/vimeo-prod-skyfire-std-us/01/2251/16/411256287/1766006419.mp4?filename=production+ID%3A4228659.mp4"



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
//        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white))
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        refreshAdapter(getAllFeeds())

    }

    private fun refreshAdapter(allFeeds: ArrayList<Feed>) {
        recyclerView.adapter = FeedAdapter(this, allFeeds)
    }

    private fun getAllFeeds(): ArrayList<Feed> {
        val stories = ArrayList<Story>()
        for (i in 0..3) {
            stories.add(Story("https://picsum.photos/500/300?random=$  i", "Shodiyor Xurramov", photo1))
            stories.add(Story(profile2, "Abdulaziz Yusupov", photo2))
            stories.add(Story(profile3, "Sherzod Jo'rabekov", photo3))
            stories.add(Story(profile4, "Sarvar Khalmatov", photo4))
        }

        val feeds = ArrayList<Feed>()
        //add Head
        feeds.add(Feed(profile1))
        //add Story
        feeds.add(Feed(stories))
        //add Posts
        for (i in 0..4) {
            feeds.add(Feed(Post(profile1, "Shodiyor Xurramov", photo1)))
            feeds.add(Feed(Post(profile2, "Abdulaziz Yusupov", photo2)))
            feeds.add(Feed(Post(profile3, "Abdulaziz Yusupov", photo3)))
            feeds.add(Feed(Post(profile4, "Shaxzod Mirzayev", photo4)))
            feeds.add(Feed(Post(profile4, "Shaxzod Mirzayev", photo5)))
        }

        return feeds
    }

    fun openLinkActivty(){
        val intent= Intent(this,LinkActivity::class.java)
        startActivity(intent)
    }
}