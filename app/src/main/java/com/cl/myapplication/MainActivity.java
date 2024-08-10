package com.cl.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.kongqw.serialportlibrary.Device;
import com.kongqw.serialportlibrary.SerialPortManager;
import com.kongqw.serialportlibrary.SerialUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
// import kotlinx.android.synthetic.main.activity_main.*

class MainActivity extends AppCompatActivity {

    private String TAG = "AppCompatActivity";
    private String DEVICE = "device";
    private SerialPortManager mSerialPortManager = null;
    private EditText mEdit = null;
    private Toast mToast = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Serializable device = this.getIntent().getSerializableExtra(DEVICE);
        // val device = intent.getSerializableExtra(DEVICE) as Device?
        mEdit = this.findViewById(R.id.et_send_content);

        if (null == device) {
            this.finish();
            return;
        }
    }


    private void onSend(View view) throws UnsupportedEncodingException {
        String editTextSendContent = mEdit.getText().toString();
        if (TextUtils.isEmpty(editTextSendContent)) {
            Log.i(TAG, "onSend: 发送内容为 null");
            return;
        }
        boolean sendBytes = mSerialPortManager.sendBytes(editTextSendContent.getBytes("UTF-8"));
        Log.i(
            TAG,
            "onSend: sendBytes = " + editTextSendContent
        );
        String res = sendBytes ? "发送成功" : "发送失败";
        showToast(res);
    }


//    fun onDestroy(view: View){
//
//    }
    private void onDestroyUI(View view) {
    }


    /**
     * Toast
     *
     * @param content content
     */
//    private fun showToast(content: String) {
//        if (null == mToast) {
//            mToast = Toast.makeText(applicationContext, null, Toast.LENGTH_SHORT)
//        }
//        mToast?.setText(content)
//        mToast?.show()
//    }
    private void showToast(String string) {
        if (null == mToast) {
            mToast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
        }
        mToast.setText(string);
        mToast.show();
    }

}