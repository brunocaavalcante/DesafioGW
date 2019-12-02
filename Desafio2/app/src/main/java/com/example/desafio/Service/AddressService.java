package com.example.desafio.Service;

import android.os.AsyncTask;

import com.example.desafio.CadastroActivity;
import com.example.desafio.Model.Endereco;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddressService extends AsyncTask<Void,Void,Endereco> {

    private WeakReference<CadastroActivity> cadastroActivityWeakReference;

    public AddressService(CadastroActivity cadastroActivity){
        this.cadastroActivityWeakReference = new WeakReference<>(cadastroActivity);
    }

    @Override
    protected Endereco doInBackground(Void... voids) {
        try {
            String jsonString = request( cadastroActivityWeakReference.get().getUrl());
            Gson gson = new Gson();
            return gson.fromJson( jsonString, Endereco.class );
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if( cadastroActivityWeakReference.get() != null ){
            cadastroActivityWeakReference.get().lockFields( true );
        }
    }

    @Override
    protected void onPostExecute(Endereco address) {
        super.onPostExecute(address);

        if( cadastroActivityWeakReference.get() != null ){
            cadastroActivityWeakReference.get().lockFields( false );

            if( address != null ){
                cadastroActivityWeakReference.get().setDataViews( address );
            }
        }
    }

    public  String request( String uri ) throws Exception {

        URL url = new URL( uri );
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader r = new BufferedReader(new InputStreamReader(in));

        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            jsonString.append(line);
        }

        urlConnection.disconnect();

        return jsonString.toString();
    }

}
