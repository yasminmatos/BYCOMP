package com.example.projetofinal.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.projetofinal.R;
import com.example.projetofinal.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    View view;
    Button IdLista;
    Button Idpromocoes;
    FloatingActionButton btLerNota;
    TextView txtview;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        IdLista = view.findViewById(R.id.IdLista);
        Idpromocoes = view.findViewById(R.id.Idpromocoes);
        btLerNota = view.findViewById(R.id.btLerNota);
        txtview = view.findViewById(R.id.txtCronometro);


        IdLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.lista2);
            }
        });

        Idpromocoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.promocao);
            }
        });

        btLerNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //inicializa o timer
                long duracao = TimeUnit.SECONDS.toMillis(1);

                //inicializa o contador
                new CountDownTimer(duracao, 1000) {
                    @Override
                    public void onTick(long l) {
                        //quando comecar
                        String sDuracao = String.format(Locale.ENGLISH, "%02d : %02d",
                                TimeUnit.MILLISECONDS. toMinutes(1),
                                TimeUnit.MILLISECONDS.toSeconds(1),
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));

                        //muda a string na tela
                        txtview.setText(sDuracao);
                    }

                    @Override
                    public void onFinish() {
                        //quando acabar
                        //esconde txtview
                        txtview.setVisibility(View.GONE);

                        //display toast
                        Toast.makeText(view.getContext(), "AFDADAFAF", Toast.LENGTH_SHORT).show();
                    }
                }.start();

            }
        });



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}