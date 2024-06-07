package ejercicioAOP.model;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


@Aspect
public class AspectRegistracion {

    @Before("execution(@Log * *(..))")
    public void registrarLog(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName(); // Nombre del método
        Object[] args = joinPoint.getArgs(); // Argumentos del método

        // Formatear los argumentos
        String argsString = "sin parametros";
        if (args.length > 0) {
            argsString = Arrays.toString(args);
            argsString = argsString.substring(1, argsString.length() - 1); // Eliminar corchetes
            argsString = argsString.replace(", ", "|"); // Reemplazar comas por pipes
        }

        // Formatear la fecha y hora de invocación
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateTime = sdf.format(new Date());

        // Formatear la entrada para el archivo de log
        String logEntry = String.format("\"%s\", \"%s\", \"%s\"\n", methodName, argsString, dateTime);

        // Escribir en el archivo de log
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir en consola (opcional)
        System.out.println("Registro de log: " + logEntry);
    }
}
