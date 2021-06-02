package com.tejaswininimbalkar.hellohomeoassesment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tejaswininimbalkar.hellohomeoassesment.LocalDB.AppDatabase;
import com.tejaswininimbalkar.hellohomeoassesment.LocalDB.CrewMembersEntity;
import com.tejaswininimbalkar.hellohomeoassesment.LocalDB.MembersAdapter;
import com.tejaswininimbalkar.hellohomeoassesment.ServerData.ApiInterface;
import com.tejaswininimbalkar.hellohomeoassesment.ServerData.CrewMembers;
import com.tejaswininimbalkar.hellohomeoassesment.ServerData.CrewMembersAdapter;
import com.tejaswininimbalkar.hellohomeoassesment.ServerData.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrewMembersDisplay extends AppCompatActivity {

    private ApiInterface apiInterface;
    private AppDatabase database;
    private RecyclerView recyclerView;
    private Button refresh, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_members_display);

        recyclerView = findViewById(R.id.crew_members_recycler);
        refresh = findViewById(R.id.refresh_btn);
        delete = findViewById(R.id.delete_button);

        LinearLayoutManager layoutManager = new LinearLayoutManager(CrewMembersDisplay.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        database = AppDatabase.getInstance(CrewMembersDisplay.this);

        loadData();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.crewMembersDao().deleteAll();
                loadData();
            }
        });
    }

    private void loadData() {
        if (!isConnected(CrewMembersDisplay.this)) {
            List<CrewMembersEntity> list = database.crewMembersDao().getAllRecords();
            MembersAdapter adapter1 = new MembersAdapter(CrewMembersDisplay.this, list);
            recyclerView.setAdapter(adapter1);
            if (list.isEmpty()) {
                Toast.makeText(this, "No data available! Please connect to the internet.", Toast.LENGTH_SHORT).show();
            }
        } else {
            callApi();
        }
    }

    private void callApi() {
        apiInterface.getCrewMembers().enqueue(new Callback<List<CrewMembers>>() {
            @Override
            public void onResponse(Call<List<CrewMembers>> call, Response<List<CrewMembers>> response) {
                if (response.body().size() > 0) {
                    List<CrewMembers> membersList = response.body();
                    CrewMembersAdapter adapter = new CrewMembersAdapter(membersList, CrewMembersDisplay.this);
                    recyclerView.setAdapter(adapter);
                    insertToLocalDB(membersList);
                } else {
                    Toast.makeText(CrewMembersDisplay.this, "List is empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CrewMembers>> call, Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

    private void insertToLocalDB(List<CrewMembers> membersList) {
        AppDatabase database = AppDatabase.getInstance(this.getApplicationContext());
        for (int i = 0; i < membersList.size(); i++) {
            CrewMembersEntity entity = new CrewMembersEntity();
            entity.name = membersList.get(i).getName();
            entity.image = membersList.get(i).getImage();
            entity.agency = membersList.get(i).getAgency();
            entity.wikipedia = membersList.get(i).getWikipedia();
            entity.status = membersList.get(i).getStatus();

//            if (Build.VERSION.SDK_INT >= 23) {
//                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        == PackageManager.PERMISSION_GRANTED) {
//                    Log.e("Permission error","You have permission");
//                } else {
//
//                    Log.e("Permission error","You have asked for permission");
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                }
//            }
//            else { //you dont need to worry about these stuff below api level 23
//                Log.e("Permission error","You already have the permission");
//            }
//
//            Uri uri = Uri.parse(membersList.get(i).getWikipedia());
//            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//            DownloadManager.Request request = new DownloadManager.Request(uri);
//            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//
//            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, "/crewmembers/"+"/"+membersList.get(i).getName()+".png");
//            request.setMimeType("*/*");
//            downloadManager.enqueue(request);

            database.crewMembersDao().insert(entity);
        }
    }

    private boolean isConnected(CrewMembersDisplay crewMembersDisplay) {
        ConnectivityManager connectivityManager = (ConnectivityManager) crewMembersDisplay.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiConnection != null && wifiConnection.isConnected()) || (mobileConnection != null && mobileConnection.isConnected());
    }


}