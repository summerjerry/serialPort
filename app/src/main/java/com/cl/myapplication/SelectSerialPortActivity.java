package com.cl.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.cl.myapplication.adapter.DeviceAdapter;
import com.kongqw.serialportlibrary.Device;
import com.kongqw.serialportlibrary.SerialPortFinder;
// import kotlinx.android.synthetic.main.activity_select_serial_port.*

class SelectSerialPortActivity extends AppCompatActivity implements OnItemClickListener {

    private DeviceAdapter mDeviceAdapter = null;
    private String DEVICE = "device";
    ListView mListview = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_select_serial_port);
        mListview = this.findViewById(R.id.lv_devices);

        SerialPortFinder serialPortFinder = new SerialPortFinder();
        java.util.ArrayList<com.kongqw.serialportlibrary.Device> devices = serialPortFinder.getDevices();
        if (mListview != null) {
            mListview.setEmptyView(this.findViewById(R.id.tv_empty));
            mDeviceAdapter = new DeviceAdapter(this.getApplication(), devices);
            mListview.setAdapter(mDeviceAdapter);
            mListview.setOnItemClickListener(this);
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_select_serial_port)
//
//        val serialPortFinder = SerialPortFinder()
//        val devices = serialPortFinder.devices
//        if (lv_devices != null) {
//            lv_devices.emptyView = tv_empty
//            mDeviceAdapter = DeviceAdapter(applicationContext, devices)
//            lv_devices.adapter = mDeviceAdapter
//            lv_devices.onItemClickListener = this
//        }
//
//    }

//    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
//        val device = mDeviceAdapter!!.getItem(position)
//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra(DEVICE, device)
//        startActivity(intent)
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Device device = mDeviceAdapter.getItem(position);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(DEVICE, device);
        this.startActivity(intent);
    }
}