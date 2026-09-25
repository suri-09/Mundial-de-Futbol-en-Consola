import java.util.Arrays;
import java.util.Scanner;

public class mundial {
    public static void main(String[] args) {
        
        System.out.println(" __  __ _   _ _   _ ____ ___    _    _       ____   ___  ____   ____ \n" + //
                        "|  \\/  | | | | \\ | |  _ \\_ _|  / \\  | |     |___ \\ / _ \\|___ \\ / ___|\n" + //
                        "| |\\/| | | | |  \\| | | | | |  / _ \\ | |       __) | | | | __) | |  _ \n" + //
                        "| |  | | |_| | |\\  | |_| | | / ___ \\| |___   / __/| |_| |/ __/| |_| |\n" + //
                        "|_|  |_|\\___/|_| \\_|____/___/_/   \\_\\_____| |_____|\\___/_____|\\____|");
        
        System.out.println("=====================================================================");
        System.out.println(" VISUALIZADOR DE BANDERAS DE LAS 48 SELECCIONES");
        System.out.println("=====================================================================");

        System.out.print(
                        "[1] Ver banderas al azar\n" + //
                        "[2] Buscar por nombre\n" + //
                        "[3] Salir");
        
        int opcion = Utilidades.input.getInt("Ingrese la opción");

        while(opcion != 3){

            switch (opcion) {
                case 1:
                    
                    break;
            
                default:
                    break;
            }

        }
    }
}