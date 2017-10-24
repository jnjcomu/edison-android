package com.jnjcomu.edison.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.jnjcomu.edison.R
import com.jnjcomu.edison.addition.appStorage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val rooms = arrayOf("그린IT", "휴머노이드", "이비실")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swt_noti.setOnCheckedChangeListener { _, isChecked ->
            appStorage.saveActive(swt_noti.isChecked)
            if (isChecked) {
                Toast.makeText(this, "알림이 활성화되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "알림이 비활성화되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun send(v: View) {
        AlertDialog.Builder(this)
                .setTitle("")
                .setItems(rooms, { _, index ->
                    Toast.makeText(applicationContext, rooms[index], Toast.LENGTH_SHORT).show()
                })
                .setCancelable(false)
                .create()
                .show()
    }
}
