package com.erick.lab5erickguerra

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.erick.lab5erickguerra.data.Restaurant
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import java.lang.reflect.Constructor

class DetailsActivity : AppCompatActivity() {

    private lateinit var txtRestName: TextView
    private lateinit var txtRestAddress: TextView
    private lateinit var txtRestSchedule: TextView
    private lateinit var btnCall: MaterialButton
    private lateinit var btnVisita: MaterialButton
    private lateinit var rootLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        txtRestName = findViewById(R.id.txt_detailsActivity_restaurantName)
        txtRestAddress = findViewById(R.id.txt_detailsActivity_restaurantAddress)
        txtRestSchedule = findViewById(R.id.txt_detailsActivity_restaurantSchedule)
        btnCall = findViewById(R.id.btn_detailsActivity_Call)
        btnVisita = findViewById(R.id.btn_detailsActivity_iniciarVisita)
        rootLayout = findViewById(R.id.root_detailsActivity)

        val restaurant: Restaurant = intent.getSerializableExtra("EXTRA_RESTAURANT") as Restaurant

        txtRestName.text = restaurant.name
        txtRestAddress.text = restaurant.address
        txtRestSchedule.text = restaurant.schedule

        initListeners()

    }

    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun checkCameraPermission(){
        if(!hasCameraPermission()){
            checkRequestRationale(Manifest.permission.CAMERA)
        }else{
            Toast.makeText(this, "El permiso ya ha sido otorgado", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkRequestRationale(permission: String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(shouldShowRequestPermissionRationale(permission)){
                Snackbar.make(
                    rootLayout,
                    "El acceso es necesario para poder llevar a cabo la visita virtual al restaurante",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Ok"){
                    ActivityCompat.requestPermissions(this, arrayOf(permission),0)
                }.show()
            }else{
                ActivityCompat.requestPermissions(this, arrayOf(permission),0)
            }
        }
    }

    private fun initListeners(){
        btnCall.setOnClickListener{
            val number = "+50278389900"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:$number"))
            startActivity(intent)
        }
        btnVisita.setOnClickListener{
            checkCameraPermission()
        }
    }
}