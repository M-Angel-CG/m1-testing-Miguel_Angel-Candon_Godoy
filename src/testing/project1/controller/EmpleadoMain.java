package testing.project1.controller;

import testing.project1.objects.Empleado;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EmpleadoMain {
    /**
     * Muestra por pantalla los datos del empleado
     * @param empl Objeto empleado que se va a mostrar
     */
    public static void muestraEmpleado(Empleado empl) {
        String casado = "soltero";
        if(empl.isEstadoCivil()) {
            casado = "casado";
        }
        System.out.println(
                "Nombre: " + empl.getNombre() + " " + empl.getApellido() + "\n\t" +
                        empl.getEdad() + " años - DNI: " +
                        empl.getDni() + " - " +
                        empl.getSalario() + "€ - Estado civil: " +
                        casado
        );
    }

    /**
     * Muestra toda la lista de empleados
     * @param empleados Lista de empleados
     */
    public static void listar(List<Empleado> empleados) {
        if(empleados.isEmpty()) {
            System.out.println("No hay empleados");
        } else {
            System.out.println("\nTotal de empleados: " + empleados.size());
            for (Empleado empl: empleados) {
                muestraEmpleado(empl);
            }
        }
    }

    /**
     * Comprueba si algun dni de la lista coincide con el dni dado
     * @param dni DNI a buscar
     * @param empleados Lista con todos los empleados
     * @return El empleado que coincide con el dni, o null si no existe
     */
    public static Empleado existeEmpleado(String dni, List<Empleado> empleados) {
        for (Empleado empl: empleados) {
            if(empl.getDni().equals(dni)) {
                return empl;
            }
        }
        return null;
    }

    /**
     * Devuelve la lista de empleados dada con el nuevo empleado
     * @param empleados Lista de empleados a modificar
     * @return La nueva lista de empleados
     */
    public static List<Empleado> crear(List<Empleado> empleados) {
        System.out.println("\nIntroduzca sus datos:");
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nNombre:");
        String nombre = scanner.next();
        System.out.println("\nApellidos:");
        String apellidos = scanner.next();
        System.out.println("\nEdad:");
        int edad = scanner.nextInt();

        System.out.println("\nEstado civil (casado->Y/soltero->N):");
        String estado = scanner.next();

        boolean casado = estado.equalsIgnoreCase("Y");

        System.out.println("\nSalario:");
        float salario = scanner.nextFloat();
        System.out.println("\nDNI:");
        String dni = scanner.next();

        if(existeEmpleado(dni, empleados) == null) {
            Empleado empleadoNuevo = new Empleado(edad,
                    nombre, apellidos, dni, salario, casado);
            //System.out.println(edad+nombre+ apellidos+ dni+ salario+ casado);
            empleados.add(empleadoNuevo);

            System.out.println("Empleado creado con éxito\n");
        } else {
            System.out.println("Ya existe un empleado con ese DNI\n");
        }

        return empleados;
    }

    /**
     * Busca a un empleado con el dni dado
     * @param dni DNI a buscar
     * @param empleados Lista con todos los empleados
     */
    public static void buscar(String dni, List<Empleado> empleados) {
        Empleado empleado = existeEmpleado(dni, empleados);

        if (empleado != null) {
            muestraEmpleado(empleado);
        } else {
            System.out.println("No existe el empleado con el dni " + dni);
        }
    }

    /**
     * Modifica el empleado de la lista que coincida con el dni dado
     * @param dni DNI a buscar
     * @param empleados Lista con todos los empleados
     * @return Lista con el empleado modificado
     */
    public static List<Empleado> modificar(String dni, List<Empleado> empleados) {
        Empleado buscado = existeEmpleado(dni, empleados);
        if (buscado == null) {
            System.out.println("El empleado no existe");
            return empleados;
        }

        int newEdad = buscado.getEdad();
        String newNombre = buscado.getNombre();
        String newApellido = buscado.getApellido();
        boolean newEstadoCivil = buscado.isEstadoCivil();
        float newSalario = buscado.getSalario();

        Scanner scanner = new Scanner(System.in);
        int confirmar = 0;

        while(true) {
            System.out.println("\nElige qué modificar:");
            System.out.println(" 1- Nombre");
            System.out.println(" 2- Apellidos");
            System.out.println(" 3- Edad");
            System.out.println(" 4- Salario");
            System.out.println(" 5- Estado civil");
            System.out.println(" 6- Confirmar");
            System.out.println(" 0- Salir");
            System.out.println(" -----------------------------");


            int eleccion = -1000;

            System.out.println("\nElige una opción:");
            try {
                eleccion = scanner.nextInt();
            } catch (InputMismatchException e1) {
                //e1.printStackTrace();
                System.out.println("Error - input no admitido");
            }

            if(eleccion == 0) { //salir
                break;
            }
            if(eleccion == 1) { //cambiar nombre
                System.out.println("Escribe un nombre:");
                newNombre = scanner.next();
                scanner.nextLine();
            }
            if(eleccion == 2) { //cambiar apellido
                System.out.println("Escribe un apellido:");
                newApellido = scanner.next();
                scanner.nextLine();
            }
            if(eleccion == 3) { //cambiar edad
                System.out.println("Escribe una edad:");
                newEdad = scanner.nextInt();
                scanner.nextLine();
            }
            if(eleccion == 4) { //cambiar salario
                System.out.println("Escribe un salario:");
                newSalario = scanner.nextFloat();
                scanner.nextLine();
            }
            if(eleccion == 5) { //cambiar estado civil
                String eCivil = "soltero";
                if(newEstadoCivil) {
                    eCivil = "casado";
                }

                System.out.println("Escribe \"soltero\" o \"casado\":");
                eCivil = scanner.next();
                scanner.nextLine();

                newEstadoCivil = eCivil.equalsIgnoreCase("casado");
            }
            if(eleccion == 6) { //confirmar y salir
                confirmar = 1;
                break;
            }
        }

        if(confirmar == 1) { //guardar los cambios
            int pos = empleados.indexOf(buscado);

            buscado.setEdad(newEdad);
            buscado.setNombre(newNombre);
            buscado.setApellido(newApellido);
            buscado.setEstadoCivil(newEstadoCivil);
            buscado.setSalario(newSalario);

            empleados.set(pos,buscado);


            return empleados;
        }

        return empleados;
    }

    /**
     * Borra un empleado por el dni dado
     * @param dni
     * @param empleados
     * @return
     */
    public static  List<Empleado> borrar(String dni, List<Empleado> empleados) {
        Empleado buscado = existeEmpleado(dni, empleados);

        if (buscado == null) {
            System.out.println("El empleado no existe");
            return empleados;
        }

        try {
            //int pos = empleados.indexOf(buscado);
            //empleados.remove(pos);
            empleados.remove(buscado);
            System.out.println("Se ha borrado el empleado con éxito\n");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se ha podido realizar la acción\n");
        }

        return empleados;
    }
}
