package beans;

/**
 *
 * @author Belmiro-Mungoi
 */
public class BeansProduto extends BeansCategoria{

    private Long idProd;
    private String nomeProd;
    private Integer quantProd;
    private Float valorProd;
    private Integer categoriaId;

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public Integer getQuantProd() {
        return quantProd;
    }

    public void setQuantProd(Integer quantProd) {
        this.quantProd = quantProd;
    }

    public Float getValorProd() {
        return valorProd;
    }

    public void setValorProd(Float valorProd) {
        this.valorProd = valorProd;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

}
