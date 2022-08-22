package com.erick.lab6erickguerra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class LibraryFragment : Fragment(R.layout.fragment_library) {

    private lateinit var addSong: ImageView
    private var songs: Int = 0
    private lateinit var songsCounter: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addSong = view.findViewById(R.id.img_libraryFragment_addSong)
        songsCounter = view.findViewById(R.id.txt_fragmentLibrary_counter)
        setListeners()
        songsCounter.text = "$songs songs"
    }

    override fun onResume() {
        super.onResume()
        songsCounter.text = "$songs songs"
    }

    private fun setListeners() {
        addSong.setOnClickListener{
            songs++
            songsCounter.text = "$songs songs"
        }
    }

}