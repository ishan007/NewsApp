package com.example.bulletinapp.util

import android.util.Log

/**
 * Util class
 */
class Logger {
    companion object{

        fun e(msg: String){
            Log.e(AppConstants.TAG, msg)
        }

    }
}