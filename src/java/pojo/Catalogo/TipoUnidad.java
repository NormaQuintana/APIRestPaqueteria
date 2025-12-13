package pojo.Catalogo;

public class TipoUnidad {
    private Integer idTipoUnidad;
    private String tipoUnidad;

    public TipoUnidad() {
    }
    
    public TipoUnidad(Integer idTipoUnidad, String tipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
        this.tipoUnidad = tipoUnidad;
    }

    public Integer getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public void setIdTipoUnidad(Integer idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }
    
}
