package com.blockent.memoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blockent.memoapp.adapter.MemoAdapter;
import com.blockent.memoapp.api.MemoApi;
import com.blockent.memoapp.api.NetworkClient;
import com.blockent.memoapp.config.Config;
import com.blockent.memoapp.model.Memo;
import com.blockent.memoapp.model.MemoList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ProgressBar progressBar;

    RecyclerView recyclerView;
    MemoAdapter adapter;
    ArrayList<Memo> memoArrayList = new ArrayList<>();

    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 억세스토큰이 저장되어있으면,
        // 로그인한 유저이므로 메인액티비티를 실행하고,

        // 그렇지 않으면, 회원가입 액티비티를 실행하고,
        // 메인액티비티는 종료!

        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        accessToken = sp.getString(Config.ACCESS_TOKEN, "");

        if(accessToken.isEmpty()){
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // 회원가입/로그인 유저면, 아래 코드를 실행하도록 둔다.
        btnAdd = findViewById(R.id.btnAdd);
        progressBar = findViewById(R.id.progressBar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        // 네트워크로부터 내 메모를 가져온다.
        getNetworkData();

    }

    private void getNetworkData() {
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = NetworkClient.getRetrofitClient(MainActivity.this);

        MemoApi api = retrofit.create(MemoApi.class);

        Call<MemoList> call = api.getMemoList("Bearer "+accessToken, 0, 20);

        call.enqueue(new Callback<MemoList>() {
            @Override
            public void onResponse(Call<MemoList> call, Response<MemoList> response) {
                progressBar.setVisibility(View.GONE);

                if(response.isSuccessful()){

                    // 정상적으로 데이터 받았으니, 리사이클러뷰에 표시
                    MemoList memoList = response.body();

                    memoArrayList.addAll(memoList.getItems());

                    adapter = new MemoAdapter(MainActivity.this, memoArrayList);
                    recyclerView.setAdapter(adapter);

                }else{
                    Toast.makeText(MainActivity.this, "서버에 문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<MemoList> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}






