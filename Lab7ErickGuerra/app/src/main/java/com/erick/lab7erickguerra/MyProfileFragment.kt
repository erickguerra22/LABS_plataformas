package com.erick.lab7erickguerra

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MyProfileFragment : Fragment(R.layout.fragment_my_profile) {

    private lateinit var btnUpdate : Button
    private lateinit var inputLayout : TextInputLayout
    private lateinit var rootFragment: ConstraintLayout
    private lateinit var iconCamera: ImageView

    private val args: MyProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnUpdate = view.findViewById(R.id.btn_myProfileFragment_update)
        inputLayout = view.findViewById(R.id.inputLayout_myProfileFragment_email)
        rootFragment = view.findViewById(R.id.layout_myProfileFragment)
        iconCamera = view.findViewById(R.id.img_myProfileFragment_camera)

        inputLayout.editText!!.setText(args.email)

        setListeners()
    }

    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun checkRequestRationale(permission: String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(shouldShowRequestPermissionRationale(permission)){
                Snackbar.make(
                    rootFragment,
                    "El acceso es necesario para poder agregar una fotografía de perfil.",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Ok"){
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission),0)
                }.show()
            }else{
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission),0)
            }
        }
    }

    private fun checkCameraPermission(){
        if(!hasCameraPermission()){
            checkRequestRationale(Manifest.permission.CAMERA)
        }else{
            Toast.makeText(activity, "El permiso ya ha sido otorgado", Toast.LENGTH_LONG).show()
        }
    }

    private fun setListeners() {
        btnUpdate.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_myProfileFragment_to_loginFragment)
        }

        iconCamera.setOnClickListener {
            checkCameraPermission()
        }
    }
}