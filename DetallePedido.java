package integrado.prog2.entities;

import java.util.ArrayList;
import java.util.List;

public class Categoria extends Base {
    private String nombre;
    private String descripcion;
    private List<Producto> productos;

    public Categoria(Long id, String nombre, String descripcion) {
        super(id);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto p) {
        if (p != null) {
            this.productos.add(p);
            p.setCategoria(this); // Sincronización bidireccional
        }
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<Producto> getProductos() { return productos; }

    @Override
    public String toString() {
        return String.format("Categoría [ID: %d, Nombre: %s, Descripción: %s]", getId(), nombre, descripcion);
    }
}