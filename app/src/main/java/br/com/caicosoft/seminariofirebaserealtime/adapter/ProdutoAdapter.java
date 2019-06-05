package br.com.caicosoft.seminariofirebaserealtime.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.caicosoft.seminariofirebaserealtime.R;
import br.com.caicosoft.seminariofirebaserealtime.model.Produto;

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    private final Context context;
    private final ArrayList<Produto> listaProdutos;

    public ProdutoAdapter(Context context, ArrayList<Produto> listaProdutos) {
        super(context, R.layout.list_view, listaProdutos);
        this.context = context;
        this.listaProdutos = listaProdutos;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha = inflater.inflate(R.layout.list_view, parent, false);

        TextView descricao = (TextView) linha.findViewById(R.id.tvDescricao);
        TextView preco = (TextView) linha.findViewById(R.id.tvPreco);

        descricao.setText(listaProdutos.get(position).getDescricao());
        preco.setText("R$ "+listaProdutos.get(position).getPreco().toString());

        return linha;
    }
}
