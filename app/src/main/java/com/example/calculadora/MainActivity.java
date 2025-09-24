package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fathzer.soft.javaluator.DoubleEvaluator;



public class MainActivity extends AppCompatActivity {
    private TextView tvRes; // mostrar el resultat
    private StringBuilder expressio = new StringBuilder(); // ex: "33+5+15")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvRes = findViewById(R.id.tvRes);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);
        Button btn0 = findViewById(R.id.button0);
        Button btnPlus = findViewById(R.id.buttonPlus);
        Button btnEquals = findViewById(R.id.buttonEqual);
        Button btnRestar = findViewById(R.id.buttonResta);
        Button btnMultiplicacio = findViewById(R.id.buttonMult);
        Button btnDividir = findViewById(R.id.buttonDividir);
        Button btnClear = findViewById(R.id.buttonAC);
        Button btnDelete = findViewById(R.id.buttonBorraruno);
        Button btnParantAb = findViewById(R.id.buttonParantAb);
        Button btnParantCerr = findViewById(R.id.buttonParantCerr);

        // Listeners
        btn1.setOnClickListener(v -> afegirNum("1"));
        btn2.setOnClickListener(v -> afegirNum("2"));
        btn3.setOnClickListener(v -> afegirNum("3"));
        btn4.setOnClickListener(v -> afegirNum("4"));
        btn5.setOnClickListener(v -> afegirNum("5"));
        btn6.setOnClickListener(v -> afegirNum("6"));
        btn7.setOnClickListener(v -> afegirNum("7"));
        btn8.setOnClickListener(v -> afegirNum("8"));
        btn9.setOnClickListener(v -> afegirNum("9"));
        btn0.setOnClickListener(v -> afegirNum("0"));
        btnEquals.setOnClickListener(v -> evaluar());
        btnPlus.setOnClickListener(v -> afegirOReemplazarOperador("+"));
        btnRestar.setOnClickListener(v -> afegirOReemplazarOperador("-"));
        btnMultiplicacio.setOnClickListener(v -> afegirOReemplazarOperador("*"));
        btnDividir.setOnClickListener(v -> afegirOReemplazarOperador("/"));
        btnClear.setOnClickListener(v -> clear());
        btnDelete.setOnClickListener(v -> delete());
        btnParantAb.setOnClickListener(v -> afegirNum("("));
        btnParantCerr.setOnClickListener(v -> afegirNum(")"));

        actualitzar();
    }

    private void afegirNum(String c) {
        expressio.append(c);
        tvRes.setText(expressio.toString());

        actualitzar();
    }

    //Este metodo sirve para reemplazar los signos, basicamente crea una variable para guardar los signos y setea el campo donde da el resultado a 0
    //entonces cada vez que pones un signo suma 1, entonces cuando escribes otro, como esta en un if, resta ese 1, lo vuelve a 0 y luego le suma el nuevo.
    private void afegirOReemplazarOperador(String p) {
        if (expressio.length() == 0) {
            return;
        }

        String resultat = expressio.toString();
        char ultimChar = resultat.charAt(resultat.length() - 1);
        if (ultimChar == '+' || ultimChar == '-' || ultimChar == '*' || ultimChar == '/') {
            expressio.setCharAt(expressio.length() - 1, p.charAt(0));
        } else {
            expressio.append(p);
        }
        actualitzar();
    }
    private void delete() {
        if (expressio.length() > 0)
            expressio.deleteCharAt(expressio.length() -1);
        actualitzar();
    }

    private void clear(){
        expressio.setLength(0);
        actualitzar();
    }

    private void evaluar() {
        if (expressio.length() == 0) {
            return;
        }
        try {
            DoubleEvaluator evaluator = new DoubleEvaluator();
            Double result = evaluator.evaluate(expressio.toString());
            tvRes.setText(result.toString());
            expressio.setLength(0);
        } catch (Exception e) {
            tvRes.setText("Error: Expresión inválida");
        }
    }
    private void actualitzar() {
        tvRes.setText(expressio.toString());
    }
}