package integrado.prog2.entities;

import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;
import integrado.prog2.interfaces.Calculable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends Base implements Calculable {
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private List<DetallePedido> detalles;
    private Usuario usuario;
    private static Long contadorDetalles = 1L; // Simulador de IDs para Detalles

    public Pedido(Long id, FormaPago formaPago) {
        super(id);
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.formaPago = formaPago;
        this.total = 0.0;
        this.detalles = new ArrayList<>();
    }

    @Override
    public void calcularTotal() {
        this.total = 0.0;
        for (DetallePedido dp : detalles) {
            this.total += dp.getSubtotal();
        }
    }

    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido nuevoDetalle = new DetallePedido(contadorDetalles++, cantidad, producto);
        this.detalles.add(nuevoDetalle);
        calcularTotal(); // Se recalcula el total al agregar
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        for (DetallePedido dp : detalles) {
            if (dp.getProducto().getId().equals(producto.getId())) {
                return dp;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido dp = findDetallePedidoByProducto(producto);
        if (dp != null) {
            detalles.remove(dp);
            calcularTotal(); // Se recalcula el total al borrar
        }
    }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public Double getTotal() { return total; }
    public FormaPago getFormaPago() { return formaPago; }
    public void setFormaPago(FormaPago formaPago) { this.formaPago = formaPago; }
    public List<DetallePedido> getDetalles() { return detalles; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public String toString() {
        return String.format("Pedido [ID: %d, Fecha: %s, Estado: %s, Total: $%.2f, Forma Pago: %s]", 
                             getId(), fecha.toString(), estado, total, formaPago);
    }
}