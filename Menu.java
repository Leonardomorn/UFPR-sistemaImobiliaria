import java.util.List;
import java.util.Scanner;

public class Menu {
   private static Controladora ctrl;

   private static void menu_acessibilidade(Scanner scanner) {
      boolean running = true;
      float contraste = 50, tamTexto = 50;
      boolean negrito = false, daltonico = false;
      String entrada;

      while (running) {
         System.out.println("0 - Modificar contraste");
         System.out.println("1 - Fonte em negrito");
         System.out.println("2 - Modo daltônico");
         System.out.println("3 - Modificar tamanho do texto");
         System.out.println("4 - Voltar");
         System.out.print("=> ");
         int opt = Integer.parseInt(scanner.nextLine());

         switch (opt) {
            case 0:
               System.out.print("Digite o valor do contraste (De 1 a 100): ");
               contraste = Float.parseFloat(scanner.nextLine());
               break;
            
            case 1:
               System.out.print("Deseja fonte em negrito (S/N): ");
               entrada = scanner.nextLine();
               if (entrada.equals("S")) negrito = true;
               if (entrada.equals("N")) negrito = false;
               break;
         
            case 2:
               System.out.print("Deseja modo daltônico (S/N): ");
               entrada = scanner.nextLine();
               if (entrada.equals("S")) daltonico = true;
               if (entrada.equals("N")) daltonico = false;
               break;

            case 3:
               System.out.print("Digite o valor do tamanho da fonte (1 a 100): ");
               tamTexto = Float.parseFloat(scanner.nextLine());
               break;

            case 4:
               running = false;
               break;
         
            default:
               break;
         }
      }
      ctrl.personalizar_vizualizacao(contraste, negrito, daltonico, tamTexto);
   }

   private static void gerencia_input(Scanner scanner) {
      boolean running = true;
      while (running) {
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
               System.out.println("2 - Retornar");
               System.out.print("=> ");
               opt = Integer.parseInt(scanner.nextLine());

               
               if (opt == 1)
                  ctrl.filtrar_imoveis();
               else if (opt == 0)
                  ctrl.seleciona_imovel();
               
               break;

            case 2:
               ctrl.mostra_favoritos();
               break;

            case 3:
               ctrl.print_conversas();
               break;

            case 4:
               menu_acessibilidade(scanner);
               break;

            case 5:
               running = false;
               break;

            case 9:
               System.out.println("------ Opções ------");
               System.out.println("0 - Anunciar imóvel");
               System.out.println("1 - Pesquisar endereço");
               System.out.println("2 - Ver favoritos");
               System.out.println("3 - Ver conversas");
               System.out.println("4 - Acessibilidade");
               System.out.println("5 - Sair");
               System.out.println("9 - Mostrar esse menu");
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

      // Enquanto não digitar um nome de usuário válido
      do {
         System.out.print("=> ");
         username = scanner.nextLine();
         c = usuarios.contains(username);
         if (!c) System.out.println("Usuário inválido");
      } while (!c);

      ctrl.login(username);
      System.out.println("Digite 9 para ver as opções");
      gerencia_input(scanner);
      scanner.close();
   }
}