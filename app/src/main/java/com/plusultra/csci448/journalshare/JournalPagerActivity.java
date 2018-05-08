package com.plusultra.csci448.journalshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * JournalPagerActivity is used to allow the user to
 * swipe left and right to navigate between their
 * local journal entries.
 *
 * Created by Han on 2/27/18.
 */

public class JournalPagerActivity extends AppCompatActivity
    implements JournalFragment.Callbacks {

    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.JournalShare.entry_id";

    private ViewPager mViewPager;
    private List<JournalEntry> mEntries;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, JournalPagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_entry_pager_view_pager);
        mEntries = JournalBook.get(this).getEntries();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                JournalEntry entry = mEntries.get(position);
                return JournalFragment.newInstance(entry.getId());
            }
            @Override
            public int getCount() {
                return mEntries.size();
            }
        });

        for (int i = 0; i < mEntries.size(); i++) {
            if (mEntries.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break; }
        }
    }

    @Override
    public void onEntryUpdated(JournalEntry entry) {
        
    }
}
