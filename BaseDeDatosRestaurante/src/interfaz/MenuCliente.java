package interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import jdbc.DBManager;
import jdbc.JDBCManager;
import jpa.ManagerJPA;
import jpa.UsuarioManager;
import logging.MyLogger;
import pojos.Clientes;
import pojos.Menus;
import pojos.Pedidos;
import pojos.Usuario;

public class MenuCliente {
	
	final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static DBManager dbman = new JDBCManager();
	private static ManagerJPA userman = new UsuarioManager();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private final static DateTimeFormatter formatterSesion = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static void main(Clientes usuario) {
		try {
			MyLogger.setup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbman.connect();
		userman.connect();
		int respuesta = 0;
		do {
			System.out.println("\nElige una opci?n:");
			System.out.println("1. Mostrar informacion del usuario");
			System.out.println("2. Modificar informacion del usuario");
			System.out.println("3. Visualizar Menu");
			System.out.println("4. Hacer pedido");
			System.out.println("5. Eliminar cuenta");
			System.out.println("0. Salir");


			try {
				respuesta=Integer.parseInt(reader.readLine());
				LOGGER.info("El usuario elige " + respuesta);
			} catch  (NumberFormatException | IOException e) {
				respuesta = -1;
				LOGGER.warning("El usuario no ha introducido un n?mero");
				e.printStackTrace();
			}
			switch(respuesta) {
				case 1: 
					System.out.println(usuario);
					break;
				case 2:
					actualizarCliente(usuario);
					System.out.println("Se tiene que reiniciar la app para guardar los cambios....");
					System.exit(0);

				case 3:
					mostrarMenu();
					break;
				case 4:
					a?adirAlPedido(usuario);
					break;
				case 5: 
					deleteCliente(usuario);
					respuesta = 0;
					break;
			}
 			
		} while (respuesta != 0);
		dbman.disconnect();
		}
	
	private static void deleteCliente(Clientes usuario) {
		try {
			System.out.println("Esta seguro que quiere eliminar esta cuenta");
			System.out.println(usuario);
			System.out.println("?Confirmar borrado?(s/n)");
			String respuesta = reader.readLine();
			if(respuesta.equalsIgnoreCase("s")) {
				String nombreCliente = usuario.getNombre();
				String email = usuario.getEmail();
				boolean existeCliente = dbman.eliminarCliente(nombreCliente);
				if(existeCliente) {
					Usuario user = userman.searchUsuario(email);
					userman.deleteUsuario(user);
					System.out.println("El Cliente se ha borrado con ?xito");
				} else {
					System.out.println("Ha habido un error al intentar eliminar el cliente");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void actualizarCliente(Clientes usuario){
		try {			
			String nombre = usuario.getNombre();
			Usuario user = userman.searchUsuario(usuario.getEmail());
			System.out.println("Esta actualizando informacion de cliente:");
			System.out.println(nombre);
			System.out.println("Nuevo Telefono: ");
			int telefono = Integer.parseInt(reader.readLine());
			System.out.println("Nuevo Email: ");
			String email = reader.readLine();
			dbman.actualizarCliente(nombre, email, telefono);
			userman.updateUsuario(user, email);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void mostrarMenu() {
		List<Menus> menus = dbman.searchMenu();
		System.out.println("\nMenu: \n");
		for(Menus menu : menus) {
			System.out.println(menu);
		}
	}
	
	private static void a?adirAlPedido(Clientes usuario) {
		int respuesta = 1;
		ArrayList<Menus> menus = new ArrayList<Menus>();
		
		float coste = 0;
		String direccion;
		LocalDate hora = LocalDate.now();
		direccion = " ";
		Pedidos pedido = new Pedidos(usuario.getId(), coste, direccion, Date.valueOf(hora), 3);
		 while (respuesta == 1) {
					mostrarMenu();
			System.out.println("Seleccione el nombre de un plato: ");
			try {
				String plato = reader.readLine();
				Menus menu = dbman.searchMenuByNombre(plato);
				System.out.println(menu);
				menus.add(menu);
				coste = coste + menu.getPrecio();
				System.out.println(coste);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				System.out.println("Desea pedir algo mas?(SI = 1 / NO = 0)");
				try {
					respuesta = Integer.parseInt(reader.readLine());
					if (respuesta == 0) {
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
					}
				}
		System.out.println("Introduzca una direcci?n de entrega: \n");
		try {
			direccion = reader.readLine();
			pedido.setDireccion(direccion);
			pedido.setCoste(coste);
			//System.out.println("Indutroduzca la Hora: ");
			//LocalDate time = LocalDate.now();
			//hora = LocalDate.parse(time, formattertime);
			//pedido = new Pedidos(usuario.getId(),coste,direccion,Date.valueOf(time), 3, menus);
			dbman.addPedido(pedido);
			pedido = dbman.searchUltimoPedido();
			for(int i=0; i<menus.size(); i++) {
					dbman.addPedido_Menu(pedido, menus.get(i));
			}
			pedido.setMenu(menus);
			System.out.println(pedido);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	
}



