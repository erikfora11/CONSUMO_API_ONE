package org.example;

import com.google.gson.annotations.SerializedName;

public class Intercambio {
    @SerializedName("base_code")
    private String moneda_1;
    @SerializedName("target_code")
    private String moneda_2;
    @SerializedName("conversion_result")
    private float cambio;

    public float getCambio() {
        return cambio;
    }

    public void setCambio(float cambio) {
        this.cambio = cambio;
    }

    public String getMoneda_2() {
        return moneda_2;
    }

    public void setMoneda_2(String moneda_2) {
        this.moneda_2 = moneda_2;
    }

    public String getMoneda_1() {
        return moneda_1;
    }

    public void setMoneda_1(String moneda_1) {
        this.moneda_1 = moneda_1;
    }

    @Override
    public String toString() {
        return "Intercambio{" +
                "moneda_1='" + moneda_1 + '\'' +
                ", moneda_2='" + moneda_2 + '\'' +
                ", cambio=" + cambio +
                '}';
    }
}
