package br.com.caicosoft.seminariofirebaserealtime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.caicosoft.seminariofirebaserealtime.adapter.ProdutoAdapter;
import br.com.caicosoft.seminariofirebaserealtime.model.Produto;

public class ListarActivity extends AppCompatActivity {

    ListView lvProdutos;
    ImageView ivExcluir, ivEditar;
    EditText etDescricao, etPreco;
    Button btnCadastrar;
    ArrayList<Produto> produtos = new ArrayList<>(); // sera preechido para ser jogado no adapter
    ProdutoAdapter adapter;
    Produto produtoSelecionado; // quando ele for selecionado para editar ou excluir

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        etDescricao = findViewById(R.id.etDescricao);
        etPreco = findViewById(R.id.etPreco);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camposPreenchidos())
                    cadastrarProduto();
            }
        });

        lvProdutos = findViewById(R.id.lvProdutos);
        adapter = new ProdutoAdapter(this, produtos);

        lvProdutos.setAdapter(adapter);
        leProdutos();
        monitoraClique();
    }

    public void cadastrarProduto(){
        String descricao = etDescricao.getText().toString();
        Double preco = Double.parseDouble(etPreco.getText().toString());

        // ...

        // questao de view
        Toast.makeText(getApplicationContext(), "Produto Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged(); // atualiza lista de produtos
        etDescricao.setText(""); // limpa campos
        etPreco.setText(""); // limpa campos
    }

    public void leProdutos(){
        // ....
    }


    public void editarProduto(Produto produto){
        String descricao = etDescricao.getText().toString();
        String preco = etPreco.getText().toString();

        if(camposPreenchidos()) { // verifica se os campos estao preenchidos
            // ....
        }

        // questao de view
        posEdicao();
    }


    public void excluirProduto(Produto produto){
        // ...

        // questao de view
        adapter.notifyDataSetChanged(); // atualiza lista de produtos
    }

    public void preEdicao(final Produto produto){
        etDescricao.setText(produto.getDescricao());
        etPreco.setText(produto.getPreco().toString());
        btnCadastrar.setText("Atualizar");

        if(btnCadastrar.getText().equals("Atualizar")){
            btnCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editarProduto(produto);
                }
            });
        }
    }

    public void posEdicao(){
        Toast.makeText(getApplicationContext(), "Atualizado com sucesso!", Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged(); // atualiza lista de produtos
        btnCadastrar.setText("Cadastrar");
        etPreco.setText("");
        etDescricao.setText("");
    }

    public Boolean camposPreenchidos(){
        if(etPreco.getText().toString().isEmpty() || etDescricao.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void monitoraClique(){
        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                produtoSelecionado = (Produto) adapterView.getItemAtPosition(i);

                ivExcluir = view.findViewById(R.id.ivExcluir);
                ivEditar = view.findViewById(R.id.ivEditar);

                ivExcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        excluirProduto(produtoSelecionado);
                    }
                });

                ivEditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // chama acao de editar
                        preEdicao(produtoSelecionado);
                    }
                });
            }
        });
    }
}
