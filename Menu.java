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
      String username;
      Scanner scanner = new Scanner(System.in); 
      do {
         System.out.print("=> ");
         username = scanner.nextLine();
         c = usuarios.contains(username);
         if (!c) System.out.println("Usuário inválido");
      } while (!c);
      scanner.close();

      ctrl.Login(username);

      System.out.println("------ Opções ------");
      System.out.println("0 - Anunciar imóvel");
      System.out.println("1 - Pesquisar endereço");
      System.out.println("2 - Ver favoritos");
      System.out.println("3 - Ver conversas");
   }
}