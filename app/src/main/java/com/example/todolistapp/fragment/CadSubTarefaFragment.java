package com.example.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistapp.R;
import com.example.todolistapp.databinding.FragmentCadSubTarefaBinding;
import com.example.todolistapp.databinding.FragmentCadTarefaBinding;
import com.example.todolistapp.databinding.FragmentDetalheTarefaBinding;

public class CadSubTarefaFragment extends Fragment {

    private FragmentCadSubTarefaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o binding
        binding = FragmentCadSubTarefaBinding.inflate(getLayoutInflater(),container, false);
        // retorna a view raiz do binding
        return binding.getRoot();
    }
}