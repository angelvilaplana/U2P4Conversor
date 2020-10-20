package dam.android.angelvilaplana.u2p4conversor;

import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

public class U2P4ConversorActivity extends LogActivity {

    // Componentes del Layout

    private TextView textViewInfo;

    private EditText etPrimerCampo;

    private EditText etSegundoCampo;

    private TextView textViewError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u2_p4_conversor);

        // Acceso a los componentes del layout
        setUI();
    }

    private void setUI() {
        // Poniendo el fondo de pantalla
        setBackground();

        // Inicializando los componentes
        textViewInfo = findViewById(R.id.textViewInfo);
        etPrimerCampo = findViewById(R.id.et_PrimerCampo);
        etSegundoCampo = findViewById(R.id.et_SegundoCampo);
        textViewError = findViewById(R.id.textViewError);
        Button btnCambiar = findViewById(R.id.btn_Cambiar);

        // Listener del primer campo para introducir la medida
        etPrimerCampo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                printLog("etPrimerCampo", "onTextChanged()");

                // Convertir medidas dependiento del textViewInfo
                try {
                    textViewError.setText(null);

                    if (s.length() > 0) {
                        if (textViewInfo.getText() == getString(R.string.textPulgadasACentimetros)) {
                            etSegundoCampo.setText(convertirPulgadasACm(s.toString()));
                        } else {
                            etSegundoCampo.setText(convertirCmAPulgadas(s.toString()));
                        }
                    } else {
                        etSegundoCampo.setText("");
                    }
                } catch (Exception e) {
                    // Mostrar las excepciones en el textViewError y en el Log
                    textViewError.setText(e.getMessage());
                    Log.e("LogsConversor", e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Botón para cambiar la conversión
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printLog("btnCambiar", "onClick()");

                // Dependiendo del texto del TextViewInfo cambiamos la conversión.
                // Modificando el TextViewInfo para informar al usuario y el EditText del primer
                // campo con el nombre de la medida a introducir
                if (textViewInfo.getText() == getString(R.string.textPulgadasACentimetros)) {
                    textViewInfo.setText(getString(R.string.textCentimetrosAPulgadas));
                    etPrimerCampo.setHint(R.string.editCentimetros);
                } else {
                    textViewInfo.setText(getString(R.string.textPulgadasACentimetros));
                    etPrimerCampo.setHint(R.string.editPulgadas);
                }

                etPrimerCampo.setText(etSegundoCampo.getText());
            }
        });
    }

    // Fondo de pantalla solo en el modo vertical para
    // poder visualizar los componentes correctamente
    private void setBackground() {
        ConstraintLayout rootLayout = findViewById(R.id.rootLayout);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ) {
            rootLayout.setBackgroundResource(R.drawable.meter_backgound);
        }
    }

    // Método para convertir de pulgadas a cm
    private String convertirPulgadasACm(String pulgadaText) throws Exception {
        double pulgadas = Double.parseDouble(pulgadaText);
        if (pulgadas < 1) {
            throw new Exception("Sólo números >= 1");
        }

        return String.format("%.2f", pulgadas * 2.54);
    }

    // Método para convertir de cm a pulgadas
    private String convertirCmAPulgadas(String cmText) throws Exception {
        double cm = Double.parseDouble(cmText);
        if (cm < 1) {
            throw new Exception("Sólo números >= 1");
        }

        return String.format("%.2f", cm / 2.54);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        // Cargar la información anterior cuando carga la pantalla
        super.onRestoreInstanceState(savedInstanceState);
        textViewInfo.setText(savedInstanceState.getString("textViewInfo"));
        printLog("onRestoreInstanceState", "textViewInfo => " + textViewInfo.getText().toString());
        etPrimerCampo.setText(savedInstanceState.getString("etPrimerCampo"));
        printLog("onRestoreInstanceState", "etPrimerCampo => " + etPrimerCampo.getText().toString());
        etSegundoCampo.setText(savedInstanceState.getString("etSegundoCampo"));
        printLog("onRestoreInstanceState", "etSegundoCampo => " + etSegundoCampo.getText().toString());
        textViewError.setText(savedInstanceState.getString("textViewError"));
        printLog("onRestoreInstanceState", "textViewError => " + textViewError.getText().toString());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // Guardar la información a la hora de volver a inicializar la pantalla
        outState.putString("textViewInfo", textViewInfo.getText().toString());
        printLog("onSaveInstanceState", "textViewInfo => " + textViewInfo.getText().toString());
        outState.putString("etPrimerCampo", etPrimerCampo.getText().toString());
        printLog("onSaveInstanceState", "etPrimerCampo => " + etPrimerCampo.getText().toString());
        outState.putString("etSegundoCampo", etSegundoCampo.getText().toString());
        printLog("onSaveInstanceState", "etSegundoCampo => " + etSegundoCampo.getText().toString());
        outState.putString("textViewError", textViewError.getText().toString());
        printLog("onSaveInstanceState", "textViewError => " + textViewError.getText().toString());
        super.onSaveInstanceState(outState);
    }

}