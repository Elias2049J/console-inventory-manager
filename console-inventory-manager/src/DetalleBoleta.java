public class DetalleBoleta {
    private int cant;
    private String modelo;
    private double precio;

    public DetalleBoleta(int cant, String modelo, double precio) {
        this.cant = cant;
        this.modelo = modelo;
        this.precio = precio;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
