package ejercicioAOP.persistencia;

import ejercicioAOP.model.Concurso;
import ejercicioAOP.model.LecturaEscritura;
import ejercicioAOP.model.Log;
import ejercicioAOP.model.Participante;

import java.io.IOException;
import java.lang.annotation.Target;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaEnBase implements LecturaEscritura {
    private final static String conexion = "jdbc:mysql://localhost:3306/concursos";
    private final static String usuario = "victor";
    private final static String clave = "nUojg8-u.uc8/a.1";

    @Override
    public List<Concurso> todosLosConcursos() throws IOException {
        List<Concurso> lista = new ArrayList<>();
        String consultaConcursos = "SELECT id, nombre, fechaInicioInscripcion, fechaFinInscripcion FROM concursos"; // Ejemplo de consulta SELECT

        try (Connection myConexion = DriverManager.getConnection(conexion, usuario, clave);
             PreparedStatement statementConsulta = myConexion.prepareStatement(consultaConcursos)) {

            ResultSet resultados = statementConsulta.executeQuery();
            while (resultados.next()) {
                int id = resultados.getInt(1);
                String nombre = resultados.getString(2);
                LocalDate fechaInicioInscripcion = resultados.getDate(3).toLocalDate();
                LocalDate fechaFinInscripcion = resultados.getDate(4).toLocalDate();
                Concurso concurso = new Concurso(id, nombre, fechaInicioInscripcion, fechaFinInscripcion);
                lista.add(concurso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void guardarInscripcion(Participante participante, Concurso concurso) throws IOException {
        var data = participante.apellido() + ", " + participante.nombre() + ", " +
                participante.telefono() + ", " + participante.email() + ", " + concurso.getId();
        String insertarPedido = "INSERT INTO inscripciones (datos) VALUES (?)";
        try (Connection myConexion = DriverManager.getConnection(conexion, usuario, clave);
             PreparedStatement statementInsertar = myConexion.prepareStatement(insertarPedido)) {

            statementInsertar.setString(1, data);
            statementInsertar.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
