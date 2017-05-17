package examples.guilherme.chat_android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Bimore on 17/05/2017.
 */

public class Utils {

    public static void hideKeyboard(Activity activity) {

        View keyboard = activity.getCurrentFocus();

        if (keyboard != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(keyboard.getWindowToken(), 0);
        }

    }
}
