package empleados;

public interface IEmpleado {
    void agregar(Empleado empleado);

    void actualizar(Empleado empleado);

    void eliminar(String id);

    void mostrarTodos();

    boolean existeId(String id);
}
