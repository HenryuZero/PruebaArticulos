// Este programa usara lo aprendido, try catch, validaciones, metodos y funciones
// Este programa usara el metodo de Insertar un producto, se guarde en un arreglo
// Este mismo imprimira todos los articulos recibidos

import ArticuloDTO.ArticuloDTO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PruebaArticulos {
  public enum Mensajes {
    MENSAJE_REVISAR_ARTICULO("¿Qué artículo desea revisar?"),
    MENSAJE_ERROR("Error, favor de introducir valores válidos"),
    MENSAJE_INSERCION("Número de Productos a Introducir: "),
    MENSAJE_LISTA_VACIA("No existe lista de productos");

    private final String MESSAGE_CONTENT;

    Mensajes(String MESSAGE_CONTENT) {
      this.MESSAGE_CONTENT = MESSAGE_CONTENT;
    }

    public String getMESSAGE_CONTENT() {
      return MESSAGE_CONTENT;
    }
  }

  static Scanner in = new Scanner(System.in);
  static int contador = 0;
  static ArticuloDTO productos;
  static ArrayList<ArticuloDTO> articulos = new ArrayList<>();
  public static final String REGEX_LETRAS = "[a-zA-Z\\s]*";

  public static void main(String[] args) {
    Inicio();
  }

  private static void Inicio() {
    int eleccion = 0;
    System.out.println("Bienvenido. Elija la opción que desee realizar");
    System.out.println(Menu.OPCION_1.getNUMBEROPT() + Menu.OPCION_1.getNAMEOPT());
    System.out.println(Menu.OPCION_2.getNUMBEROPT() + Menu.OPCION_2.getNAMEOPT());
    System.out.println(Menu.OPCION_3.getNUMBEROPT() + Menu.OPCION_3.getNAMEOPT());
    System.out.println(Menu.OPCION_4.getNUMBEROPT() + Menu.OPCION_4.getNAMEOPT());
    System.out.println(Menu.OPCION_5.getNUMBEROPT() + Menu.OPCION_5.getNAMEOPT());
    try {
      eleccion = in.nextInt();
      switch (eleccion) {
        case 1:
          InsercionProductos();
          break;
        case 2:
          ImprimirProductos();
          break;
        case 3:
          Comprar();
          break;
        case 4:
          Existencia();
          break;
        case 5:
          System.exit(0);
        default:
          System.out.println("Favor de introducir un número válido");
          Inicio();
          break;
      }
    } catch (InputMismatchException e) {
      System.out.println(Mensajes.MENSAJE_ERROR.getMESSAGE_CONTENT());
      in.nextLine();
    }
    if (eleccion >= 0) {
      Inicio();
    }
  }

  private static void ImprimirProductos() {
    if (articulos.size() != 0) {
      printProductos(articulos);
    } else {
      System.out.println(Mensajes.MENSAJE_LISTA_VACIA.getMESSAGE_CONTENT());
    }
  }

  private static void printProductos(ArrayList<ArticuloDTO> Muestra) {
    Muestra.forEach(
        ProductoDTO -> {
          System.out.println("===============================");
          System.out.println(ProductoDTO);
          System.out.println("===============================");
        });
  }

  private static void InsercionProductos() {
    String pedidoName, pedidoBrand, cosaName, cosaBrand;
    int id = -1;
    System.out.println(Mensajes.MENSAJE_INSERCION.getMESSAGE_CONTENT());
    try {
      contador = in.nextInt();
      in.nextLine();
      for (int i = 0; i < contador; i++) {
        productos = new ArticuloDTO();
        Nombre();
        Marca();
        Tipo();
        Precio();
        Disponibilidad();
        in.nextLine();
        pedidoName = productos.getName();
        pedidoBrand = productos.getBrand();
        for (int j = 0; j < articulos.size(); j++) {
          cosaName = articulos.get(j).getName();
          cosaBrand = articulos.get(j).getBrand();
          if (pedidoName.trim().compareTo(cosaName.trim()) == 0 && pedidoBrand.trim().compareTo(cosaBrand.trim()) == 0) {
            id = j;
          }
        }
        if (id == -1) {
          articulos.add(productos);
        } else {
          articulos.set(id,productos);
        }
       // articulos.add(productos);
      }
    } catch (InputMismatchException e) {
      System.out.println(Mensajes.MENSAJE_ERROR.getMESSAGE_CONTENT());
      in.nextLine();
    }
    if (contador <= 0) {
      InsercionProductos();
    }
  }

  private static void Disponibilidad() {
    System.out.print("¿Cuántos productos hay?: ");
    try {
      in.nextLine();
      productos.setCantidad(in.nextInt());
      if (productos.getCantidad() == 0) {
        productos.setDisponible(false);
      } else if (productos.getCantidad() > 0) {
        productos.setDisponible(true);
      }
      // in.nextLine();

    } catch (InputMismatchException e) {
      System.out.println(Mensajes.MENSAJE_ERROR.getMESSAGE_CONTENT());
    }
    if (productos.getCantidad() < 0) {
      System.out.println("Favor de usar cantidades validas");
      Disponibilidad();
    }
  }

  private static void Precio() {
    System.out.print("Precio: ");
    try {
      productos.setPrecio(in.nextDouble());
    } catch (InputMismatchException e) {
      System.out.println(Mensajes.MENSAJE_ERROR.getMESSAGE_CONTENT());
      in.nextLine();
    }
    if (productos.getPrecio() == null) {
      System.out.println("Favor de usar numeros para el precio");
      Precio();
    }
  }

  private static void Tipo() {
    do {
      System.out.print("Tipo de Producto (Comida, Electrodomestico, Limpieza, etc): ");
      productos.setTipo(in.nextLine());
    } while (!productos.getTipo().matches(REGEX_LETRAS));
  }

  private static void Marca() {
    do {
      System.out.print("Marca del Producto: ");
      productos.setBrand(in.nextLine());
    } while (!productos.getBrand().matches(REGEX_LETRAS));
  }

  private static void Nombre() {
    do {
      System.out.print("Nombre del Producto: ");
      productos.setName(in.nextLine());
    } while (!productos.getName().matches(REGEX_LETRAS));
  }

  private static void Comprar() {
    String Pedido, Cosa;
    int id = -1;
    ArticuloDTO muestra;
    if (articulos.size() != 0) {
      System.out.println(Mensajes.MENSAJE_REVISAR_ARTICULO.getMESSAGE_CONTENT());
      in.nextLine();
      Pedido = in.nextLine();

      for (int i = 0; i < articulos.size(); i++) {
        // muestra=Articulos.get(i);
        Cosa = articulos.get(i).getName();
        // System.out.println(Pedido);
        // System.out.println("-"+ Articulos.get(i).toString());
        // System.out.println(Pedido.equalsIgnoreCase(Articulos.get(i).getName()));
        if (Pedido.trim().compareTo(Cosa.trim()) == 0) {
          //                   System.out.println(" "+Cosa);
          id = i;
        }
      }
      if (id == -1) {
        System.out.println("Producto no existe");
      } else {
        muestra = articulos.get(id);
        System.out.println("El costo es de: " + muestra.getPrecio());
      }
    } else {
      System.out.println(Mensajes.MENSAJE_LISTA_VACIA.getMESSAGE_CONTENT());
      return;
    }
  }

  private static void Existencia() {
    String Pedido, Cosa;
    int id = -1;
    ArticuloDTO muestra;
    if (articulos.size() != 0) {
      System.out.println(Mensajes.MENSAJE_REVISAR_ARTICULO.getMESSAGE_CONTENT());
      in.nextLine();
      Pedido = in.nextLine();
      for (int i = 0; i < articulos.size(); i++) {
        Cosa = articulos.get(i).getName();
        if (Pedido.trim().compareTo(Cosa.trim()) == 0) {
          id = i;
        }
      }
      if (id == -1) {
        System.out.println("Producto no existe");
      } else {
        muestra = articulos.get(id);
        if (muestra.isDisponible() == true) {
          System.out.println("Hay: " + muestra.getCantidad() + " disponibles");
        } else {
          System.out.println("No hay articulos disponibles");
        }
      }
    } else {
      System.out.println(Mensajes.MENSAJE_LISTA_VACIA.getMESSAGE_CONTENT());
      return;
    }
  }
}
