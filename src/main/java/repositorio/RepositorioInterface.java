package repositorio;

import java.util.List;

public interface RepositorioInterface<T> {
    void incluir(T entidade);
    void alterar(T entidade);
    void excluir(Integer id);
    T consultar(Integer id);
    List<T> listar();
}