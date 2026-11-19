package com.curso.tareas.model;

public class Tarea {
    private final long id;
    private final String titulo;
    private boolean hecha;

    public Tarea(long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.hecha = false;
    }

    public long getId(){
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public boolean isHecha() {
        return this.hecha;
    }

    public void completar() {
        this.hecha = true;
    }

    @Override
    public String toString() {
        return (hecha? "[X] " : "[ ] ") + id + " - " + titulo;
    }
}
