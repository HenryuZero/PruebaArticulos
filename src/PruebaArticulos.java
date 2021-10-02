//Este programa usara lo aprendido, try catch, validaciones, metodos y funciones
//Este programa usara el metodo de Insertar un producto, se guarde en un arreglo
//Este mismo imprimira todos los articulos recibidos
import ArticuloDTO.ArticuloDTO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PruebaArticulos {
    static Scanner in = new Scanner(System.in);
    static int contador=0;
    static ArticuloDTO Productos;
    static ArrayList<ArticuloDTO> Articulos = new ArrayList<>();
    public static final String REGEX_LETRAS="[a-zA-Z\\s]*";

    public static void main(String[] args) {
        Inicio();
    }

    private static void Inicio() {
        int eleccion=0;
        try{
        System.out.println("Bienvenido. Elija la opción que desee realizar");
        System.out.println("1.- Introducir Producto");
        System.out.println("2.- Imprimir lista de productos");
        System.out.println("3.- Ver Precio");
        System.out.println("4.- Salir");
        eleccion = in.nextInt();
        switch(eleccion){
            case 1: InsercionProductos(); break;
            case 2: ImprimirProductos();break;
            case 3: Comprar();break;
            case 4: System.exit(0);
            default:
                System.out.println("Favor de introducir un numero valido"); Inicio();break;
        }
        }catch(Exception e){
            System.out.println("Error, solo introduzca numeros validos");
            in.nextLine();
        }
        if (eleccion>=0){
            Inicio();
        }
    }

    private static void ImprimirProductos() {
        if (Articulos.size() != 0){
         printProductos(Articulos);
        } else{
            System.out.println("No existe lista de productos");
            return;
        }
    }

    private static void printProductos(ArrayList<ArticuloDTO> Muestra) {
        Muestra.forEach(ProductoDTO -> {
            System.out.println("===============================");
            System.out.println(ProductoDTO);
            System.out.println("===============================");
        });
    }

    private static void InsercionProductos() {
        try{
        System.out.println("Numero de Productos a Introducir: ");
        contador=in.nextInt();
        in.nextLine();
        for (int i=0;i<contador;i++){
            Productos = new ArticuloDTO();
            Nombre();
            Marca();
            Tipo();
            Precio();
            Disponibilidad();
            in.nextLine();
            Articulos.add(Productos);
        }
        }catch(Exception e) {
            System.out.println("Error. Introduzca un numero");
            in.nextLine();
        }
        if (contador<=0){
            InsercionProductos();
        }
    }

    private static void Disponibilidad() {
        try{
            System.out.print("¿Cuantos productos hay?: ");
            Productos.setCantidad(in.nextInt());
            if (Productos.getCantidad()==0){
                Productos.setDisponible(false);
            } else if (Productos.getCantidad()>0){
                Productos.setDisponible(true);
            }
            //in.nextLine();

        }catch(Exception e){
            System.out.println("Favor de escribir numeros validos");
        }
        if (Productos.getCantidad()<0){
        System.out.println("Favor de usar cantidades validas");
        Disponibilidad();}
    }

    private static void Precio() {
        try{
            System.out.print("Precio: ");
            Productos.setPrecio(in.nextDouble());
        }catch(InputMismatchException e){
        System.out.println("Valor no valido"); in.nextLine();}
        if (Productos.getPrecio()==null){
            System.out.println("Favor de usar numeros para el precio");
            Precio();}
    }

    private static void Tipo() {
        do{
            System.out.print("Tipo de Producto (Comida, Electrodomestico, Limpieza, etc): ");
            Productos.setTipo(in.nextLine());
        }while (!Productos.getTipo().matches(REGEX_LETRAS));
    }

    private static void Marca() {
        do{
            System.out.print("Marca del Producto: ");
            Productos.setBrand(in.nextLine());
        }while (!Productos.getBrand().matches(REGEX_LETRAS));
    }

    private static void Nombre() {
        do{
            System.out.print("Nombre del Producto: ");
            Productos.setName(in.nextLine());
        }while (!Productos.getName().matches(REGEX_LETRAS));
    }

    private static void Comprar(){
     String Pedido,Cosa;
     int id=-1;
     ArticuloDTO muestra;
        if (Articulos.size() != 0){
            System.out.println("¿Que articulo quiere comprar?");
            Pedido= in.nextLine();
            in.nextLine();

            for (int i=0;i< Articulos.size();i++){
                //muestra=Articulos.get(i);
                Cosa=Articulos.get(i).getName();
                System.out.println("-"+Articulos.get(i).getName());

                System.out.println(Pedido.equalsIgnoreCase(Articulos.get(i).getName()));
                if (Pedido.compareTo(Cosa)==0){

                    System.out.println(" "+Cosa);
                    id=i;
                }
            }
            if (id==-1){
                System.out.println("Producto no existe");
            }else{
                muestra=Articulos.get(id);
                System.out.println("El costo es de: "+ muestra.getPrecio() );}
        } else{
            System.out.println("No existe lista de productos");
            return;
        }

    }



}
