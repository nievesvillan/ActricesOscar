public class Pelicula implements Comparable<Pelicula> {

    private String nombre;

    private int anio;

    private int edadActriz;

    public Pelicula(String nombre, int anio, int edadActriz) {
        this.nombre = nombre;
        this.anio = anio;
        this.edadActriz = edadActriz;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEdadActriz() {
        return edadActriz;
    }

    public void setEdadActriz(int edadActriz) {
        this.edadActriz = edadActriz;
    }

    @Override
    public int compareTo(Pelicula o) {
        return o.getAnio() - this.getAnio();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\t- ").append(nombre);
        sb.append(", ").append(anio);

        return sb.toString();
    }
}
