package tendencias;

import catalogo.Cancion;
import helpers.Icono;
import lombok.Getter;
import lombok.Setter;

public class EnAuge extends Popularidad{
    @Getter @Setter private Integer cantReproduccionesIniciales;
    @Getter @Setter public static Integer cantReproduccionesMaximasEnAuge = 50000;
    @Getter @Setter public static Integer cantLikesMaximos = 20000;
    @Getter @Setter public static Integer cantDislikesMaximos = 5000;
    @Getter @Setter private Integer cantDislikes;
    @Getter @Setter private Integer cantLikes;

    public EnAuge(Integer cantReproducciones){
        this.cantReproduccionesIniciales = cantReproducciones;
        this.cantDislikes = 0;
        this.cantLikes = 0;
    }

    @Override
    public void reproducir(Cancion cancion){
        if(this.superaReproduccionesRequeridas(cancion) && this.superaCantDeLikesRequeridos(cancion)){
            cancion.setPopularidad(new EnTendencia());
        }else if(this.superaCantDeDislikesMaximos(cancion)){
            cancion.setPopularidad(new Normal(cancion.getCantReproducciones()));
        }
    }

    public boolean superaReproduccionesRequeridas(Cancion cancion) {
        return this.cantidadDeReproduccionesEnEstaPopularidad(cancion) > this.cantReproduccionesMaximasEnAuge;
    }

    public int cantidadDeReproduccionesEnEstaPopularidad(Cancion cancion){
        return cancion.getCantReproducciones() - this.cantReproduccionesIniciales;

    }

    public boolean superaCantDeLikesRequeridos(Cancion cancion){
        return cancion.getCantLikes() > this.likesRequeridosEnEstaPopularidad();
    }

    public boolean superaCantDeDislikesMaximos(Cancion cancion){
        return this.cantDislikes > this.cantDislikesMaximos;
    }

    public int likesRequeridosEnEstaPopularidad(){
        return this.cantLikesMaximos;
    }

    public int dislikesMaximosEnEstaPopularidad(){
        return this.cantDislikesMaximos;
    }
    public int reproduccionesRequeridasEnEstaPopularidad(){
        return 50000;
    }

    @Override
    public void recibirDislike(){
        this.cantDislikes ++;
    }



    @Override
    protected String icono(){
        return Icono.ROCKET.texto();
    }

    @Override
    protected String leyenda(Cancion cancion){
        return armarLeyendaPara(cancion);
    }

    public static String armarLeyendaPara(Cancion cancion){
        return String.format(" %s - %s ( %s - %d)", cancion.getArtista().getNombreArtista(), cancion.getTitulo(), cancion.getAlbum().getNombreAlbum(), cancion.getAlbum().getAnioAlbum());
    }
}
