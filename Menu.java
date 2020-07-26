
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu extends OperacionesEmpleado {
    private static Scanner sc = new Scanner(System.in);
    private static int entrada;

    public Menu() {
        super();
    }

    public void ver() {
        System.out.println("----------Menú------------------------------------------");
        System.out.println("1.Agregar empleado\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("2.Eliminar empleado\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("3.Actualizar empleado\t\t\t\t\t\t\t\t\t|");
        System.out.println("4.Mostrar todos los empleados\t\t\t\t\t\t\t|");
        System.out.println("5.Empleado con mayor salario\t\t\t\t\t\t\t|");
        System.out.println("6.Empleado con menor salario\t\t\t\t\t\t\t|");
        System.out.println("7.Ordenar empleados por nombres\t\t\t\t\t\t\t|");
        System.out.println("8.Sumar todos los salarios, mayores a 700000\t\t\t|");
        System.out.println("9.Total de empleados cuyo apellido comienza con A\t\t|");
        System.out.println("10.Los 5 primeros empleados con mayor salario\t\t\t|");
        System.out.println("0.Salir\t\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("--------------------------------------------------------");
        captar();
    }


    private void captar() {
        try {
            System.out.print("Ingresar valor: ");
            entrada = sc.nextInt();
            sc.nextLine();
            if (estaEnElRango(entrada)) {
                opciones(entrada);
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException exception) {
            Util.mostrarMensajeStandarError("Solo números entre (1-5),presionar enter para continuar");
            Util.mesageEnterError();
            sc.nextLine();
            ver();
        }
    }

    private boolean estaEnElRango(int entrada) {
        return entrada >= 0 && entrada <= 10;
    }

    private void opciones(int opcion) {
        switch (opcion) {
            case 1:
                super.agregarEmpleado();
                ver();
                break;
            case 2:
                super.eliminarEmpleado();
                ver();
                break;
            case 3:
                super.actualizarEmpleado();
                ver();
                break;
            case 4:
                super.mostrarTodosEmpleados();
                ver();
                break;
            case 5:
                empleadoMayorSalario();
                ver();
                break;
            case 6:
                empleadoMenorSalario();
                ver();
                break;
            case 7:
                empleadosOrdenarPorNombres();
                ver();
                break;
            case 8:
                empleadosSalarioTotalSuma();
                ver();
                break;
            case 9:
                totalApellidosPorA();
                ver();
                break;
            case 10:
                Primeros5ConMayorSalario();
                ver();
                break;
            case 0:
                System.exit(0);
                break;
        }
    }
}
