
import java.util.*;

public class EmpleadoDAO implements IEmpleado {
    //private Map<String, Empleado> empleados = new HashMap<>();
    private List<Empleado> empleados = new ArrayList<>();

    @Override
    public void agregar(Empleado empleado) {
        empleados.add(empleado);
        System.out.print("¡Registro exitoso!.");
        Util.mesageEnter();
        Util.pausarEjecucion(new Scanner(System.in));
    }

    @Override
    public void actualizar(Empleado empleado) {
        empleados.stream()
                .filter(empleado1 -> empleado1.getId().equals(empleado.getId()))
                .forEach(empleado1 -> {
                    empleado1.setNombre(empleado.getNombre());
                    empleado1.setApellido(empleado.getApellido());
                    empleado1.setSalario(empleado.getSalario());
                });
        Util.mostrarMensajeStandar("Actualización exitosa.");
        Util.mesageEnter();
        Util.pausarEjecucion(new Scanner(System.in));
    }

    private boolean hayEmpleados() {
        return !empleados.isEmpty();
    }

    @Override
    public void eliminar(String id) {
        empleados.remove(getEmpleado(id).get());
        Util.mostrarMensajeStandar("Empleado eliminado.");
        Util.mesageEnter();
        Util.pausarEjecucion(new Scanner(System.in));
    }


    @Override
    public void mostrarTodos() {
        if (hayEmpleados()) {
            empleados.stream()
                    .forEach(empleado -> System.out.println(empleado.toString()));
            Util.mesageEnter();
            Util.pausarEjecucion(new Scanner(System.in));
        } else {
            System.err.print("¡Ningún empleado resgistrado!.");
            Util.mesageEnterError();
            Util.pausarEjecucion(new Scanner(System.in));
        }
    }

    @Override
    public boolean existeId(String id) {
        Optional<Empleado> existeEmpleado = getEmpleado(id);
        return existeEmpleado.isPresent() ? true : false;
    }

    public Optional<Empleado> getEmpleado(String id) {
        return empleados.stream()
                .filter(empleado -> empleado.getId().equals(id))
                .findFirst();
    }
}
