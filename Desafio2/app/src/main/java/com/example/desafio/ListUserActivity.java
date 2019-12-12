package com.example.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.desafio.Controller.EnderecoController;
import com.example.desafio.Controller.PessoaController;
import com.example.desafio.Model.EnderecoModel;
import com.example.desafio.Model.PessoaFisicaModel;
import com.example.desafio.Model.PessoaJuridicaModel;
import com.example.desafio.dbHelper.ConexaoSQLite;

import java.util.List;

public class ListUserActivity extends AppCompatActivity {

    private SwipeMenuListView listView;
    private PessoaController controller;
    private List<PessoaFisicaModel> pessoaFisicaModelList;
    private List<PessoaJuridicaModel>pessoaJuridicaModelList;
    private EnderecoController enderecoController;
    private List<EnderecoModel>enderecoList;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        ConexaoSQLite con = ConexaoSQLite.getInstancia(this);
        controller = new PessoaController(con);
        enderecoController = new EnderecoController(con);

        listView = (SwipeMenuListView) findViewById(R.id.lvListaUsuarios);
        pessoaFisicaModelList = controller.pessoaFisicaModelList();

        setEndereco();

        ArrayAdapter<PessoaFisicaModel> adapterPessoaFisica = new ArrayAdapter<PessoaFisicaModel>(this,android.R.layout.simple_list_item_1,pessoaFisicaModelList);

        listView.setAdapter(adapterPessoaFisica);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                openItem.setWidth(170);

                openItem.setIcon(R.drawable.ic_edit_foreground);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_foreground);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Log.d("Teste", "onMenuItemClick: clicked item " + index);
                        break;
                    case 1:
                        Log.d("Teste", "onMenuItemClick: clicked item " + index);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }

    private void setEndereco(){
        for (PessoaFisicaModel pessoaFisicaModel: pessoaFisicaModelList){
            pessoaFisicaModel.setEndereco(enderecoController.getEnderecoById(pessoaFisicaModel.getId()));
        }
    }
}
