public enum Menu {
    OPCION_1 (1, ".- Introducir Producto"),
    OPCION_2 (2, ".- Imprimir lista de productos"),
    OPCION_3 (3, ".- Ver Precio"),
    OPCION_4 (4, ".- Ver Existencia"),
    OPCION_5 (5, ".- Salir");

    private final int NUMBEROPT;
    private final String NAMEOPT;

    Menu(int NUMBEROPT, String NAMEOPT) {
        this.NUMBEROPT = NUMBEROPT;
        this.NAMEOPT = NAMEOPT;
    }

    public int getNUMBEROPT() {
        return NUMBEROPT;
    }

    public String getNAMEOPT() {
        return NAMEOPT;
    }
}
