

import java.util.Scanner;

public class Util {

    public static void pausarEjecucion(Scanner sc) {
        sc.nextLine();
    }

    public static void mesageEnterError() {
        System.err.append("\'presionar enter para continuar\'");
        pausarEjecucion(new Scanner(System.in));
    }

    public static void mesageEnter() {
        System.out.append("\'presionar enter para continuar\'");
        pausarEjecucion(new Scanner(System.in));
    }

    public static void mostrarMensajePersonalizado(String mensaje) {
        System.out.println("\n\n-------" + mensaje + "------\n");
    }

    public static void mostrarMensajeStandar(String mensaje) {
        System.out.print(mensaje);
    }

    public static void mostrarMensajeNingunEmpleado() {
        mostrarMensajeStandarError("¡Ningún empleado resgistrado!.\n");
        mesageEnterError();
    }

    public static String getMayuscula(String cadena) {
        return cadena.toUpperCase();
    }

    public static void mostrarMensajeStandarError(String mensaje) {
        System.err.print(mensaje);
    }
}
