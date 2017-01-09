package com.samsung.astudy;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Song on 2016-12-28.
 */

public class FindMember extends Activity implements OnMapReadyCallback{

    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;
    private Button mBtnTest;
    private EditText mEditDest;
    private EditText mEditMsg;
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private SmsManager mSmsManager;
    private double mMyLat = 0.0f;
    private double mMyLng = 0.0f;

    private static final int REPORT_LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.findfriend);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mListView = (ListView) findViewById(R.id.member_loc_list);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        mEditDest = (EditText) findViewById(R.id.edit_dest);
        mEditMsg = (EditText) findViewById(R.id.edit_msg);

        mSmsManager = SmsManager.getDefault();
        mBtnTest = (Button) findViewById(R.id.btn_findmember);

        mBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sentIntent = new Intent("ACTION_SMS_SENT");
                PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, sentIntent, 0);
                mLocationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, mLocationListener,Looper.myLooper());
                mLocationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, mLocationListener,Looper.myLooper());
                mHandler.sendEmptyMessageDelayed(REPORT_LOCATION,10000);

            }
        });
    }


    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REPORT_LOCATION:
                    String message = mMyLat + "," + mMyLng;
                    String dest = mEditDest.getText().toString();    //TODO : delete
                    sendSMS(dest, message);
                    mLocationManager.removeUpdates(mLocationListener);
                    break;
                default:
                    break;
            }
        }
    };


    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals("ACTION_SMS_SENT")){
                if(getResultCode() == Activity.RESULT_OK){
                    Toast.makeText(getApplicationContext(), "전송성공", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "전송실패", Toast.LENGTH_SHORT).show();
                }
            }else if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                Toast.makeText(getApplicationContext(), "메시지수신", Toast.LENGTH_SHORT).show();
                Bundle bundle = intent.getExtras();
                if(bundle!=null){
                    String message = null;
                    String sender = null;

                    Object pdus[] = (Object[]) bundle.get("pdus");
                    for (Object pdu : pdus){
                        SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
                        message = msg.getMessageBody();
                        sender = msg.getOriginatingAddress();
                    }
                    Toast.makeText(getApplicationContext(), "보낸사람:"+sender+" 내용:"+message, Toast.LENGTH_LONG).show();
                    mAdapter.addItem(sender, message);

                    Float lat = Float.parseFloat(message.split(",")[0]);
                    Float lng =  Float.parseFloat(message.split(",")[1]);
                    updatePosition(lat, lng);


                }
            }

        }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            String message = lat + "," + lng;
            String dest = mEditDest.getText().toString();
            if(location.getProvider().equals(LocationManager.GPS_PROVIDER)){
                Toast.makeText(getApplicationContext(), "GPS Location", Toast.LENGTH_SHORT).show();
                mMyLat = lat;
                mMyLng = lng;
            }else{
                Toast.makeText(getApplicationContext(), "Network Location", Toast.LENGTH_SHORT).show();
                mMyLat = lat;
                mMyLng = lng;
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private void updatePosition(double lat, double lng){
        LatLng position = new LatLng(lat, lng);
        Marker marker = mMap.addMarker(new MarkerOptions().position(position));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 17.0f));
    }

    private void sendSMS(String dest, String message){
        Intent sentIntent = new Intent("ACTION_SMS_SENT");
        PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, sentIntent, 0);

        mSmsManager.sendTextMessage(dest,null,message,sentPI, null);
        IntentFilter sentIntentFilter = new IntentFilter("ACTION_SMS_SENT");
        registerReceiver(mBroadcastReceiver, sentIntentFilter);

        IntentFilter recvIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(mBroadcastReceiver, recvIntentFilter);
    }

    public class ViewHolder {
        public TextView mName;
        public TextView mLoc;
    }

    public class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<PersonData> mListData = new ArrayList<PersonData>();

        public ListViewAdapter(Context mContext){
            super();
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int idx) {
            return mListData.get(idx);
        }

        @Override
        public long getItemId(int idx) {
            return idx;
        }

        @Override
        public View getView(int idx, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.findmember_list_item,null);

                holder.mName = (TextView) convertView.findViewById(R.id.person_name);
                holder.mLoc = (TextView) convertView.findViewById(R.id.person_loc);

                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            PersonData member = mListData.get(idx);

            holder.mName.setText(member.getmName());
            holder.mLoc.setText(member.getmTelNum());

            return convertView;
        }

        public void addItem(String name, String loc){
            PersonData addInfo = null;
            addInfo = new PersonData(false, "astudy", name, loc);   // TODO: 2017-01-01
            mListData.add(addInfo);
            dataChange();
        }

        public void remove(int idx){
            mListData.remove(idx);
            dataChange();
        }

        public void dataChange(){
            mAdapter.notifyDataSetChanged();
        }
    }


}


