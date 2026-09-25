import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {

    /**
     * Da el array con los colores de la bandera del pais dado.
     *
     * @param pais Índice del país en la tabla.
     * @return Array bidimensional con los colores de la bandera del país.
     */

    public static byte[][] getBandera(int pais) throws FileNotFoundException {
        byte[][] bandera = new byte[10][15];

        Scanner sc = new Scanner(new File("./banderas.csv"));

        int saltos = (10 * pais) + (pais + 1);

        for (int i = 0; i < saltos; i++) {
            sc.nextLine();
        }

        for (int i = 0; i < 10; i++) {

            String[] lineaActual = sc.nextLine().split(",");

            for (int j = 0; j < 15; j++) {
                bandera[i][j] = Byte.parseByte(lineaActual[j]);
            }
        }

        sc.close();

        return bandera;
    }

    /**
     * Merma un array bidimensional según el factor dado, eliminando las filas y las columnas que están entre
     * aquellas que sobreviven.
     * Si las columnas o las filas no son divisibles por el factor, se truncan las últimas.
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
     * Interpola un array bidimensional según el factor dado, copiando los valores del array original a los elementos
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

        String[] colores = {
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

        System.out.print(colores[color - 1] + "   ");
    }

    public static class input {

        public static Scanner sc = new Scanner(System.in);

        /**
         * Imprime el prompt dada e intenta obtener el número entero de la consola, maneja errores.
         *
         * @param prompt Mensaje a mostrar al usuario.
         */
        public static int getInt(String prompt) {
            int num;

            while (true) {
                try {
                    System.out.print(prompt + " ");
                    num = sc.nextInt();

                    break;
                } catch (InputMismatchException e) {
                    sc.next();

                    System.out.println("Debes ingresar un numero valido!");
                }
            }

            return num;
        }

    }
}