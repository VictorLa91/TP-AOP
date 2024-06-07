package ejercicioAOP.model;

import java.time.LocalDate;
import java.util.List;

public class Inscripcion {
    private List <Participante> listaParticipantes;
    LocalDate fechaInicionInscripcion;
    LocalDate fechaFinInscripcion;


    public void guardar (Participante participante){
        listaParticipantes.add(participante);
    }
}
