import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Usuario {
    private static int contadorId = 0;
    private Integer id;

    private String nome;
    private String email;
    private String senhaCriptografada;
    private PerfilUsuario perfil;
    private boolean ativo;

    private static final String ALGORITMO = "AES";
    private static final byte[] CHAVE_SECRETA = "ChaveSecretaUsuarios".getBytes(StandardCharsets.UTF_8);

    public Usuario(String nome, String email, String senhaNormal, PerfilUsuario perfil) {
        ativo = true;
        contadorId += 1;
        id = contadorId;
        this.nome = nome;
        this.email = email;
        this.senhaCriptografada = criptografarSenha(senhaNormal);
        this.perfil = perfil;
    }

    private String criptografarSenha(String senha) {
        try {
            SecretKeySpec chaveSecreta = new SecretKeySpec(CHAVE_SECRETA, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, chaveSecreta);

            byte[] bytesCriptografados = cipher.doFinal(senha.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(bytesCriptografados);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar a senha", e);
        }
    }

    private String descriptografarSenha(String senhaCriptografada) {
        try {
            SecretKeySpec chaveSecreta = new SecretKeySpec(CHAVE_SECRETA, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, chaveSecreta);

            byte[] bytesDecodificados = Base64.getDecoder().decode(senhaCriptografada);
            byte[] bytesDescriptografados = cipher.doFinal(bytesDecodificados);

            return new String(bytesDescriptografados, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar a senha", e);
        }
    }

    public boolean autenticar_usuario(String senha) {
        String tentativaCriptografada = criptografarSenha(senha);
        return tentativaCriptografada.equals(this.senhaCriptografada);
    }

    // Ok
    public void AtualizarDados(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Ok
    public void desativar() {
        this.ativo = false;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}