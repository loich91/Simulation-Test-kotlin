package com.technipixl.simulation_entre_kotlin.web

import retrofit2.create

class MmoImpl:AppRetrofit() {
    suspend fun mmoImpl() = getRetrofit().create(MmoConnexion::class.java).mmo()
    suspend fun mmoDetailimpl(id:Int) = getRetrofit().create(MmoConnexion::class.java).mmoDetail(id)
}