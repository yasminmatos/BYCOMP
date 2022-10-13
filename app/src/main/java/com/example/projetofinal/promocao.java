package com.example.projetofinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetofinal.recyclerview.ItemAdapter;
import com.example.projetofinal.recyclerview.ItemPesq;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link promocao#newInstance} factory method to
 * create an instance of this fragment.
 */
public class promocao extends Fragment {

    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public promocao() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment promocao.
     */
    // TODO: Rename and change types and number of parameters
    public static promocao newInstance(String param1, String param2) {
        promocao fragment = new promocao();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_promocao, container, false);

        //simulando itens a serem integrados na tela
        //esses dois representam um item do recycler view
        ItemPesq itp1 = new ItemPesq("0","Calegaris","Aven Amizade",2,"Tomate Cereja",(float)6.00);
        ItemPesq itp2 = new ItemPesq("0","Carrefour","Aven Amizade",3,"Tomate Cereja",(float)6.00);
        ItemPesq itp3 = new ItemPesq("0","Pague-Menos","Aven Amizade",5,"Tomate Cereja",(float)6.00);

        ArrayList<ItemPesq> itemp = new ArrayList<>();
        itemp.add(itp1);
        itemp.add(itp2);
        itemp.add(itp3);
        //passando a lista par o adapter personalizad

        RecyclerView recyclerTela = view.findViewById(R.id.ListaTelaPesq);

        recyclerTela.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerTela.setAdapter(new ItemAdapter(itemp));


        return view;
    }
}