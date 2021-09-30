package testing.project1;

import testing.project1.controller.EmpleadoMain;
import testing.project1.objects.Empleado;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //empleados creados por defecto
        Empleado empleado1 = new Empleado(30,
                "Fernando","Fernández Fernández",
                "12345678A",2000.00f, false);
        Empleado empleado2 = new Empleado(30,
                "Pedro","Pérez Pérez",
                "12345677B",2000.00f, false);

        //List heredando de ArrayList
        List<Empleado> empleados = new ArrayList<>();

        empleados.add(empleado1);
        empleados.add(empleado2);
        while (true) {

            System.out.println(" -----------------------------");
            System.out.println(" --    Gestor de empleados  --");
            System.out.println(" --                         --");
            System.out.println(" -- 1- Lista de empleados   --"); //Hecho
            System.out.println(" -- 2- Crear un empleado    --"); //Hecho
            System.out.println(" -- 3- Modificar empleado   --"); //Hecho
            System.out.println(" -- 4- Borrar un empleado   --"); //Hecho
            System.out.println(" -- 5- Encontrar empleado   --"); //Hecho
            System.out.println(" -- 6- Borrar todo          --"); //
            System.out.println(" -- 0- Salir                --"); //Hecho
            System.out.println(" -----------------------------");


            int eleccion = -1000;

            System.out.println("\nElige una opción:");
            try {
                eleccion = scanner.nextInt();
            } catch (InputMismatchException e1) {
                //e1.printStackTrace();
                System.out.println("Error - No se puede leer");
            }

            //Salir
            if(eleccion == 0) {
                break;
            }

            //Listar
            if(eleccion == 1) {
                EmpleadoMain.listar(empleados);
                scanner.nextLine();
                scanner.nextLine();
            }

            //Crear
            if(eleccion == 2) {
                empleados = EmpleadoMain.crear(empleados);
                scanner.nextLine();
                scanner.nextLine();
            }

            //Modificar
            if(eleccion == 3) {
                if(!empleados.isEmpty()) {
                    System.out.println("\nEscibe el dni del usuario: ");
                    String dni = scanner.next();
                    empleados = EmpleadoMain.modificar(dni, empleados);
                } else {
                    System.out.println("No hay empleados que modificar");
                }
                scanner.nextLine();
                scanner.nextLine();
            }

            //Borrar
            if(eleccion == 4) {
                if(!empleados.isEmpty()) {
                    System.out.println("\nEscibe el dni del usuario: ");
                    String dni = scanner.next();
                    empleados = EmpleadoMain.borrar(dni, empleados);
                } else {
                    System.out.println("No hay empleados que modificar");
                }
                scanner.nextLine();
                scanner.nextLine();
            }

            //Buscar
            if(eleccion == 5) {
                if(!empleados.isEmpty()) {
                    System.out.println("\nEscibe el dni del usuario: ");
                    String dni = scanner.next();
                    EmpleadoMain.buscar(dni, empleados);
                } else {
                    System.out.println("No hay empleados que modificar");
                }
                scanner.nextLine();
                scanner.nextLine();
            }

            //Borrar lista entera
            if (eleccion == 6) {
                empleados.clear();
                scanner.nextLine();
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
