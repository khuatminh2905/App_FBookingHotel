package com.example.fbooking.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fbooking.R;

public class CheckAgainActivity extends AppCompatActivity {
    private TextView tvDateCheckInAgain, tvNightAgain, tvDateCheckOutAgain,
                    tvRoomNumberAgain, tvRoomTypeAgain, tvRankAgain, tvPeopleAgain, tvDescriptionAgain,
                    tvNameAgain, tvPhoneNumberAgain, tvIdPersonAgain, tvEmailAgain,
                    tvOrderAgain, tvCheckInTimeAgain;
    private ImageView imgRoomAgain;
    private AppCompatButton btnCancelAgain, btnOpenPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_again);

        initUi();

        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        imgRoomAgain.getLayoutParams().height = height / 5;

        onClickButton();
    }

    private void onClickButton() {
        btnCancelAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckAgainActivity.this, FillInformationActivity.class));
            }
        });

        btnOpenPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckAgainActivity.this, PayActivity.class));
            }
        });
    }

    private void initUi() {
        tvDateCheckInAgain = findViewById(R.id.tv_date_check_in_again);
        tvNightAgain = findViewById(R.id.tv_night_again);
        tvDateCheckOutAgain = findViewById(R.id.tv_date_check_out_again);
        tvRoomNumberAgain = findViewById(R.id.tv_room_number_again);
        tvRoomTypeAgain = findViewById(R.id.tv_room_type_again);
        tvRankAgain = findViewById(R.id.tv_rank_again);
        tvPeopleAgain = findViewById(R.id.tv_people_again);
        tvDescriptionAgain = findViewById(R.id.tv_description_again);
        tvNameAgain = findViewById(R.id.tv_name_again);
        tvPhoneNumberAgain = findViewById(R.id.tv_phone_number_again);
        tvIdPersonAgain = findViewById(R.id.tv_id_person_again);
        tvEmailAgain = findViewById(R.id.tv_email_again);
        tvOrderAgain = findViewById(R.id.tv_order_again);
        tvCheckInTimeAgain = findViewById(R.id.tv_check_in_time_again);

        imgRoomAgain = findViewById(R.id.img_room_again);

        btnCancelAgain = findViewById(R.id.btn_cancel_again);
        btnOpenPay = findViewById(R.id.btn_open_pay_again);
    }
}