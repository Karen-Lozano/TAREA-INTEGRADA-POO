package util;

import java.util.ArrayList;
import modelo.Empleado;

public class Validador {

    public static void validarEdad(int edad) throws IllegalArgumentException {
        if (edad <= 0 || edad >= 150) {
            throw new IllegalArgumentException("La edad debe ser mayor a 0 y menor a 150.");
        }
    }

    public static void validarCedulaDuplicada(String cedula, ArrayList<Empleado> lista) throws IllegalArgumentException {
        for (Empleado emp : lista) {
            if (emp.getCedula().equalsIgnoreCase(cedula)) {
                throw new IllegalArgumentException("Error: La cédula ya está registrada en el sistema.");
            }
        }
    }

    public static void validarCampoVacio(String texto, String nombreCampo) throws IllegalArgumentException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' no puede estar vacío.");
        }
    }

    public static void validarCorreo(String correo) throws IllegalArgumentException {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("El correo electrónico debe contener un '@'.");
        }
    }

    public static void validarTelefono(String telefono) throws IllegalArgumentException {
        if (!telefono.matches("\\d+")) {
            throw new IllegalArgumentException("El teléfono solo debe contener números.");
        }
    }

    public static void validarMayorCero(double valor, String nombreCampo) throws IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException("El valor de '" + nombreCampo + "' debe ser mayor a cero.");
        }
    }
}