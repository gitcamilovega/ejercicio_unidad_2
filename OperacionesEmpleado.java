import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class OperacionesEmpleado implements IOperacionesEmpleado {
    private Scanner sc = new Scanner(System.in);
    private EmpleadoDAO empleadoDAO;

    public OperacionesEmpleado() {
        super();
        empleadoDAO = new EmpleadoDAO();
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
        String nombre = Util.getMayuscula(sc.nextLine()).trim();

        Util.mostrarMensajeStandar("\nIngresar apellido\n->");
        String apellido = Util.getMayuscula(sc.nextLine());

        Float salario = ingresarSalario();
        sc.nextLine();
        return new Empleado(id, nombre, apellido, salario);
    }

    private Empleado empleadoParaActualizar(String id) {
        Util.mostrarMensajeStandar("\nIngresar nombre\n->");
        String nombre = Util.getMayuscula(sc.nextLine()).trim();

        Util.mostrarMensajeStandar("\nIngresar apellido\n->");
        String apellido = Util.getMayuscula(sc.nextLine()).trim();
        Float salario = ingresarSalario();
        sc.nextLine();
        return new Empleado(id, nombre, apellido, salario);
    }

    @Override
    public void eliminarEmpleado() {
        Util.mostrarMensajePersonalizado("Eliminar empleado");
        empleadoDAO.mostrarTodos();
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
        empleadoDAO.mostrarTodos();

        Util.mostrarMensajeStandar("\nIngresar id\n->");
        String id = sc.nextLine().trim();
        boolean empleado = empleadoDAO.existeId(id);
        if (empleado) {
            empleadoDAO.actualizar(empleadoParaActualizar(id));
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
        if (empleadoDAO.hayEmpleados()) {
            Util.mostrarMensajePersonalizado("Empleados ordenados por nombre");
            empleadoDAO.ordenarPorNombre();
            empleadoDAO.mostrarTodos();
            Util.mesageEnter();
        } else {
            Util.mostrarMensajeNingunEmpleado();
        }
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
    public void totalApellidosPorA() {
        if (empleadoDAO.hayEmpleados()) {
            Util.mostrarMensajePersonalizado("Total Apellidos que empiezan por la letra \'A\'");
            Long total = empleadoDAO.getTotalEmpleadosPorA();
            Util.mostrarMensajeStandar(String.format("Total =  %d\n", total));
            Util.mesageEnter();
        } else {
            Util.mostrarMensajeNingunEmpleado();
        }
    }

    @Override
    public void Primeros5ConMayorSalario() {
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
