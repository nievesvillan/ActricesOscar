import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Actriz implements Comparable<Actriz> {

    private String nombre;

    private Set<Pelicula> oscars;

    public Actriz(String nombre) {
        this.nombre = nombre;
        this.oscars = new TreeSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Pelicula> getOscars() {
        return oscars;
    }

    public void setOscars(Set<Pelicula> oscars) {
        this.oscars = oscars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actriz actriz = (Actriz) o;

        return Objects.equals(nombre, actriz.nombre);
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    public void agregarPelicula(Pelicula pelicula) {
        oscars.add(pelicula);
    }

    @Override
    public int compareTo(Actriz o) {
        return o.oscars.size() - this.oscars.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(nombre).append('\t');
        sb.append(oscars.size()).append(" Oscars");
        sb.append('\n');

        for(Pelicula p : this.getOscars()){
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}
