package com.example.fbooking.booking;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.fbooking.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {
    private View view;
    private EditText edtSearchPerson;
    private AppCompatButton btnSortRoom, btnOpenBottomSheet,
            btnRegularRoom, btnHighClassRoom, btnLuxuryRoom,
            btnSingleRoom, btnTwinRoom, btnTwoBedRoom,
            btnWifi, btnPool, btnParking,
            btnRestaurant, btnReceptionist, btnElevator,
            btnWheelChair, btnGym, btnMeeting,
            btnTaking, btnLaundary, btnDiffrent;
    private RecyclerView rcvRoom;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout srlBooking;

    private ProgressDialog progressDialog;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_booking, container, false);

        initUi();

        srlBooking.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progressDialog.show();

//                srlBooking.setRefreshing(false);
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
            }
        });

        openBottomSheet();

        return view;
    }

    //Mo cac tien ich khac
    private void openBottomSheet() {
        btnOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }


    private void initUi() {
        edtSearchPerson = view.findViewById(R.id.edt_search_person_per_room);

        btnSortRoom = view.findViewById(R.id.btn_sort);
        btnOpenBottomSheet = view.findViewById(R.id.btn_open_bottom_sheet);

        btnRegularRoom = view.findViewById(R.id.btn_regular_room);
        btnHighClassRoom = view.findViewById(R.id.btn_high_class_room);
        btnLuxuryRoom = view.findViewById(R.id.btn_luxury_room);
        btnSingleRoom = view.findViewById(R.id.btn_single_room);
        btnTwinRoom = view.findViewById(R.id.btn_twin_room);
        btnTwoBedRoom = view.findViewById(R.id.btn_two_bed_room);
        btnWifi = view.findViewById(R.id.btn_wifi);
        btnPool = view.findViewById(R.id.btn_pool);
        btnParking = view.findViewById(R.id.btn_parking);
        btnRestaurant = view.findViewById(R.id.btn_restaurant);
        btnReceptionist = view.findViewById(R.id.btn_receptionist);
        btnElevator = view.findViewById(R.id.btn_elevator);
        btnWheelChair = view.findViewById(R.id.btn_wheel_chair);
        btnGym = view.findViewById(R.id.btn_gym);
        btnMeeting = view.findViewById(R.id.btn_meeting);
        btnTaking = view.findViewById(R.id.btn_taking);
        btnLaundary = view.findViewById(R.id.btn_laundary);
        btnDiffrent = view.findViewById(R.id.btn_diffrent);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("??ang t???i l???i d??? li???u...");
        srlBooking = view.findViewById(R.id.srl_booking);
        rcvRoom = view.findViewById(R.id.rcv_room);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rcvRoom.setLayoutManager(linearLayoutManager);

        LinearLayout linearLayout = view.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
    }
}