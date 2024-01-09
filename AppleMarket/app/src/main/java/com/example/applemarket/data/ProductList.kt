package com.example.applemarket.data

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.example.applemarket.R

object ProductList {
    val list: MutableList<Product> = mutableListOf()

    val size = list.count()

    init {
        add(
            Product(
                R.drawable.sample1,
                1,
                "산진 한달된 선풍기 팝니다",
                "이사가서 필요가 없어졌어요 급하게 내놓습니다",
                "대현동",
                "1,000",
                "서울 서대문구 창천동",
                13,
                25,
                false
            )
        )
        add(
            Product(
                R.drawable.sample2,
                1,
                "김치냉장고",
                "이사로인해 내놔요",
                "안마담",
                "20,000",
                "인천 계양구 귤현동",
                8,
                28,
                false
            )
        )
        add(
            Product(
                R.drawable.sample3,
                1,
                "샤넬 카드지갑",
                "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",
                "코코유",
                "10,000",
                "수성구 범어동",
                23,
                5,
                false
            )
        )
        add(
            Product(
                R.drawable.sample4,
                1,
                "샤넬 카드지갑",
                "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",
                "코코유",
                "10,000",
                "수성구 범어동",
                23,
                5,
                false
            )
        )
        add(
            Product(
                R.drawable.sample5,
                1,
                "샤넬 카드지갑",
                "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",
                "코코유",
                "10,000",
                "수성구 범어동",
                23,
                5,
                false
            )
        )
    }

    fun add(product: Product) {
        list.add(product)
    }

    fun get(index: Int): Product {
        return list[index]
    }
}