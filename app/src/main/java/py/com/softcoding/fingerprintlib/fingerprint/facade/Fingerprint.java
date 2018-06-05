package py.com.softcoding.fingerprintlib.fingerprint.facade;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;

/**
 * Created by Alfredo Cano on 04/06/18.
 */

public class Fingerprint {
    private final static String TAG = "Fingerprint";

    // FINGERPRINT_STATE (state) define si el usuario esta utilizando fingerprint o no para la autenticacion
    // FINGERPRINT_REGISTERED (boolean) define si el usuario necesita registrar su huella dactilar o no
    // FINGERPRINT_FIRST_TIME_LOGIN (boolean) define si se necesita realizar la pregunta inicial en el primer login para el fingerprint

    /**
     * Check if a fingerprint scanner is available and if at least one finger is enrolled in the phone.
     * @param context A context
     * @return True if authentication is available, False otherwise
     */
    public static boolean isAvailable(Context context) {
        FingerprintManager manager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
        return (manager != null && manager.isHardwareDetected() && manager.hasEnrolledFingerprints());
    }

    /**
     *
     * @param context A context
     * @return True if fingerprint authentication is enabled, False otherwise
     */
    public static boolean isEnabled(Context context) {
        // SharedPreferences
        // logic
        return true;
    }

    /**
     *
     * @param context A context
     * @param isEnabled
     */
    public static void isEnabled(Context context, boolean isEnabled) {
        // SharedPreferences
        // logic
    }

    /**
     *
     * @param context A context
     * @return
     */
    public static boolean hasRequested(Context context) {
        // SharedPreferences
        // logic
        return true;
    }

    /**
     *
     * @param context A context
     * @param hasRequested
     */
    public static void hasRequested(Context context, boolean hasRequested) {
        // SharedPreferences
        // logic
    }

    public static boolean isRegistered(Context context) {
        // SharedPreferences - check for token
        // logic
        return true;
    }

    /**
     *
     * @param context A context
     * @return
     */
    public static boolean isFirstLogin(Context context) {
        // SharedPreferences
        // logic
        return true;
    }

    /**
     *
     * @param context A context
     * @param isFirstLogin
     */
    public static void isFirstLogin(Context context, boolean isFirstLogin) {
        // SharedPreferences
        // logic
    }

    public static void register(Context context) {
        // set true to all fingerprint preferences
        // isEnabled(enabled)
        // hasRequested(true)
        // isFirstLogin(false)
    }

    public static void unregister(Context context) {
        // reset all fingerprint preferences
        // isEnabled(disabled)
        // hasRequested(false)
        // isFirstLogin(false)
    }

    public static class Keys {
        public final static String FINGERPRINT_KEY = "FINGERPRINT_KEY";
        public final static String FINGERPRINT_TOKEN = "FINGERPRINT_TOKEN";
        public final static String FINGERPRINT_STATE = "FINGERPRINT_STATE";
        public final static String FINGERPRINT_IV_PARAMETER = "FINGERPRINT_IV_PARAMETER";
    }

    public static class DefaultConfiguration {
        public final static int TRY_LIMIT = 5;
        public final static int DELAY_AFTER_ERROR = 1200;
    }

    public class State {
        public final static int ENABLED = 1;
        public final static int DISABLED = 2;
        public final static int REQUESTED = 3;
        public final static int FIRST_AUTH = 4;
        public final static int UNSUPPORTED = 5;
    }
}