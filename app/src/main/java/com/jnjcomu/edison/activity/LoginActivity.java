package com.jnjcomu.edison.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.jnjcomu.edison.R;
import com.jnjcomu.edison.api.APIBuilder;
import com.jnjcomu.edison.model.response.LoginResponse;
import com.jnjcomu.edison.storage.UserStorage;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements Callback<LoginResponse> {

    @ViewById(R.id.edt_id_field)
    protected EditText edtIdField;

    @ViewById(R.id.edt_pw_field)
    protected EditText edtPwField;

    protected ProgressDialog statusDialog;

    @AfterViews
    protected void initUI() {
        statusDialog = new ProgressDialog(this);
        statusDialog.setMessage("잠시만 기다려 주세요...");
    }

    @Click(R.id.btn_login)
    protected void doTryLogin() {
        String userId = edtIdField.getText().toString();
        String userPassword = edtPwField.getText().toString();

        APIBuilder.getAPI().login(userId, userPassword).enqueue(this);
    }

    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if (response.code() == 200) {
            LoginResponse loginResult = response.body();

            UserStorage.getInstance(this)
                    .saveUser(loginResult.getUserData())
                    .saveUserTicket(loginResult.getTicket());

            sendResult(true);
        } else {
            sendResult(false, response.body().getMessage());
        }
    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {

    }

    @UiThread
    protected void sendResult(boolean finishLogin, String message) {
        statusDialog.dismiss();

        if (finishLogin) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        if (!"".equals(message)) {
            new AlertDialog.Builder(this)
                    .setMessage(message)
                    .show();
        }
    }

    protected void sendResult(boolean finishLogin) {
        sendResult(finishLogin, "");
    }

}
