package beans;

/**
 *
 * @author Belmiro-Mungoi
 */
public class BeansEndereco extends BeansUsuario{

    private Integer idEnder;
    private Integer enderecoId;
    private String provincia;
    private String distrito;
    private String bairro;
    private String mobile;
    private String mobile2;

    public BeansEndereco() {
    }

    public BeansEndereco(Integer idEnder, Integer enderecoId, String provincia,
            String distrito, String bairro, String mobile, String mobile2) {
        this.idEnder = idEnder;
        this.enderecoId = enderecoId;
        this.provincia = provincia;
        this.distrito = distrito;
        this.bairro = bairro;
        this.mobile = mobile;
        this.mobile2 = mobile2;
    }

    public Integer getIdEnder() {
        return idEnder;
    }

    public void setIdEnder(Integer idEnder) {
        this.idEnder = idEnder;
    }

    public Integer getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Integer enderecoId) {
        this.enderecoId = enderecoId;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

}
