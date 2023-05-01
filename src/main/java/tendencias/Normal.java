package tendencias;

import catalogo.Cancion;
import helpers.Icono;
import lombok.Getter;
import lombok.Setter;

public class Normal extends Popularidad{
    public static Integer cantMaxReproduccionesTendenciaNormal = 1000;
    @Getter @Setter private Integer cantReproduccionesIniciales;


    public Normal(Integer cantReproducciones){
        this.cantReproduccionesIniciales = cantReproducciones;
    }

    @Override
    public void reproducir(Cancion cancion){
        if(this.superaReproduccionesRequeridas(cancion)){
            cancion.setPopularidad(new EnAuge(cancion.getCantReproducciones()));
        }
    }

    public boolean superaReproduccionesRequeridas(Cancion cancion) {
        return this.cantidadDeReproduccionesEnEstaPopularidad(cancion) > this.reproduccionesRequeridasEnEstaPopularidad();
    }

    public int cantidadDeReproduccionesEnEstaPopularidad(Cancion cancion){
        return cancion.getCantReproducciones() - this.cantReproduccionesIniciales;

    }
    public int reproduccionesRequeridasEnEstaPopularidad(){
        return cantMaxReproduccionesTendenciaNormal;
    }

    @Override
    protected String icono(){
        return Icono.MUSICAL_NOTE.texto();
    }

    @Override
    protected String leyenda(Cancion cancion){
        return armarLeyendaPara(cancion);
    }
    public static String armarLeyendaPara(Cancion cancion){
        return String.format( "%s - %s - %s",cancion.getArtista().getNombreArtista(),cancion.getAlbum().getNombreAlbum(),cancion.getTitulo());
    }

}
