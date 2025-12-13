package pojo.Geofrafia;

public class Colonia {
    private Integer id;
    private String nombre;
    private String ciudad;
    private Municipio municipio;
    private String asentamiento;
    private int codigoPostal;

    public Colonia() {
    }

    public Colonia(Integer id, String nombre, String ciudad, Municipio municipio, String asentamiento, int codigoPostal) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.municipio = municipio;
        this.asentamiento = asentamiento;
        this.codigoPostal = codigoPostal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getAsentamiento() {
        return asentamiento;
    }

    public void setAsentamiento(String asentamiento) {
        this.asentamiento = asentamiento;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }   
            
}
