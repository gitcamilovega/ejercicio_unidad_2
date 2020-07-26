import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class OperacionesEmpleado implements IOperacionesEmpleado {
    private Scanner sc = new Scanner(System.in);
    private EmpleadoDAO empleadoDAO;

    public OperacionesEmpleado() {
        super();
        empleadoDAO = new EmpleadoDAO();
        empleadoDAO.agregar(new Empleado("1", "edinson", "Salamanca", (float) 41));
        empleadoDAO.agregar(new Empleado("2", "leonardo", "Saravia", (float) 45));
        empleadoDAO.agregar(new Empleado("3", "zoraida", "camargo", (float) 450000000));
        empleadoDAO.agregar(new Empleado("4", "alicia", "camacho", (float) 450));
        empleadoDAO.agregar(new Empleado("5", "aldemar", "sadoval", (float) 450));
        empleadoDAO.agregar(new Empleado("6", "lucas", "sanabria", (float) 42));
    }


    @Override
    public void agregarEmpleado() {
        Util.mostrarMensajePersonalizado("Agregar empleado");
        Empleado empleado = capturarEmpleado();
        if (!empleadoDAO.existeId(empleado.getId())) {
            empleadoDAO.agregar(empleado);
        } else {
            Util.mostrarMensajeStandarError("id " + empleado.getId() +
                    " existe, por lo tanto no se puede guardar él empleado.");
            Util.mesageEnterError();

        }

    }

    private Empleado capturarEmpleado() {
        Util.mostrarMensajeStandar("\nIngresar id\n->");
        String id = sc.nextLine().trim().substring(0, 1).toUpperCase();
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
        Util.mostrarMensajePersonalizado("Eliminar empleado");
        Util.mostrarMensajeStandar("Ingresar id: ");
        String id = sc.nextLine();
        if (!empleadoDAO.existeId(id)) {
            Util.mostrarMensajeStandarError("id " + id + ", no existe.");
            Util.mesageEnterError();
        } else {
            empleadoDAO.eliminar(id);
        }

    }

    @Override
    public void actualizarEmpleado() {
        Util.mostrarMensajePersonalizado("Actualizar empleado");
        Util.mostrarMensajeStandar("\nIngresar id\n->");
        String id = sc.nextLine().trim();
        boolean empleado = empleadoDAO.existeId(id);
        if (empleado) {
            empleadoDAO.actualizar(empleadoActualizado(id));
        } else {
            Util.mostrarMensajeStandarError("Empleado con el id " + id + " no existe.");
            Util.mesageEnterError();
        }

    }

    @Override
    public void mostrarTodosEmpleados() {
        Util.mostrarMensajePersonalizado("todos los empleados");
        empleadoDAO.mostrarTodos();
    }

    @Override
    public void empleadoMayorSalario() {
        Optional<Empleado> mayorSalario = empleadoDAO.getEmpleadoMayorSalario();
        if (mayorSalario.isPresent()) {
            Util.mostrarMensajePersonalizado("Empleado con el mayor salario");
            Util.mostrarMensajeStandar(mayorSalario.get().toString() + "\n");
            Util.mesageEnter();
        } else {
            Util.mostrarMensajeNingunEmpleado();
        }
    }

    @Override
    public void empleadoMenorSalario() {
        Optional<Empleado> menorSalario = empleadoDAO.getEmpleadoMenorSalario();
        if (menorSalario.isPresent()) {
            Util.mostrarMensajePersonalizado("Empleado con el menor salario");
            menorSalario.stream()
                    .forEach(System.out::println);
            Util.mesageEnter();
        } else {
            Util.mostrarMensajeNingunEmpleado();
        }
    }

    @Override
    public void empleadosOrdenarPorNombres() {
        Util.mostrarMensajePersonalizado("Empleados ordenados por nombre");
        empleadoDAO.ordenarPorNombre();
        empleadoDAO.mostrarTodos();
        Util.mesageEnter();
    }

    @Override
    public void empleadosSalarioTotalSuma() {
        if (empleadoDAO.hayEmpleados()) {
            Optional<Float> sumSalarios = empleadoDAO.sumarTodosSalarios();
            if (sumSalarios.isPresent()) {
                Util.mostrarMensajePersonalizado("Suma total de los salarios mayores a 700000");
                Util.mostrarMensajeStandar(String.format("Total = %.4f\n", sumSalarios.get()));
                Util.mesageEnter();
            } else {
                Util.mostrarMensajeStandarError("¡Ningún empleado tiene salarios mayores 700000!.");
                Util.mesageEnterError();
            }
        } else {
            Util.mostrarMensajeNingunEmpleado();
        }
    }

    @Override
    public void getTotalEmpleadosPorS() {
        if (empleadoDAO.hayEmpleados()) {
            Util.mostrarMensajePersonalizado("Total de empleados por la letra \'S\'");
            Long total = empleadoDAO.getTotalEmpleadosPorS();
            Util.mostrarMensajeStandar(String.format("Total =  %d\n", total));
            Util.mesageEnter();
        } else {
            Util.mostrarMensajeNingunEmpleado();
        }
    }

    @Override
    public void get5PrimerosConMayorSalario() {
        Util.mostrarMensajePersonalizado("Los 5 primeros empleados con mayor salario\t");
        if (empleadoDAO.hayEmpleados()) {
            empleadoDAO.get5PrimerosConMayorSalario()
                    .forEach(System.out::println);
            Util.mesageEnter();
        } else {
            Util.mostrarMensajeNingunEmpleado();
        }
    }

    private Float ingresarSalario() {
        Float salario = null;
        try {
            Util.mostrarMensajeStandar("\nIngresar salario\n->");
            salario = sc.nextFloat();
            return salario;
        } catch (InputMismatchException exception) {
            Util.mostrarMensajeStandar("Salario ingresado no valido.");
            Util.mesageEnterError();
            System.out.println();
            ingresarSalario();
        }
        return salario;
    }


}
