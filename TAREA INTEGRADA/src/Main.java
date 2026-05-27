package app;

import java.util.Scanner;
import modelo.*;
import servicio.EmpleadoServicio;
import util.Validador;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmpleadoServicio servicio = new EmpleadoServicio();
        int opcion = 0;

        do {
            System.out.println("\n===== CLÍNICA SALUD TOTAL =====");
            System.out.println("1. Registrar médico");
            System.out.println("2. Registrar administrativo");
            System.out.println("3. Mostrar empleados");
            System.out.println("4. Buscar por cédula");
            System.out.println("5. Reemplazar información");
            System.out.println("6. Eliminar registro");
            System.out.println("7. Calcular pagos");
            System.out.println("8. Mostrar estadísticas");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                // Captura de errores en menú (letras o números no válidos)
                String opcionTxt = sc.nextLine();
                opcion = Integer.parseInt(opcionTxt); // Conversión obligatoria

                if (opcion < 1 || opcion > 9) {
                    System.out.println("Error: opción inválida. Ingrese un número entre 1 y 9.");
                    continue;
                }

                switch (opcion) {
                    case 1: // Registrar Médico
                        try {
                            System.out.print("Ingrese cédula: ");
                            String cedula = sc.nextLine();
                            Validador.validarCampoVacio(cedula, "Cédula");
                            Validador.validarCedulaDuplicada(cedula, servicio.getListaEmpleados());

                            System.out.print("Ingrese nombre: ");
                            String nombre = sc.nextLine();
                            Validador.validarCampoVacio(nombre, "Nombre");

                            System.out.print("Ingrese edad: ");
                            int edad = Integer.parseInt(sc.nextLine()); // Conversión obligatoria
                            Validador.validarEdad(edad);

                            System.out.print("Ingrese teléfono: ");
                            String telf = sc.nextLine();
                            Validador.validarTelefono(telf);

                            System.out.print("Ingrese correo: ");
                            String correo = sc.nextLine();
                            Validador.validarCorreo(correo);

                            System.out.print("Ingrese especialidad: ");
                            String esp = sc.nextLine();
                            Validador.validarCampoVacio(esp, "Especialidad");

                            System.out.print("Ingrese número de pacientes atendidos: ");
                            int pac = Integer.parseInt(sc.nextLine());
                            Validador.validarMayorCero(pac, "Pacientes atendidos");

                            System.out.print("Ingrese valor por consulta: ");
                            double valC = Double.parseDouble(sc.nextLine()); // Conversión obligatoria
                            Validador.validarMayorCero(valC, "Valor consulta");

                            Medico medico = new Medico(cedula, nombre, edad, telf, correo, esp, pac, valC);
                            servicio.registrarEmpleado(medico);

                        } catch (NumberFormatException e) {
                            System.out.println("Error: Se esperaba un dato numérico válido en los campos matemáticos/edad.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2: // Registrar Administrativo
                        try {
                            System.out.print("Ingrese cédula: ");
                            String cedula = sc.nextLine();
                            Validador.validarCampoVacio(cedula, "Cédula");
                            Validador.validarCedulaDuplicada(cedula, servicio.getListaEmpleados());

                            System.out.print("Ingrese nombre: ");
                            String nombre = sc.nextLine();
                            Validador.validarCampoVacio(nombre, "Nombre");

                            System.out.print("Ingrese edad: ");
                            int edad = Integer.parseInt(sc.nextLine());
                            Validador.validarEdad(edad);

                            System.out.print("Ingrese teléfono: ");
                            String telf = sc.nextLine();
                            Validador.validarTelefono(telf);

                            System.out.print("Ingrese correo: ");
                            String correo = sc.nextLine();
                            Validador.validarCorreo(correo);

                            System.out.print("Ingrese departamento: ");
                            String depto = sc.nextLine();
                            Validador.validarCampoVacio(depto, "Departamento");

                            System.out.print("Ingrese horas trabajadas: ");
                            int horas = Integer.parseInt(sc.nextLine());
                            Validador.validarMayorCero(horas, "Horas trabajadas");

                            System.out.print("Ingrese valor por hora: ");
                            double valH = Double.parseDouble(sc.nextLine());
                            Validador.validarMayorCero(valH, "Valor por hora");

                            Administrativo admin = new Administrativo(cedula, nombre, edad, telf, correo, depto, horas, valH);
                            servicio.registrarEmpleado(admin);

                        } catch (NumberFormatException e) {
                            System.out.println("Error: Formato de número inválido.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3:
                        servicio.mostrarEmpleados();
                        break;

                    case 4: // Buscar por Cédula
                        System.out.print("Ingrese la cédula a buscar: ");
                        String cedBuscar = sc.nextLine();
                        Empleado empEncontrado = servicio.buscarPorCedula(cedBuscar);
                        if (empEncontrado != null) {
                            empEncontrado.mostrarInformacion();
                        } else {
                            System.out.println("Registro no encontrado.");
                        }
                        break;

                    case 5: // UPDATE - Reemplazar Información
                        try {
                            System.out.print("Ingrese la cédula del empleado a modificar: ");
                            String cedMod = sc.nextLine();
                            Empleado empMod = servicio.buscarPorCedula(cedMod);

                            if (empMod != null) {
                                System.out.print("Ingrese nuevo nombre: ");
                                String nName = sc.nextLine();
                                Validador.validarCampoVacio(nName, "Nombre");
                                empMod.setNombre(nName);

                                System.out.print("Ingrese nuevo correo: ");
                                String nMail = sc.nextLine();
                                Validador.validarCorreo(nMail);
                                empMod.setCorreo(nMail);

                                System.out.println("Datos básicos actualizados de manera exitosa.");
                            } else {
                                System.out.println("Registro no encontrado.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 6: // DELETE - Eliminar registro
                        System.out.print("Ingrese la cédula del empleado a eliminar: ");
                        String cedDel = sc.nextLine();
                        if (servicio.eliminarRegistro(cedDel)) {
                            System.out.println("Registro eliminado correctamente.");
                        } else {
                            System.out.println("Registro no encontrado.");
                        }
                        break;

                    case 7:
                        servicio.calcularPagos();
                        break;

                    case 8:
                        servicio.mostrarEstadisticas();
                        break;

                    case 9:
                        System.out.println("Saliendo del sistema clínico. ¡Hasta pronto!");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: opción inválida. Por favor, introduzca un número entero.");
            }
        } while (opcion != 9);

        sc.close();
    }
}