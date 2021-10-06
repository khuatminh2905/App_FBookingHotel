package com.example.fbooking.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fbooking.R;
import com.example.fbooking.userloginandsignup.LoginActivity;
import com.example.fbooking.userloginandsignup.SignUpActivity;
import com.google.firebase.auth.FirebaseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class FillInformationActivity extends AppCompatActivity {
    private EditText edtNameFill, edtPhoneFill, edtIdPersonFill, edtEmailFill,
            edtCheckInDateFill, edtCheckOutDateFill, edtPeopleFill, edtNightFill,
            edtCheckInTimeFill, edtCheckOutTimeFill;
    private RadioGroup rgCheck;
    private RadioButton rbSelfFill, rbOtherFill, rbCheck;
    private CheckBox cbPayFill;
    private TextView tvPriceFill, tvErrorFill;
    private AppCompatButton btnCancelFill, btnOpenCheckAgain, btnOpenLoginFill;

    private String amPm;

    private RelativeLayout rlFormFill, rlMainFill;
    private LinearLayout lnLoginFill;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_information);

        initUi();

//        showFrom();

        onChoseDate();

        onChoseTime();

        onClickButton();
    }

    //Hien thi form dang nhap khi nguoi dung chua dang nhap
    private void showFrom() {
        if (user == null) {
            lnLoginFill.setVisibility(View.VISIBLE);
            btnOpenLoginFill.setEnabled(true);
            btnOpenLoginFill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(FillInformationActivity.this, LoginActivity.class));
                }
            });

            rlMainFill.setBackgroundColor(Color.WHITE);
            rlFormFill.setVisibility(View.GONE);
        } else {
            lnLoginFill.setVisibility(View.GONE);
            btnOpenLoginFill.setEnabled(false);

            rlFormFill.setVisibility(View.VISIBLE);
        }
    }

    private void onClickButton() {
        btnCancelFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FillInformationActivity.this, RoomDetailActivity.class));
            }
        });

        btnOpenCheckAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCheckInformationAgain();
            }
        });
    }

    private void onChoseDate() {
        Calendar calendarCheckInDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener checkInDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarCheckInDate.set(Calendar.YEAR, year);
                calendarCheckInDate.set(Calendar.MONTH, month);
                calendarCheckInDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateCalender();
            }

            private void updateCalender() {
                String formatDate = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate, Locale.getDefault());
                edtCheckInDateFill.setText(simpleDateFormat.format(calendarCheckInDate.getTime()));
            }
        };
        edtCheckInDateFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new DatePickerDialog(FillInformationActivity.this, checkInDate, calendarCheckInDate.get(Calendar.YEAR),
//                        calendarCheckInDate.get(Calendar.MONTH), calendarCheckInDate.get(Calendar.DAY_OF_MONTH)).show();
                DatePickerDialog datePickerDialog = new DatePickerDialog(FillInformationActivity.this, checkInDate,
                        calendarCheckInDate.get(Calendar.YEAR),calendarCheckInDate.get(Calendar.MONTH), calendarCheckInDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        Calendar calendarCheckOutDate = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener checkOutDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarCheckOutDate.set(Calendar.YEAR, year);
                calendarCheckOutDate.set(Calendar.MONTH, month);
                calendarCheckOutDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateCalender();
            }

            private void updateCalender() {
                String formatDate = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate, Locale.getDefault());
                edtCheckOutDateFill.setText(simpleDateFormat.format(calendarCheckOutDate.getTime()));
            }
        };

        edtCheckOutDateFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new DatePickerDialog(FillInformationActivity.this, checkOutDate, calendarCheckOutDate.get(Calendar.YEAR),
//                        calendarCheckOutDate.get(Calendar.MONTH), calendarCheckOutDate.get(Calendar.DAY_OF_MONTH)).show();
                DatePickerDialog datePickerDialog = new DatePickerDialog(FillInformationActivity.this, checkOutDate,
                        calendarCheckOutDate.get(Calendar.YEAR),calendarCheckOutDate.get(Calendar.MONTH), calendarCheckOutDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        edtCheckInDateFill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                caculateDays();
            }
        });

        edtCheckOutDateFill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                caculateDays();
            }
        });
    }

    //Dang co lõi
    private void caculateDays() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String checkInDate = edtCheckInDateFill.getText().toString();
        String checkOutDate = edtCheckOutDateFill.getText().toString();

        try {
            Date startDate = simpleDateFormat.parse(checkInDate);
            Date endDate = simpleDateFormat.parse(checkOutDate);

            long duration = (endDate.getTime() - startDate.getTime());

            if (startDate.compareTo(endDate) > 0) {
                tvErrorFill.setText("Sai ngày nhận / trả phòng!");
                edtNightFill.setText("Lỗi");
                return;
            } else {
                tvErrorFill.setText("");
                edtNightFill.setText(TimeUnit.DAYS.convert(duration, TimeUnit.MILLISECONDS) + "");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void onChoseTime() {
        Calendar selectedTime = Calendar.getInstance();
        int hour = selectedTime.get(Calendar.HOUR_OF_DAY);
        int minute = selectedTime.get(Calendar.MINUTE);
        TimePickerDialog checkInTime = new TimePickerDialog(FillInformationActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                selectedTime.set(Calendar.MINUTE, selectedMinute);
                if (selectedHour >= 12) {
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }
                edtCheckInTimeFill.setText(String.format("%02d:%02d", selectedHour, selectedMinute) + " " + amPm);
            }
        }, hour, minute, false);

        edtCheckInTimeFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInTime.show();
            }
        });

        TimePickerDialog checkOutTime = new TimePickerDialog(FillInformationActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                selectedTime.set(Calendar.MINUTE, selectedMinute);
                if (selectedHour >= 12) {
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }
                edtCheckOutTimeFill.setText(String.format("%02d:%02d", selectedHour, selectedMinute) + " " + amPm);
            }
        }, hour, minute, false);

        edtCheckOutTimeFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOutTime.show();
            }
        });
    }

    private void openCheckInformationAgain() {
        String strName = edtNameFill.getText().toString().trim();
        String strPhoneNumber = edtPhoneFill.getText().toString().trim();
        String strIdPerson = edtIdPersonFill.getText().toString().trim();
        String strEmail = edtEmailFill.getText().toString().trim();

        String strCheckInDate = edtCheckInDateFill.getText().toString().trim();
        String strCheckOutDate = edtCheckOutDateFill.getText().toString().trim();
        String strPeopleFill = edtPeopleFill.getText().toString().trim();
        String strNight = edtNightFill.getText().toString().trim();

        int selectedId = rgCheck.getCheckedRadioButtonId();
        rbCheck = findViewById(selectedId);
//        rbSelfFill = findViewById(selectedId);
//        rbOtherFill = findViewById(selectedId);
//        String forMySelf = rbSelfFill.getText().toString();
//        String forTheOther = rbOtherFill.getText().toString();

        String strCheckInTime = edtCheckInTimeFill.getText().toString().trim();
        String strCheckOutTime = edtCheckOutTimeFill.getText().toString().trim();

        if (strName.isEmpty() && strPhoneNumber.isEmpty() && strIdPerson.isEmpty() && strEmail.isEmpty()
                && strCheckInDate.isEmpty() && strCheckOutDate.isEmpty() && strPeopleFill.isEmpty() && strNight.isEmpty()
                && strCheckInTime.isEmpty() && strCheckOutTime.isEmpty()) {
            tvErrorFill.setText("Thông tin bị trống!");
            return;
        }

        if (strName.isEmpty()) {
            tvErrorFill.setText("Bạn chưa nhập họ và tên!");
            return;
        }

        if (strPhoneNumber.isEmpty()) {
            tvErrorFill.setText("Bạn chưa nhập số điện thoại!");
            return;
        } else if (!strPhoneNumber.matches(String.valueOf(Patterns.PHONE))) {
            tvErrorFill.setText("Sai định dạng số điện thoại!");
            return;
        }

        if (strIdPerson.isEmpty()) {
            tvErrorFill.setText("Bạn chưa nhập CMND/CCCD!");
            return;
        }

        if (strEmail.isEmpty()) {
            tvErrorFill.setText("Bạn chưa nhập email!");
            return;
        } else if (!strEmail.matches(String.valueOf(Patterns.EMAIL_ADDRESS))) {
            tvErrorFill.setText("Sai định dạng Email!");
            return;
        }

        if (strCheckInDate.isEmpty()) {
            tvErrorFill.setText("Bạn chưa nhập ngày nhận phòng!");
            return;
        }

        if (strCheckOutDate.isEmpty()) {
            tvErrorFill.setText("Bạn chưa nhập ngày trả phòng!");
            return;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate = simpleDateFormat.parse(strCheckInDate);
            Date endDate = simpleDateFormat.parse(strCheckOutDate);

            if (startDate.compareTo(endDate) > 0) {
                tvErrorFill.setText("Sai ngày nhận / trả phòng!");
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (strPeopleFill.isEmpty()) {
            tvErrorFill.setText("Bạn chưa nhập số người!");
            return;
        }

        if (strNight.isEmpty()) {
            tvErrorFill.setText("Bạn chưa nhập số đêm!");
            return;
        }

        if (strCheckInTime.isEmpty()) {
            tvErrorFill.setText("Bạn chưa nhập ngày nhận phòng!");
            return;
        }

        if (strCheckInTime.isEmpty()) {
            tvErrorFill.setText("Bạn chưa nhập ngày trả phòng!");
            return;
        }

        tvErrorFill.setText("");

        startActivity(new Intent(FillInformationActivity.this, CheckAgainActivity.class));
    }

    private void initUi() {
        edtNameFill = findViewById(R.id.edt_name_fill);
        edtPhoneFill = findViewById(R.id.edt_phone_fill);
        edtIdPersonFill = findViewById(R.id.edt_id_person_fill);
        edtEmailFill = findViewById(R.id.edt_email_fill);
        edtCheckInDateFill = findViewById(R.id.edt_check_in_date_fill);
        edtCheckOutDateFill = findViewById(R.id.edt_check_out_date_fill);
        edtPeopleFill = findViewById(R.id.edt_people_fill);
        edtNightFill = findViewById(R.id.edt_night_fill);
        edtCheckInTimeFill = findViewById(R.id.edt_check_in_time_fill);
        edtCheckOutTimeFill = findViewById(R.id.edt_check_out_time_fill);

        rgCheck = findViewById(R.id.rg_check);
//        rbSelfFill = findViewById(R.id.rb_self_fill);
//        rbOtherFill = findViewById(R.id.rb_other_fill);

        cbPayFill = findViewById(R.id.cb_pay_fill);

        tvPriceFill = findViewById(R.id.tv_price_fill);
        tvErrorFill = findViewById(R.id.tv_error_fill);

        btnCancelFill = findViewById(R.id.btn_cancel_fill);
        btnOpenCheckAgain = findViewById(R.id.btn_open_check_again);

        rlMainFill = findViewById(R.id.rl_mail_fill);
        rlFormFill = findViewById(R.id.rl_form_fill);
        lnLoginFill = findViewById(R.id.ln_login_fill);
        btnOpenLoginFill = findViewById(R.id.btn_open_login_fill);
    }
}