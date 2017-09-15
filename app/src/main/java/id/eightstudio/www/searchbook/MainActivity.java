package id.eightstudio.www.searchbook;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button buttonSearchData;
    TextView textViewInstruksi;
    Random random = new Random();
    int randNumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();

        buttonSearchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textViewInstruksi.setText(" Process . . . ");

                new ProsesDataDownload().execute();

            }
        });

    }

    private void bindView() {

        buttonSearchData  = (Button) findViewById(R.id.searchButton);
        textViewInstruksi = (TextView) findViewById(R.id.instructions);

    }

    public class ProsesDataDownload extends AsyncTask<String, Void, String> {

        public ProsesDataDownload() {
        }

        @Override
        protected String doInBackground(String... strings) {

            return NetworkUtils.getDataInfo();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



        }
    }

}
