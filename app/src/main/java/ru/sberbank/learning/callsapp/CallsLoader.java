package ru.sberbank.learning.callsapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Тичер on 10.06.2017.
 */
public class CallsLoader extends AsyncTaskLoader<List<Call>> {

    public CallsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Call> loadInBackground() {
        List<Call> calls = new ArrayList<>();

        Cursor cursor = getCallsCursor();
        if (cursor != null) {
            DatabaseUtils.dumpCursor(cursor);
            cursor.close();
        } else {
            Log.e("Loader", "cursor is null");
        }

        return calls;
    }

    private Cursor getCallsCursor() {
        ContentResolver resolver = getContext().getContentResolver();
        Cursor cursor = null;
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
            cursor = resolver.query(
                    CallLog.Calls.CONTENT_URI,
                    null, null, null, null
            );
        }
        return cursor;
    }
}
