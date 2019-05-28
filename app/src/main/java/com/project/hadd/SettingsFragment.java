package com.project.hadd;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.UUID;

public class SettingsFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String TAG = "MainActivity";
    private Button button;
    Button btnFindUnpairedDevices;
    BluetoothAdapter mBluetoothAdapter;

    Button btnStartConnection;
    Button btnSend;

    EditText etSend;

    BluetoothConnectionService mBluetoothConnection;

    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");

    BluetoothDevice mBTDevice;

    public ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();
    public DeviceListAdapter mDeviceListAdapter;
    ListView lvNewDevices;
    private Context mActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        button = view.findViewById(R.id.Connectionbutton);
        btnFindUnpairedDevices = view.findViewById(R.id.btnFindUnpairedDevices);
        lvNewDevices = view.findViewById(R.id.lvNewDevices);

        btnStartConnection = view.findViewById(R.id.btnStartConnection);
        btnSend = view.findViewById(R.id.btnSend);
        etSend = view.findViewById(R.id.editText);
        mBTDevices = new ArrayList<>();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mBroadcastReceiver3, filter);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        lvNewDevices.setOnItemClickListener(SettingsFragment.this);

        btnFindUnpairedDevices.setOnClickListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Enable/disable bluetooth");
                enableDisableBT();
            }
        });

        btnStartConnection.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               startConnection();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                byte[] bytes = etSend.getText().toString().getBytes(Charset.defaultCharset());
                mBluetoothConnection.write(bytes);
            }
        });
        return view;
    }

    public void startConnection(){
        startBTConnection(mBTDevice, MY_UUID_INSECURE);
    }

    //starting chat service method
    public void startBTConnection(BluetoothDevice device, UUID uuid){
        Log.d(TAG, "startBTConnection: Initializing RFCOM Bluetooth Connection");

        mBluetoothConnection.startClient(device, uuid);
    }

    private void registerReceiver(BroadcastReceiver mBroadcastReceiver3, IntentFilter filter) {
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mBroadcastReceiver1 =
            new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (action.equals(mBluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)) {
                        final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, mBluetoothAdapter.ERROR);

                        switch (state) {
                            case BluetoothAdapter.STATE_OFF:
                                Log.d(TAG, "onReceive: STATE OFF");
                                break;
                            case BluetoothAdapter.STATE_TURNING_OFF:
                                Log.d(TAG, "onReceive: STATE TURNING OFF");
                                break;
                            case BluetoothAdapter.STATE_ON:
                                Log.d(TAG, "onReceive: STATE ON");
                                break;
                            case BluetoothAdapter.STATE_TURNING_ON:
                                Log.d(TAG, "onReceive: STATE TURNING ON");
                                break;
                        }
                    }
                }
            };

    private BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: ACTION FOUND.");

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mBTDevices.add(device);
                Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
                mDeviceListAdapter = new DeviceListAdapter(context, R.layout.device_adapter_view, mBTDevices);
                lvNewDevices.setAdapter(mDeviceListAdapter);
            }
        }
    };

    private final BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if(action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)){
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //3 cases:
                //case 1: bonded already
                if(mDevice.getBondState() == BluetoothDevice.BOND_BONDED){
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDED.");
                    mBTDevice = mDevice;
                }//case 2: creating a bond
                if(mDevice.getBondState() == BluetoothDevice.BOND_BONDING){
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDING.");
                }//case 3: breaking a bond
                if(mDevice.getBondState() == BluetoothDevice.BOND_NONE){
                    Log.d(TAG, "BroadcastReceiver: BOND_NONE.");
                }

            }
        }
    };

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroy: called.");
        super.onDestroyView();

        unregisterReceiver(mBroadcastReceiver1);
        unregisterReceiver(mBroadcastReceiver2);
        unregisterReceiver(mBroadcastReceiver3);
        /*try {
            mActivity.unregisterReceiver(mBroadcastReceiver1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private void unregisterReceiver(BroadcastReceiver mBroadcastReceiver3) {
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        Log.d(TAG, " v id = " + v.getId());
        switch (v.getId()) {
            case R.id.btnFindUnpairedDevices:
                btnDiscover(v);
                break;
        }
    }

    public void enableDisableBT() {
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "enableDisabledBT: Does not have BT capabilities");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Log.d(TAG, "enableDisabledBT: enabling BT.");
            Intent enabledBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enabledBTIntent);

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            mActivity.registerReceiver(mBroadcastReceiver1, BTIntent);
        }
        if (mBluetoothAdapter.isEnabled()) {
            Log.d(TAG, "enabledDisabledBT: disabling BT.");
            mBluetoothAdapter.disable();

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            mActivity.registerReceiver(mBroadcastReceiver1, BTIntent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void btnDiscover(View view) {
        Log.d(TAG, "btnDiscover: Looking for unpaired devices");

        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
            Log.d(TAG, "btnDiscover: Canceling discovery");

            //checkt versie van mobiel
            checkBTPermissions();

            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            mActivity.registerReceiver(mBroadcastReceiver2, discoverDevicesIntent);
        }
        if (!mBluetoothAdapter.isDiscovering()) {
            //check BT permissions in manifest
            checkBTPermissions();

            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            mActivity.registerReceiver(mBroadcastReceiver2, discoverDevicesIntent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkBTPermissions() {
        int permissionCheck = mActivity.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
        permissionCheck += mActivity.checkSelfPermission("Manifest.permission.ACCESS_COURSE_LOCATION");
        if (permissionCheck != 0) {
            this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001);
        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //first cancel discovery because it's very memory intensive
        mBluetoothAdapter.cancelDiscovery();

        Log.d(TAG, "onItemClick: You clicked on a device.");
        String deviceName = mBTDevices.get(i).getName();
        String deviceAddress = mBTDevices.get(i).getAddress();

        Log.d(TAG, "onItemClick: deviceName = " + deviceName);
        Log.d(TAG, "onItemClick: deviceAddress = " + deviceAddress);

        //create the bond
        //requires API 17+, Jellybean
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
            Log.d(TAG, "Trying to pair with " + deviceName);
            mBTDevices.get(i).createBond();

            mBTDevice = mBTDevices.get(i);
            mBluetoothConnection = new BluetoothConnectionService(SettingsFragment.this);
        }

    }

}
