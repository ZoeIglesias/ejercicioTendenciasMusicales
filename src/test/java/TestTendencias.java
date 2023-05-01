import catalogo.Album;
import catalogo.Artista;
import catalogo.Cancion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tendencias.EnAuge;
import tendencias.EnTendencia;
import tendencias.Normal;

public class TestTendencias {
    private Cancion cancion;
    private Artista artista;
    private Album album;

    @BeforeEach
    public void init(){
        this.artista = new Artista();
        this.artista.setNombreArtista("Coldplay");

        this.album = new Album();
        this.album.setNombreAlbum("A Rush of Blood to the head");
        this.album.setAnioAlbum(2002);

        this.cancion = new Cancion();
        this.cancion.setTitulo("The Scientist");
        this.cancion.setArtista(this.artista);
        this.cancion.setAlbum(this.album);


        Normal.cantMaxReproduccionesTendenciaNormal = 2;
        EnAuge.cantReproduccionesMaximasEnAuge = 3;
        EnAuge.cantLikesMaximos = 3;
        EnAuge.cantDislikesMaximos = 5;
        EnTendencia.cantHorasMaximasSinSerEscuchada = 24;

    }

    @Test
    @DisplayName("The Scientist recien se lanza y tiene popularidad normal")
    public void laTendenciaEsNormal(){
        cancion.reproducir();
        String detalle = cancion.detalleCompleto();

        Assertions.assertTrue(detalle.contains(Normal.armarLeyendaPara(this.cancion)));
        Assertions.assertEquals(1,cancion.getCantReproducciones());
    }

    @Test
    @DisplayName("The Scientist esta en auge por superar el minimo de reproducciones esperadas")
    public void laTendenciaEsEnAuge(){
        cancion.reproducir();
        cancion.reproducir();
        cancion.reproducir();
        String detalle = cancion.detalleCompleto();

        Assertions.assertTrue(detalle.contains(EnAuge.armarLeyendaPara(this.cancion)));
        Assertions.assertEquals(3,cancion.getCantReproducciones());

    }

    @Test
    @DisplayName("The Scientist baja del auge por tener muchos dislikes")
    public void bajaLaTendenciaANormalPorDislikes(){
        cancion.reproducir();
        cancion.reproducir();
        cancion.reproducir();

        Assertions.assertEquals(EnAuge.class.getName(),cancion.getPopularidad().getClass().getName());

        cancion.recibirDislike();
        cancion.recibirDislike();
        cancion.recibirDislike();
        cancion.recibirDislike();
        cancion.recibirDislike();
        cancion.recibirDislike();

        cancion.reproducir();

        Assertions.assertEquals(Normal.class.getName(),cancion.getPopularidad().getClass().getName());
        Assertions.assertEquals(6,cancion.getCantDislikes());
    }


}
