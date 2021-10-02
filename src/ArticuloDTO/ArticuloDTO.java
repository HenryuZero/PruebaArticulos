package ArticuloDTO;
public class ArticuloDTO {
    //Nombre, marca, tipo, precio, sección
    private String Name;
    private String Brand;
    private String Tipo;
    private Double Precio;
    private int Cantidad;
    private boolean Disponible;

    public int getCantidad() { return Cantidad;  }

    public void setCantidad(int cantidad) { Cantidad = cantidad; }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public boolean isDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean disponible) {
        Disponible = disponible;
    }

    @Override
    public String toString() {
        return  "\n Nombre='" + Name + '\'' +
                "\n Brand='" + Brand + '\'' +
                "\n Tipo='" + Tipo + '\'' +
                "\n Precio=" + Precio +
                "\n Cantidad=" + Cantidad +
                "\n Disponible=" + Disponible;
    }
}
