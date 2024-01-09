package com.example.applemarket.data

import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(val image: Int, val number: Int, val name: String, val productExplanation: String, val seller: String,
                   val price: String, val address: String, val like: Int, val comments: Int, val isLike: Boolean) : Parcelable {

}