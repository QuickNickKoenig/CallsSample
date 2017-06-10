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

    private static final String TAG = "CallsLoader";

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
            fillList(cursor, calls);
            cursor.close();
        } else {
            Log.e(TAG, "cursor is null");
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

    private static void fillList(Cursor source, List<Call> target) {
        if (source.moveToFirst()) {
            while (!source.isAfterLast()) {
                target.add(createCallFromCursor(source));
                source.moveToNext();
            }
        }
    }

    private static Call createCallFromCursor(Cursor cursor) {
        Call call = new Call();
        call.id = getLong(cursor, CallLog.Calls._ID);
        call.date = getLong(cursor, CallLog.Calls.DATE);
        call.duration = getLong(cursor, CallLog.Calls.DURATION);
        call.number = getString(cursor, CallLog.Calls.NUMBER);
        call.read = getInt(cursor, CallLog.Calls.IS_READ) != 0;
        return call;
    }

    private static long getLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    private static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    private static int getInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
