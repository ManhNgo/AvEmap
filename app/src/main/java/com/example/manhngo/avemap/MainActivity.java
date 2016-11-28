package com.example.manhngo.avemap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.manhngo.avemap.Fragments.CallBack.MainCallbacks;
import com.example.manhngo.avemap.Fragments.ControlMapFragment;
import com.example.manhngo.avemap.Fragments.MMapFragment;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainCallbacks, OnMapReadyCallback {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private SupportMapFragment supportMapFragment;


    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager = getSupportFragmentManager();
    ControlMapFragment controlMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Map_Fragment

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        controlMapFragment = ControlMapFragment.newInstance("first-Controll");
        fragmentTransaction.replace(R.id.controll_map, controlMapFragment);
        //fragmentTransaction.commit();
        if(controlMapFragment == null){
            Toast.makeText(this, "Null cmnr", Toast.LENGTH_SHORT).show();
        }


        //supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        supportMapFragment = SupportMapFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.map, supportMapFragment).commit();

        supportMapFragment.getMapAsync(this);
        //fragmentTransaction.commit();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.ket_xe:
                Toast.makeText(this, "Kẹt xe", Toast.LENGTH_SHORT).show();
                break;
            case R.id.co_csgt:
                Toast.makeText(this, "Có cảnh sát giao thông", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ngap_lut:
                Toast.makeText(this, "Ngập lụt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tai_nan_giao_thong:
                Toast.makeText(this, "Tai nạn giao thông", Toast.LENGTH_SHORT).show();
                break;
            case R.id.khac:
                Toast.makeText(this, "Khác", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Không chọn được Item nào", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle nav_navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_signin:
                Toast.makeText(this, "Đăng nhập", Toast.LENGTH_SHORT).show();
                Intent signinIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(signinIntent);
                break;
            case R.id.nav_choosetype:
                Toast.makeText(this, "Choose type", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_comment:
                Toast.makeText(this, "Comment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navShowMap:
                Toast.makeText(this, "Show map location", Toast.LENGTH_SHORT).show();
                fragmentManager.beginTransaction().replace(R.id.map, new MMapFragment()).commit();
                break;
            case R.id.nav_shownotifes:
                Toast.makeText(this, "Show notifies", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_details:
                Intent detailIntent = new Intent(MainActivity.this, TabsDetailActivity.class);
                startActivity(detailIntent);
                break;
            default:
                Toast.makeText(this, "Không chọn được Item nào", Toast.LENGTH_SHORT).show();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onMsgFromFragToMain(String sender, String receiver, String strValue)  {
        // show message arriving to MainActivity
        //Fragment chứa các button gửi vị trí hiện sau khi thao tác với các button cho supportMapFragment chứa ListView
        if (sender.equals("Controll-Frag")) {
            if(receiver.equals("Show-Traffic")){
                if(strValue.equals("Turn-On")){
                    try {
                        // forward blue-data to redFragment using its callback method
                        boolean a = true;
                        mMapFragment.onMsgFromMainToFragment_Traffic(a);
                    } catch (Exception e) {
                        Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
                    }
                }

            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

}
