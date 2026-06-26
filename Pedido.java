package integrado.prog2.entities;

public class DetallePedido extends Base {
    private int cantidad;
    private Double subtotal;
    private Producto producto;

    public DetallePedido(Long id, int cantidad, Producto producto) {
        super(id);
        this.producto = producto;
        setCantidad(cantidad); // Setter para que calcule el subtotal
    }

    // Encapsulamiento con lógica de negocio (Recálculo de subtotal)
    private Double calcularSubtotal() {
        if (this.producto != null) {
            return this.cantidad * this.producto.getPrecio();
        }
        return 0.0;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }
    public Double getSubtotal() { return subtotal; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) {
        this.producto = producto;
        this.subtotal = calcularSubtotal();
    }

    @Override
    public String toString() {
        return String.format("Detalle [ID: %d, Producto: %s, Cantidad: %d, Subtotal: $%.2f]", 
                             getId(), producto.getNombre(), cantidad, subtotal);
    }
}