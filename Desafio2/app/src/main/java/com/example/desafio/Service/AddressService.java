package com.example.desafio.Service;

import android.location.Address;
import android.os.AsyncTask;

import com.example.desafio.CadastroActivity;
import com.example.desafio.Model.Endereco;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;

public class AddressService extends AsyncTask<Void,Void,Void> {

    private WeakReference<CadastroActivity> cadastroActivityWeakReference;

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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if( cadastroActivityWeakReference.get() != null ){
            cadastroActivityWeakReference.get().lockFields( true );
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String jsonString = JsonRequest.request( cadastroActivityWeakReference.get().getUrl());

            Gson gson = new Gson();
            return gson.fromJson( jsonString, Address.class );
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public AddressService(CadastroActivity cadastroActivity){
        this.cadastroActivityWeakReference = new WeakReference<>(cadastroActivity);
    }

}
