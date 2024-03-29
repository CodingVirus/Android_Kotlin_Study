package com.example.applemarket

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.applemarket.data.Product
import com.example.applemarket.data.ProductList
import com.example.applemarket.databinding.ActivityDetailPageBinding
import java.text.DecimalFormat

class DetailPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.backArrowIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        with(binding) {
            val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getParcelableExtra(
                    Constants.ITEM_INDEX, Product::class.java
                )
            } else {
                intent?.getParcelableExtra(
                    Constants.ITEM_INDEX
                )
            }

            mainImage.setImageResource(data!!.image)
            userName.text = data.seller
            mainTitleText.text = data.name
            userAddress.text = data.address
            detailExplanationText.text = data.productExplanation
            priceText.text = "${DecimalFormat("#,###").format(data.price.toInt())}원"

            if (data.isLike == true) heartIcon.setImageResource(R.drawable.fill_heart_icon)
            else heartIcon.setImageResource(R.drawable.heart)

            heartIcon.setOnClickListener {
                if (data.isLike == false) {
                    data.isLike = true
                    heartIcon.setImageResource(R.drawable.fill_heart_icon)
                } else {
                    data.isLike = false
                    heartIcon.setImageResource(R.drawable.heart)
                }
                intent.putExtra(Constants.ITEM_INDEX, data)
            }
        }
    }
}