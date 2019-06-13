package com.project.hadd;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

public class SettingsFragment extends Fragment {

    private static final String TAG = "MainActivity";

    Button btnSend;
    BluetoothSocket btSocket;
    SeekBar threshold;
    public BluetoothDevice device;
    BluetoothAdapter btAdapter;
    private OutputStream out;
    private int port = 1;

    //    private static String btAdress = "B8:27:EB:EC:4F:E5";
    private static String btAdress = Globals.getInstance().getBtAdress();
    //    private static final UUID MY_UUID_INSECURE = UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
    private static final UUID MY_UUID_INSECURE = UUID.fromString("5fa1ec37-3719-4b25-be14-1f7d29135a13");

    private Context mActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        btnSend = view.findViewById(R.id.btnSend);
        threshold = view.findViewById(R.id.threshold);
        threshold.setMax(60);

        btAdapter = BluetoothAdapter.getDefaultAdapter();


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                byte[] bytes = etSend.getText().toString().getBytes(Charset.defaultCharset());
//                mBluetoothConnection.write(bytes);
                if (Globals.getInstance().getDevice() != null) {
                    device = btAdapter.getRemoteDevice(Globals.getInstance().getDevice().getAddress());
                    try {
                        Method m = device.getClass().getMethod("createInsecureRfcommSocket", new Class[]{int.class});
                        btSocket = (BluetoothSocket) m.invoke(device, port);
//                    btSocket = device.createRfcommSocketToServiceRecord(MY_UUID_INSECURE);
                    } catch (Exception e) {
                        Log.e(TAG, "erno? ", e);
                    }

                    try {
                        Log.d(TAG, "" + threshold.getProgress());
                        if (threshold.getProgress() > 50) {
                            Toast.makeText(getContext(), "Gevoeligheid hoog", Toast.LENGTH_LONG).show();
                        } else if (threshold.getProgress() < 10) {
                            Toast.makeText(getContext(), "Gevoeligheid laag", Toast.LENGTH_LONG).show();
                        }
                        String sendvalue = "{\"threshold\":\" -" + threshold.getProgress() + "\"}";
                        btSocket.connect();
                        out = btSocket.getOutputStream();
                        String msg = sendvalue;
                        byte[] msgBffr = msg.getBytes();
                        out.write(msgBffr);
//                    header.append("\n btSocket Created!");
                    } catch (IOException e) {
                        Toast.makeText(getContext(), "Could not connect to socket", Toast.LENGTH_LONG).show();
                        try {
                            btSocket.close();
                        } catch (Exception b) {
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "No connected device", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroy: called.");
        super.onDestroyView();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }


}
