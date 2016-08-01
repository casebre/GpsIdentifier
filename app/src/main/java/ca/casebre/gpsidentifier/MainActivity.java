package ca.casebre.gpsidentifier;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GpsStatus.Listener {

    private Button btnGpsStatus;
    private TextView txtGpsStatus;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        txtGpsStatus = (TextView) findViewById(R.id.txtGpsStatus);

        btnGpsStatus = (Button) findViewById(R.id.button);
        btnGpsStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        if(checkLocationPermission()) {
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.addGpsStatusListener(new GpsStatus.Listener() {
                @Override
                public void onGpsStatusChanged(int event) {
                    Log.d("DEBUG", "uhhh " + String.valueOf(event));

                    if(event == 2)
                        txtGpsStatus.setText("Gps is OFF");
                    else txtGpsStatus.setText("Gps is ON");


                }
            });
        } else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


    }

    public boolean checkLocationPermission()
    {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {



                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGpsStatusChanged(int event) {

        if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS) {
            GpsStatus status = locationManager.getGpsStatus(null);
            Iterable<GpsSatellite> sats = status.getSatellites();

            Log.d("DEBUG", sats.toString());
        }
        Log.d("DEBUG", "asdasda");
        txtGpsStatus.setText("blah blah");

    }
}
