package menu;

import Utilidades.Util;
import empleados.Captar;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu extends Captar {
    private static Scanner sc = new Scanner(System.in);
    private static int entrada;

    public Menu() {
        super();
    }

    public void ver() {
        System.out.println("----------Menú------------------");
        System.out.println("1.Agregar empleado\t\t\t\t|");
        System.out.println("2.Eliminar empleado\t\t\t\t|");
        System.out.println("3.Actualizar empleado\t\t\t|");
        System.out.println("4.Mostrar todos los empleados\t|");
        System.out.println("5.Salir\t\t\t\t\t\t\t|");
        System.out.println("--------------------------------");
        captar();
    }


    private void captar() {
        try {
            System.out.print("Ingresar valor: ");
            entrada = sc.nextInt();
            sc.nextLine();
            if (entrada > 0 && entrada < 6) {
                opciones(entrada);
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException exception) {
            Util.mostrarMensajeStandarError("Solo números entre (1-5),presionar enter para continuar");
            Util.mesageEnterError();
            Util.pausarEjecucion(sc);
            sc.nextLine();
            ver();
        }
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
                System.exit(0);
                break;
        }
    }
}
