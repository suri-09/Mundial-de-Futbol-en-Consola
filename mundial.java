import java.io.FileNotFoundException;

public class mundial {
    public static void main(String[] args) throws FileNotFoundException {

        while(true){
              System.out.println(" __  __ _   _ _   _ ____ ___    _    _       ____   ___  ____   ____ \n" + //
                "|  \\/  | | | | \\ | |  _ \\_ _|  / \\  | |     |___ \\ / _ \\|___ \\ / ___|\n" + //
                "| |\\/| | | | |  \\| | | | | |  / _ \\ | |       __) | | | | __) | |  _ \n" + //
                "| |  | | |_| | |\\  | |_| | | / ___ \\| |___   / __/| |_| |/ __/| |_| |\n" + //
                "|_|  |_|\\___/|_| \\_|____/___/_/   \\_\\_____| |_____|\\___/_____|\\____|");

        System.out.println("=====================================================================");
        System.out.println(" VISUALIZADOR DE BANDERAS DE LAS 48 SELECCIONES");
        System.out.println("=====================================================================");

        System.out.println("[1] Ver la bandera de un país.\n" +
                "[2] Salir");

        int opcion = Utilidades.input.getInt("Ingrese la opción:");

        String[] paises = {"AUSTRIA", "MEXICO", "MARRUECOS", "NORUEGA", "BOSNIA Y HERZEGOVINA", "TUNEZ", "INGLATERRA "
                , "ESPANA", "FRANCIA", "CABO VERDE", "COREA DEL SUR", "CONGO RD", "ECUADOR", "ALEMANIA", "BÉLGICA",
                "CHEQUIA", "JAPON", "SUDÁFRICA", "TURQUÍA", "COLOMBIA", "ESCOCIA", "PARAGUAY", "SUIZA", "EGIPTO",
                "PORTUGAL", "HAITI", "ARGELIA", "ARABIA SAUDI", "CROACIA", "ARGENTINA", "COSTA DE MARFIL", "PAISES " +
                "BAJOS", "BRASIL", "QATAR", "ESTADOS UNIDOS", "URUGUAY", "SENEGAL", "JORDANIA", "CANADA", "AUSTRALIA"
                , "NUEVA ZELANDA", "PANAMA", "CURAZAO", "SUECIA", "IRAN", "UZBEKISTAN", "IRAK", "GHANA"};

    
            if (opcion == 1){
                System.out.println("Lista de paises:");

                for (int i = 0; i < paises.length; i++) {
                        System.out.println((i + 1) + ") " + paises[i]);
                    }
                    
                    byte[][] banderaSel;
                    byte[][] bandera;
                    int seleccion;

                    while (true) {
                        try {
                            seleccion = Utilidades.input.getInt("Seleccione un país:") - 1;
                            banderaSel = Utilidades.getBandera(seleccion);

                            break;
                        } catch (RuntimeException e) {
                            System.out.println("Debes ingresar un número de país valido!");
                        }
                    }

                    System.out.println(paises[seleccion]);

                    System.out.println("Escoge el tamaño de la bandera.");
                    System.out.println("[1] Ícono\n" +
                            "[2] Pequeño\n" +
                            "[3] Mediano\n" +
                            "[4] Grande");

                    int size = Utilidades.input.getInt("Seleccione un tamaño:");


                    while (!(size < 5 && size > 0)) {
                        System.out.println("Debes ingresar una opción valida!");
                        size = Utilidades.input.getInt("Seleccione un tamaño:") - 1;
                    }

                     //TODO: Buscar los valores mas optimos para cada //tamaño

                    switch (size) {
                        case 1:
                            bandera = Utilidades.mermar(Utilidades.agregar(banderaSel, 7), 13);
                            break;
                        case 2:
                            bandera = Utilidades.mermar(Utilidades.agregar(banderaSel, 1), 1);
                            break;
                        case 3:
                            bandera = Utilidades.mermar(Utilidades.agregar(banderaSel, 3), 2);
                            break;
                        case 4:
                            bandera = Utilidades.mermar(Utilidades.agregar(banderaSel, 2), 1);
                            break;
                        default:
                            throw new IllegalStateException("Valor inesperado: " + size);
                    }

                    for (int i = 0; i < bandera.length; i++) {
                        for (int j = 0; j < bandera[0].length; j++) {
                            Utilidades.printColor(bandera[i][j]);
                        }
                        System.out.println("\033[0m");
                    }   

            }

            else{

                System.out.println("¡Hasta luego!");
                break; 
            }
        }

       
    }

        
}
