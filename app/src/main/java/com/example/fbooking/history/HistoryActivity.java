package com.example.fbooking.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fbooking.R;

public class HistoryActivity extends AppCompatActivity {
    private TextView tvTitleHistory, tvDeleteAllHistory;
    private RecyclerView rcvRoomHistory;
    private LinearLayoutManager linearLayoutManager;

    private SwipeRefreshLayout srlHistory;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

//        srlHistory.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                progressDialog.show();
//
////                srlBooking.setRefreshing(false);
////                if (progressDialog.isShowing()) {
////                    progressDialog.dismiss();
////                }
//            }
//        });

        initUi();
    }

    private void initUi() {
        tvTitleHistory = findViewById(R.id.tv_title_history);
        tvDeleteAllHistory = findViewById(R.id.tv_delete_all_history);
        rcvRoomHistory = findViewById(R.id.rcv_room_history);

        linearLayoutManager = new LinearLayoutManager(HistoryActivity.this);
        rcvRoomHistory.setLayoutManager(linearLayoutManager);

        progressDialog = new ProgressDialog(HistoryActivity.this);
        progressDialog.setMessage("Đang tải lại dữ liệu...");
        srlHistory = findViewById(R.id.srl_history);
    }
}