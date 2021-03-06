package com.example.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistapp.R;
import com.example.todolistapp.databinding.FragmentDetalheTarefaBinding;
import com.example.todolistapp.databinding.FragmentPrincipalBinding;


public class DetalheTarefaFragment extends Fragment {
    private FragmentDetalheTarefaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o binding
        binding = FragmentDetalheTarefaBinding.inflate(getLayoutInflater(),container, false);
        // retorna a view raiz do binding
        return binding.getRoot();
    }
}