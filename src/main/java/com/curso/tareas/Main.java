package com.curso.tareas;
import com.curso.tareas.model.Tarea;

import java.util.*;

/**
 * Gestor de tareas en consola: punto de partida de la charla 1.
 * Seguí EJERCICIOS.md: cada ejercicio le agrega una parte a este programa.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<Long, Tarea> tareas = new HashMap<>();
    private static long proximoId = 1;

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

    private static void agregarTarea(String tituloTarea) {
        if (tituloTarea.isEmpty()){
            System.out.println("El título no puede estar vacío");
        } else {
            Tarea tarea = new Tarea(proximoId, tituloTarea);
            tareas.put(tarea.getId(), tarea);
            proximoId++;

            System.out.println("Tarea agregada con Id " + tarea.getId());
        }
    }

    private static void agregarTarea() {
        String titulo = leerTexto("Titulo: ");
        agregarTarea(titulo);
    }

    private static void listarTareas() {
        if (tareas.isEmpty()){
            System.out.println("No hay tareas");
        } else {
            List<Tarea> listaTareas = new ArrayList<>(tareas.values());
            listaTareas.sort((a,b) -> a.getId().compareTo(b.getId()));
            listaTareas.forEach(t -> System.out.println(t));
        }
    }

    private static void completarTarea() {
        long id = leerEntero("Id de la tarea: ");
        Tarea tarea = tareas.get(id);
        if (tarea == null) {
            System.out.println("No existe la tarea con Id " + id);
        } else {
            tarea.completar();
            System.out.print("Tarea completada");
        }
    }

    private static void eliminarTarea() {
        long id = leerEntero("Id de la tarea: ");
        Tarea tarea = tareas.get(id);
        if (tarea == null) {
            System.out.println("No existe una tarea con Id" + id);
        } else {
            tareas.remove(id);
            System.out.println("Tarea eliminada");
        }
    }

    private static void buscarTarea() {
        String nombre = leerTexto("Nombre de la tarea: ");
        List<Tarea> encontradas = new ArrayList<>();

        for (Tarea tarea : tareas.values()) {
            if (tarea.getTitulo().toLowerCase().contains(nombre)) {
                encontradas.add(tarea);
            }
        }
        if (encontradas.isEmpty()) {
            System.out.println("No se encontraron tareas");
        }
        encontradas.forEach(t -> System.out.println(t));
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
