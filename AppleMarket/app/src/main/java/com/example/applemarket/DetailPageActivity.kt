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
                    "key", Product::class.java
                )
            } else {
                intent?.getParcelableExtra(
                    "key"
                )
            }

            mainImage.setImageResource(data!!.image)
            userName.text = data.seller
            mainTitleText.text = data.name
            userAddress.text = data.address
            detailExplanationText.text = data.productExplanation
            priceText.text = data.price + "원"

        }

    }
}