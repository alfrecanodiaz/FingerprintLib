package py.com.softcoding.fingerprintlib.fingerprint.facade;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;

/**
 * Created by Alfredo Cano on 04/06/18.
 */

public class Fingerprint {

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
     * @return True if fingerprint authentication is activated, False otherwise
     */
    public static boolean isActivated(Context context) {
        // SharedPreferences
        // logic
        return true;
    }

    /**
     *
     * @param context A context
     * @param isActivated
     * @return
     */
    public static void isActivated(Context context, boolean isActivated) {
        // SharedPreferences
        // logic
    }

    /**
     *
     * @param context A context
     * @return
     */
    public static boolean isRegistered(Context context) {
        // SharedPreferences
        // logic
        return true;
    }

    /**
     *
     * @param context A context
     * @param isRegistered
     */
    public static void isRegistered(Context context, boolean isRegistered) {
        // SharedPreferences
        // logic
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
        // isActivated(enabled)
        // isRegistered(true)
        // isFirstLogin(false)
    }

    public static void unregister(Context context) {
        // reset all fingerprint preferences
        // isActivated(disabled)
        // isRegistered(false)
        // isFirstLogin(false)
    }

    public static class Keys {
        public final static String FINGERPRINT_STATE = "FINGERPRINT_STATE";
        public final static String FINGERPRINT_REGISTERED = "FINGERPRINT_REGISTERED";
        public final static String FINGERPRINT_FIRST_TIME_LOGIN = "FINGERPRINT_FIRST_TIME_LOGIN";
    }

    public static class DefaultConfiguration {
        public final static int TRY_LIMIT = 5;
    }

    public class State {
        public final static int ENABLED = 1;
        public final static int DISABLED = 2;
        public final static int UNKNOWN = 3;
        public final static int UNSUPPORTED = 4;
    }
}