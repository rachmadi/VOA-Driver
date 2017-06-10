package id.ac.mdp.voadriver.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import id.ac.mdp.voadriver.models.User;

/**
 * Created by Marhadi Wijaya on 4/9/2017.
 */

public class Utilities {
    private static Toast mToast;

    public static void showAsToast(Context context, String text, int duration) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(context, text, duration);
        mToast.show();
    }

    public static void hideKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static void vibrateFor(Context context, int ms){
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(ms);
    }

    public static String getCurrency(String value){
        DecimalFormat currency = (DecimalFormat) NumberFormat.getInstance();
        Locale currentLocale=new Locale("in","ID");
        String symbol = Currency.getInstance(currentLocale).getSymbol(currentLocale);
        currency.setGroupingUsed(true);
        currency.setPositivePrefix(symbol + " ");
        currency.setNegativePrefix("-" + symbol + " ");
        currency.setMinimumFractionDigits(2);
        currency.setMaximumFractionDigits(2);
        return currency.format(Double.parseDouble(value));

        //Locale currentLocale=new Locale("in","ID");
        //NumberFormat currency=NumberFormat.getCurrencyInstance(currentLocale);
        //return currency.format(Double.parseDouble(value));
    }

    public String getConnectionUrl(String namaPHP){
        //return "http://192.168.0.123/dbvoa/index.php/ControlAndroid/"+namaPHP;
        return "http://siddiqachmad.com/testvoa/index.php/ControlAndroid/"+namaPHP;
    }

    public static String getDateTime(String timestamp, String dateFormatStr){
        Date date = new Date(Integer.parseInt(timestamp) * 1000L);
        DateFormat format = new SimpleDateFormat(dateFormatStr, Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        return format.format(date);
    }

    public void setUser(Context context, String userId, String userDomain, String name, String position, String photo, String phoneNumber, String pin, String currentQuota, String maximunQuota){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit=sp.edit();
        edit.putString("xUserId",userId);
        edit.putString("xUserDomain",userDomain);
        edit.putString("xUserName",name);
        edit.putString("xUserPosition",position);
        edit.putString("xUserPhoto",photo);
        edit.putString("xUserPhoneNumber",phoneNumber);
        edit.putString("xUserPin",pin);
        edit.putString("xUserCurrentQuota",currentQuota);
        edit.putString("xUserMaximumQuota",maximunQuota);
        edit.apply();
    }

    public User getUser(Context context){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(context);
        String id=sp.getString("xUserId",null);
        String domain=sp.getString("xUserDomain",null);
        String name=sp.getString("xUserName",null);
        String position=sp.getString("xUserPosition",null);
        String photo=sp.getString("xUserPhoto",null);
        String phoneNumber=sp.getString("xUserPhoneNumber",null);
        String pin=sp.getString("xUserPin",null);
        Integer currentQuota= Integer.valueOf(sp.getString("xUserCurrentQuota",null));
        Integer maximumQuota= Integer.valueOf(sp.getString("xUserMaximumQuota",null));

        return new User(id,domain,name,position,photo,phoneNumber,pin,currentQuota,maximumQuota);
    }

    public void clearUser(Context context){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit=sp.edit();
        edit.putString("xUserId",null);
        edit.putString("xUserDomain",null);
        edit.putString("xUserName",null);
        edit.putString("xUserPosition",null);
        edit.putString("xUserPhoto",null);
        edit.putString("xUserPhoneNumber",null);
        edit.putString("xUserPin",null);
        edit.putString("xUserCurrentQuota",null);
        edit.putString("xUserMaximumQuota",null);
        edit.apply();
    }

    public Boolean isLogin(Context context){
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context);
        String user=sp.getString("xUserName",null);
        return user != null;
    }
}
