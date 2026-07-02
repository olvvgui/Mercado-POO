package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entity.PerfilUsuario;
import entity.Usuario;


public class UsuarioTest {

    @Test
    @DisplayName("Deve criar um usuário corretamente e validar a herança de Pessoa")
    public void testCriacaoUsuarioEHeranca() {
        // Ação: Criar o usuário
        Usuario usuario = new Usuario("João Silva", "joao@email.com", "senha123", PerfilUsuario.ATENDENTE_CAIXA);

        // Verificação: Os atributos herdados de Pessoa devem estar acessíveis e corretos
        assertNotNull(usuario.getId());
        assertEquals("João Silva", usuario.getNome());
  
        assertEquals("joao@email.com", usuario.getEmail()); 
        assertEquals(PerfilUsuario.ATENDENTE_CAIXA, usuario.getPerfil());
        assertTrue(usuario.isAtivo());
    }
}