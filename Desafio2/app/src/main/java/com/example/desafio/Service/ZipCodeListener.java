package com.example.desafio.Service;

import android.text.Editable;
import android.text.TextWatcher;

public class ZipCodeListener implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String zipCode = s.toString();
        if(s.length() == 8){
           AddressService addressService = new AddressService();
           addressService.doInBackground(zipCode);
        }
    }
}
