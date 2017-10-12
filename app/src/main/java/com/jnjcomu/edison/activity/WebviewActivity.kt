package com.jnjcomu.edison.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jnjcomu.edison.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val title = intent.getStringExtra("title")
        toolbar_title.text = title

        val doc = intent.getStringExtra("doc")
        webview.loadUrl(doc)
    }
}
