package com.jnjcomu.edison.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/*
 * NOTE
 * ProgressDialog 가 Progress Viewing 은 Activity View 안에서 이루어
 * 져야 한다는 Google Material Design Guideline 에 따라 Deprecated 됨.
 * 그리하여 로그인 버튼 하단에 있는 ProgressBar 로 대체함.
 */
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
