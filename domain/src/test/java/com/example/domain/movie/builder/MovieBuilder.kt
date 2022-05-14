package com.example.domain.movie.builder

import com.example.domain.movie.entity.Movie

class MovieBuilder {
    private var id: Int = 1
    private var title: String = ""
    private var originaltitle: String = ""
    private var originallanguage: String = ""
    private var overview: String = ""
    private var status: String = ""
    private var releasedate: String = ""
    private var adult: Boolean = true
    private var backdroppath: String = ""
    private var posterpath: String = ""
    private var video: Boolean = false
    private var popularity: Double = 0.0
    private var voteaverage: Double = 0.0
    private var votecount: Int = 1

    fun setId(id: Int){
        this.id = id
    }

    fun setTitle(title: String){
        this.title = title
    }

    fun setOriginalTitle(originaltitle: String){
        this.originaltitle = originaltitle
    }

    fun setOriginallanguage(originallanguage: String){
        this.originallanguage = originallanguage
    }

    fun setOverview(overview: String){
        this.overview = overview
    }

    fun setStatus(status: String){
        this.status = status
    }

    fun setReleasedate(releasedate: String){
        this.releasedate = releasedate
    }

    fun setadult(adult: Boolean){
        this.adult = adult
    }

    fun setPopularity(popularity: Double){
        this.popularity = popularity
    }

    fun setVoteaverage(voteaverage: Double){
        this.voteaverage = voteaverage
    }

    fun setVotecount(votecount: Int){
        this.votecount = votecount
    }

    fun build(): Movie = Movie(id, title, originaltitle, originallanguage, overview, status, releasedate, adult, backdroppath, posterpath, video, popularity, voteaverage, votecount)

}