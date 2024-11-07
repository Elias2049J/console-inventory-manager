import java.util.Date;
import java.util.List;

public record Boleta(String codigo, Date fecha, String dni, String nombreCliente,
                     List<DetalleBoleta> detalleBoletaList) {

}

