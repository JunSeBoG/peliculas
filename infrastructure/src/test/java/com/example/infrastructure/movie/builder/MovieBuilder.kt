package com.example.infrastructure.movie.builder

import com.example.domain.movie.entity.Movie

class MovieBuilder {
    private var id: Int = 1
    private var title: String = ""
    private var overview: String = ""
    private var status: String = ""
    private var releasedate: String = ""
    private var adult: Boolean = true
    private var posterpath: String = ""
    private var voteaverage: Double = 0.0

    fun setId(id: Int){
        this.id = id
    }

    fun setTitle(title: String){
        this.title = title
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

    fun setPosterPath(posterpath: String){
        this.posterpath = posterpath
    }

    fun setVoteaverage(voteaverage: Double){
        this.voteaverage = voteaverage
    }

    fun build(): Movie = Movie(id, title, overview, status, releasedate, adult, posterpath, voteaverage)
}