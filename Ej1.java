package Ejercicios;
import java.util.Scanner;
public class Ej1 {
	private static Scanner input=new Scanner(System.in);
	
	final static Double PRECIO_MEDIALUNA=30.00;
	final static Double PRECIO_CHIPA=50.00;
	final static Double PRECIO_ROSCA=350.00;
	final static Double PRECIO_ALFAJOR=70.00;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int dni, cantVentas=0;
		Double totalCompra, montoMayorCompra=0.0, recaudacionDiaria=0.0;
		Double promedioMontoVenta;
		String mayorCompra="";
		
		bienvenida();
		dni=ingresoDni();
		
		do{
			listarPrecios(PRECIO_MEDIALUNA, PRECIO_CHIPA, PRECIO_ROSCA, PRECIO_ALFAJOR);
			totalCompra=tomarPedido();
			cantVentas+=1;
			recaudacionDiaria+=totalCompra;
			montoMayorCompra=obtenerMayorCompra(totalCompra, montoMayorCompra);
			mayorCompra=outMayorCompra(dni, totalCompra, montoMayorCompra, mayorCompra);
			dni=ingresoDni();
		}while (dni!=0);
		
		promedioMontoVenta=obtenerPromedio(recaudacionDiaria, cantVentas);
		despedida(cantVentas, recaudacionDiaria, promedioMontoVenta, mayorCompra);
		
		input.close();
	}
	
	public static void bienvenida() {
		System.out.println("Bienvenido!");
	}
	public static int ingresoDni() {
		int dni;
		boolean dniValido;
		
		System.out.println("");
		do {
			System.out.println("Ingrese dni del cliente (0 para terminar)");
			dni=Integer.parseInt(input.nextLine());
			dniValido= dni>=0;
		}while(!dniValido);
		return dni;
	}
	public static void listarPrecios(Double medialuna, Double chipa, Double rosca, Double alfajor) {
		System.out.println("Medialuna: $"+ medialuna);
		System.out.println("Chipa: $"+ chipa);
		System.out.println("Rosca: $"+ rosca);
		System.out.println("Alfajor: $"+ alfajor);
	}
	public static Double tomarPedido() {
		int cantProducto;
		Double precioUnitario, importeXProd, total=0.0;
		boolean salida;
		String nombreProducto;
		do {
			nombreProducto=pedirNombreProd();
			cantProducto=pedirCantidad();
			precioUnitario=obtenerPrecioUnitario(nombreProducto);
			importeXProd=obtenerImporte(precioUnitario, cantProducto);
			total+=importeXProd;
			salida=salida();
		}while(!salida);
		return total;
	}
	public static String pedirNombreProd() {
		String producto;
		boolean prodValido;
		do {
			System.out.println("Ingrese el producto:");
			producto=input.nextLine();
			prodValido=producto.equalsIgnoreCase("medialuna") || producto.equalsIgnoreCase("chipa") || producto.equalsIgnoreCase("rosca") || producto.equalsIgnoreCase("alfajor");
		}while (!prodValido);
		return producto;
	}
	public static int pedirCantidad() {
		int cantProd;
		boolean cantValida;
		do {
			System.out.println("Ingrese cantidad:");
			cantProd=Integer.parseInt(input.nextLine());
			cantValida= cantProd>0;
		}while (!cantValida);
		return cantProd;
	}
	public static Double obtenerPrecioUnitario(String producto) {
		
		switch (producto) {
		case "medialuna": return PRECIO_MEDIALUNA;
		case "chipa": return PRECIO_CHIPA;
		case "rosca": return PRECIO_ROSCA;
		default: return PRECIO_ALFAJOR;
		}
	}
	public static Double obtenerImporte(Double precioUnitario, int cantProducto) {
		Double importe;
		importe=precioUnitario*cantProducto;
		return importe;
	}
	public static boolean salida() {
		boolean salir;
		boolean entradaValida;
		String siONo;
		do {
			System.out.println("Desea cargar otro producto? (S/N)");
			siONo= input.nextLine();
			entradaValida= siONo.equalsIgnoreCase("s") || siONo.equalsIgnoreCase("n");
		}while (!entradaValida);
		salir= siONo.equalsIgnoreCase("n");
		
		return salir;
	}
	public static Double obtenerPromedio(Double monto, int cantidad) {
		Double promedio;
		promedio=monto/cantidad;
		
		return promedio;
	}
	public static Double obtenerMayorCompra(Double totalCompra, Double mayorCompra) {
		if (totalCompra>mayorCompra) {
			mayorCompra=totalCompra;
		}
		return mayorCompra;
	}
	public static String outMayorCompra(int dni, Double totalCompra, Double montoMayorCompra, String mayorCompra) {
		
		if (totalCompra==montoMayorCompra) {
			mayorCompra="Importe y DNI cliente mejor compra: $"+ montoMayorCompra+ " "+ dni;
		}
		return mayorCompra;
	}
	public static void despedida(int cantVentas, Double recaudacionDiaria, Double promedioMontoVenta, String mayorVenta) {
		System.out.println("Ingreso terminado");
		System.out.println("");
		System.out.println("Cantidad de ventas realizada en el dia: "+ cantVentas);
		System.out.println("Recaudacion diaria: "+ recaudacionDiaria);
		System.out.println("Venta promedio: $"+ promedioMontoVenta);
		System.out.println(mayorVenta);
	}
}
