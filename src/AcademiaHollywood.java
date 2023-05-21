import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class AcademiaHollywood {

    private Set<Actriz> actrices = new HashSet<>();

    public Set<Actriz> getActrices() {
        return actrices;
    }

    public void setActrices(Set<Actriz> actrices) {
        this.actrices = actrices;
    }

    public void agregarPelicula(Actriz actriz, Pelicula pelicula) {
        if (!actrices.contains(actriz)) {
            actriz.agregarPelicula(pelicula);
            actrices.add(actriz);
        } else {
            Actriz existente = buscarActriz(actriz);
            existente.agregarPelicula(pelicula);
        }
    }

    private Actriz buscarActriz(Actriz actriz) {
        for (Actriz a : actrices) {
            if (a.equals(actriz)) {
                return a;
            }
        }
        return null;
    }

    public List<Actriz> ordenarActricesPorPremios() {
        List<Actriz> actricesOrdenadas = new ArrayList<>(actrices);
        Collections.sort(actricesOrdenadas);
        return actricesOrdenadas;
    }

    /**
     * Supuesto 1
     * Actrices que han ganado algún Oscar con más de x años.
     * El formato de la salida será un archivo HTML con listas anidadadas (ul – ol)
     */
    public void escribirArchivoHTML(int edad) {
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(new BufferedWriter(new FileWriter("actricesConOscarPorEdad.html")));
            salida.println("<html>" +
                    "<head> " +
                    "<title>Actrices de Hollywood</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>Actrices de Hollywood</h1>" +
                    "<ul>");
            for (Actriz actriz : actrices) {
                boolean actrizImpresa = false;
                for (Pelicula pelicula : actriz.getOscars()) {
                    if (pelicula.getEdadActriz() >= edad) {
                        if (!actrizImpresa) {
                            salida.println("<li>" + actriz.getNombre() + "<ol>");
                            actrizImpresa = true;
                        }
                        salida.println("<li>" + pelicula.getEdadActriz() + " anios, " + pelicula.getNombre() + "</li>");
                    }
                }
                if (actrizImpresa) {
                    salida.println("</ol></li>");
                }
            }
            salida.println("</ul></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            salida.close();
        }
    }

    /**
     * Supuesto 2
     * Búsqueda de películas por cadena de texto. Imprimirá las películas con su actriz ganadora
     */
    public void buscarPeliculas(String palabraTitulo) {
        System.out.println("Peliculas que contienen la cadena '" + palabraTitulo + "':");
        for (Actriz actriz : actrices) {
            for (Pelicula pelicula : actriz.getOscars()) {
                if (pelicula.getNombre().toLowerCase().contains(palabraTitulo.toLowerCase()))
                    System.out.println(" - " + pelicula.getNombre() + ", " + pelicula.getAnio() + ", " + actriz.getNombre() + " (" + pelicula.getEdadActriz() + ")");
            }
        }
    }


    /**
     * Supuesto troll
     * Actrices que han ganado el Oscar con una determinada edad, ordenadas
     * alfabéticamente según el nombre de la película por la que lo han ganado.
     * La salida será a un fichero CSV con ‘;’ como separador.
     */
    public void oscarPorEdadOrdenAlfabetico(int edad) {
        List<String> actricesOscar = obtenerActricesOscarPorEdad(edad);
        ordenarActricesPorNombrePelicula(actricesOscar);
        escribirArchivoCSV(actricesOscar, edad);
    }

    //Se crea una colección nombre;nombrePelicula
    private List<String> obtenerActricesOscarPorEdad(int edad) {
        List<String> actricesOscar = new ArrayList<>();
        for (Actriz actriz : actrices) {
            for (Pelicula pelicula : actriz.getOscars()) {
                if (pelicula.getEdadActriz() == edad) {
                    actricesOscar.add(actriz.getNombre() + ";" + pelicula.getNombre());
                }
            }
        }
        return actricesOscar;
    }

    //Se ordena la colección por peliculas en orden alfabético - método compare del Comparator de String
    private void ordenarActricesPorNombrePelicula(List<String> actricesOscar) {
        Collections.sort(actricesOscar, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String peliculaA = a.split(";")[1];
                String peliculaB = b.split(";")[1];
                return peliculaA.compareTo(peliculaB);
            }
        });
    }

    //Se genera el csv
    private void escribirArchivoCSV(List<String> actricesOscar, int edad) {
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(new BufferedWriter(new FileWriter("oscarCon" + edad + "Edad.csv")));
            for (String actrizOscar : actricesOscar) {
                salida.println(actrizOscar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (salida != null) {
                salida.close();
            }
        }
    }
}

