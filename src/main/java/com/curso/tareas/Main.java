package com.curso.tareas;
import com.curso.tareas.model.Tarea;
import com.curso.tareas.repository.TareaRepositoryEnMemoria;
import com.curso.tareas.service.TareaNoEncontradaException;
import com.curso.tareas.service.TareaService;

import java.util.*;

/**
 * Gestor de tareas en consola: punto de partida de la charla 1.
 * Seguí EJERCICIOS.md: cada ejercicio le agrega una parte a este programa.
 */
public class Main {

    private static final String MENU = """

        === Gestor de tareas ===
        1. Agregar tarea
        2. Listar tareas
        3. Completar tarea
        4. Eliminar tarea
        5. Buscar tareas
        6. Ver pendientes
        0. Salir""";

    private static final Scanner scanner = new Scanner(System.in);
    private static final TareaService service = new TareaService(new TareaRepositoryEnMemoria());

    public static void main(String[] args) {

        // EJERCICIO 1: repetir el menú con un do-while y resolver cada opción con un switch
        int opcion;

        do {
            System.out.println(MENU);

            opcion = leerEntero("Elegí una opción: ");

            try {

                switch (opcion) {
                    case 1 -> agregarTarea();
                    case 2 -> listarTareas();
                    case 3 -> completarTarea();
                    case 4 -> eliminarTarea();
                    case 5 -> buscarTarea();
                    case 6 -> pendientes();
                    case 0 -> System.out.println("Hasta luego!");
                    default -> System.out.println("Opción inválida");
                }
            } catch (RuntimeException e) {
                mostrarError(e);
            }
        } while (opcion!= 0);
    }

    private static void agregarTarea() {
        var tarea = service.crear(leerTexto("Titulo: "));
        System.out.println("Tarea agregada con id " + tarea.id());
    }

    private static void listarTareas() {
        mostrar(service.listar());
    }

    private static void completarTarea() {
        var tarea = service.completar(leerId());
        System.out.println("Tarea: " + tarea.titulo() + " completada");
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

    private static void mostrarError(RuntimeException e) {
        if (e instanceof TareaNoEncontradaException noEncontrada) {
            System.out.println("No existe la tarea " + noEncontrada.getId() + ". Revisá la lista con la opción 2.");
        } else if (e instanceof IllegalArgumentException) {
            System.out.println(e.getMessage());
        } else {
            throw e;
        }
    }


    private static void pendientes() {
        //service.pendientes().forEach(tarea -> System.out.println(tarea));
        mostrar(service.pendientes());
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
