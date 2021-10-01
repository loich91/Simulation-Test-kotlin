package com.technipixl.simulation_entre_kotlin.web

import com.technipixl.simulation_entre_kotlin.data.DataMmo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface MmoConnexion {
    @Headers("Content-type: application/json")
    @GET("games")
    suspend fun mmo():Response<List<DataMmo>>

    @GET("game")
    suspend fun mmoDetail(
        @Query("id",encoded = false)id:Int
    ):Response<DataMmo>
}