package com.example.ronny.sheplus;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.multidex.MultiDex;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;

import co.chatsdk.core.session.ChatSDK;
import co.chatsdk.core.session.Configuration;
import co.chatsdk.firebase.FirebaseModule;
import co.chatsdk.firebase.file_storage.FirebaseFileStorageModule;
import co.chatsdk.firebase.push.FirebasePushModule;
import co.chatsdk.firebase.social_login.FirebaseSocialLoginModule;
import co.chatsdk.ui.login.LoginActivity;


public class ShePlus extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_plus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


// Enable multi-dexing
        MultiDex.install(this);

        Context context = getApplicationContext();

        FirebaseApp.initializeApp(context);

// Create a new configuration
        Configuration.Builder builder = new Configuration.Builder(context);

        builder.firebase("firebase_url", "firebase_root_path", "firebase_storage_url", "firebase_cloud_messaging_server_key");


// Perform any configuration steps

// Initialize the Chat SDK
        ChatSDK.initialize(builder.build());

   //     FirebaseSocialLoginModule.activate(getApplicationContext());

        builder.twitterLoginEnabled(false);

// Activate the Firebase module
        FirebaseModule.activate(context);

// File storage is needed for profile image upload and image messages
        FirebaseFileStorageModule.activate();
        FirebasePushModule.activateForFirebase();


/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatsdk = new Intent(ShePlus.this, ChatSDKLoginActivity.class);
                startActivity(chatsdk);
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    protected void attachBaseContext (Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
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
        getMenuInflater().inflate(R.menu.she_plus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.sheplus_home) {
            Intent sheplusHome =new Intent(ShePlus.this,ShePlus.class);
            startActivity(sheplusHome);
        } else if (id == R.id.newsletter) {
//            Toast.makeText(this, "You are welcome to youngvoices.org", Toast.LENGTH_SHORT).show();
          String youngvoices = "http://www.savsign.org/";
           Intent young = new Intent(Intent.ACTION_VIEW,Uri.parse(youngvoices));
                    startActivity(young);


        }  else if (id == R.id.nav_share) {
            String shareBody = "Download SHE+ App. From https://github.com/SGSCollect/SHEPLUS";
            final Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(intent.EXTRA_TEXT, shareBody);
            try{
                startActivity(intent.createChooser(intent, "Share SHE+ Using"));
            }catch (android.content.ActivityNotFoundException exception){
                //handle error
            }

        }
        else if(id==R.id.nav_facebook){
            String FacebookPageUrl = "https://www.facebook.com/SHEplusGhana/";
            Intent shepluspage = new Intent(Intent.ACTION_VIEW, Uri.parse(FacebookPageUrl) );
            startActivity(shepluspage);
        }
        else if(id==R.id.she_chat){

            Intent chatsdk = new Intent(ShePlus.this, LoginActivity.class);
            startActivity(chatsdk);
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
