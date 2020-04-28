package com.example.flypath.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.flypath.Login;
import com.example.flypath.R;
import com.example.flypath.Registrar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button btnCrear;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btnCrear = (Button) root.findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//funciona
                try{
                    Intent intent = new Intent(getActivity(), MapaRutes.class);
                    startActivity(intent);
                }catch (Exception ex){
                    Toast.makeText(getActivity(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}