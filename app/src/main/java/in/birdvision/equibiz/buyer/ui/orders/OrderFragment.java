/*
 * *
 *  * Created by Vaibhav Andhare on 13/7/20 8:32 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.buyer.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import in.birdvision.equibiz.R;

public class OrderFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);

        ViewPager viewPager = root.findViewById(R.id.buyer_fragment_vp);
        setUpAdapter(viewPager);
        TabLayout tabLayout = root.findViewById(R.id.buyer_fragment_tablayout);
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }


    private void setUpAdapter(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AllOrdersFragment(), "All");
        adapter.addFragment(new CurrentOrdersFragment(), "Current");
        adapter.addFragment(new PendingOrdersFragment(), "Pending");
        adapter.addFragment(new CanceledOrdersFragment(), "Cancelled");
        viewPager.setAdapter(adapter);
    }


}