package catalogo;

import lombok.Getter;
import lombok.Setter;
import tendencias.Normal;
import tendencias.Popularidad;

import java.time.LocalDateTime;

public class Cancion {
    @Getter @Setter private String titulo;
    @Getter @Setter private Album album;
    @Getter @Setter private Artista artista;
    @Getter @Setter private Integer cantReproducciones;
    @Getter @Setter private Integer cantLikes;
    @Getter @Setter private Integer cantDislikes;
    @Getter @Setter private Popularidad popularidad;
    @Getter @Setter private LocalDateTime ultVezEscuchada;

    public Cancion(){
        this.cantReproducciones = 0;
        this.cantLikes = 0;
        this.cantDislikes = 0;
        this.popularidad = new Normal(this.cantReproducciones);
    }

    public void reproducir(){
        this.cantReproducciones += 1;
        this.popularidad.reproducir(this);
        this.ultVezEscuchada = LocalDateTime.now();

    }

    public void recibirDislike(){
        this.cantDislikes ++;
        this.popularidad.recibirDislike();
    }

    public void recibirLike(){
        this.cantLikes ++;

    }

    public String detalleCompleto(){
        return this.popularidad.detalle(this);
    }

    public String serEscuchada(){
        this.reproducir();
        return this.detalleCompleto();
    }

}

