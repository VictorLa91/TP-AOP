package ejercicioAOP.persistencia;

import ejercicioAOP.model.Concurso;
import ejercicioAOP.model.LecturaEscritura;
import ejercicioAOP.model.Log;
import ejercicioAOP.model.Participante;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PersistenciaLocal implements LecturaEscritura {
    @Override
    @Log
    public List <Concurso> todosLosConcursos() throws IOException {
        List<Concurso> concursos = new ArrayList<>();
        String path = "C:\\Users\\victo\\IdeaProjects\\TP-4\\src\\main\\resources\\concursos.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNext()) {
            String fila = scanner.nextLine();
            StringTokenizer atributo= new StringTokenizer(fila,",");
            while (atributo.hasMoreElements()){
                int id = Integer.parseInt(atributo.nextToken().trim());
                String nombre = atributo.nextToken();
                LocalDate fechaInicioInscripcion = LocalDate.parse(atributo.nextToken().trim());
                LocalDate fechaFinInscriocion = LocalDate.parse(atributo.nextToken().trim());
                Concurso concurso = new Concurso(id,nombre,fechaInicioInscripcion,fechaFinInscriocion );
                concursos.add(concurso);
            }
        }
        return concursos;
    }

    @Override
    @Log
    public void guardarInscripcion(Participante participante, Concurso concurso) throws IOException {
        var datos= participante.apellido()+", "+participante.nombre()+", "
                + participante.telefono()+", "+ participante.email()+ ", " + concurso.getId();
                    FileWriter writer = new FileWriter("inscripciones.txt", true); // El true indica que se agregará al final del archivo
            writer.write(datos + "\n");
            writer.close();
    }

    }

