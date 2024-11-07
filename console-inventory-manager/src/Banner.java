//SE IMPORTAN TODAS LAS CLASES NECESARIAS
import java.text.SimpleDateFormat;
import java.util.*;

//SE CREA UNA CLASE PÚBLICA PARA LA FUNCIONALIDAD
public class Banner {
    //SE DEFINE EL SCANNER QUE SERVIRÁ PARA TOMAR ENTRADAS DEL USUARIO
    private final Scanner sc = new Scanner(System.in);
    // SE DEFINE UNA VARIABLE datos QUE CARGA POR COMPLETO EL CONTENIDO DE LA CLASE Datos
    private static Datos datos = new Datos();
    private int opcionParaSeguir;
    static {
        datos = datos.precargarDatos();
    }

    //SE CREA UN MÉTODO PUBLICO PARA MOSTRAR EL MENÚ Y QUE SERÁ REUTILIZABLE
    public void mostrarMenu() {
        //SE DEFINE UNA ESTRUCTURA REPETITIVA (BUCLE) PARA DIBUJAR DE FORMA REPETIDA LA CONSOLA
        do {
            System.out.println("┌───────────────────────────────────────────┐");
            System.out.println("│            MENÚ PRINCIPAL                 │");
            System.out.println("├───────────────────────────────────────────┤");
            System.out.println("│ 1. Ingreso de Nuevo Stock                 │");
            System.out.println("│ 2. Eliminar  Producto del Inventario      │");
            System.out.println("│ 3. Consultar Por Código de producto       │");
            System.out.println("│ 4. Actualizar Stock                       │");
            System.out.println("│ 5. Consultar Boleta emitida, Por DNI      │");
            System.out.println("│ 6. Mostrar Todo el Inventario             │");
            System.out.println("│ 7. Salir                                  │");
            System.out.println("└───────────────────────────────────────────┘");

            while (true) {
                System.out.print("Elija una opción:");
                if (sc.hasNextInt()) {
                    //opcionParaSeguir es la variable donde se guarda la opción que ingrese el usuario
                    opcionParaSeguir = sc.nextInt();
                    if (opcionParaSeguir >= 1 && opcionParaSeguir <= 7) {
                        sc.nextLine();
                        break; // ROMPER BUCLE SI INGRESA UN NÚMERO VÁLIDO
                    } else {
                        System.out.println("Ingrese una opción válida");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese solo números enteros del 1 al 7.");
                    sc.next();
                }
            }
            //SE DEFINEN LOS CASOS DE USO
            switch (opcionParaSeguir) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    consultarProducto();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    consultarCliente();
                    break;
                case 6:
                    imprimirInventario();
                    break;
                case 7:
                    //SE SALE DEL PROGRAMA
                    System.out.println("Saliendo del Programa");
                    break;
            }
        } while (opcionParaSeguir != 7);
    }

    //MÉTODO PARA INSERTAR UN NUEVO OBJETO DENTRO DE LA ListaDeProductos
    private void registrarProducto() {
        String modelo, color;
        double precio;
        int talla, cantidad;
        System.out.println("Ingrese detalles del zapato: ");
        int codigo = datos.getListaProductos().getLast().getCodigo()+1;
        System.out.print("Modelo: ");
        modelo = sc.nextLine();
        do {
            System.out.print("Color: ");
            color = sc.nextLine();
            if (!color.matches("^\\D+$")) {
                System.out.println("EL COLOR NO PUEDE CONTENER UN NÚMERO, INTENTE DE NUEVO");
            }
        } while (!color.matches("^\\D+$"));
        while (true) {
            System.out.print("Talla: ");
            if (sc.hasNextInt()) {
                talla = sc.nextInt();
                sc.nextLine();
                if (talla >= 38 && talla <= 42) {
                    break;
                } else {
                    System.out.println("TIENE QUE INGRESAR UNA TALLA DENTRO DEL RANGO: 38 - 42");
                }
            } else {
                System.out.println("Por favor, ingrese un número entero válido para la talla.");
                sc.nextLine();
            }
        }
        System.out.print("Precio: ");
        precio = sc.nextDouble();
        sc.nextLine();
        System.out.print("Cantidad: ");
        cantidad = sc.nextInt();
        sc.nextLine();

        Producto nuevoProducto = new Producto(codigo, modelo, color, talla, precio, cantidad);
        datos.agregarZapato(nuevoProducto);

        System.out.println("Producto registrado de manera exitosa");
    }

    private void eliminarProducto() {
        boolean eliminado;
        do {
            System.out.print("Ingresa un código: ");
            while (!sc.hasNextInt()) {
                System.out.println("El código debe ser numérico, intente de nuevo:");
                sc.next();
            }
            int codigo = sc.nextInt();
            sc.nextLine();
            eliminado = datos.eliminarZapato(codigo);
            if (!eliminado) {
                System.out.println("No se encontró el producto o no se pudo eliminar, intente de nuevo:");
            }
        } while (!eliminado);
        eleccionCerrar("PRODUCTO ELIMINADO DEL INVENTARIO CON ÉXITO\nElija una opción: \n1.VOLVER AL MENÚ PRINCIPAL\n2.SALIR DEL PROGRAMA");
    }

    private void maquetaDetalle(Producto producto){
        int eleccion;
        System.out.println("┌───────────────────────────────────────────────┐");
        System.out.println("|               DETALLE DE PRODUCTO             |");
        System.out.println(" ───────────────────────────────────────────────");
        System.out.println("|       MODELO | " + completarEspacios(producto.getModelo(), 31) + "|");
        System.out.println("|        COLOR | " + completarEspacios(producto.getColor(), 31) + "|");
        System.out.println("|        TALLA | " + completarEspacios(String.valueOf(producto.getTalla()), 31) + "|");
        System.out.println("|       PRECIO | " + completarEspacios(String.valueOf(producto.getPrecio()), 31) + "|");
        System.out.println("|        CANT. | " + completarEspacios(String.valueOf(producto.getStock()), 31) + "|");
        System.out.println("|  Desea vender? DIGITE UNA OPCIÓN:             | ");
        System.out.println("|  1. SÍ         2.NO                           | ");
        System.out.println("└───────────────────────────────────────────────┘");
        eleccion = sc.nextInt();
        sc.nextLine();
        if (eleccion == 1) {
            venderProducto(producto);
        } else if (eleccion == 2){
            mostrarMenu();
        } else {
            System.out.println("ELIJA UNA OPCIÓN VÁLIDA");
            maquetaDetalle(producto);
        }
    }

    private void consultarProducto() {
        System.out.print("Ingresa codigo del Producto: ");
        int codigo = sc.nextInt();
        Producto producto = datos.consultarProducto(codigo);
        if (producto != null) {
            maquetaDetalle(producto);
        } else {
            System.out.println("Producto no encontrado, intente de nuevo");
        }
    }

    private void venderProducto(Producto producto) {
        int cantidadAVender;
        String nombreCliente;
        String dni;
        String codigoBoleta = datos.getListaBoletas().getLast().codigo();
        Date fechaActual = new Date();
        List<DetalleBoleta> carritoDeCompras = new ArrayList<>();
        boolean seguirVendiendo;

        // Bucle para agregar los detalles de la venta
        do {
            System.out.print("Cantidad que desea vender: ");
            cantidadAVender = sc.nextInt();
            sc.nextLine();
            // Verificar si hay stock
            if (cantidadAVender > producto.getStock()) {
                System.out.println("NO HAY STOCK SUFICIENTE");
            } else {
                //Crear una nueva lista
                DetalleBoleta nuevoArticulo = new DetalleBoleta(cantidadAVender, producto.getModelo(), producto.getPrecio());
                carritoDeCompras.add(nuevoArticulo);
                producto.reducirStock(cantidadAVender); // Reduce el stock a la par que se vende
            }

            // Estructura selectiva para decidir si seguir vendiendo o no
            System.out.print("¿DESEA CONTINUAR VENDIENDO? DIGITE UNA OPCIÓN\n1.SI\n2.NO\n");
            seguirVendiendo = sc.nextInt() == 1;
            sc.nextLine();
            // Si responde que sí, le pide otro código para añadir un producto a la venta
            if (seguirVendiendo) {
                System.out.print("Ingresa código del Producto: ");
                int codigo = sc.nextInt();
                sc.nextLine();
                producto = datos.consultarProducto(codigo);
                if (producto == null) {
                    System.out.println("Producto no encontrado.");
                    break;
                }
                maquetaDetalle(producto);
            }
        } while (seguirVendiendo);

        if (!carritoDeCompras.isEmpty()) {
            System.out.print("Ingrese el NOMBRE COMPLETO del comprador: ");
            nombreCliente = sc.nextLine();
            // Validación del DNI
            boolean dniValido;
            do {
                System.out.print("Ingrese el DNI del Comprador: ");
                dni = sc.nextLine();
                dniValido = dni.matches("\\d{8}"); // Verifica que sean 8 dígitos y que sean números
                if (!dniValido) {
                    System.out.println("El DNI debe tener 8 dígitos numéricos, intente de nuevo.");
                }
            } while (!dniValido);

            Boleta nuevaBoleta = new Boleta(codigoBoleta + 1, fechaActual, dni, nombreCliente, carritoDeCompras);
            datos.agregarBoleta(nuevaBoleta);
            imprimirBoleta(nuevaBoleta);
            eleccionCerrar("SU BOLETA HA SIDO IMPRESA DE MANERA EXITOSA\nElija una opción: \n1.VOLVER AL MENÚ PRINCIPAL\n2.SALIR DEL PROGRAMA");
        }
    }


    private void actualizarProducto() {
        int eleccionActualizacion;
        int codigoZapato;
        int nuevaTalla;
        String nuevoModelo, nuevoColor;
        double nuevoPrecio;
        int nuevaCantidad;
        System.out.println("Ingrese el CÓDIGO del producto que va a actualizar: ");
        codigoZapato = sc.nextInt();
        sc.nextLine();
        Producto zapato = datos.consultarProducto(codigoZapato);
        if (zapato != null) {
            System.out.println("Desea actualizar:\n1.Modelo\n2.Color\n3.Talla\n4.Precio\n5.Stock\n6.Todos los Detalles");
            eleccionActualizacion = sc.nextInt();
            switch (eleccionActualizacion) {
                case 1:
                    System.out.print("Nuevo modelo: ");
                    nuevoModelo = sc.next();
                    sc.nextLine();
                    zapato.setModelo(nuevoModelo);
                    break;
                case 2:
                    System.out.print("Nuevo color: ");
                    nuevoColor = sc.next();
                    sc.nextLine();
                    zapato.setColor(nuevoColor);
                    break;
                case 3:
                    System.out.print("Nueva talla: ");
                    nuevaTalla = sc.nextInt();
                    sc.nextLine();
                    zapato.setTalla(nuevaTalla);
                    break;
                case 4:
                    System.out.print("Nuevo precio: ");
                    nuevoPrecio = sc.nextDouble();
                    sc.nextLine();
                    zapato.setPrecio(nuevoPrecio);
                    break;
                case 5:
                    System.out.print("Nuevo stock: ");
                    nuevaCantidad = sc.nextInt();
                    sc.nextLine();
                    zapato.setStock(nuevaCantidad);
                    break;
                case 6:
                    System.out.println("Ingrese los nuevos detalles del zapato:");
                    System.out.print("Nuevo modelo: ");
                    nuevoModelo = sc.next();
                    sc.nextLine();
                    System.out.print("Nuevo color: ");
                    nuevoColor = sc.next();
                    sc.nextLine();
                    System.out.print("Nueva talla: ");
                    nuevaTalla = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo precio: ");
                    nuevoPrecio = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Nuevo stock: ");
                    nuevaCantidad = sc.nextInt();
                    sc.nextLine();

                    zapato.setModelo(nuevoModelo);
                    zapato.setColor(nuevoColor);
                    zapato.setTalla(nuevaTalla);
                    zapato.setPrecio(nuevoPrecio);
                    zapato.setStock(nuevaCantidad);
                default:
                    System.out.println("Ingrese una opción válida");
            }

            eleccionCerrar("SU ARTÍCULO DEL INVENTARIO HA SIDO ACTUALIZADO CON ÉXITO\nElija una opción: \n1.VOLVER AL MENÚ PRINCIPAL\n2.SALIR DEL PROGRAMA");
        } else {
            System.out.println("Zapato no encontrado.");
        }
    }

    private void consultarCliente() {
        //SE PIDE DNI DEL CLIENTE
        System.out.print("Ingrese DNI: ");
        String dni = sc.next();
        //BUSCA EL DNI DENTRO DE LA LISTA DE BOLETAS
        for(Boleta boleta: datos.getListaBoletas()){
            //VERIFICA SI EXISTE ESE DNI
            if(boleta.dni().equals(dni)){
                //IMPRIME LA BOLETA
                imprimirBoleta(boleta);
            }
        }
    }

    private void imprimirInventario() {
        //OBTIENE LA LISTA DE PRODUCTOS
        ArrayList<Producto> listaProductos = datos.getListaProductos();
        //VERIFICA SI LA LISTA ESTÁ VACÍA
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos para mostrar.");
        //SI NO ESTÁ VACÍA, DIBUJA LA CONSOLA
        } else {
            System.out.println("┌───────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("| COD | Modelo                         | Color                  | Talla | Precio | Cant |");
            System.out.println("└───────────────────────────────────────────────────────────────────────────────────────┘");
            //IMPRIME LOS DATOS
            for (Producto producto : listaProductos) {
                alineamientoCol(producto);
            }
            System.out.println("└───────────────────────────────────────────────────────────────────────────────────────┘");
            int cantidadListas = listaProductos.size();
            System.out.println("Tamaño de Inventario: "+cantidadListas+" | Total de Unidades en Stock:"+datos.calcularTotalStock());
            eleccionCerrar("SU INVENTARIO HA SIDO IMPRESO DE MANERA EXITOSA\nElija una opción: \n1.VOLVER AL MENÚ PRINCIPAL\n2.SALIR DEL PROGRAMA");
        }
    }

    private void imprimirBoleta(Boleta boleta){
        SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd/MM/yyyy");
        String DDMMYYYY = fechaFormateada.format(boleta.fecha());
        System.out.println(""
                .concat("┌─────────────────────────────────────────────────────────────────────────────────────────────────────┐\n")
                .concat("|    RUC:20603021640                                                                      "+DDMMYYYY+" |\n")
                .concat("|                                      EMPRESA DE ZAPATERIA ZAPATAH                                   |\n")
                .concat("|    BOLETA N° : "+completarEspacios(boleta.codigo(),85)+"|\n")
                .concat("|  DNI CLIENTE : "+completarEspacios(boleta.dni(),85)+"|\n")
                .concat("|       NOMBRE : "+completarEspacios(boleta.nombreCliente(),85)+"|\n")
                .concat("┌─────────────────────────────────────────────────────────────────────────────────────────────────────┐\n")
                .concat("| CANT | MODELO                                                                      | PRECIO U. |\n")
                .concat(" ────────────────────────────────────────────────────────────────────────────────────────────────────\n")
                .concat(detalleboleta(boleta.detalleBoletaList()))
        );
    }
    private String detalleboleta(List<DetalleBoleta> listDetBol){
        String producto = "";
        double totalpago = 0;
        for(DetalleBoleta dt: listDetBol){
            producto=producto
                    .concat("|  ").concat(completarEspacios(String.valueOf(dt.getCant()),4))
                    .concat("| ").concat(completarEspacios(dt.getModelo(),81))
                    .concat("| ").concat(completarEspacios(String.valueOf(dt.getPrecio()),10))
                    .concat("|\n");

            totalpago += totalpago+dt.getPrecio();
        }

        producto = producto
                .concat("|                                                                                  TOTAL  | "+completarEspacios(String.valueOf(totalpago),10)+"|\n")
                .concat("|──────────────────────────────────────────────────────────────────────────────────────────────────────── ");

        return producto;
    }

    private void eleccionCerrar(String x) {
        System.out.println(x);
        boolean eleccionFinal = sc.nextInt() == 1;
        if (eleccionFinal) {
            mostrarMenu();
        } else {
            opcionParaSeguir = 7;
            System.out.println("Saliendo del programa");
        }
    }

    //MÉTODO PARA ALINEAR LAS COLUMNAS TOMANDO EL MÉTODO completarEspacios
    private void alineamientoCol(Producto producto){
        System.out.println(""
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getCodigo()),4))
                .concat("| ").concat(completarEspacios(producto.getModelo(),25))
                .concat("| ").concat(completarEspacios(producto.getColor(),29))
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getTalla()),6))
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getPrecio()),7))
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getStock()),5))
                .concat("|")
        );
    }

    //MÉTODO PARA COMPLETAR LOS ESPACIOS AL DIBUJAR (TOMA DE VALORES el String y los espacios)
    private String completarEspacios(String palabra, int espacios){
        for(int i = palabra.length() ; i < espacios; i++){
            palabra=palabra.concat(" ");
        }
        return palabra;
    }
}
