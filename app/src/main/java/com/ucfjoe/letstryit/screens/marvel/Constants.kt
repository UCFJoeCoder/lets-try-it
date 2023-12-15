package com.ucfjoe.letstryit.screens.marvel

import com.ucfjoe.letstryit.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com:443"
        val ts = Timestamp(System.currentTimeMillis()).time.toString()

        const val API_KEY = BuildConfig.MARVEL_API_KEY
        const val PRIVATE_KEY = BuildConfig.MARVEL_PRIVATE_KEY
        //const val limit = 100

        fun hash(): String {
            val input = "$ts$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")

            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }

}