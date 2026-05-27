package servicio;

import java.util.ArrayList;
import modelo.Empleado;
import modelo.Medico;
import modelo.Administrativo;

public class EmpleadoServicio {
    // Polimorfismo Obligatorio con ArrayList
    private ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    // CREATE
    public void registrarEmpleado(Empleado emp) {
        listaEmpleados.add(emp);
        System.out.println("Empleado registrado exitosamente.");
    }

    // READ
    public void mostrarEmpleados() {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        for (Empleado emp : listaEmpleados) {
            emp.mostrarInformacion();
            System.out.println("--------------------------------");
        }
    }

    // BUSCAR POR CÉDULA
    public Empleado buscarPorCedula(String cedula) {
        for (Empleado emp : listaEmpleados) {
            if (emp.getCedula().equals(cedula)) {
                return emp;
            }
        }
        return null;
    }

    // DELETE
    public boolean eliminarRegistro(String cedula) {
        Empleado emp = buscarPorCedula(cedula);
        if (emp != null) {
            listaEmpleados.remove(emp);
            return true;
        }
        return false;
    }

    // CALCULAR PAGOS INDIVIDUALES
    public void calcularPagos() {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados para calcular pagos.");
            return;
        }
        System.out.println("=== CÁLCULO DE PAGOS ===");
        for (Empleado emp : listaEmpleados) {
            System.out.println("Empleado: " + emp.getNombre() + " | Cargo: " +
                    (emp instanceof Medico ? "Médico" : "Administrativo") +
                    " | Pago: $" + emp.calcularPago());
        }
    }

    // ESTADÍSTICAS MIGRADAS
    public void mostrarEstadisticas() {
        int totalMedicos = 0;
        int totalAdmins = 0;
        double pagoMedicos = 0;
        double pagoAdmins = 0;
        Empleado mayorIngreso = null;

        for (Empleado emp : listaEmpleados) {
            double pago = emp.calcularPago();

            if (mayorIngreso == null || pago > mayorIngreso.calcularPago()) {
                mayorIngreso = emp;
            }

            if (emp instanceof Medico) {
                totalMedicos++;
                pagoMedicos += pago;
            } else if (emp instanceof Administrativo) {
                totalAdmins++;
                pagoAdmins += pago;
            }
        }

        System.out.println("====== ESTADÍSTICAS DEL SISTEMA ======");
        System.out.println("Total Médicos: " + totalMedicos);
        System.out.println("Total Administrativos: " + totalAdmins);
        System.out.println("Total Empleados: " + listaEmpleados.size());
        System.out.println("Pago Total Médicos: $" + pagoMedicos);
        System.out.println("Pago Total Administrativos: $" + pagoAdmins);
        if (mayorIngreso != null) {
            System.out.println("Empleado con mayor ingreso: " + mayorIngreso.getNombre() + " ($" + mayorIngreso.calcularPago() + ")");
        } else {
            System.out.println("Empleado con mayor ingreso: Ninguno");
        }
    }
}