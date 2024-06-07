package ejercicioAOP.model;

import java.io.IOException;
import java.util.List;
public interface  LecturaEscritura {
    public List <Concurso> todosLosConcursos()throws IOException;
    public void guardarInscripcion(Participante participante, Concurso concurso) throws IOException;


}
