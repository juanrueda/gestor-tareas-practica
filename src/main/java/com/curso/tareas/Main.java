package com.curso.tareas;
import com.curso.tareas.model.Tarea;
import com.curso.tareas.repository.TareaRepositoryEnMemoria;
import com.curso.tareas.service.TareaService;

import java.util.*;

/**
 * Gestor de tareas en consola: punto de partida de la charla 1.
 * Seguí EJERCICIOS.md: cada ejercicio le agrega una parte a este programa.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TareaService service = new TareaService(new TareaRepositoryEnMemoria());

    public static void main(String[] args) {

        // EJERCICIO 1: repetir el menú con un do-while y resolver cada opción con un switch
        int opcion;

        do {
            mostrarMenu();

            opcion = leerEntero("Elegí una opción: ");

            System.out.println("Elegiste la opción " + opcion);

            switch (opcion) {
                case 1:
                    agregarTarea();
                    break;
                case 2:
                    listarTareas();
                    break;
                case 3:
                    completarTarea();
                    break;
                case 4:
                    eliminarTarea();
                    break;
                case 5:
                    buscarTarea();
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
        System.out.println("3. Completar tarea");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Buscar tarea");

        System.out.println("0. Salir");
    }

    private static void agregarTarea() {
        Tarea tarea = service.crear(leerTexto("Titulo: "));
        System.out.println("Tarea agregada con id " + tarea.getId());
    }

    private static void listarTareas() {
        mostrar(service.listar());
    }

    private static void completarTarea() {
        service.completar(leerId());
        System.out.println("Tarea completada");
    }

    private static void eliminarTarea() {
        service.eliminar(leerId());
        System.out.println("Tarea eliminada");
    }

    private static void buscarTarea() {
        mostrar(service.buscar(leerTexto("Texto a buscar: ")));
    }

    private static void mostrar(List<Tarea> tareas) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas");
        }
        tareas.forEach(tarea -> System.out.println(tarea));
    }

    private static Long leerId() {
        return (long) leerEntero("Id de la tarea ");
    }

    // ------------------------------------------------------------------
    // Métodos de ayuda para leer de la consola: ya están listos, usalos.
    // ------------------------------------------------------------------

    /** Lee un número; si el usuario escribe otra cosa, lo vuelve a pedir. */
    private static int leerEntero(String texto) {
        System.out.print(texto);
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
    private static String leerTexto(String texto) {
        System.out.print(texto);
        return scanner.nextLine().trim();
    }
}
