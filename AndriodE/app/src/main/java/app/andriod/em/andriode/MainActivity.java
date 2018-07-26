package app.andriod.em.andriode;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView texto;
    TextView texto_rest;
    Button boton;
    Button boton_rest_action;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (TextView) findViewById(R.id.texto);

        texto_rest = (TextView) findViewById(R.id.texto_rest);
        texto_rest.setMovementMethod(new ScrollingMovementMethod());

        boton = (Button) findViewById(R.id.boton);

        boton_rest_action = (Button) findViewById(R.id.boton_rest_acttion);

        progressBar = (ProgressBar)  findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyAsyncTask().execute(100);
            }
        });


        boton_rest_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*for(int i = 0; i <= 100; i++){
                    cargarDatos("numero : " + i);
                }*/

                MyTask myTask  = new MyTask();
                myTask.execute();
            }
        });
    }

    public void cargarDatos(String datos){
        texto_rest.append(datos + "\n" );
    }

    private class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cargarDatos("inicio de carga");
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            for(int i = 0; i <= 10; i++) {
                publishProgress("numero : " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Terminamos.";
        }

        @Override
        protected void onPostExecute(String dato) {
            super.onPostExecute(dato);
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            cargarDatos(values[0]);
        }
    }



    public class MyAsyncTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... params) {
            int max = params[0];

            for(int i=1; i < max; i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }

            return "End";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int contador = values[0];
            String textto = "contador " + contador;
            texto.setText(textto);
            texto.setTextSize(contador);

            progressBar.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            texto.append("\n" + s);

            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
