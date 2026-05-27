package modelo;

public class Medico extends Empleado {
    private String specialty; // especialidad
    private int numeroPacientesAtendidos;
    private double valorConsulta;

    public Medico(String cedula, String nombre, int edad, String telefono, String correo,
                  String especialidad, int numeroPacientesAtendidos, double valorConsulta) {
        super(cedula, nombre, edad, telefono, correo);
        this.specialty = especialidad;
        this.numeroPacientesAtendidos = numeroPacientesAtendidos;
        this.valorConsulta = valorConsulta;
    }

    @Override
    public double calcularPago() {
        return numeroPacientesAtendidos * valorConsulta;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("--- DATOS DEL MÉDICO ---");
        super.mostrarInformacion();
        System.out.println("Especialidad: " + specialty);
        System.out.println("Pacientes Atendidos: " + numeroPacientesAtendidos);
        System.out.println("Valor Consulta: $" + valorConsulta);
        System.out.println("Pago Total: $" + calcularPago());
    }

    // Getters y Setters
    public String getEspecialidad() { return specialty; }
    public void setEspecialidad(String especialidad) { this.specialty = especialidad; }
    public int getNumeroPacientesAtendidos() { return numeroPacientesAtendidos; }
    public void setNumeroPacientesAtendidos(int n) { this.numeroPacientesAtendidos = n; }
    public double getValorConsulta() { return valorConsulta; }
    public void setValorConsulta(double v) { this.valorConsulta = v; }
}