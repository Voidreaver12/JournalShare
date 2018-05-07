package com.plusultra.csci448.journalshare;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by ndeibert on 2/27/2018.
 */

public class JournalListActivity extends SingleFragmentActivity
                                    implements JournalListFragment.Callbacks, JournalFragment.Callbacks {

    @Override
    protected Fragment createFragment() { return new JournalListFragment(); }

    @Override
    public void onEntrySelected(JournalEntry entry) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = JournalPagerActivity.newIntent(this, entry.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = JournalFragment.newInstance(entry.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail).commit();
        }
    }

    @Override
    public void onEntryUpdated(JournalEntry entry) {
        JournalListFragment listFragment = (JournalListFragment) getSupportFragmentManager()
                                            .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }

}
