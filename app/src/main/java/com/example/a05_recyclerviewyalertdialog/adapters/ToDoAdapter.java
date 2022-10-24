package com.example.a05_recyclerviewyalertdialog.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a05_recyclerviewyalertdialog.MainActivity;
import com.example.a05_recyclerviewyalertdialog.R;
import com.example.a05_recyclerviewyalertdialog.modelos.ToDo;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoVh> {

    private List<ToDo> objects;
    private int resource;
    private Context context;

    public ToDoAdapter(List<ToDo> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public ToDoVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View toDoView = LayoutInflater.from(context).inflate(resource, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toDoView.setLayoutParams(lp);
        return new ToDoVh(toDoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoVh holder, int position) {
        ToDo toDo = objects.get(position);
        holder.lblTitulo.setText(toDo.getTitulo());
        holder.lblContenido.setText(toDo.getContenido());
        holder.lblFecha.setText(toDo.getFecha().toString());
        if (toDo.isCompletado())
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_off_background);
        else
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_off_background);
        holder.btnCompletado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaCambioEstado("Estas seguro de cambiar el estado?", toDo).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    private AlertDialog confirmaCambioEstado(String mensaje, ToDo toDo){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(mensaje);
        builder.setCancelable(false);

        builder.setNegativeButton("NO", null);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                toDo.setCompletado(!toDo.isCompletado());
                notifyDataSetChanged();
            }
        });

        return builder.create();
    }

    public class ToDoVh extends RecyclerView.ViewHolder {

        TextView lblTitulo, lblContenido, lblFecha;
        ImageButton btnCompletado;

        public ToDoVh(@NonNull View itemView) {
            super(itemView);
            lblTitulo = itemView.findViewById(R.id.lblTituloToDoModelView);
            lblContenido = itemView.findViewById(R.id.lblContenidoToDoModelView);
            lblFecha = itemView.findViewById(R.id.lblFechaToDoModelView);
            btnCompletado = itemView.findViewById(R.id.btnCompletadoToDoModelView);
        }
    }
}
