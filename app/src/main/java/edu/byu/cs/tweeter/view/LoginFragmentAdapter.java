package edu.byu.cs.tweeter.view;

import android.content.res.Resources;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class LoginFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;
    ArrayList<CharSequence> titles;

    public LoginFragmentAdapter(FragmentManager fm, CharSequence... titles) {
        super(fm);
        fragments = new ArrayList<>();
        this.titles = new ArrayList<>();

        fragments.add(new LoginFragment());
        fragments.add(new RegisterFragment());
        this.titles.addAll(Arrays.asList(titles));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
