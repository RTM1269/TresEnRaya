package com.rtm.tresenraya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button btn;
private String botonAbuscar;
private ArrayList<Button> listaBotones;
private jugador jug1,jug2;
private int turno=1;
private String tablero [][];
private char simbolo1='X',simbolo2='O';


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearListaBotones();
        crearJugadores();
        crearTablero();
        imprimirTablero();

    }
    private void crearTablero(){
        tablero = new String[3][3];
        for (int f=0; f<3; f++){
            for (int c=0; c<3; c++){
                tablero[f][c]=String.valueOf(f)+String.valueOf(c);
            }
        }
    }
    private void imprimirTablero(){
        for (int f=0; f<3; f++){
            for (int c=0; c<3; c++){
                Log.d("Tab: ",tablero[f][c]);
            }
        }
    }
    private void crearJugadores() {
        jug1 = new jugador("raúl",'X');
        jug2 = new jugador("mario",'O');
    }

    private void crearListaBotones() {
        listaBotones = new ArrayList<Button>();
        for (int i=0; i<9;i++){
            botonAbuscar = "button"+String.valueOf(i);
            int etID = getResources().getIdentifier(botonAbuscar,"id",getPackageName());
            btn = findViewById(etID);
            Log.d("Boton: ",btn.getText().toString());
            listaBotones.add(btn);
        }
    }
    private boolean comprobar_paColocar(Button btn) {
        boolean vacio=false;
        if(btn.getText().toString().equals("")) {
            vacio=true;
        }
        return vacio;
    }
    private boolean comprobar_ganar(String simbolos) {
        int cont=0;
        boolean ganar=false;
        //FILAS
        for (int i = 0; i < tablero.length ; i++) {
            for (int j = 0; j < tablero.length-1; j++) {//PORQUE SON 2 COMPROBACIONES
                if (tablero[i][j]==simbolos) {
                    if (tablero[i][j]==tablero[i][j+1] ){

                        cont ++;

                    }
                }
            }

        }
        if(cont==2) {
            ganar = true;
        }

        //COLUMNAS
        cont = 0;
        for (int i = 0; i < tablero.length-1; i++) {
            for (int j = 0; j < tablero.length-1; j++) { //PORQUE SON 2 COMPROBACIONES
                if (tablero[i][j]==simbolos) {
                    if (tablero[i][j]==tablero[i+1][j]){
                        cont++;


                    }
                }

            }

        }
        if(cont==2) {
            ganar = true;
        }
        //DIAGONAL DESCENDENTE
        cont = 0;
        for (int i = 0; i < tablero.length-1 ; i++) {
            for (int j = 0; j < tablero.length-1; j++) {//PORQUE SON 2 COMPROBACIONES
                if (tablero[i][j]==simbolos) {
                    if (tablero[i][j]==tablero[i+1][j+1]) {
                        cont++;


                    }
                }

            }
        }
        if(cont==2) {
            ganar = true;
        }
        //DIAGONAL ASCENDENTE
        cont = 0;
        for (int i = 0; i < tablero.length-1 ; i++) {
            for (int j = 2; j > 0; j--) {//PORQUE SON 2 COMPROBACIONES
                if (tablero[i][j]==tablero[i+1][j-1]) {
                    if (tablero[i][j]==simbolos) {
                        cont++;


                    }
                }

            }

        }
        if(cont==2) {
            ganar = true;
        }
        return ganar;

    }
    private void actualizarTabla(){
        int b=0;
        for (int f=0;f<3;f++){
            for (int c=0;c<3;c++){
                tablero[f][c]=listaBotones.get(b).getText().toString();
                b++;
            }
        }
    }
    /*private boolean comprobar_ganar(String ficha) {
        int cont=0;
        boolean ganar=false;
        //FILAS
        for (int i = 0; i < tablero.length ; i++) {
            for (int j = 0; j < tablero.length-1; j++) {//PORQUE SON 2 COMPROBACIONES
                if (tablero[i][j].equals(ficha)) {
                    if (tablero[i][j].equals(tablero[i][j+1])){
                        cont ++;
                    }
                }
            }

        }
        if(cont==2) {
            ganar = true;
        }

        //COLUMNAS
        cont = 0;
        for (int i = 0; i < tablero.length-1; i++) {
            for (int j = 0; j < tablero.length-1; j++) { //PORQUE SON 2 COMPROBACIONES
                if (tablero[i][j]==simbolos) {
                    if (tablero[i][j]==tablero[i+1][j]){
                        cont++;


                    }
                }

            }

        }
        if(cont==2) {
            ganar = true;
        }
        //DIAGONAL DESCENDENTE
        cont = 0;
        for (int i = 0; i < tablero.length-1 ; i++) {
            for (int j = 0; j < tablero.length-1; j++) {//PORQUE SON 2 COMPROBACIONES
                if (tablero[i][j]==simbolos) {
                    if (tablero[i][j]==tablero[i+1][j+1]) {
                        cont++;


                    }
                }

            }
        }
        if(cont==2) {
            ganar = true;
        }
        //DIAGONAL ASCENDENTE
        cont = 0;
        for (int i = 0; i < tablero.length-1 ; i++) {
            for (int j = 2; j > 0; j--) {//PORQUE SON 2 COMPROBACIONES
                if (tablero[i][j]==tablero[i+1][j-1]) {
                    if (tablero[i][j]==simbolos) {
                        cont++;


                    }
                }

            }

        }
        if(cont==2) {
            ganar = true;
        }
        return ganar;

    }*/

    @Override
    public void onClick(View view) {
        Button btn_click = findViewById(view.getId());
//        Log.d("btn_clickado",String.valueOf(btn_click.getr));
        if (turno == 1){
            if (comprobar_paColocar(btn_click)==true){
                btn_click.setText(String.valueOf(jug1.getFicha()));
                actualizarTabla();
//               Log.d("Comprobar: ", String.valueOf(comprobar_ganar(String.valueOf(jug1.getFicha()))));
                if (comprobar_ganar(String.valueOf(jug1.getFicha()))==true){
                    Log.d("HA GANADO:",jug1.getNombre());
                }
                imprimirTablero();
                turno = 2;
            }

        }else{
            if (comprobar_paColocar(btn_click)==true){
                btn_click.setText(String.valueOf(jug2.getFicha()));
                actualizarTabla();
                if (comprobar_ganar(String.valueOf(jug2.getFicha()))==true){
                    Log.d("HA GANADO:",jug2.getNombre());
                }
                imprimirTablero();
                turno = 1;
            }
        }
        /*switch (view.getId()){
            case R.id.button0:
                Log.d("debug"," Estoy en el case");
                if (turno == 1){
                    listaBotones.get(0).setText(String.valueOf(jug1.getFicha()));
                    turno=2;
                }else{
                    listaBotones.get(0).setText(String.valueOf(jug2.getFicha()));
                    turno=1;
                }
                break;
            case R.id.button1:
                if (turno == 1){
                    listaBotones.get(1).setText(String.valueOf(jug1.getFicha()));
                    turno=2;
                }else{
                    listaBotones.get(1).setText(String.valueOf(jug2.getFicha()));
                    turno=1;
                }
                break;
            case R.id.button2:
                if (turno == 1){
                    listaBotones.get(2).setText(String.valueOf(jug1.getFicha()));
                    turno=2;
                }else{
                    listaBotones.get(2).setText(String.valueOf(jug2.getFicha()));
                    turno=1;
                }
                break;
            case R.id.button3:
                if (turno == 1){
                    listaBotones.get(3).setText(String.valueOf(jug1.getFicha()));
                    turno=2;
                }else{
                    listaBotones.get(3).setText(String.valueOf(jug2.getFicha()));
                    turno=1;
                }
                break;
            case R.id.button4:
                if (turno == 1){
                    listaBotones.get(4).setText(String.valueOf(simbo));
                    turno=2;
                }else{
                    listaBotones.get(4).setText(String.valueOf(simbolo2));
                    turno=1;
                }
                break;
            case R.id.button5:
                if (turno == 1){
                    listaBotones.get(5).setText(String.valueOf(simbolo1));
                    turno=2;
                }else{
                    listaBotones.get(5).setText(String.valueOf(simbolo2));
                    turno=1;
                }
                break;
            case R.id.button6:
                if (turno == 1){
                    listaBotones.get(6).setText(String.valueOf(simbolo1));
                    turno=2;
                }else{
                    listaBotones.get(6).setText(String.valueOf(simbolo2));
                    turno=1;
                }
                break;
            case R.id.button7:
                if (turno == 1){
                    listaBotones.get(7).setText(String.valueOf(simbolo1));
                    turno=2;
                }else{
                    listaBotones.get(7).setText(String.valueOf(simbolo2));
                    turno=1;
                }
                break;
            case R.id.button8:
                if (turno == 1){
                    listaBotones.get(8).setText(String.valueOf(simbolo1));
                    turno=2;
                }else{
                    listaBotones.get(8).setText(String.valueOf(simbolo2));
                    turno=1;
                }
                break;
        }*/

    }
}