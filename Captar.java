package empleados;

import Utilidades.Util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Captar extends EmpleadoDAO implements ICaptar {
    private Scanner sc = new Scanner(System.in);

    public Captar() {
        super();
    }


    @Override
    public void agregarEmpleado() {
        Util.mostrarMensaje("Agregar empleado");
        Empleado empleado = capturarEmpleado();
        if (!super.existeId(empleado.getId())) {
            super.agregar(empleado);
        } else {
            Util.mostrarMensajeStandarError("id " + empleado.getId() +
                    " existe, por lo tanto no se puede guardar él empleado.");
            Util.mesageEnterError();
            Util.pausarEjecucion(sc);
        }

    }

    private Empleado capturarEmpleado() {
        Util.mostrarMensajeStandar("\nIngresar id\n->");
        String id = sc.nextLine().trim();
        Util.mostrarMensajeStandar("\nIngresar nombre\n->");
        String nombre = sc.nextLine().trim();
        Util.mostrarMensajeStandar("\nIngresar apellido\n->");
        String apellido = sc.nextLine().trim();
        Float salario = ingresarSalario();
        sc.nextLine();
        return new Empleado(id, nombre, apellido, salario);
    }

    private Empleado empleadoActualizado(String id) {
        Util.mostrarMensajeStandar("\nIngresar nombre\n->");
        String nombre = sc.nextLine().trim();
        Util.mostrarMensajeStandar("\nIngresar apellido\n->");
        String apellido = sc.nextLine().trim();
        Float salario = ingresarSalario();
        sc.nextLine();
        return new Empleado(id, nombre, apellido, salario);
    }

    @Override
    public void eliminarEmpleado() {
        Util.mostrarMensaje("Eliminar empleado");
        Util.mostrarMensajeStandar("Ingresar id: ");
        String id = sc.nextLine();
        if (!existeId(id)) {
            Util.mostrarMensajeStandarError("id " + id + ", no existe.");
            Util.mesageEnterError();
            Util.pausarEjecucion(new Scanner(System.in));
        }else{
            super.eliminar(id);
        }

    }

    @Override
    public void actualizarEmpleado() {
        Util.mostrarMensaje("Actualizar empleado");
        Util.mostrarMensajeStandar("\nIngresar id\n->");
        String id = sc.nextLine().trim();
        boolean empleado = super.existeId(id);
        if (empleado) {
            super.actualizar(empleadoActualizado(id));
        } else {
            Util.mostrarMensajeStandarError("Empleado con el id " + id + " no existe.");
            Util.mesageEnterError();
            Util.pausarEjecucion(sc);
        }

    }

    @Override
    public void mostrarTodosEmpleados() {
        Util.mostrarMensaje("todos los empleados");
        super.mostrarTodos();
    }

    private Float ingresarSalario() {
        Float salario = null;
        try {
            Util.mostrarMensajeStandar("\nIngresar salario\n->");
            salario = sc.nextFloat();
            return salario;
        } catch (InputMismatchException exception) {
            System.err.print("Salario ingresado no valido.");
            Util.mesageEnterError();
            Util.pausarEjecucion(sc);
            System.out.println();
            ingresarSalario();
        }
        return salario;
    }


}
