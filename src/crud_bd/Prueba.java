/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud_bd;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author WinOs
 */
public class Prueba {
    public static void main(String[] args) {
        PersonaDao personaDao = new PersonaDao();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("********** Menú **********");
            System.out.println("1. Crear persona");
            System.out.println("2. Obtener persona por cédula");
            System.out.println("3. Actualizar persona");
            System.out.println("4. Eliminar persona");
            System.out.println("5. Mostrar todas las personas");
            System.out.println("6. Salir");
            System.out.println("**************************");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("Ingrese el nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese la cédula:");
                    String cedula = scanner.nextLine();
                    System.out.println("Ingrese el sexo:");
                    String sexo = scanner.nextLine();
                    System.out.println("Ingrese la estatura:");
                    double estatura = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Ingrese la fecha de nacimiento (en formato yyyy-MM-dd):");
                    String fechaNacimientoStr = scanner.nextLine();
                    java.util.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);

                    Persona nuevaPersona = new Persona(nombre, cedula, sexo, estatura, fechaNacimiento);
                    personaDao.crearPersona(nuevaPersona);
                    System.out.println("Persona creada con éxito.");
                    break;

                case 2:
                    System.out.println("Ingrese la cédula de la persona a buscar:");
                    String cedulaBuscar = scanner.nextLine();
                    Persona personaEncontrada = personaDao.obtenerPersonaPorCedula(cedulaBuscar);
                    if (personaEncontrada != null) {
                        System.out.println("Persona encontrada:");
                        System.out.println(personaEncontrada);
                    } else {
                        System.out.println("No se encontró ninguna persona con esa cédula.");
                    }
                    break;

                case 3:
                    System.out.println("Ingrese la cédula de la persona a actualizar:");
                    String cedulaActualizar = scanner.nextLine();
                    Persona personaActualizar = personaDao.obtenerPersonaPorCedula(cedulaActualizar);
                    if (personaActualizar != null) {
                        System.out.println("Datos de la persona a actualizar:");
                        System.out.println("Nombre: " + personaActualizar.getNombre());
                        System.out.println("Cédula: " + personaActualizar.getCedula());
                        System.out.println("Sexo: " + personaActualizar.getSexo());
                        System.out.println("Estatura: " + personaActualizar.getEstatura());
                        System.out.println("Fecha de nacimiento: " + personaActualizar.getFechaNacimiento());

                        System.out.println("Ingrese el nuevo nombre:");
                        String nuevoNombre = scanner.nextLine();
                        System.out.println("Ingrese el nuevo sexo:");
                        String nuevoSexo = scanner.nextLine();
                        System.out.println("Ingrese la nueva estatura:");
                        double nuevaEstatura = scanner.nextDouble();
                        System.out.println("Ingrese la nueva fecha de nacimiento (en formato yyyy-MM-dd):");
                        scanner.nextLine();
                        String nuevaFechaNacimientoStr = scanner.nextLine();
                        java.util.Date nuevaFechaNacimiento = java.sql.Date.valueOf(nuevaFechaNacimientoStr);

                        personaActualizar.setNombre(nuevoNombre);
                        personaActualizar.setSexo(nuevoSexo);
                        personaActualizar.setEstatura(nuevaEstatura);
                        personaActualizar.setFechaNacimiento(nuevaFechaNacimiento);

                        personaDao.actualizarPersona(personaActualizar);
                        System.out.println("Persona actualizada con éxito.");
                    } else {
                        System.out.println("No se encontró ninguna persona con esa cédula.");
                    }
                    break;

                case 4:
                    System.out.println("Ingrese la cédula de la persona a eliminar:");
                    String cedulaEliminar = scanner.nextLine();
                    Persona personaEliminar = personaDao.obtenerPersonaPorCedula(cedulaEliminar);
                    if (personaEliminar != null) {
                        System.out.println("Datos de la persona a eliminar:");
                        System.out.println("Nombre: " + personaEliminar.getNombre());
                        System.out.println("Cédula: " + personaEliminar.getCedula());
                        System.out.println("Sexo: " + personaEliminar.getSexo());
                        System.out.println("Estatura: " + personaEliminar.getEstatura());
                        System.out.println("Fecha de nacimiento: " + personaEliminar.getFechaNacimiento());

                        System.out.print("¿Está seguro de eliminar esta persona? (S/N): ");
                        String respuesta = scanner.nextLine().trim().toUpperCase();
                        if (respuesta.equals("S")) {
                            if (personaDao.eliminarPersona(cedulaEliminar)) {
                                System.out.println("Persona eliminada con éxito.");
                            } else {
                                System.out.println("Error al eliminar la persona.");
                            }
                        } else {
                            System.out.println("Operación de eliminación cancelada.");
                        }
                    } else {
                        System.out.println("No se encontró ninguna persona con esa cédula.");
                    }
                    break;

                case 5:
                    ArrayList<Persona> personas = (ArrayList<Persona>) personaDao.obtenerTodasLasPersonas();
                    if (!personas.isEmpty()) {
                        System.out.println("Listado de personas:");
                        for (Persona persona : personas) {
                            System.out.println(persona);
                        }
                    } else {
                        System.out.println("No hay personas registradas.");
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}
