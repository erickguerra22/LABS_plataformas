package com.erick.lab5erickguerra

import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.erick.lab5erickguerra.data.Restaurant
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnDetails: MaterialButton
    private lateinit var btnIniciar: MaterialButton
    private lateinit var icnMap: ImageButton
    private lateinit var btnDownload: MaterialButton
    private lateinit var txtRestName: TextView
    private lateinit var txtRestAddress: TextView
    private lateinit var txtRestSchedule: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDetails = findViewById(R.id.btn_mainActivity_detalles)
        btnIniciar = findViewById(R.id.btn_mainActivity_iniciar)
        icnMap = findViewById(R.id.icon_map)
        btnDownload = findViewById(R.id.btn_mainActivity_download)
        txtRestName = findViewById(R.id.txt_restaurantName)
        txtRestAddress = findViewById(R.id.txt_restaurantAddress)
        txtRestSchedule = findViewById(R.id.txt_restaurantSchedule)

        initListeners()

    }

    private fun initListeners(){
        btnDetails.setOnClickListener{
            val restaurant = Restaurant(
                name = txtRestName.text.toString(),
                address = txtRestAddress.text.toString(),
                schedule = txtRestSchedule.text.toString()
            )
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("EXTRA_RESTAURANT", restaurant)
            startActivity(intent)
        }
        btnIniciar.setOnClickListener{
            val name = "Erick Stiv Junior Guerra Muñoz"
            val toastMsg = Toast.makeText(applicationContext, name, Toast.LENGTH_SHORT)
            toastMsg.show()
        }
        icnMap.setOnClickListener{
            val location = "https://goo.gl/maps/HZm7DqeAKMTtfSK57"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location))
            startActivity(intent)
        }
        btnDownload.setOnClickListener{
            val app = "https://play.google.com/store/apps/details?id=com.spotify.music&hl=es_GT&gl=US"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(app))
            startActivity(intent)
        }
    }
}