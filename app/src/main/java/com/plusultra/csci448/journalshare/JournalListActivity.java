package com.plusultra.csci448.journalshare;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * JournalListActivity is used to host the JournalListFragment.
 * In addition, it implements callback methods to properly interact with the fragment.
 *
 * Created by ndeibert on 2/27/2018.
 */

public class JournalListActivity extends SingleFragmentActivity
                                    implements JournalListFragment.Callbacks, JournalFragment.Callbacks {

    public static Intent newIntent(Context context) {
        return new Intent(context, JournalListActivity.class);
    }

    // get JournalListFragment
    @Override
    protected Fragment createFragment() { return new JournalListFragment(); }

    // when tapping on journal entry in list
    @Override
    public void onEntrySelected(JournalEntry entry) {
        // if vertical view, go to journal entry
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = JournalPagerActivity.newIntent(this, entry.getId());
            startActivity(intent);
        // if horizontal view, open entry in second pane
        } else {
            Fragment newDetail = JournalFragment.newInstance(entry.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail).commit();
        }
    }

    // update UI whenever entry is updated
    @Override
    public void onEntryUpdated(JournalEntry entry) {
        JournalListFragment listFragment = (JournalListFragment) getSupportFragmentManager()
                                            .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }

}
