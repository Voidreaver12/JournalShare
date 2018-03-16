package com.plusultra.csci448.journalshare;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by ndeibert on 2/27/2018.
 */

public class JournalActivity extends SingleFragmentActivity {

    private static final String EXTRA_ENTRY_ID = "com.csci448.PLUSULTRA.entry_id";

    public static Intent newIntent(Context packageContext, UUID entryId) {
        Intent intent = new Intent(packageContext, JournalActivity.class);
        intent.putExtra(EXTRA_ENTRY_ID, entryId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID entryId = (UUID) getIntent().getSerializableExtra(EXTRA_ENTRY_ID);
        return JournalFragment.newInstance(entryId);
    }
}
