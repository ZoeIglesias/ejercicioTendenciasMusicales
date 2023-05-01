package tendencias;

import catalogo.Cancion;
import helpers.Icono;
import lombok.Getter;
import lombok.Setter;


import catalogo.Cancion;
import helpers.Icono;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;


public class EnTendencia extends Popularidad{
    public static Integer cantHorasMaximasSinSerEscuchada = 24;
    @Setter
    private LocalDateTime fechaAComparar;


    public LocalDateTime getFechaAComparar() {
        return fechaAComparar == null? LocalDateTime.now() : this.fechaAComparar;
    }

    @Override
    public void reproducir(Cancion cancion){
        if(this.pasaronMasHorasDeLasPermitidas(cancion)){
            cancion.setPopularidad(new Normal(cancion.getCantReproducciones()));
        }
    }

    public boolean pasaronMasHorasDeLasPermitidas(Cancion cancion){
        Duration horasTranscurridas = Duration.between(cancion.getUltVezEscuchada(),this.fechaAComparar); //cant de horas que pasaron desde la ultima vez
        long horas = horasTranscurridas.toHours(); //lo pasa de formato hours a long
        return horas > this.cantHorasMaximasSinSerEscuchada;
    }

    @Override
    protected String icono(){
        return Icono.FIRE.texto();
    }

    @Override
    protected String leyenda(Cancion cancion){
        return armarLeyendaPara(cancion);
    }

    public static String armarLeyendaPara(Cancion cancion){
        return String.format(" %s - %s ( %s - %d)", cancion.getTitulo(), cancion.getArtista().getNombreArtista(), cancion.getAlbum().getNombreAlbum(),cancion.getAlbum().getAnioAlbum());
    }

}
