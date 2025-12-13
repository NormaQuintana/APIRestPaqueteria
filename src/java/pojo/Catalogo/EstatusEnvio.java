package pojo.Catalogo;

public class EstatusEnvio {
    private Integer idEstatusEnvio;
    private String estatusEnvio;

    public EstatusEnvio() {
    }
    
    public EstatusEnvio(Integer idEstatusEnvio, String estatusEnvio) {
        this.idEstatusEnvio = idEstatusEnvio;
        this.estatusEnvio = estatusEnvio;
    }

    public Integer getIdEstatusEnvio() {
        return idEstatusEnvio;
    }

    public void setIdEstatusEnvio(Integer idEstatusEnvio) {
        this.idEstatusEnvio = idEstatusEnvio;
    }

    public String getEstatusEnvio() {
        return estatusEnvio;
    }

    public void setEstatusEnvio(String estatusEnvio) {
        this.estatusEnvio = estatusEnvio;
    }
       
    
}
