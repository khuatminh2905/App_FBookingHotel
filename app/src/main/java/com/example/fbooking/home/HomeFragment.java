package com.example.fbooking.home;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fbooking.R;
import com.example.fbooking.home.Photo;
import com.example.fbooking.home.PhotoAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private View view;
    private ViewPager vpSlide;
    private CircleIndicator ciSlide;
    private PhotoAdapter photoAdapter;
    private List<Photo> photoList;
    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_home, container, false);

        initUi();

        autoSlideShow();
        return view;
    }

    //Slide anh
    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.room1));
        list.add(new Photo(R.drawable.room2));
        list.add(new Photo(R.drawable.room3));
        list.add(new Photo(R.drawable.room4));
        list.add(new Photo(R.drawable.room5));
        list.add(new Photo(R.drawable.room6));
        list.add(new Photo(R.drawable.room7));
        list.add(new Photo(R.drawable.room8));
        list.add(new Photo(R.drawable.room9));
        list.add(new Photo(R.drawable.room10));

        return list;
    }

    private void autoSlideShow() {
        if (photoList == null || photoList.isEmpty() || vpSlide == null) {
            return;
        }

        if (timer == null) {
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = vpSlide.getCurrentItem();
                        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
                        vpSlide.getLayoutParams().height = height / 4;
                        int totalItem = photoList.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            vpSlide.setCurrentItem(currentItem);
                        } else {
                            vpSlide.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }

    private void initUi() {
        vpSlide = view.findViewById(R.id.vp_slide);
        ciSlide = view.findViewById(R.id.ci_slide);

        photoList = getListPhoto();

        photoAdapter = new PhotoAdapter(getContext(), photoList);
        vpSlide.setAdapter(photoAdapter);
        ciSlide.setViewPager(vpSlide);
        photoAdapter.registerDataSetObserver(ciSlide.getDataSetObserver());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}