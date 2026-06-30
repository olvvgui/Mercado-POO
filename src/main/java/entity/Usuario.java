package entity;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// Ok
public class Usuario extends Pessoa{
    private static int contadorId = 0;
    private String email;
    private String senhaCriptografada;
    private PerfilUsuario perfil;
    private boolean ativo;

    private static final String ALGORITMO = "AES";
    private static final byte[] CHAVE_SECRETA = "ChaveSecretaUsuarios".getBytes(StandardCharsets.UTF_8);

    // Ok
    public Usuario(String nome, String email, String senhaNormal, PerfilUsuario perfil) {
        super(++contadorId, nome);
        this.email = email;
        this.senhaCriptografada = criptografarSenha(senhaNormal);
        this.perfil = perfil;
        this.ativo = true;

    }

    // Ok
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

    // Ok
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


    // Ok
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

    // Ok
    public boolean isAtivo() {
        return ativo;
    }

    // Ok
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Ok
    public PerfilUsuario getPerfil() {
        return perfil;
    }

    // Ok
    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    // Ok
    public String getSenhaCriptografada() {
        return this.senhaCriptografada;
    }

    // Ok
    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    // Ok
    public String getEmail() {
        return this.email;
    }

    // Ok
    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setId(Integer id) {
        this.id = id;
    }
}