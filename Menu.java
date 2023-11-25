import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Menu {
   public static void main(String[] args) {
      Controladora ctrl = new Controladora();
      
      System.out.println("Bem-Vindo! Quem é você?");
      List<String> usuarios = ctrl.getUsuarios();
      for (String usuario : usuarios) {
         System.out.println("- " + usuario);
      }

      boolean c;
      Scanner scanner = new Scanner(System.in); 
      do {
         System.out.print("=> ");
         String userName = scanner.nextLine();
         System.out.println("Usuário é: " + userName);
         c = usuarios.contains(userName);
         if (!c) System.out.println("Usuário inválido");
      } while (!c);
      scanner.close();
   }
}
