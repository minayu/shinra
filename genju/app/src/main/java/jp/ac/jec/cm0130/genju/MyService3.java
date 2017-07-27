package jp.ac.jec.cm0130.genju;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by minayu on 16/11/21.
 */
public class MyService3 extends IntentService {
    final static String TAG = "ServiceTest3";
    public MyService3() {
        super(TAG);
    }

    @Override protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
    }
}
