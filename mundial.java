import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class mundial {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(" __  __ _   _ _   _ ____ ___    _    _       ____   ___  ____   ____ \n" + //
                "|  \\/  | | | | \\ | |  _ \\_ _|  / \\  | |     |___ \\ / _ \\|___ \\ / ___|\n" + //
                "| |\\/| | | | |  \\| | | | | |  / _ \\ | |       __) | | | | __) | |  _ \n" + //
                "| |  | | |_| | |\\  | |_| | | / ___ \\| |___   / __/| |_| |/ __/| |_| |\n" + //
                "|_|  |_|\\___/|_| \\_|____/___/_/   \\_\\_____| |_____|\\___/_____|\\____|");

        System.out.println("=====================================================================");
        System.out.println(" VISUALIZADOR DE BANDERAS DE LAS 48 SELECCIONES");
        System.out.println("=====================================================================");

        System.out.println("[1] Ver la bandera de un país.\n" +
                "[3] Salir");

        int opcion = Utilidades.input.getInt("Ingrese la opción:");

        String[] paises = {"AUSTRIA", "MEXICO", "MARRUECOS", "NORUEGA", "BOSNIA Y HERZEGOVINA", "TUNEZ", "INGLATERRA "
                , "ESPANA", "FRANCIA", "CABO VERDE", "COREA DEL SUR", "CONGO RD", "ECUADOR", "ALEMANIA", "BÉLGICA",
                "CHEQUIA", "JAPON", "SUDÁFRICA", "TURQUÍA", "COLOMBIA", "ESCOCIA", "PARAGUAY", "SUIZA", "EGIPTO",
                "PORTUGAL", "HAITI", "ARGELIA", "ARABIA SAUDI", "CROACIA", "ARGENTINA", "COSTA DE MARFIL", "PAISES " +
                "BAJOS", "BRASIL", "QATAR", "ESTADOS UNIDOS", "URUGUAY", "SENEGAL", "JORDANIA", "CANADA", "AUSTRALIA"
                , "NUEVA ZELANDA", "PANAMA", "CURAZAO", "SUECIA", "IRAN", "UZBEKISTAN", "IRAK", "GHANA"};

        while (opcion != 3) {

            switch (opcion) {
                case 1:
                    System.out.println("Lista de paises:");

                    for (int i = 0; i < paises.length; i++) {
                        System.out.println((i + 1) + ") " + paises[i]);
                    }

                    byte[][] bandera;
                    int seleccion;

                    while (true) {
                        try {
                            seleccion = Utilidades.input.getInt("Seleccione un país:") - 1;
                            bandera = Utilidades.getBandera(seleccion);

                            break;
                        } catch (RuntimeException e) {
                            System.out.println("Debes ingresar un número de país valido!");
                        }
                    }

                    System.out.println(paises[seleccion]);

                    //TODO: Añadir menu para seleccionar el tamaño de la bandera.

                    for (int i = 0; i < bandera.length; i++) {
                        for (int j = 0; j < bandera[0].length; j++) {
                            Utilidades.printColor(bandera[i][j]);
                        }
                        System.out.println("\033[0m");
                    }

                    break;

                default:
                    break;
            }


            System.out.println(" __  __ _   _ _   _ ____ ___    _    _       ____   ___  ____   ____ \n" + //
                    "|  \\/  | | | | \\ | |  _ \\_ _|  / \\  | |     |___ \\ / _ \\|___ \\ / ___|\n" + //
                    "| |\\/| | | | |  \\| | | | | |  / _ \\ | |       __) | | | | __) | |  _ \n" + //
                    "| |  | | |_| | |\\  | |_| | | / ___ \\| |___   / __/| |_| |/ __/| |_| |\n" + //
                    "|_|  |_|\\___/|_| \\_|____/___/_/   \\_\\_____| |_____|\\___/_____|\\____|");

            System.out.println("=====================================================================");
            System.out.println(" VISUALIZADOR DE BANDERAS DE LAS 48 SELECCIONES");
            System.out.println("=====================================================================");

            System.out.println("[1] Ver la bandera de un país.\n" +
                    "[3] Salir");

            opcion = Utilidades.input.getInt("Ingrese la opción:");

        }
    }
}