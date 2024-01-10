package com.example.applemarket

import android.Manifest
import android.app.AlertDialog
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.applemarket.adapter.ProductListAdapter
import com.example.applemarket.data.Product
import com.example.applemarket.data.ProductList
import com.example.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val channelID = "default"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        createNotificationChannel()

        binding.alarmIcon.setOnClickListener {
            showNotification()
        }

        val adapter = ProductListAdapter(ProductList.list)
        binding.recyclerView.adapter = adapter

        val intent = Intent(this, DetailPageActivity::class.java)

        adapter.itemClick = object : ProductListAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                intent.putExtra("key", ProductList.get(position))
                startActivity(intent)
            }

            override fun onLongClick(view: View, position: Int) {
                removeItemDialog(adapter, position)
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                exitDialog()
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)
       // binding.spinner.onItemSelectedListener = object
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, "First channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Test description for my channel"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification() {
        val builder = NotificationCompat.Builder(this, channelID)
        builder.setSmallIcon(R.drawable.smile_icon)
            .setContentTitle("키워드 알림")
            .setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(1, builder.build())
        }
    }

    private fun exitDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("종료")
        builder.setMessage("정말 종료하시겠습니까?")
        builder.setIcon(R.drawable.comment_icon)

        val listener = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which) {
                    DialogInterface.BUTTON_POSITIVE -> finish()
                }
            }
        }
        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", listener)
        builder.show()
    }

    private fun removeItemDialog(adapter: ProductListAdapter, position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("상품 삭제")
        builder.setMessage("상품을 정말로 삭제하시겠습니까?")
        builder.setIcon(R.drawable.comment_icon)

        val listener = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        adapter.removeItem(position)
                    }
                }
            }
        }
        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", listener)
        builder.show()
    }
}