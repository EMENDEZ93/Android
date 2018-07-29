package app.andriod.em.andriode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.andriod.em.andriode.adapters.MyAdapter;
import app.andriod.em.andriode.adapters.UsuariosAdapter;
import app.andriod.em.andriode.parsers.UsuarioJSONParser;
import app.andriod.em.andriode.parsers.UsuarioXMLParser;
import app.andriod.em.andriode.pojo.Usuario;

public class MainActivity extends AppCompatActivity {

    TextView texto;
    TextView texto_rest;
    Button boton;
    Button boton_rest_action;
    ProgressBar progressBar;
    List<MyTask> taskList;
    List<Usuario> usuarioList;

    //ListView listView;
    //MyAdapter adapter;

    RecyclerView recyclerView;
    UsuariosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // texto = (TextView) findViewById(R.id.texto);

       // texto_rest = (TextView) findViewById(R.id.texto_rest);
        // texto_rest.setMovementMethod(new ScrollingMovementMethod());


        //  boton = (Button) findViewById(R.id.boton);

        // boton_rest_action = (Button) findViewById(R.id.boton_rest_acttion);

        // progressBar = (ProgressBar)  findViewById(R.id.progressBar);
        //  progressBar.setVisibility(View.INVISIBLE);

        taskList = new ArrayList<>();

        //boton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        new MyAsyncTask().execute(100);
        //    }
        //});




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        if(isOnline() == true){
            //pedirDatos("http://maloschistes.com/maloschistes.com/jose/usuarios.xml");
            //pedirDatos("http://maloschistes.com/maloschistes.com/jose/webservice.php");
            pedirDatos("http://maloschistes.com/maloschistes.com/jose/webserviceI.php");
            //url con seguridad
            //pedirDatos("http://maloschistes.com/maloschistes.com/jose/s/webservice.php");
        }else {
            Toast.makeText(getApplicationContext(),"Sin conexion", Toast.LENGTH_SHORT).show();
        }


        //boton_rest_action.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        /*for(int i = 0; i <= 100; i++){
        //            cargarDatos("numero : " + i);
        //        }*/
        //
        //        if(isOnline() == true){
        //            //pedirDatos("http://maloschistes.com/maloschistes.com/jose/usuarios.xml");
        //            //pedirDatos("http://maloschistes.com/maloschistes.com/jose/webservice.php");
        //            pedirDatos("http://maloschistes.com/maloschistes.com/jose/webserviceI.php");
        //
        //            //url con seguridad
        //            //pedirDatos("http://maloschistes.com/maloschistes.com/jose/s/webservice.php");
        //        }else {
        //            Toast.makeText(getApplicationContext(),"Sin conexion", Toast.LENGTH_SHORT).show();
        //        }
        //    }
        //});
    }

    public void cargarDatos(){
        //texto_rest.append(datos + "\n" );
        //if(usuarioList != null){
        //    for (Usuario usuario : usuarioList) {
        //        texto_rest.append(usuario.getUsariosId()+ " - " +
        //                          usuario.getNombre() + " - " +
        //                          usuario.getTwitter() + "\n");
        //    }
        //}

        adapter = new UsuariosAdapter(getApplicationContext(), usuarioList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    public void pedirDatos(String uri){

        MyTask myTask  = new MyTask();
        //myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        myTask.execute(uri);
    }

    public boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else {
            return false;
        }
    }


    private class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //cargarDatos("inicio de carga");

            //if(taskList.size() == 0){
            //    progressBar.setVisibility(View.VISIBLE);
            //}
            taskList.add(this);
        }

        /*@Override
        //protected String doInBackground(String... strings) {
        //    /*for(int i = 0; i <= 10; i++) {
        //        publishProgress("numero : " + i);
        //        try {
        //            Thread.sleep(1000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //    }*/
        //    String content = HttpManager.getData(strings[0]);
        //    return content;
        //}


        @Override
        protected String doInBackground(String... strings) {
            //String content = HttpManager.getData(strings[0], "pepito", "pepito");
            String content = HttpManager.getData(strings[0]);
            publishProgress(content);
            //texto_rest.append(content);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result == null){
                Toast.makeText(MainActivity.this, "no se pudo conectar", Toast.LENGTH_SHORT).show();
                //progressBar.setVisibility(View.INVISIBLE);
                return;
            }

            usuarioList = UsuarioJSONParser.parser(result);
            cargarDatos();
            //progressBar.setVisibility(View.INVISIBLE);

            /*taskList.remove(this);
            if(taskList.size() == 0){
                progressBar.setVisibility(View.INVISIBLE);
            }*/
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //cargarDatos(values[0]);
        }
    }



    public class MyAsyncTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressBar.setVisibility(View.VISIBLE);
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

            //progressBar.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            texto.append("\n" + s);

            //progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
