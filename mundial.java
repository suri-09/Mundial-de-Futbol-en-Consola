import java.io.FileNotFoundException;
import java.io.IOException;

public class mundial {
    public static void main(String[] args) throws FileNotFoundException {

        while (true) {
            System.out.println(" __  __ _   _ _   _ ____ ___    _    _       ____   ___  ____   ____ \n" + //
                    "|  \\/  | | | | \\ | |  _ \\_ _|  / \\  | |     |___ \\ / _ \\|___ \\ / ___|\n" + //
                    "| |\\/| | | | |  \\| | | | | |  / _ \\ | |       __) | | | | __) | |  _ \n" + //
                    "| |  | | |_| | |\\  | |_| | | / ___ \\| |___   / __/| |_| |/ __/| |_| |\n" + //
                    "|_|  |_|\\___/|_| \\_|____/___/_/   \\_\\_____| |_____|\\___/_____|\\____|");

            System.out.println("=====================================================================");
            System.out.println(" VISUALIZADOR DE BANDERAS DE LAS 48 SELECCIONES");
            System.out.println("=====================================================================");

            System.out.println("[1] Ver la bandera de un país.\n" +
                    "[2] Ver o editar la tabla de posiciones.\n" +
                    "[3] Ver partidos e integrantes por grupos.\n" +
                    "[4] Salir");

            int opcion = Utilidades.input.getInt("ingrese una opción");

            String[] paises = { "AUSTRIA", "MEXICO", "MARRUECOS", "NORUEGA", "BOSNIA Y HERZEGOVINA", "TUNEZ",
                    "INGLATERRA ", "ESPANA", "FRANCIA", "CABO VERDE", "COREA DEL SUR", "CONGO RD", "ECUADOR",
                    "ALEMANIA", "BÉLGICA", "CHEQUIA", "JAPON", "SUDÁFRICA", "TURQUÍA", "COLOMBIA", "ESCOCIA",
                    "PARAGUAY", "SUIZA", "EGIPTO", "PORTUGAL", "HAITI", "ARGELIA", "ARABIA SAUDI", "CROACIA",
                    "ARGENTINA", "COSTA DE MARFIL", "PAISES BAJOS", "BRASIL", "QATAR", "ESTADOS UNIDOS", "URUGUAY",
                    "SENEGAL", "JORDANIA", "CANADA", "AUSTRALIA", "NUEVA ZELANDA", "PANAMA", "CURAZAO", "SUECIA",
                    "IRAN", "UZBEKISTAN", "IRAK", "GHANA" };

            switch (opcion) {
                case 1:
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
                        System.out.println("¡Debes ingresar una opción valida!");
                        size = Utilidades.input.getInt("Seleccione un tamaño:") - 1;
                    }

                    // TODO: Buscar los valores mas optimos para cada tamaño

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

                    break;

                case 2:

                    int mostrar = 12;
                    int paginas = 48 / mostrar;
                    int contador = 0;

                    String[][] tabla = Utilidades.tablaPosiciones.getTabla();

                    boolean centinela = true;

                    while (centinela) {

                        Utilidades.mostrarTabla(tabla, contador, mostrar);

                        System.out.println();
                        System.out.println("[1] Mostrar más.\n" +
                                "[2] Mostrar menos.\n" +
                                "[3] Editar algún valor.\n" +
                                "[4] Salir");

                        int hola = Utilidades.input.getInt("Ingrese una opción:");

                        switch (hola) {
                            case 1:
                                if (contador + mostrar < tabla.length) {
                                    contador = contador + 12;
                                    // continue;
                                } else {
                                    System.out.println("Ya estas mostrando todas las opciones");
                                }

                                break;

                            case 2:
                                if (contador - 12 >= 0) {
                                    contador = contador - 12;
                                    // continue;
                                } else {
                                    System.out.println("No hay menos opciones");
                                }

                                break;
                            case 3:

                                int fila = Utilidades.input.getInt("ingrese el número de la fila que desea cambiar")
                                        - 1;
                                int col = Utilidades.input.getInt("ingrese el número de la columna que desea cambiar")
                                        - 1;
                                int valor = Utilidades.input.getInt("ingrese el valor al que desea cambiar");

                                if (valor < 20 && col != 10 && col != 7) {
                                    try {
                                        Utilidades.tablaPosiciones.editTabla(fila, col, valor);
                                        tabla = Utilidades.tablaPosiciones.getTabla();
                                    } catch (IOException e) {
                                        System.out.println("error" + e.getMessage());
                                    }

                                } else {
                                    System.out.println("El valor no es permitido");
                                }

                                break;

                            case 4:
                                centinela = false;
                                break;

                        }

                    }
                    break;

                case 3:
                    boolean centine = true;
                    while (centine) {

                        System.out.println();
                        System.out.println("[1] Mostrar partidos por grupos.\n" +
                                "[2] Mostrar hora e integrantes de un partido.\n" +
                                "[3] Salir.\n");

                        int suri = Utilidades.input.getInt("Ingrese una opción:");
                        switch (suri) {
                            case 1:
                                String[][] partidos;

                                while (true) {
                                    try {
                                        String grupo = Utilidades.input.getString("Ingresa la letra del grupo:");

                                        partidos = Utilidades.partidos.partidosGrupo(grupo);

                                        break;

                                    } catch (Exception e) {
                                        System.out.println(e);
                                        System.out.println("¡Ese grupo no ha sido encontrado!");
                                    }
                                }

                                System.out.println("Los partidos en el grupo " + partidos[0][1] + " son:");

                                for (int i = 0; i < partidos.length; i++) {
                                    System.out.println(partidos[i][5] + " vs " + partidos[i][6] + " el dia "
                                            + partidos[i][3] + " a las " + partidos[i][4]);

                                }

                                break;

                            case 2:
                                String[] partido = new String[7];

                                while (true) {
                                    try {
                                        String grupo = Utilidades.input.getString("Ingresa el código del partido:");

                                        partido = Utilidades.partidos.getPartido(grupo);

                                        break;

                                    } catch (Exception e) {
                                        System.out.println("¡Ese partido no ha sido encontrado!");
                                    }
                                }

                                System.out.println("El partido " + partido[0] + " sera " + partido[5] + " vs "
                                        + partido[6] + " el dia " + partido[3] + " a " +
                                        "las " + partido[4]);

                                break;

                            case 3:
                                centine = false;
                                break;
                            default:
                                System.out.println("¡Debes ingresar una opción valida!");
                                break;
                        }
                    }

                case 4:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("¡Debes ingresar una opción valida!");
                    break;
            }
        }
    }
}
