import java.util.List;
import java.util.Scanner;

public class Menu {
   private static Controladora ctrl;

   private static void gerencia_input(Scanner scanner) {
      boolean running = true;
      while (running) {
         System.out.println("------ Opções ------");
         System.out.println("0 - Anunciar imóvel");
         System.out.println("1 - Pesquisar endereço");
         System.out.println("2 - Ver favoritos");
         System.out.println("3 - Ver conversas");
         System.out.println("4 - Sair");

         System.out.print("=> ");

         int opt = Integer.parseInt(scanner.nextLine());
         switch (opt) {
            case 0:
               String confirma = "", endereco, bairro, descricao;
               float preco;
               do{
                  System.out.print("Digite o preco: ");
                  preco = Float.parseFloat(scanner.nextLine());
                  System.out.print("Digite o endereco: ");
                  endereco = scanner.nextLine();
                  System.out.print("Digite o bairro: ");
                  bairro = scanner.nextLine();
                  System.out.print("Digite a descricao: ");
                  descricao = scanner.nextLine();

                  System.out.println("-> Endereço: " + endereco);
                  System.out.println("-> Bairro: " + bairro);
                  System.out.println("-> Descrição: " + descricao);
                  System.out.println("-> Preço: " + preco);
                  System.out.println("-> Confirmar (S/N)");
                  confirma = scanner.nextLine();

               } while (!confirma.equals("S"));
               ctrl.cria_imovel(preco, endereco, descricao, bairro);
               break;
            
            case 1:
               System.out.print("Digite o bairro desejado: ");
               bairro = scanner.nextLine();

               ctrl.pesquisa_endereco (bairro);

               System.out.println("0 - Escolher imóvel");
               System.out.println("1 - Filtrar imóveis");
               opt = Integer.parseInt(scanner.nextLine());
               
               if (opt == 1)
                  ctrl.filtrar_imoveis();
               ctrl.seleciona_imovel();
               
               break;

            case 2:
               
               break;

            case 3:
               
               break;

            case 4:
               running = false;
               break;

            default:
               break;
         }
      }
   }
   public static void main(String[] args) {
      ctrl = new Controladora();
      
      System.out.println("Bem-Vindo! Quem é você?");
      List<String> usuarios = ctrl.get_usuarios();
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

      ctrl.login(username);
      gerencia_input(scanner);      
      scanner.close();
   }
}