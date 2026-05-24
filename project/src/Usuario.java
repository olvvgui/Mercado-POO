public class Usuario {
    private static int contadorId = 0;
    private int id;

    private String nome;
    private String email;
    private String senhaCriptografada;
    private PerfilUsuario perfil;
    private boolean ativo;

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
        // TODO: criptografar senha
        /*
         * usar a  API padrão de criptografia do Java (JCA) com o
         * algoritmo AES (Advanced Encryption Standard).
         */
        return senha;
    }

    private String descriptografarSenha(String senha) {
        return "not implemented";
    }

    public boolean autenticar_usuario(String senha) {
        /**
         * TODO: CRIPTOGRAFAR SENHA, COMPARAR COM A SENHA CRIPTOGRAFADA E RETORNAR TRUE OR FALSE
         */
        return false;
    }

    public void AtualizarDados(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public void desativar() {
        this.ativo = false;
    }
}
