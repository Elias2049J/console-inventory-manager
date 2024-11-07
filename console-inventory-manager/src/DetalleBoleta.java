public class DetalleBoleta {
    private final int cant;
    private final String modelo;
    private final double precio;

    public DetalleBoleta(int cant, String modelo, double precio) {
        this.cant = cant;
        this.modelo = modelo;
        this.precio = precio;
    }

    public int getCant() {
        return cant;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio() {
        return precio;
    }
}
