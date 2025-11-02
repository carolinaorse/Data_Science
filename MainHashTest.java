import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainHashTest {

    private static HashTable buildTable(CollisionStrategy strategy) {
        switch (strategy) {
            case LINEAR:
            case QUADRATIC:  return new HashTableOpenAddressing(strategy);
            case CHAINING:   return new HashTableChaining();
            default: throw new IllegalArgumentException("Estrategia no soportada");
        }
    }

    private static void demo(HashTable table) {
        List<Integer> datos = Arrays.asList(115269, 111254, 222269, 123450, 555550, 100010, 900019);
        System.out.println("Insertando datos de prueba: " + datos);
        for (int x : datos) table.insert(x);
        table.printTable();

        System.out.println("Buscar 111254: " + (table.contains(111254) ? "ENCONTRADO" : "NO"));
        System.out.println("Eliminar 123450: " + (table.remove(123450) ? "ELIMINADO" : "NO ESTABA"));
        table.printTable();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("TP2 - Tablas de Hash (H(x) = x mod 10)");
        System.out.println("Seleccione estrategia de colisiones:");
        System.out.println("1) Sondeo lineal");
        System.out.println("2) Sondeo cuadrático");
        System.out.println("3) Hash abierto (encadenamiento)");
        System.out.print("> ");
        int op = sc.nextInt();

        CollisionStrategy strategy = (op == 1) ? CollisionStrategy.LINEAR :
                                     (op == 2) ? CollisionStrategy.QUADRATIC :
                                                 CollisionStrategy.CHAINING;

        HashTable table = buildTable(strategy);
        System.out.println("\nTabla creada con estrategia: " + strategy + "\n");

        // Demo automática
        demo(table);

        // Menú simple
        boolean running = true;
        while (running) {
            System.out.println("Opciones: 1=insertar  2=buscar  3=eliminar  4=imprimir  5=limpiar  0=salir");
            System.out.print("> ");
            int opt = sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.print("Ingrese clave entera (6 dígitos): ");
                    System.out.println(table.insert(sc.nextInt()) ? "Insertado" : "No insertado");
                    break;
                case 2:
                    System.out.print("Ingrese clave a buscar: ");
                    System.out.println(table.contains(sc.nextInt()) ? "Encontrado" : "No encontrado");
                    break;
                case 3:
                    System.out.print("Ingrese clave a eliminar: ");
                    System.out.println(table.remove(sc.nextInt()) ? "Eliminado" : "No estaba");
                    break;
                case 4:
                    table.printTable();
                    break;
                case 5:
                    table.clear();
                    System.out.println("Tabla vaciada.");
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.println();
        }
        sc.close();
        System.out.println("Fin.");
    }
}
