// Entrada para direccionamiento abierto (sondeos) con “tombstone” para borrado lógico.
public class OpenAddressEntry {
    Integer key;   // null => celda libre
    boolean deleted;
    public OpenAddressEntry() {
        this.key = null;
        this.deleted = false;
    }
}
