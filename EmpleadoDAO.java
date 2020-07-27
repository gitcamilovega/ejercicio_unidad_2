
import java.util.*;
import java.util.stream.Stream;


public class EmpleadoDAO implements IEmpleado {
    //private Map<String, Empleado> empleados = new HashMap<>();
    private List<Empleado> empleados = new ArrayList<>();

    @Override
    public void agregar(Empleado empleado) {
        empleados.add(empleado);
        System.out.print("¡Registro exitoso!.");
        Util.mesageEnter();

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

    }

    protected boolean hayEmpleados() {
        return !empleados.isEmpty();
    }

    @Override
    public void eliminar(String id) {
        empleados.remove(getEmpleado(id).get());
        Util.mostrarMensajeStandar("Empleado eliminado.");
        Util.mesageEnter();
    }


    @Override
    public void mostrarTodos() {
        empleados.forEach(System.out::println);
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

    public Optional<Empleado> getEmpleadoMayorSalario() {
        return empleados.stream()
                .max(Comparator.comparing(Empleado::getSalario));
    }

    public Optional<Empleado> getEmpleadoMenorSalario() {
        return empleados.stream()
                .min(Comparator.comparing(Empleado::getSalario));
    }

    public void ordenarPorNombre() {
        empleados.sort(Comparator.comparing(Empleado::getNombre));
    }

    public Optional<Float> sumarTodosSalarios() {
        return empleados.stream()
                .filter(empleado -> empleado.getSalario() > 700000)
                .map(Empleado::getSalario)
                .reduce(Float::sum);
    }

    public Long getTotalEmpleadosPorA() {
        return empleados.stream()
                .filter(empleado ->
                        empleado.getApellido()
                                .toUpperCase()
                                .startsWith("A")
                )
                .count();
    }

    public Stream<Empleado> get5PrimerosConMayorSalario() {
        return empleados.stream()
                .sorted(Comparator.comparing(Empleado::getSalario))
                .limit(5);
    }


}
