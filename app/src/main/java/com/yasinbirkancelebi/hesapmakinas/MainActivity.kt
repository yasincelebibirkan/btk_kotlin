package com.yasinbirkancelebi.hesapmakinas

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yasinbirkancelebi.hesapmakinas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    data class  SayiBirVeSayiİki(var s1: Double, var s2:Double)
    var Hesap : Double? = 0.0
    fun SayiAl() : SayiBirVeSayiİki {
        var s1 : Double = 0.0
        var s2 : Double = 0.0
        var TextValue : String

        if(binding.editTextText.text.isEmpty() || binding.editTextText2.text.isEmpty()){
            Toast.makeText(this@MainActivity,"Gerekli alanları doldurun",Toast.LENGTH_LONG).show()
        }
        else{
            TextValue = binding.editTextText.text.toString()
            s1 = TextValue.toDoubleOrNull() ?: 0.0 // eğer null dönerse varsayılan değer olarak 0.0 atar
            TextValue = binding.editTextText2.text.toString()
            s2 = TextValue.toDouble()

            return SayiBirVeSayiİki(s1, s2)
        }


        return SayiBirVeSayiİki(0.0, 0.0)
    }

    fun Yazdir(sonuc : Double){
        binding.textView.text = "işlem sonucunuz $sonuc"
    }

    fun Topla(view: View){
        Hesap = SayiAl().s1 + SayiAl().s2
        if (SayiAl().s1 != 0.0 && SayiAl().s2 != 0.0) Yazdir(Hesap!!)

    }

    fun Cikar(view: View){
        Hesap = SayiAl().s1 - SayiAl().s2
        if (SayiAl().s1 != 0.0 && SayiAl().s2 != 0.0) Yazdir(Hesap!!)
    }

    fun Bol(view: View) {
        val (s1, s2) = SayiAl()
        if (s2 != 0.0) {
            Hesap = s1 / s2
            if (SayiAl().s1 != 0.0 && SayiAl().s2 != 0.0) Yazdir(Hesap!!)
        } else {
            Toast.makeText(this@MainActivity, "Sıfıra bölme hatası!", Toast.LENGTH_LONG).show()
        }
    }

    fun Carp(view: View){
        Hesap = SayiAl().s1 * SayiAl().s2
        if (SayiAl().s1 != 0.0 && SayiAl().s2 != 0.0) Yazdir(Hesap!!)
    }

    fun Temizle(view: View){
        binding.editTextText.text.clear()
        binding.editTextText2.text.clear()
    }
}