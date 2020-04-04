package com.neeraj.gamechangedemo.network

import com.neeraj.gamechangedemo.model.Comment
import com.neeraj.gamechangedemo.model.Issue
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */
interface Api {
    /**
     * Get the list of the issues from the API
     */
    @GET("/repos/firebase/firebase-ios-sdk/issues")
    fun getIssues(@Query("sort") sort:String="updated",@Query("direction") direction:String="desc",@Query("per_page") perPage:Int=100,@Query("page") page:Int): Observable<ArrayList<Issue>>

    @GET("/repos/firebase/firebase-ios-sdk/issues/{issue_id}/comments")
    fun getComments(@Path("issue_id") issue_id:String): Observable<ArrayList<Comment>>
}