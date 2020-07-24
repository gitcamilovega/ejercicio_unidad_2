

import java.util.Scanner;

public class Util {

    public static void pausarEjecucion(Scanner sc) {
        sc.nextLine();
    }

    public static void mesageEnterError() {
        System.err.append("\'presionar enter para continuar\'");
    }

    public static void mesageEnter() {
        System.out.append("\'presionar enter para continuar\'");
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println("-------" + mensaje + "------\n");
    }

    public static void mostrarMensajeStandar(String mensaje) {
        System.out.print(mensaje);
    }

    public static void mostrarMensajeStandarError(String mensaje) {
        System.err.print(mensaje);
    }
}
