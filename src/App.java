import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public class App {


    public static void main(String[] args) {
        AcademiaHollywood academia = leerPeliculasYActrices();

        imprimir(academia.ordenarActricesPorPremios());

        academia.escribirArchivoHTML(45);

        academia.buscarPeliculas("good");

        academia.oscarPorEdadOrdenAlfabetico(33);
        academia.oscarPorEdadOrdenAlfabetico(22);
    }

    private static void imprimir(Collection<Actriz> actrices) {
        for (Actriz a : actrices) {
            System.out.println(a);
        }
    }

    private static AcademiaHollywood leerPeliculasYActrices() {
        AcademiaHollywood academia = new AcademiaHollywood();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/in/oscar_age_female.csv"));
            br.readLine();
            String linea = null;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (partes != null && partes.length > 1) {
                    int anio = Integer.parseInt(partes[1].trim());
                    int edad = Integer.parseInt(partes[2].trim());
                    String nombre = partes[3].trim().replaceAll("\"", "");
                    String nombrePelicula = partes[4].trim().replaceAll("\"", "");

                    Actriz actriz = new Actriz(nombre);
                    Pelicula pelicula = new Pelicula(nombrePelicula, anio, edad);

                    academia.agregarPelicula(actriz, pelicula);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return academia;
    }
}
