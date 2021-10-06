package com.example.fbooking.favorite;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fbooking.userloginandsignup.LoginActivity;
import com.example.fbooking.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {
    private View view;
    private LinearLayout lnLoginFavorite;
    private AppCompatButton btnOpenLogin;

    private TextView tvDeleteAll, tvTitleFavorite;
    private RecyclerView rcvRoomFavorite;
    private LinearLayoutManager linearLayoutManager;

    private FirebaseUser user;
    private DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_favorite, container, false);

        initUi();

        showFrom();

        return view;
    }

    //Hien thi form dang nhap khi nguoi dung chua dang nhap
    private void showFrom() {
        if (user == null) {
            lnLoginFavorite.setVisibility(View.VISIBLE);
            btnOpenLogin.setEnabled(true);
            btnOpenLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            });

            tvTitleFavorite.setVisibility(View.GONE);
            tvDeleteAll.setVisibility(View.GONE);
            rcvRoomFavorite.setVisibility(View.GONE);
        } else {
            lnLoginFavorite.setVisibility(View.GONE);
            btnOpenLogin.setEnabled(false);

            tvTitleFavorite.setVisibility(View.VISIBLE);
            tvDeleteAll.setVisibility(View.VISIBLE);
            rcvRoomFavorite.setVisibility(View.VISIBLE);
        }
    }

    private void initUi() {
        lnLoginFavorite = view.findViewById(R.id.ln_login_favorite);
        btnOpenLogin = view.findViewById(R.id.btn_open_login_favorite);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        tvTitleFavorite = view.findViewById(R.id.tv_title_favorite);
        tvDeleteAll = view.findViewById(R.id.tv_delete_all);
        rcvRoomFavorite = view.findViewById(R.id.rcv_room_favorite);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rcvRoomFavorite.setLayoutManager(linearLayoutManager);
    }
}