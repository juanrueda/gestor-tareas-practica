package com.curso.tareas;

import java.util.Scanner;

/**
 * Gestor de tareas en consola: punto de partida de la charla 1.
 * Seguí EJERCICIOS.md: cada ejercicio le agrega una parte a este programa.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] tareas = new String[10];
    private static int cantidad = 0;

    public static void main(String[] args) {

        // EJERCICIO 1: repetir el menú con un do-while y resolver cada opción con un switch
        int opcion;

        do {
            mostrarMenu();

            opcion = leerEntero("Elegí una opción: ");

            System.out.println("Elegiste la opción " + opcion);

            switch (opcion) {
                case 1:
                    agregarTareas();
                    break;
                case 2:
                    listarTareas();
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion!= 0);
    }

    private static void mostrarMenu(){
        System.out.println();
        System.out.println("=== Gestor de tareas ===");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar tareas");
        System.out.println("0. Salir");
    }

    private static void agregarTareas() {
        tareas[cantidad] = leerTexto("Titulo: ");
        cantidad++;
    }

    private static void listarTareas() {
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i+1) + ". " + tareas[i]);
        }
    }

    // ------------------------------------------------------------------
    // Métodos de ayuda para leer de la consola: ya están listos, usalos.
    // ------------------------------------------------------------------

    /** Lee un número; si el usuario escribe otra cosa, lo vuelve a pedir. */
    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Ingresá un número: ");
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // descarta el Enter que deja nextInt()
        return valor;
    }

    /**
     * Lee una línea de texto, sin espacios al principio ni al final.
     */
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}
