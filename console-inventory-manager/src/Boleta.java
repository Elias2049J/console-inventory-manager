import java.util.Date;
import java.util.List;

public class Boleta {
    private String codigo;
    private Date fecha;
    private String dni;
    private String nombreCliente;
    private List<DetalleBoleta> detalleBoletaList;

    public Boleta(String codigo, Date fecha, String dni, String nombreCliente, List<DetalleBoleta> detalleBoletaList) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.detalleBoletaList = detalleBoletaList;
    }

    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public Date getFecha(){
        return fecha;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

    public String getDni(){
        return dni;
    }

    public void setDni(String dni){
        this.dni = dni;
    }

    public String getNombreCliente(){
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente){
        this.nombreCliente = nombreCliente;
    }

    public List<DetalleBoleta> getDetalleBoletaList() {
        return detalleBoletaList;
    }

    public void setDetalleBoletaList(List<DetalleBoleta> detalleBoletaList) {
        this.detalleBoletaList = detalleBoletaList;
    }
}

