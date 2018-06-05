package py.com.softcoding.fingerprintlib.fingerprint.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Alfredo Cano on 03/06/18.
 */

@SuppressWarnings("unchecked")
public class FingerprintDialog<T extends FingerprintDialog> {
    protected Context context;
    protected String title;
    protected String message;

    protected boolean cancelOnTouchOutside;
    protected boolean cancelOnPressBack;
    protected boolean dimBackground;

    LayoutInflater layoutInflater;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    View dialogView;

    public FingerprintDialog(Context context) {
        this.context = context;
        this.title = "";
        this.message = "";
        this.cancelOnTouchOutside = false;
        this.cancelOnPressBack = false;
        this.dimBackground = true;
        this.layoutInflater = LayoutInflater.from(context);
        this.builder = new AlertDialog.Builder(context);
    }

    public T title(String title) {
        this.title = title;
        return (T) this;
    }

    public T message(String message) {
        this.message = message;
        return (T) this;
    }

    public T title(int resTitle) {
        this.title = context.getResources().getString(resTitle);
        return (T) this;
    }

    public T message(int resMessage) {
        this.message = context.getResources().getString(resMessage);
        return (T) this;
    }

    public T cancelOnTouchOutside(boolean cancelOnTouchOutside) {
        this.cancelOnTouchOutside = cancelOnTouchOutside;
        return (T) this;
    }

    public T cancelOnPressBack(boolean cancelOnPressBack) {
        this.cancelOnPressBack = cancelOnPressBack;
        return (T) this;
    }

    public T dimBackground(boolean dimBackground) {
        this.dimBackground = dimBackground;
        return (T) this;
    }
}