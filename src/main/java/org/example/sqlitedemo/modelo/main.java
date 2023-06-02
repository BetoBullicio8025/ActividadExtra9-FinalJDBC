package org.example.sqlitedemo.modelo;

public class main {
    public static void main(String[] args) {
        demoLibroDB demo = new demoLibroDB();
        libro libro1 = new libro(0, "El juego", "Unknown");

//        if(demo.insertarLibro(libro1)){
//            System.out.println("Se insertó correctamente ");
//        }else {
//            System.out.println("No se insertó ");
//        }

        System.out.println(demo.buscarLibroPorId(100));

        System.out.println("-------------------------");
        for (libro tmp : demo.obtenerTodos()) {
            System.out.println("Libro: " + tmp);
            System.out.println("Titulo: " + tmp.getTitulo());
            {

            }
        }
    }
    }
