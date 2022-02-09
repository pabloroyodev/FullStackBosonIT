import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  public static void main(String[] args){
    Scanner s = new Scanner(System.in); //Ej:   C:\Users\pablo.royo\IdeaProjects\Test\src\file.txt
    System.out.print("Introduzca la ruta del archivo: ");
    File file = new File(s.nextLine());

    try (Scanner ignored = new Scanner(file)) {
      Scanner scanner = new Scanner(file);
      List<Persona> personas = new ArrayList();
      while (scanner.hasNext()) {
        String[] txt = scanner.nextLine().split(":");
        personas.add(new Persona(txt));
        Arrays.fill(txt, null);
      }

      personas.stream()
              .filter(persona -> persona.getPoblacion().equals(""))
              .forEach(
                      (p) -> p.setPoblacion("Desconocida"));

      final AtomicInteger count = new AtomicInteger();

      personas.stream()
              .filter(persona -> Integer.parseInt(persona.getEdad()) < 25)
              .forEach(
                      (p) -> {
                        if (p.getEdad().equals("-1")) {
                          System.out.println("Linea " + count.getAndIncrement() + ". " + p.toStringEdad());
                        } else {
                          System.out.println("Linea " + count.getAndIncrement() + ". " + p.toString());
                        }
                      });
    } catch (FileNotFoundException e) {
      System.out.println("Archivo no encontrado!: " + s);
    }
  }
}