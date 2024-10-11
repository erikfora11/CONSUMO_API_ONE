package org.example;


import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Solicitudes {

    Dotenv dotenv = Dotenv.load();
    String token = dotenv.get("TOKEN");

    public Solicitudes() throws IOException, InterruptedException {

        System.out.println("\n==========================\nCAMBIADOR DE MONEDAS\n by: erikfora11\n==========================\n");
        menu();

        while (continuar()){
            menu();
        }

        System.out.println("\n==========================\nFIN\n==========================\n");

    }

    private void solicitar(String moneda_1, String moneda_2, int valor) throws IOException, InterruptedException {

        Gson gson = new Gson();
        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/" +
                        token + "/pair/" + moneda_1 + "/" + moneda_2 + "/" + valor))
                .build();

        HttpResponse<String> response = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        Intercambio cambio = gson.fromJson(json, Intercambio.class);

        System.out.printf("\nMoneda:%s\nMoneda a cambiar:%s\nValor:%d\nCambio:%.0f\n",cambio.getMoneda_1(),cambio.getMoneda_2(),valor,cambio.getCambio());
    }


    private void mostrar_monedas() {
        for (monedas Moneda : monedas.values()) {
            System.out.println(Moneda.ordinal() + "." + Moneda);
        }
    }

    private String solicitar_moneda(int lugar) {
        for (monedas moneda : monedas.values()) {
            if (moneda.ordinal() == lugar) {
                return moneda.toString();
            }
        }
        return null;
    }

    private boolean continuar(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("continuar? (Y/N)");

        return teclado.next().equalsIgnoreCase("Y");
    }
    private void menu() throws IOException, InterruptedException {
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nInserte el numero de la moneda que quiere cambiar: ");
        mostrar_monedas();
        String moneda_1 = solicitar_moneda(teclado.nextInt());

        System.out.println("\nInserte el numero de la moneda a la quiere cambiar: ");
        mostrar_monedas();
        String moneda_2 = solicitar_moneda(teclado.nextInt());

        System.out.println("\nInserte el valor para cambiar: ");
        int valor = teclado.nextInt();

        solicitar(moneda_1, moneda_2,valor);
    }

}