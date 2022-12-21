package com.example.x;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ViewBarChart extends View {
    private Paint paint;
    private int anchoCanvas;
    private int altoCanvas;
    private int anchoBarra;
    private int valorMaximo = 100;
    private int paddingLeft = 80;
    private int paddingButton = 100;
    private int tamañoLetra = 35;
    private int tamañoIntervalos = 10;
    private List<String> datosHorizontales = new ArrayList<String>();
    private List<Integer> datosVerticales = new ArrayList<Integer>();

    public ViewBarChart(Context context) {
        super(context);
        configurarPaint();
    }

    public ViewBarChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        configurarPaint();
    }

    public ViewBarChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        configurarPaint();
    }

    public void setDatos(List<String> datosHorizontales, List<Integer> datosVerticales) {
        if (datosHorizontales.size() == datosVerticales.size()) {
            this.datosHorizontales = datosHorizontales;
            this.datosVerticales = datosVerticales;
        } else {
            //No se pudo asignar datos por incompatibilidad
        }
    }

    public void setTamañoLetra(int tamañoLetra) {
        this.tamañoLetra = tamañoLetra;
    }

    public void setTamañoIntervalos(int tamañoIntervalos) {
        this.tamañoIntervalos = tamañoIntervalos;
    }

    @Override
    public void onDraw(Canvas canvas) {
        altoCanvas = canvas.getHeight();
        anchoCanvas = canvas.getWidth();
        //Calculo de ancho de cada barra
        anchoBarra = (anchoCanvas - paddingLeft - 20) / datosHorizontales.size();

        //Determinar valorMaximo de datosVerticales
        for (int i = 0; i < datosVerticales.size(); i++) {
            valorMaximo = Math.max(valorMaximo, datosVerticales.get(i));
        }

        //Dibujar etiquetas verticales en un tamaño de intervalos establecido o por defecto
        for (int valorActual = 0; valorActual < valorMaximo; valorActual += tamañoIntervalos) {
            canvas.drawText(valorActual + "", 40, altoCanvas - paddingButton - ((altoCanvas - paddingButton) / valorMaximo) * valorActual, paint);
        }

        //Dibujar barras
        for (int i = 0; i < datosHorizontales.size(); i++) {
            int corXBarraI = anchoBarra * (i) + paddingLeft;
            int corYBarraI = ((altoCanvas - paddingButton) / valorMaximo) * datosVerticales.get(i) + paddingButton;
            canvas.drawRect(corXBarraI + 5, altoCanvas - corYBarraI, corXBarraI + anchoBarra, altoCanvas - paddingButton, paint);
            canvas.drawText(datosHorizontales.get(i), corXBarraI + anchoBarra / 2, altoCanvas - 70, paint);
        }
    }

    void configurarPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setTextSize(tamañoLetra);
        paint.setTextAlign(Paint.Align.CENTER);

    }
}