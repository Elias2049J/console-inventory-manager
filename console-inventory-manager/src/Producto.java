public class Producto {
    private int codigo;
    private String modelo;
    private String color;
    private int talla;
    private double precio;
    private int stock;

    public Producto(int codigo, String modelo, String color, int talla, double precio, int stock) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.color = color;
        this.talla = talla;
        this.precio = precio;
        this.stock = stock;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public void reducirStock(int cantidadAVender) {
        if (this.stock >= cantidadAVender) {
            this.stock -= cantidadAVender;
        } else {
            System.out.println("OPERACION INVÁLIDA, NO HAY SUFICIENTE STOCK");
        }
    }
}


