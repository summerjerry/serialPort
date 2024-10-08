package com.kongqw.serialportlibrary;

import android.util.Log;

import com.cl.log.XLog;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public class SerialPort {

    static {
        System.loadLibrary("SerialPort");
    }

    private static final String TAG = SerialPort.class.getSimpleName();

    /**
     * 文件设置最高权限 777 可读 可写 可执行
     *
     * @param file 文件
     * @return 权限修改是否成功
     */
    boolean chmod777(File file) {
        Log.i("SerialPort","Runtime.getRuntime().exec(\"/system/xbin/su\")");
        if (null == file || !file.exists()) {
            // 文件不存在
            return false;
        }
        try {
            // 获取ROOT权限
            // NOTE(qingxia): Notice su is in different place for system，/system/xbin/su
            // Process su = Runtime.getRuntime().exec("/system/bin/su");
            Process su = Runtime.getRuntime().exec("su");
            XLog.i("SerialPort","run /system/xbin/su " + file.getAbsolutePath());
            // 修改文件属性为 [可读 可写 可执行]
            String cmd = "chmod 777 " + file.getAbsolutePath() + "\n" + "exit\n";
            XLog.i("SerialPort","run " + cmd);
            su.getOutputStream().write(cmd.getBytes());

            if (0 == su.waitFor() && file.canRead() && file.canWrite() && file.canExecute()) {
                return true;
            }
        } catch (IOException | InterruptedException e) {
            // 没有ROOT权限
            e.printStackTrace();
        }
        return false;
    }

    // 打开串口
    protected native FileDescriptor open(String path, int baudrate, int flags, int databits, int stopbits, int parity);

    // 关闭串口
    protected native void close();
}
