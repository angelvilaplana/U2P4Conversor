package dam.android.angelvilaplana.u2p4conversor;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LogActivity extends AppCompatActivity {

    private final String ACTIVITY_NAME = this.getClass().getSimpleName();

    private final String DEBUG_TAG = "LOG-" + ACTIVITY_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(DEBUG_TAG, "onCreate");
    }

    /**
     * Aquesta devolució de llama es diu només quan hi ha una instància
     * guardada que es va guardar prèviament usan onSaveInstanceState().
     * Restaurem algun estat en onCreate(), mentre que podem restablir
     * opcionalment altre estat, posiblement usable després que onStart()
     * haja sigut completat.
     * El savedInstanceState Bundle es el mateix que la utilizada en
     * onCreate().
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(DEBUG_TAG, "onRestoreInstanceState");
    }


    /**
     * S'invoca quan l'activitat pot ser destruida temporalment,
     * guarda l'estat de la instància ací.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(DEBUG_TAG, "onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(DEBUG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(DEBUG_TAG, "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(DEBUG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(DEBUG_TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(DEBUG_TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(DEBUG_TAG, "onDestroy");
        Log.i(DEBUG_TAG, "isFinishing() => " + isFinishing());
    }

    // Imprimir el log de los elementos
    protected void printLog(String element, String action) {
        Log.i(DEBUG_TAG, element + " => " + action);
    }

}
