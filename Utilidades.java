import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {

    /**
     * Da el array con los colores de la bandera del pais dado.
     * @param pais Índice del país en el array.
     * @return Array bidimensional con los colores del país.
     */

    public static byte[][] banderas(int pais) {
        byte[][][] paises = {
                // Colombia.
                {{ 4, 4, 4, 4, 4, 4, 4, 4, 4 },
                 { 4, 4, 4, 4, 4, 4, 4, 4, 4 },
                 { 4, 4, 4, 4, 4, 4, 4, 4, 4 },
                 { 2, 2, 2, 2, 2, 2, 2, 2, 2 },
                 { 2, 2, 2, 2, 2, 2, 2, 2, 2 },
                 { 1, 1, 1, 1, 1, 1, 1, 1, 1 }},
                {{}}};
                
        return paises[pais];
    }

    /**
     * Merma un array bidimensional según el factor dado, eliminando las filas y las columnas que están entre aquellas que sobreviven.
     * Si las columnas o las filas no son divisibles por el factor, se truncan las últimas.
     * @param array Array a diezmar.
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
     * Interpola un array bidimensional según el factor dado, copiando los valores del array original a los elementos interpolados.
     * @param array Array a interpolar.
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

    public static class input {

        public static Scanner sc = new Scanner(System.in);

        /**
         * Imprime el prompt dada e intenta obtener el número entero de la consola, maneja errores.
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