package tendencias;

import catalogo.Cancion;
public abstract class Popularidad {
    //son metodos abstratos que serán implementados por las subclases
    protected abstract String icono();
    protected abstract String leyenda(Cancion cancion);
    public abstract void reproducir(Cancion cancion);

    public  void recibirDislike() { }


    public String detalle(Cancion cancion){
        String titulo = this.icono();
        titulo += this.leyenda(cancion);
        return titulo;

    }
}
