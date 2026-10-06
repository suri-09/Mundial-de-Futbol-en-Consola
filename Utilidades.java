import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Utilidades {

    private static final int FILAS = 10;
    private static final int COLS = 15;
    private static final int TOTAL_PAISES = 48;

    private static final String[] COLORES = {
            "\033[43m", // Amarillo
            "\033[48;5;208m", // Naranja
            "\033[41m", // Rojo
            "\033[45m", // Morado
            "\033[44m", // Azul
            "\033[42m", // Verde
            "\033[47m", // Blanco
            "\033[40m", // Negro
            "\033[48;5;88m", // Cafe
    };

    /**
     * Da el array con los colores de la bandera del pais dado.
     *
     * @param pais Índice del país en la tabla.
     * @return Array bidimensional con los colores de la bandera del país.
     */

    public static byte[][] getBandera(int pais) throws FileNotFoundException {
        byte[][] bandera = new byte[FILAS][COLS];

        Scanner sc = new Scanner(new File("./banderas.csv"));

        // TODO: Usar IllegalArgumentException con mensaje en vez de RuntimeException
        // vacía
        if (pais < 0 || pais > TOTAL_PAISES - 1) {
            throw new RuntimeException();
        }

        int saltos = (FILAS * pais) + (pais + 1);

        for (int i = 0; i < saltos; i++) {
            sc.nextLine();
        }

        for (int i = 0; i < FILAS; i++) {

            String[] lineaActual = sc.nextLine().split(",");

            for (int j = 0; j < COLS; j++) {
                bandera[i][j] = Byte.parseByte(lineaActual[j]);
            }
        }

        sc.close();

        return bandera;
    }

    /**
     * Da los nombres de los países, leídos de la primera columna de cada bloque del
     * CSV.
     *
     * @return Array con los nombres en el mismo orden que las banderas.
     */
    public static String[] getPaises() throws FileNotFoundException {

        String[] paises = new String[TOTAL_PAISES];

        Scanner sc = new Scanner(new File("./banderas.csv"));

        for (int pais = 0; pais < TOTAL_PAISES; pais++) {

            paises[pais] = sc.nextLine().split(",")[0].trim();

            for (int i = 0; i < FILAS; i++) {
                sc.nextLine();
            }
        }

        sc.close();

        return paises;
    }

    /**
     * Imprime el encabezado del programa.
     */
    public static void banner() {

        System.out.println(" __  __ _   _ _   _ ____ ___    _    _       ____   ___  ____   ____ \n" + //
                "|  \\/  | | | | \\ | |  _ \\_ _|  / \\  | |     |___ \\ / _ \\|___ \\ / ___|\n" + //
                "| |\\/| | | | |  \\| | | | | |  / _ \\ | |       __) | | | | __) | |  _ \n" + //
                "| |  | | |_| | |\\  | |_| | | / ___ \\| |___   / __/| |_| |/ __/| |_| |\n" + //
                "|_|  |_|\\___/|_| \\_|____/___/_/   \\_\\_____| |_____|\\___/_____|\\____|");

        System.out.println("=====================================================================");
        System.out.println(" VISUALIZADOR DE BANDERAS DE LAS 48 SELECCIONES");
        System.out.println("=====================================================================");
    }

    /**
     * Merma un array bidimensional según el factor dado, eliminando las filas y las
     * columnas que están entre
     * aquellas que sobreviven.
     * Si las columnas o las filas no son divisibles por el factor, se truncan las
     * últimas.
     *
     * @param array  Array a diezmar.
     * @param factor Factor de diezmado.
     * @return Array diezmado.
     */
    public static byte[][] mermar(byte[][] array, int factor) {

        // Da el tamaño del array tras ser diezmado.
        int filas = array.length / factor;
        int cols = array[0].length / factor;

        byte[][] arrayMermar = new byte[filas][cols];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                arrayMermar[i][j] = array[i * factor][j * factor];
            }
        }

        return arrayMermar;
    }

    /**
     * Interpola un array bidimensional según el factor dado, copiando los valores
     * del array original a los elementos
     * interpolados.
     *
     * @param array  Array a interpolar.
     * @param factor Factor de interpolación.
     * @return Array interpolado.
     */
    public static byte[][] agregar(byte[][] array, int factor) {

        // Da el tamaño del array tras ser interpolado.
        int filas = array.length * factor;
        int cols = array[0].length * factor;

        byte[][] arrayAgregar = new byte[filas][cols];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                arrayAgregar[i][j] = array[i / factor][j / factor];
            }
        }

        return arrayAgregar;
    }

    /**
     * Imprime un espacio con el BG color dado según la tabla de colores.
     * 1. amarillo
     * 2. naranja
     * 3. rojo
     * 4. morado
     * 5. azul
     * 6. verde
     * 7. blanco
     * 8. negro
     * 9. cafe
     *
     * @param color Indice del color en la tabla.
     */
    public static void printColor(int color) {

        System.out.print(COLORES[color - 1] + "   ");
    }

    public static void mostrarTabla(String[][] tabla, int contador, int mostrar) {

        String[] arriba = { "Equipo", "PJ", "PG", "PE", "PP", "GF", "GC", "DG", "TA", "TR", "Pts" };

        for (int c = 0; c < mostrar; c++) {

            System.out.printf("%-4s%-22s", "#", arriba[0]);
            for (int j = 1; j < arriba.length; j++) {
                System.out.printf("%5s", arriba[j]);
            }
            System.out.println();

            for (int i = contador; i < mostrar + contador; i++) {
                System.out.printf("%-4d%-22s", i + 1, tabla[i][0]);

                for (int j = 1; j < tabla[i].length; j++) {
                    System.out.printf("%5s", tabla[i][j]);
                }
                System.out.println();
            }
            break;
        }

    }

    public static class input {
        /**
         * Imprime el prompt dada e intenta obtener el número entero de la consola,
         * maneja errores.
         *
         * @param prompt Mensaje a mostrar al usuario.
         */
        public static int getInt(String prompt) {

            Scanner sc = new Scanner(System.in);

            int num;

            while (true) {
                try {
                    System.out.print(prompt + " ");
                    num = sc.nextInt();

                    break;
                } catch (InputMismatchException e) {
                    sc.next(); // Limpia el buffer de Scanner

                    System.out.println("Debes ingresar un numero valido!");
                }
            }

            return num;
        }

        public static String getString(String prompt) {

            Scanner sc = new Scanner(System.in);

            String str = null;

            System.out.print(prompt + " ");

            while (true) {
                if (sc.hasNextLine()) {
                    str = sc.nextLine();
                    break;
                }
            }
            return str;
        }
    }

    public static class tablaPosiciones {
        private static final int COLS_TABLA = 11;

        /**
         * Lee la tabla del archivo, y la retorna como una matriz 2x2.
         *
         * @return Matriz con la tabla de posiciones.
         */
        public static String[][] getTabla() throws FileNotFoundException {

            Scanner sc = new Scanner(new File("./posiciones.csv"));

            String[][] tabla = new String[TOTAL_PAISES][COLS_TABLA];

            for (int i = 0; i < TOTAL_PAISES; i++) {

                String[] lineaActual = sc.nextLine().split(",");

                tabla[i] = lineaActual;
            }

            sc.close();

            return tabla;
        }

        /**
         * Edita la tabla en el archivo según la posición y el valor dado.
         *
         * @param fila  Fila a editar.
         * @param col   Columna a editar.
         * @param valor Valor a poner en la posición dada.
         */
        public static void editTabla(int fila, int col, int valor) throws IOException {

            Scanner sc = new Scanner(new File("./posiciones.csv"));

            String[][] tabla = new String[TOTAL_PAISES][COLS_TABLA];

            for (int i = 0; i < TOTAL_PAISES; i++) {

                String[] lineaActual = sc.nextLine().split(",");

                tabla[i] = lineaActual;
            }

            sc.close();

            tabla[fila][col + 1] = "" + valor;

            for (fila = 0; fila < tabla.length; fila++) {
                // DG = GF - GC ----> DG = 7 , GF = 5 , GC = 6
                int GF = Integer.parseInt(tabla[fila][5]);
                int GC = Integer.parseInt(tabla[fila][6]);
                int GD = GF - GC;

                tabla[fila][7] = String.valueOf(GD);
            }
            for (fila = 0; fila < tabla.length; fila++) {
                // Pts = (PG * 3) + (PE * 1 ) ---> pts = 10
                int PG = Integer.parseInt(tabla[fila][2]);
                int PE = Integer.parseInt(tabla[fila][3]);
                int pts = (PG * 3) + PE;

                tabla[fila][10] = String.valueOf(pts);

            }

            for (fila = 0; fila < tabla.length; fila++) {
                // PJ = PG + PE + PP ---> PJ = 1, PG = 2, PE = 3, PP = 4
                int PG = Integer.parseInt(tabla[fila][2]);
                int PE = Integer.parseInt(tabla[fila][3]);
                int PP = Integer.parseInt(tabla[fila][4]);

                int PJ = PG + PE + PP;

                tabla[fila][1] = String.valueOf(PJ);

            }

            FileWriter fw = new FileWriter("./posiciones.csv");

            String[] lineas = new String[TOTAL_PAISES];

            for (int i = 0; i < tabla.length; i++) {
                lineas[i] = String.join(",", tabla[i]);
            }

            String archivo = String.join("\n", lineas);

            fw.write(archivo);

            fw.close();

        }
    }

    public static class partidos {
        private static final int columnas = 7;

        public static String[][] mostrarPartidos() throws FileNotFoundException {

            Scanner sc = new Scanner(new File("./partidos.csv"));

            String[][] tabla = new String[TOTAL_PAISES][columnas];

            for (int i = 0; i < TOTAL_PAISES; i++) {

                String[] lineaActual = sc.nextLine().split(",");

                tabla[i] = lineaActual;
            }

            sc.close();

            return tabla;

        }

        public static String[][] mostrarEquipos() throws FileNotFoundException {

            Scanner sc = new Scanner(new File("./equipos_mundial.csv"));

            String[][] tabla = new String[TOTAL_PAISES][columnas];

            for (int i = 0; i < TOTAL_PAISES; i++) {

                String[] lineaActual = sc.nextLine().split(",");

                tabla[i] = lineaActual;
            }

            sc.close();

            return tabla;
        }

        public static String[][] partidosGrupo(String grupo) throws FileNotFoundException {

            Scanner sc = new Scanner(new File("./partidos.csv"));

            String[][] tabla = new String[TOTAL_PAISES][columnas];
            String[][] partidos = new String[6][columnas];

            for (int i = 0; i < TOTAL_PAISES; i++) {

                String[] lineaActual = sc.nextLine().split(",");

                tabla[i] = lineaActual;
            }

            sc.close();

            try {
                for (int i = 0; i < partidos.length; i++) {
                    for (int j = 0; j < tabla.length; j++) {
                        String grupoTabla = tabla[j][1];

                        if (grupoTabla.equalsIgnoreCase(grupo)) {
                            partidos[i] = tabla[j];
                            i += 1;
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return partidos;

        }

        public static String[] getPartido(String codigo) throws FileNotFoundException {

            Scanner sc = new Scanner(new File("./partidos.csv"));

            String[][] tabla = new String[TOTAL_PAISES][columnas];
            String[] partido = new String[columnas];

            for (int i = 0; i < TOTAL_PAISES; i++) {

                String[] lineaActual = sc.nextLine().split(",");

                tabla[i] = lineaActual;
            }

            sc.close();

            try {
                for (int i = 0; i < tabla.length; i++) {
                    if (codigo.equalsIgnoreCase(tabla[i][0])) {
                        partido = tabla[i];
                        break;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return partido;

        }
    }

}
