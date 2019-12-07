package com.example.desafio.Service;

import android.os.AsyncTask;

import com.example.desafio.Model.EnderecoModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class AddressService extends  AsyncTask<Void,Void, EnderecoModel>{

    private final String url;

       public AddressService(String url){
           this.url = url;
       }

    @Override
    protected EnderecoModel doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
           try {
               String url1 = "http://viacep.com.br/ws/"+this.url+"/json/";
            URL url = new URL(url1); //Criando a URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");//configura o metodo da requisição
            connection.setRequestProperty("Accept","aplication/json");//Configura o tipo de retorno

            connection.connect();

           Scanner sc =  new Scanner(url.openStream());//O metodo url.openStream() retorna a resposta da requisica numa variavel stream estamos lendo com scanner
                while (sc.hasNext()){//Lendo resposta
                    resposta.append(sc.next());
                }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
           return new Gson().fromJson(resposta.toString(), EnderecoModel.class);//Converte a resposta em um objeto EnderecoModel
    }
}
