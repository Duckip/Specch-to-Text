package com.example.speechtotextgoogleapi

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private val speechreq = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.btn)

        btn.setOnClickListener {
            speechinput()
        }
    }

    private fun speechinput() {
        if (!SpeechRecognizer.isRecognitionAvailable(this)) {
            Toast.makeText( this, "Speech recognition tidak tersedia", Toast.LENGTH_SHORT).show()
        }
        else {
            val a = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            a.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            a.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            a.putExtra(RecognizerIntent.EXTRA_PROMPT,"ucapkan sesuatu")
            getResult.launch(a)
        }
    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        val teks: TextView = findViewById(R.id.teks)
        if (it.resultCode == speechreq) {
            val result: ArrayList<String>? = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            teks.text = result?.get(0).toString()
        }
    }
}