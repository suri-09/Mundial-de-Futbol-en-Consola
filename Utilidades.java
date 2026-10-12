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

    /**
     * Imprime la tabla de posiciones dada.
     *
     * @param tabla          Matriz de la tabla a imprimir.
     * @param desplazamiento Desplazamiento vertical de la tabla.
     * @param mostrar        Cantidad de filas a mostrar a la vez.
     */
    public static void mostrarTabla(String[][] tabla, int desplazamiento, int mostrar) {

        String[] arriba = {"Equipo", "PJ", "PG", "PE", "PP", "GF", "GC", "DG", "TA", "TR", "Pts"};

        for (int c = 0; c < mostrar; c++) {

            System.out.printf("%-4s%-22s", "#", arriba[0]);
            for (int j = 1; j < arriba.length; j++) {
                System.out.printf("%5s", arriba[j]);
            }
            System.out.println();

            for (int i = desplazamiento; i < mostrar + desplazamiento; i++) {
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
         * @return Entrada del usuario.
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

        /**
         * Imprime el prompt dada e intenta obtener el string de la consola,
         * maneja errores.
         *
         * @param prompt Mensaje a mostrar al usuario.
         * @return Entrada del usuario.
         */
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

            tabla[fila][col] = "" + valor;

            for (int i = 0; i < tabla.length; i++) {
                // DG = GF - GC ----> DG = 7 , GF = 5 , GC = 6
                int GF = Integer.parseInt(tabla[i][5]);
                int GC = Integer.parseInt(tabla[i][6]);
                int GD = GF - GC;

                tabla[i][7] = String.valueOf(GD);
            }
            for (int i = 0; i < tabla.length; i++) {
                // Pts = (PG * 3) + (PE * 1 ) ---> pts = 10
                int PG = Integer.parseInt(tabla[i][2]);
                int PE = Integer.parseInt(tabla[i][3]);
                int pts = (PG * 3) + PE;

                tabla[i][10] = String.valueOf(pts);

            }

            for (int i = 0; i < tabla.length; i++) {
                // PJ = PG + PE + PP ---> PJ = 1, PG = 2, PE = 3, PP = 4
                int PG = Integer.parseInt(tabla[i][2]);
                int PE = Integer.parseInt(tabla[i][3]);
                int PP = Integer.parseInt(tabla[i][4]);

                int PJ = PG + PE + PP;

                tabla[i][1] = String.valueOf(PJ);

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

        /**
         * Lee la tabla de equipos del mundial y los organiza en una matriz.
         *
         * @return Matriz de los equipos del mundial.
         */
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

        /**
         * Lee la tabla de partidos, busca todos los del grupo dado, y los devuelve en una matriz.
         *
         * @param grupo Grupo a buscar.
         * @return Matriz con los 6 partidos del grupo.
         */
        public static String[][] partidosGrupo(String grupo) throws FileNotFoundException {

            Scanner sc = new Scanner(new File("./partidos.csv"));

            String[][] tabla = new String[72][columnas];
            String[][] partidos = new String[6][columnas];

            for (int i = 0; i < tabla.length; i++) {

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

            if (partidos[0][0] == null) {
                throw new RuntimeException();
            }

            return partidos;

        }

        /**
         * Busca el partido del codigo en la tabla de partidos, y lo devuelve como un arreglo, si el partido no se
         * encuentra arroja una excepción.
         *
         * @param codigo Código del partido a buscar.
         * @return Arreglo del partido.
         */
        public static String[] getPartido(String codigo) throws FileNotFoundException {

            Scanner sc = new Scanner(new File("./partidos.csv"));

            String[][] tabla = new String[72][columnas];
            String[] partido = new String[columnas];

            for (int i = 0; i < tabla.length; i++) {

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

            if (partido[0] == null) {
                throw new RuntimeException();
            }

            return partido;

        }
    }

    public static class info {

        /**
         * Busca el equipo en la tabla de información de equipos, y lo devuelve.
         *
         * @param pais Índice del país a buscar.
         * @return Arreglo de información del país.
         */
        public static String[] getInfo(int pais) throws FileNotFoundException {

            Scanner sc = new Scanner(new File("./info.csv"));

            for (int fila = 0; fila < pais; fila++) {
                sc.nextLine();
            }

            String[] info = sc.nextLine().split(",");

            sc.close();

            return info;
        }
    }

}
