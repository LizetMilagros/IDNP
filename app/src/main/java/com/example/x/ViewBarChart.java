package com.example.x;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewBarChart extends View {
    private Paint paint;
    private int valorMaximo = 100;
    private String[ ] datosHorizontales = new String[7];
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
    public void setDatos(String[] datosHorizontales, List<Integer> datosVerticales) {
        if (datosHorizontales.length == datosVerticales.size()) {
            this.datosHorizontales = datosHorizontales;
            this.datosVerticales = datosVerticales;
        } else {
            //no muestra nada
        }
    }
    @Override
    public void onDraw(Canvas canvas) {
        int altoCanvas = canvas.getHeight();
        int anchoCanvas = canvas.getWidth();
        int paddingLeft = 80;
        int anchoBarra = (anchoCanvas - paddingLeft - 20) / datosHorizontales.length;
        for (int i = 0; i < datosVerticales.size(); i++) {
            valorMaximo = Math.max(valorMaximo, datosVerticales.get(i));
        }
        int paddingButton = 100;
        int tamañoIntervalos = 10;
        for (int valorActual = 0; valorActual < valorMaximo; valorActual += tamañoIntervalos) {
            canvas.drawText(valorActual + "", 40, altoCanvas - paddingButton
                    - ((altoCanvas - paddingButton) / valorMaximo) * valorActual, paint);
        }
        for (int i = 0; i < datosHorizontales.length; i++) {
            int corXBarraI = anchoBarra * (i) + paddingLeft;
            int corYBarraI = ((altoCanvas - paddingButton) / valorMaximo) * datosVerticales.get(i) + paddingButton;
            canvas.drawRect(corXBarraI + 5, altoCanvas - corYBarraI, corXBarraI + anchoBarra,
                    altoCanvas - paddingButton, paint);
            canvas.drawText(datosHorizontales[i], corXBarraI + anchoBarra / 2, altoCanvas - 70, paint);
        }
    }
    void configurarPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        int tamañoLetra = 35;
        paint.setTextSize(tamañoLetra);
        paint.setTextAlign(Paint.Align.CENTER);
    }
}