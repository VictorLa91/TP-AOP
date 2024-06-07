package ejercicioAOP.model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Concurso {
        private static final String NOMBRE_ARCHIVO = "concursos";
        private int id;
        private String nombre;
        private LocalDate fechaInicioInscripcion;
        private LocalDate fechaFinInscripcion;

        private Inscripcion listaDeInscriptos;

    public Concurso() {

    }

    public Concurso(int id, String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {
            this.id = id;
            this.nombre = nombre;
            this.fechaInicioInscripcion = fechaInicioInscripcion;
            this.fechaFinInscripcion = fechaFinInscripcion;
        }

        public static List<Concurso> leerConcursos(String archivo) throws IOException {
            List<Concurso> concursos = new ArrayList<>();
            String path = "C:\\Users\\victo\\IdeaProjects\\TP-4\\src\\main\\resources\\concursos.txt";
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()){
                System.out.println(scanner.nextLine());
            }


            return concursos;
        }

        public  void agregarConcurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String linea = String.format("%s, %s, %s, %s%n",
                        obtenerUltimoId() + 1,
                        nombre,
                        fechaInicio.format(formatter),
                        fechaFin.format(formatter));
                writer.write(linea);
                System.out.println("Concurso agregado con éxito.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private int obtenerUltimoId() throws IOException {
            int ultimoId = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",\\s*");
                    int id = Integer.parseInt(partes[0].trim());
                    ultimoId = Math.max(ultimoId, id);
                }
            }
            return ultimoId;
        }
        public String getNombre(){return this.nombre;}

        public void inscribir (Participante participante){
        this.listaDeInscriptos.guardar(participante);
        }

    public int getId() {
        return id;
    }
}

