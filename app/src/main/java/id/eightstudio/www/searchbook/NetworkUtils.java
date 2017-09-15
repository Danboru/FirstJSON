package id.eightstudio.www.searchbook;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by danbo on 9/15/17.
 */

public class NetworkUtils  {

    private static final String TAG = "NetworkUtils";
    private static final String BASE_URL = "https://759b74ce43947f5f4c91aeddc3e5bad3d.codepolitan.com/api/v2/posts/category/news";
//    private static final String QUERY_PARAM = "q";

//    static String geetDataInfo(String stringQuery){ //Ketika meminta query
    static String getDataInfo(){

        HttpsURLConnection urlConeection = null;
        BufferedReader bufferedReader = null;
        String bookJSONString = null;

        try {

            Uri buildUri = Uri.parse(BASE_URL).buildUpon()
//                    .appendQueryParameter(QUERY_PARAM, stringQuery)
                    .build();

            URL requestURL = new URL(buildUri.toString());
            urlConeection = (HttpsURLConnection) requestURL.openConnection();
            urlConeection.setRequestMethod("GET");

            InputStream inputStream = urlConeection.getInputStream();
            StringBuilder builderString = new StringBuilder();

            if (inputStream == null) {
                return null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null){

                builderString.append(line + "\n");
            }

            if (builderString.length() == 0) {

                return null;
            }

            bookJSONString = builderString.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {


            if (urlConeection != null) {
                urlConeection.disconnect();
            }

            if (bufferedReader != null) {

                try {

                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, bookJSONString);
                return bookJSONString;

            }

        }

        return bookJSONString;
    }

}
