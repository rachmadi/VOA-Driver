package id.ac.mdp.voadriver.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marhadi Wijaya on 4/15/2017.
 */

public class CustomRequest extends Request<JSONObject> {

    private Response.Listener<JSONObject> mListener;
    private HashMap<String,String> mParam;

    public CustomRequest(String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener listener) {
        super(url, listener);
        this.mListener=responseListener;
    }

    public CustomRequest(int method, String url, HashMap<String,String> param, Response.Listener<JSONObject> responseListener, Response.ErrorListener listener) {
        super(method, url, listener);
        this.mListener=responseListener;
        this.mParam=param;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString=new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParam;
    }
}
