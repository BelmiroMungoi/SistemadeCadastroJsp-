package beans;

/**
 *
 * @author Belmiro-Mungoi
 */
public class BeansUsuario {

    private String nomeCompleto;
    private String biUser;
    private String telefone;
    private String nomeUser;
    private String senha;
    private String imagem;
    private String imagemMini;
    private String curriculo;
    private String contentType;
    private String contentTypeCv;
    private String tempFoto;
    private Integer idUser;
    private boolean updateImage = true;
    private boolean updatePdf = true;

    public String getTempFoto() {
        tempFoto = "data:" + contentType + ";base64," + imagem;

        return tempFoto;
    }

    public boolean isUpdateImage() {
        return updateImage;
    }

    public void setUpdateImage(boolean updateImage) {
        this.updateImage = updateImage;
    }

    public boolean isUpdatePdf() {
        return updatePdf;
    }

    public void setUpdatePdf(boolean updatePdf) {
        this.updatePdf = updatePdf;
    }

    public String getImagemMini() {
        return imagemMini;
    }

    public void setImagemMini(String imagemMini) {
        this.imagemMini = imagemMini;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public String getContentTypeCv() {
        return contentTypeCv;
    }

    public void setContentTypeCv(String contentTypeCv) {
        this.contentTypeCv = contentTypeCv;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getBiUser() {
        return biUser;
    }

    public void setBiUser(String biUser) {
        this.biUser = biUser;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

}
